# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy Maven wrapper and config
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Make mvnw executable and download dependencies
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copy the actual source code
COPY src src

# Build the Spring Boot jar
RUN ./mvnw package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy only the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]


#  FROM openjdk:17
#
#  ARG JAR_File=target/*.jar
#
#  COPY ${JAR_File} app.jar
#
#  ENTRYPOINT ["java","-jar","/app.jar"]
#
#  EXPOSE 8080
