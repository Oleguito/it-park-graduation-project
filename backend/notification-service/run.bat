cls

setx GP_AUTHSERVICE_DB_NAME authservicedb
setx GP_AUTHSERVICE_DB_USERNAME authservice
setx GP_AUTHSERVICE_DB_PASSWORD 12345
setx GP_AUTHSERVICE_DB_URL jdbc:postgresql://localhost:5432/authservicedb

setx GP_AUTHSERVICE_KEYSTORE_PASSWORD 1PwCgNAb

setx GP_KEYCLOAK_ADMIN_PASSWORD nvwS6vK#IaE@Ds4RveKs
rem setx GP_KEYCLOAK_ADMIN_PASSWORD HVG-Rqf-5PV-Lp6 dppmai

setx GP_AUTHSERVICE_CLIENT_SECRET 7TCb2UhbgVpyh186oC6VMe9srakq16Bp
rem setx GP_AUTHSERVICE_CLIENT_SECRET HmdTDuZWiH39jZlFsXPwuHqUcqgdXcV9 dppmai

rem setx KEYCLOAK_DEAD 1

setx GP_PROJECTSERVICE_DB_URL jdbc:postgresql://localhost:5432/projectservicedb
setx GP_PROJECTSERVICE_DB_USERNAME projectservice
setx GP_PROJECTSERVICE_DB_PASSWORD 12345

setx GP_NOTIFICATION_SERVICE_DB_URL jdbc:postgresql://localhost:5432/notificationservicedb
setx GP_NOTIFICATION_SERVICE_DB_USERNAME notificationservice
setx GP_NOTIFICATION_SERVICE_DB_PASSWORD 12345

setx GP_NOTIFICATION_SERVICE_SERVER itpark.projects@mail.ru
setx GP_NOTIFICATIONSERVICE_SERVER_PASS vq28qBv4jct2VfV5hWXe
set GP_NOTIFICATION_SERVICE_SERVER=itpark.projects@mail.ru
set GP_NOTIFICATIONSERVICE_SERVER_PASS=vq28qBv4jct2VfV5hWXe

setx GP_NOTIFICATION_SERVICE_DB_URL jdbc:postgresql://localhost:5432/notificationservicedb
setx GP_NOTIFICATION_SERVICE_DB_USERNAME notificationservice
setx GP_NOTIFICATION_SERVICE_DB_PASSWORD 12345

set GP_NOTIFICATION_SERVICE_DB_URL=jdbc:postgresql://localhost:5432/notificationservicedb
set GP_NOTIFICATION_SERVICE_DB_USERNAME=notificationservice
set GP_NOTIFICATION_SERVICE_DB_PASSWORD=12345

rem call mvn liquibase:dropAll -P include-liquibase-plugin
rem call mvn liquibase:update -P include-liquibase-plugin
call mvn -P include-liquibase-plugin liquibase:dropAll liquibase:update


rem cls
rem call mvn clean package
call mvn spring-boot:run