// This is the complete, corrected file for your backend project.
// filepath: src/main/java/com/example/demo/Security/SecurityConfig.java
package com.example.demo.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ✅ 1. ADD CORS CONFIGURATION HERE
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. Disable CSRF (your existing setting)
                .csrf(AbstractHttpConfigurer::disable)

                // 3. Define authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers("/api/alumni/search/**").permitAll()

                        // Admin-only endpoints (Corrected path for 'add')
                        .requestMatchers("/api/alumni/add").hasRole("ADMIN")
                        .requestMatchers("/api/alumni/all").hasRole("ADMIN")

                        // All other requests must be authenticated
                        .anyRequest().authenticated()
                )

                // 4. Add your JWT filter (your existing setting)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow requests from your React frontend
        configuration.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "https://connect-frontend-rho.vercel.app" ));
        // Allow all standard methods
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Allow specific headers, including Authorization for your JWT
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        // Allow credentials
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Apply this CORS configuration to all API endpoints
        source.registerCorsConfiguration("/api/**", configuration);

        return source;
    }
}