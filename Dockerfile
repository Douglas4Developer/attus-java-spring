# Etapa de build
FROM maven:3.8.5-openjdk-21-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa de runtime
FROM openjdk:21-jdk-slim
COPY --from=build /app/target/attus.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
