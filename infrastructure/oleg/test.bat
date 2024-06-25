@echo off
chcp 65001 >nul

cd C:\Users\1\Desktop\1\8\it-park-graduation-project\infrastructure\oleg
del boo.html

cd C:\Users\1\Desktop\1\8\it-park-graduation-project\infrastructure\oleg
call oleg-test-into-log.bat > boo.txt

"C:\Program Files\Google\Chrome\Application\chrome.exe" ^
"C:\Users\1\Desktop\1\8\it-park-graduation-project\infrastructure\oleg\boo.txt"