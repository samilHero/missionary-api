FROM arm64v8/amazoncorretto:21-alpine-jdk
LABEL authors="eden"

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 5000

ENTRYPOINT ["java","-jar","/app.jar"]