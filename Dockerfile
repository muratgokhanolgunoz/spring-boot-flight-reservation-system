FROM openjdk:11-jdk-slim

EXPOSE 8080

ARG JAR_FILE=target/flight-reservation-system-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
