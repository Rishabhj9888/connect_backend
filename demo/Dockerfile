# Stage 1: Build the JAR
FROM eclipse-temurin:17-jdk AS build

# Set working directory inside container
WORKDIR /app

# Copy Maven descriptor + download dependencies first (to cache layers)
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

# Copy the actual source code
COPY src src

# Package the Spring Boot app (skip tests for faster build)
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the JAR with lightweight JRE
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the packaged JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080 (Spring Boot default)
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
