@echo off
chcp 65001 >nul
cls

cd C:\Users\1\Desktop\1\8\it-park-graduation-project\infrastructure
call docker compose up -d

cd C:\Users\1\Desktop\1\8\it-park-graduation-project\infrastructure\oleg
call oleg-set-env.bat

cd C:\Users\1\Desktop\1\8\it-park-graduation-project\backend\auth-service
call mvnw test

rem if %ERRORLEVEL% equ 0 (
rem         goto end
rem       )

cd C:\Users\1\Desktop\1\8\it-park-graduation-project\infrastructure

rem "C:\Program Files\Google\Chrome\Application\chrome.exe" "C:\Users\1\Desktop\1\8\it-park-graduation-project\boo.txt"

rem end:
rem echo THE END