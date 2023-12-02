# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Create the uploads directory inside the container
RUN mkdir -p /app/uploads

# Copy the application JAR file into the container at /app
COPY target/app.jar /app/app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]
