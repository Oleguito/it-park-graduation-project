cls

set GP_DOCUMENT_SERVICE_DB_URL=jdbc:postgresql://localhost:5432/documentservicedb
set GP_DOCUMENT_SERVICE_DB_USERNAME=documentservice
set GP_DOCUMENT_SERVICE_DB_PASSWORD=12345

rem call mvnw liquibase:dropAll -P include-liquibase-plugin
call mvnw liquibase:update -P include-liquibase-plugin

mvnw spring-boot:run