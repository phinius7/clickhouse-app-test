FROM openjdk:11
EXPOSE 8080
COPY target/event-generator-1.0.jar app.jar
ENTRYPOINT ["java","-jar","-noverify","app.jar","--server.port=8080"]