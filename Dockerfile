FROM openjdk:17-jdk-alpine

COPY build/libs/Tutorial-0.0.1-SNAPSHOT.jar app.jar


WORKDIR /app

COPY build/libs/Tutorial-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application-docker.properties /app/


ENV SPRING_PROFILES_ACTIVE=docker

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]