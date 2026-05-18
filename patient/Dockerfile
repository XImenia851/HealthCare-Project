FROM openjdk:17.0.1-jdk-slim AS build
LABEL authors="XImenia851"
COPY target/patient-0.0.1-SNAPSHOT.jar patient-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/patient-0.0.1-SNAPSHOT.jar"]