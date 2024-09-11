# Этап 1: Сборка проекта с использованием Maven
FROM eclipse-temurin:17-alpine AS builder

# Установка необходимых зависимостей
RUN apk add --no-cache maven

# Установка рабочей директории
WORKDIR /app

# Определение аргументов сборки
ARG DB_URL
ARG DB_USER
ARG DB_PASSWORD
ARG DB_NAME
ARG DB_PORT
ARG KAFKA_BOOTSTRAP_SERVER

ENV DB_NAME=notificationservicedb
ENV DB_PORT=5432
ENV DB_URL=jdbc:postgresql://collaborative-project-postgres-db:5432/notificationservicedb
ENV DB_USER=notificationservice
ENV DB_PASSWORD=12345
ENV KAFKA_BOOTSTRAP_SERVER=kafka:9092

# Копирование файлов проекта
COPY pom.xml ./
RUN mvn dependency:go-offline -B
COPY src ./src

# Сборка проекта с использованием Maven и передача аргументов сборки
RUN mvn clean package -P skip-kafka-tests
#    -DskipTests

# Этап 2: Создание финального образа
FROM eclipse-temurin:17-alpine

# Установка рабочей директории
WORKDIR /apps

# Копирование JAR файла из предыдущего этапа
COPY --from=builder /app/target/*.jar ./myapp.jar
COPY --from=builder /app/target ./target

RUN #chmod +x ./myapp.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "myapp.jar"]
