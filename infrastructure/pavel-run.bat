@echo off
chcp 65001 >nul
cls
docker compose stop
docker compose up -d

cd ../backend/auth-service

rem :retry
rem call mvnw clean install spring-boot:run -Dspring-boot.run.profiles=dev

rem    if %ERRORLEVEL% neq 0 (
rem          echo Error occurred during mvnw clean install, but continuing...
rem          goto retry
rem      )

rem spring-boot:run