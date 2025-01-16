# Use a base image
FROM openjdk

  # Set the working directory
WORKDIR /app

  # Copy the application JAR
COPY out/artifacts/client_microservice_jar/client-microservice.jar app.jar

  # Expose the port your app runs on
EXPOSE 8080

  # Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]



