# Этап 1: Сборка проекта с использованием Maven
FROM eclipse-temurin:17-alpine AS builder

# Установка необходимых зависимостей
RUN apk add --no-cache maven

# Установка рабочей директории
WORKDIR /app

# Определение аргументов сборки
ARG GP_INVITATIONSERVICE_KEYSTORE_PASSWORD
ARG GP_INVITATIONSERVICE_DB_URL
ARG GP_INVITATIONSERVICE_DB_USERNAME
ARG GP_INVITATIONSERVICE_DB_PASSWORD

ENV GP_INVITATIONSERVICE_KEYSTORE_PASSWORD=1PwCgNAb
ENV GP_INVITATIONSERVICE_DB_URL=jdbc:postgresql://localhost:5432/invitationservicedb
ENV GP_INVITATIONSERVICE_DB_USERNAME=invitationservice
ENV GP_INVITATIONSERVICE_DB_PASSWORD=12345

# Копирование файлов проекта
COPY pom.xml ./
RUN mvn dependency:go-offline -B
COPY src ./src
COPY certificate.p12 ./

# Сборка проекта с использованием Maven и передача аргументов сборки
RUN mvn clean package
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
