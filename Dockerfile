FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/app.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]
