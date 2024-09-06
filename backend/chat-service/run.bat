cls

set GP_CHATSERVICE_DB_URL=jdbc:postgresql://localhost:5432/chatservicedb
set GP_CHATSERVICE_DB_USERNAME=chatservice
set GP_CHATSERVICE_DB_PASSWORD=12345

rem setx GP_CHATSERVICE_DB_URL jdbc:postgresql://localhost:5432/chatservicedb
rem setx GP_CHATSERVICE_DB_USERNAME chatservice
rem setx GP_CHATSERVICE_DB_PASSWORD 12345

del target /s /q

call mvnw liquibase:dropAll -P include-liquibase-plugin
call mvnw liquibase:update -P include-liquibase-plugin

mvnw spring-boot:run