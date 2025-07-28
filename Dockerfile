# Start from a Maven image to build the app
FROM maven:3.9.11-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
# Utilise une image Java légère
FROM eclipse-temurin:21-jre-alpine
# Mainteneur de l'image
LABEL maintainer="Spring Developer mm@gmail.com"
# Installe curl pour le healthcheck
RUN apk add --no-cache curl
WORKDIR /app
# Copie du JAR généré
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]