# TEMPORAL BUILD IMAGE
FROM openjdk:11-jdk-slim AS build-env

COPY . /app
RUN cd /app && ./mvnw clean package

FROM openjdk:11-jdk-slim
COPY --from=build-env /app/target/producer-0.1.jar /producer.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "producer.jar"]