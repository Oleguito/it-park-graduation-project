# Этап 1: Сборка проекта с использованием Maven
FROM eclipse-temurin:17-alpine AS builder

# Установка Maven
RUN apk add --no-cache maven

# Установка рабочей директории
WORKDIR /app

# Определение аргументов сборки
ARG DB_URL
ARG DB_USER
ARG DB_PASSWORD

# Копирование файлов проекта
COPY pom.xml ./
RUN mvn dependency:go-offline -B
COPY src ./src

# Сборка проекта с использованием Maven и передача аргументов сборки
#RUN mvn clean package -Dliquibase.url=${DB_URL} -Dliquibase.username=${DB_USER} -Dliquibase.password=${DB_PASSWORD}
RUN mvn clean package

# Этап 2: Создание финального образа
FROM eclipse-temurin:11-alpine

# Установка рабочей директории
WORKDIR /app

# Копирование JAR файла из предыдущего этапа
COPY --from=builder /app/target/*.jar ./myapp.jar

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "myapp.jar"]