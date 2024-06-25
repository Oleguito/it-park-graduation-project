@echo off
chcp 65001 >nul
cls
rem docker compose stop

cd "C:\Users\1\Desktop\1\8\it-park-graduation-project\infrastructure"
docker compose up -d

cd "C:\Users\1\Desktop\1\8\it-park-graduation-project\backend\auth-service"
call mvnw clean test spring-boot:run -Dspring-boot.run.profiles=dev

    rem if %ERRORLEVEL% neq 0 (
        rem   echo Error occurred during mvnw clean install, but continuing...
          rem goto retry
      rem )

rem spring-boot:run