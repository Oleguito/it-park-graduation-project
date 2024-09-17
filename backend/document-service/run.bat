cls

set GP_DOCUMENT_SERVICE_DB_URL=jdbc:postgresql://localhost:5432/documentservicedb
set GP_DOCUMENT_SERVICE_DB_USERNAME=documentservice
set GP_DOCUMENT_SERVICE_DB_PASSWORD=12345

rem call mvnw liquibase:dropAll -P include-liquibase-plugin
rem call mvnw liquibase:update -P include-liquibase-plugin
call mvn -P include-liquibase-plugin liquibase:dropAll liquibase:update


mvnw spring-boot:run