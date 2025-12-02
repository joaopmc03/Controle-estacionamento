FROM openjdk:21
COPY target/garagem.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
