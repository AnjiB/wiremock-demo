FROM maven:3.8.4-openjdk-17 as build

COPY src /app/src

COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests

FROM openjdk:17-alpine

COPY --from=build app/target/wiremock-demo-*.jar /api-service/wiremock-demo.jar

WORKDIR /api-service

ENTRYPOINT ["java","-jar","wiremock-demo.jar"]