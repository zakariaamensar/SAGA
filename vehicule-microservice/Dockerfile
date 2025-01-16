# Use a base image
FROM openjdk:19-jdk

  # Set the working directory
WORKDIR /app

  # Copy the application JAR
COPY out/artifacts/vehicule_microservice_jar/vehicule-microservice.jar app.jar

  # Expose the port your app runs on
EXPOSE 8080

  # Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]



