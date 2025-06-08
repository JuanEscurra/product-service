FROM eclipse-temurin:11-jre-alpine

WORKDIR /app

COPY target/product-service.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]