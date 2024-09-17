chcp 65001

set GP_INVITATION_SERVICE_DB_URL=jdbc:postgresql://localhost:5432/invitationservicedb
set GP_INVITATION_SERVICE_DB_USERNAME=invitationservice
set GP_INVITATION_SERVICE_DB_PASSWORD=12345

cls

del /s /q target

rem call mvnw liquibase:update -P include-liquibase-plugin
call mvn -P include-liquibase-plugin liquibase:dropAll liquibase:update


mvnw spring-boot:run