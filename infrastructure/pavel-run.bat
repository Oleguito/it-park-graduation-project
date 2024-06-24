@echo off
chcp 65001 >nul
cls
docker compose stop
docker compose up -d

cd ../backend/auth-service

:retry
call mvnw clean install spring-boot:run -Dspring-boot.run.profiles=dev

    if %ERRORLEVEL% neq 0 (
          echo Error occurred during mvnw clean install, but continuing...
          goto retry
      )

rem spring-boot:run