rem @echo off
rem chcp 65001 >nul
rem cls
rem docker compose stop
rem docker compose up -d

rem cd ../backend/auth-service

rem :retry
rem call mvnw clean install spring-boot:run -Dspring-boot.run.profiles=dev

rem    if %ERRORLEVEL% neq 0 (
rem          echo Error occurred during mvnw clean install, but continuing...
rem          goto retry
rem      )

rem spring-boot:run

@echo off
chcp 65001 >nul
echo Остановка сервисов...
docker compose stop
echo Подготовка к запуску...
docker compose up -d collaborative-project-postgres-db liquibase liquibase-auth liquibase-notification liquibase-document
echo Ожидание заверешения подготовки
docker compose wait liquibase liquibase-auth liquibase-notification liquibase-document

REM Проверяем, успешно ли завершилась предыдущая команда
if %errorlevel% equ 0 (
    echo Запуск основных сервисов...
    docker compose up
) else (
    echo Liquibase services failed. Exiting.
    exit /b 1
)
