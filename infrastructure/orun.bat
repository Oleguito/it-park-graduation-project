@echo off
cls
docker compose down
docker compose up -d

start ^
C:\Users\1\Desktop\it-park-graduation-project\backend\auth-service\run.bat ^
test
rem spring-boot:run