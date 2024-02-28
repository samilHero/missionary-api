FROM amazoncorretto:21-alpine-jdk
LABEL authors="eden"

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]