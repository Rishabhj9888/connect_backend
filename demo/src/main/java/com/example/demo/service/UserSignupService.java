package com.example.demo.service;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.SignupRequest;
import com.example.demo.Repository.UserSignupRepository;
import com.example.demo.Security.JwtUtil;
import com.example.demo.model.Role;
import com.example.demo.model.UserSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserSignupService {

    @Autowired
    private UserSignupRepository repository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    //    register user
    public ResponseEntity<?> registerUser(SignupRequest request) {
        Optional<UserSignup> existing = repository.findByEmail(request.getEmail());
        Map<String, Object> response = new HashMap<>();

        if (existing.isPresent()) {
            response.put("message", "User with this email already exists!");
            return ResponseEntity.badRequest().body(response);
        }

        UserSignup user = new UserSignup();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setCollegeName(request.getCollegeName());
        user.setSpecialization(request.getSpecialization());

        // Default role always STUDENT
        user.setRole(Role.STUDENT);

        repository.save(user);

        response.put("message", "User registered successfully!");
        return ResponseEntity.ok(response);
    }

    //login user
    public ResponseEntity<?> loginUser(LoginRequest request) {
        Optional<UserSignup> userOpt = repository.findByEmail(request.getEmail());
        Map<String, Object> response = new HashMap<>();

        if (userOpt.isPresent()) {
            UserSignup user = userOpt.get();
            if (encoder.matches(request.getPassword(), user.getPassword())) {
                // 🔑 Here generate JWT (pseudo-code, we'll implement full JWT util separately)
                String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

                response.put("message", "Login successful!");
                response.put("token", token);
                response.put("role", user.getRole().name());

                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Invalid password!");
                return ResponseEntity.badRequest().body(response);
            }
        }

        response.put("message", "User not found!");
        return ResponseEntity.badRequest().body(response);
    }
}
