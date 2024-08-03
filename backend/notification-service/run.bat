cls
setx GP_NOTIFICATION_SERVICE_DB_URL jdbc:postgresql://localhost:5432/notificationservicedb
setx GP_NOTIFICATION_SERVICE_DB_USERNAME notificationservice
setx GP_NOTIFICATION_SERVICE_DB_PASSWORD 12345

set GP_NOTIFICATION_SERVICE_DB_URL=jdbc:postgresql://localhost:5432/notificationservicedb
set GP_NOTIFICATION_SERVICE_DB_USERNAME=notificationservice
set GP_NOTIFICATION_SERVICE_DB_PASSWORD=12345

rem mvn liquibase:update -P include-liquibase-plugin

rem cls
call mvn clean package
call mvn spring-boot:run