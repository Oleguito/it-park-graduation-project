@echo off
chcp 65001 >nul
cls
docker compose stop
docker compose up -d

cd ../backend/auth-service
mvnw clean package spring-boot:run -Dspring-boot.run.profiles=dev
rem spring-boot:run