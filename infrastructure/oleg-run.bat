cls

rem rmdir db\dbdata /s /q

docker compose -f docker-compose-oleg.yaml down -v --remove-orphans --volumes

rem docker system prune -a -q

docker compose -f docker-compose-oleg.yaml up

