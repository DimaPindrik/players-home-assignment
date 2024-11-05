# Use a base image that has Java installed
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/yourapp-0.0.1-SNAPSHOT.jar app.jar

# Copy the CSV file to the same directory
COPY src/main/resources/csv/player.csv csv/player.csv

# Expose the port the application runs on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]