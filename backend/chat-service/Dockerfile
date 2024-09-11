# Этап 1: Сборка проекта с использованием Maven
FROM eclipse-temurin:17-alpine AS builder

# Установка необходимых зависимостей
RUN apk add --no-cache maven protobuf protobuf-dev libc6-compat

# Установка рабочей директории
WORKDIR /app

# Определение аргументов сборки
ARG GP_CHATSERVICE_DB_URL
ARG GP_CHATSERVICE_DB_USERNAME
ARG GP_CHATSERVICE_DB_PASSWORD

ENV GP_CHATSERVICE_DB_URL=jdbc:postgresql://localhost:5432/chatservicedb
ENV GP_CHATSERVICE_DB_USERNAME=chatservice
ENV GP_CHATSERVICE_DB_PASSWORD=12345

# Копирование файлов проекта
COPY pom.xml ./
COPY src ./src
RUN mvn dependency:go-offline -B

# Сборка проекта с использованием Maven и передача аргументов сборки
RUN mvn clean package 
# -P skip-kafka-tests
#    -DskipTests

# Этап 2: Создание финального образа
FROM eclipse-temurin:17-alpine

# Установка рабочей директории
WORKDIR /apps

# Копирование JAR файла из предыдущего этапа
COPY --from=builder /app/target/*.jar ./myapp.jar

RUN #chmod +x ./myapp.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "myapp.jar"]
