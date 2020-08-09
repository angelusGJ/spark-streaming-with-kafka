# TEMPORAL BUILD IMAGE
FROM openjdk:8-jdk-slim AS build-env

COPY . /app
RUN cd /app && ./mvnw clean package

FROM bde2020/spark-worker:2.4.5-hadoop2.7

COPY --from=build-env /app/target/consumer-0.1-jar-with-dependencies.jar /consumer-jar-with-dependencies.jar

#ENV SPARK_APPLICATION_JAR_NAME consumer-0.1-jar-with-dependencies
#ENV SPARK_APPLICATION_MAIN_CLASS com.poc.twitter.Application
#ENV SPARK_APPLICATION_ARGS ""
