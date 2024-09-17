cls

set GP_CHATSERVICE_DB_URL=jdbc:postgresql://localhost:5432/chatservicedb
set GP_CHATSERVICE_DB_USERNAME=chatservice
set GP_CHATSERVICE_DB_PASSWORD=12345

rem setx GP_CHATSERVICE_DB_URL jdbc:postgresql://localhost:5432/chatservicedb
rem setx GP_CHATSERVICE_DB_USERNAME chatservice
rem setx GP_CHATSERVICE_DB_PASSWORD 12345

del target /s /q

rem call mvnw liquibase:dropAll -P include-liquibase-plugin
rem call mvnw liquibase:update -P include-liquibase-plugin

call mvn -P include-liquibase-plugin liquibase:dropAll liquibase:update


mvnw spring-boot:run