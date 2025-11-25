# Step 1: Use lightweight JDK base image
FROM eclipse-temurin:17-jdk


# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy jar from target folder into container
COPY target/*.jar app.jar

# Step 4: Expose the port Spring Boot runs on
EXPOSE 8080

# Step 5: Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

