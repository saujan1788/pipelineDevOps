# Use the official Maven image to create a build artifact
FROM maven:3.8.5-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /home/app

# Copy the source code to the container
COPY src ./src
COPY pom.xml .git

# Package the application without running tests
RUN mvn clean package -DskipTests

# Use OpenJDK for the runtime image
FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /usr/local/runme

# Copy the JAR from the build stage to the current directory
COPY --from=build /home/app/target/*.jar ./app.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java","-jar","app.jar"]
