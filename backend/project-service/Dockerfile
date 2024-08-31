# Этап 1: Сборка проекта с использованием Maven
FROM eclipse-temurin:17-alpine AS builder

# Установка необходимых зависимостей
RUN apk add --no-cache maven protobuf protobuf-dev libc6-compat

# Установка рабочей директории
WORKDIR /app

# Определение аргументов сборки
ARG DB_URL
ARG DB_USER
ARG DB_PASSWORD
ARG DB_NAME
ARG DB_PORT
ARG KAFKA_BOOTSTRAP_SERVER
ARG AUTH_SERVICE_URL
ARG GP_PROJECTSERVICE_DB_URL
ARG GP_PROJECTSERVICE_DB_USERNAME
ARG GP_PROJECTSERVICE_DB_PASSWORD

ENV DB_NAME=projectservicedb
ENV DB_PORT=5432
ENV DB_URL=jdbc:postgresql://localhost:5432/projectservicedb
#ENV DB_URL=jdbc:postgresql://collaborative-project-postgres-db:5432/projectservicedb
ENV DB_USER=projectservice
ENV DB_PASSWORD=12345
ENV KAFKA_BOOTSTRAP_SERVER=kafka:9092
ENV AUTH_SERVICE_URL=auth-service
ENV GP_PROJECTSERVICE_DB_URL=jdbc:postgresql://localhost:5432/projectservicedb
ENV GP_PROJECTSERVICE_DB_USERNAME=projectservice
ENV GP_PROJECTSERVICE_DB_PASSWORD=12345
ENV GP_PROJECTSERVICE_KEYSTORE_PASSWORD=1PwCgNAb

# Копирование файлов проекта
COPY pom.xml ./
RUN mvn dependency:go-offline -B
COPY src ./src
COPY certificate.p12 ./

# Сборка проекта с использованием Maven и передача аргументов сборки
RUN mvn clean package -Pskip-kafka-tests
#    -DskipTests

# Этап 2: Создание финального образа
FROM eclipse-temurin:17-alpine

# Установка рабочей директории
WORKDIR /apps

# Копирование JAR файла из предыдущего этапа
COPY --from=builder /app/target/*.jar ./myapp.jar
COPY --from=builder /app/target ./target
COPY --from=builder /app/certificate.p12 ./


RUN #chmod +x ./myapp.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "myapp.jar"]
