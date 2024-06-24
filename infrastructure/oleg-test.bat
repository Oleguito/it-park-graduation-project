@echo off
chcp 65001 >nul
cls
docker compose up -d

cd ../backend/auth-service
call mvnw test
cd ../../infrastructure
rem spring-boot:run