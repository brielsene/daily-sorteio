# Etapa de Build - Usa a imagem oficial do Maven
FROM maven:3.9.6-eclipse-temurin-21 as build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Compila o projeto e gera o .jar
RUN mvn clean package -DskipTests

# Etapa de Execução - Usa uma imagem menor do OpenJDK 21
FROM openjdk:21-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Expõe a porta da aplicação
EXPOSE 8080

# Copia o JAR gerado da etapa de build para a imagem final
COPY --from=build /app/target/sorteio-daily-0.0.1-SNAPSHOT.jar sorteio-daily.jar

# Copia os demais arquivos para garantir acesso ao JSON
COPY src/main/resources /app/resources

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "sorteio-daily.jar"]
