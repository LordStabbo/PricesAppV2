FROM maven:3.8.3-openjdk-17 AS builder

WORKDIR /home/app

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn clean package

FROM openjdk:17-alpine

COPY --from=builder home/app/target/hexagonal-architecture-1.0.0-SNAPSHOT.jar /usr/local/lib/app.jar
EXPOSE 7373
ENTRYPOINT [ "java", "-jar", "usr/local/lib/app.jar" ]
