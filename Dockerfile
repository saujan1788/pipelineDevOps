# Use the official Maven image to create a build artifact
FROM maven:3.8.5-openjdk-17-slim AS build

# Copy the source code to the container
COPY src /home/app/src
COPY pom.xml /home/appdocker

# Package the application
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Use OpenJDK for the runtime image
FROM openjdk:17-oracle

# Copy the JAR from the build stage to the /usr/local/runme directory
COPY --from=build /home/app/target/*.jar /usr/local/runme/app.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java","-jar","/usr/local/runme/app.jar"]

