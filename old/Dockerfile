# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Create the uploads directory inside the container
RUN mkdir -p /app/uploads

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]
