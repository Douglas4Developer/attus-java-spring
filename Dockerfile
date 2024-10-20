# Use a imagem base do JDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho no container
WORKDIR /app

# Copia todo o código fonte para o diretório de trabalho do container
COPY . .

# Executa o Maven para construir o projeto e gerar o JAR
RUN ./mvnw clean package

# Copia o JAR gerado para o diretório correto no container
COPY target/attus-0.0.1-SNAPSHOT.jar /app/attus.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando para rodar o JAR da aplicação
ENTRYPOINT ["java", "-jar", "/app/attus.jar"]
