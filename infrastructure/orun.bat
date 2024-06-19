@echo off
chcp 65001 >nul
cls
docker compose down
docker compose up -d

cd ../backend/auth-service
mvnw clean test spring-boot:run -Dspring-boot.run.profiles=dev
rem spring-boot:run