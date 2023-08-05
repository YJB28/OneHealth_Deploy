# Use a lightweight base image for Java1
# Dockerfile
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app
RUN pwd
COPY . .
RUN ls 
# RUN ls ServiceRegistry/target
# Copy the Spring Boot application JAR into the container
COPY target/ConfigServer-0.0.1-SNAPSHOT.jar app.jar
# COPY ConfigServer-0.0.1-SNAPSHOT.jar app.jar


# Expose the port your Spring Boot application is running on (change the port accordingly)
EXPOSE 8761

# Run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]