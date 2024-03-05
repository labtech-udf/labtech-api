FROM maven:3.9.3 AS builder

WORKDIR /app

COPY pom.xml ./
COPY src src/
COPY resources resources/

RUN mvn package

FROM openjdk:21

COPY builder/target/*.jar /opt/labtech.jar

WORKDIR /opt

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "labtech.jar"]
