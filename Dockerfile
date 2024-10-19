# Use uma imagem base do JDK 17 ou versão compatível com sua aplicação
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo JAR gerado pelo Maven/Gradle para dentro do container
COPY target/attus-0.0.1-SNAPSHOT.jar /app/attus.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando para executar o JAR da aplicação
ENTRYPOINT ["java", "-jar", "/app/attus.jar"]
