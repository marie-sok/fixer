FROM openjdk:21

WORKDIR /app

COPY target/fixer-app-1.0.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]