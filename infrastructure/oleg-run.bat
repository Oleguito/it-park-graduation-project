cls

rmdir db\dbdata /s /q

docker compose down -v --remove-orphans --volumes 

docker system prune -a -q

docker compose up

