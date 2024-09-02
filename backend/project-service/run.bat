
set GP_PROJECTSERVICE_KEYSTORE_PASSWORD=1PwCgNAb

rem cls
chcp 65001

cls
del /s /q target

rem cls
call mvn -P include-liquibase-plugin liquibase:dropAll liquibase:update

rem cls
call mvn jooq-codegen:generate

rem cls
call mvn spring-boot:run

rem -P generate-jooq-manually
rem cls
rem call mvn jooq-codegen:generate
rem call mvn clean package -P skip-kafka-tests
rem -DskipTests
rem cls
rem call mvn protobuf:compile
rem
rem cls
rem call mvn protobuf:compile-custom
rem mvn jooq-codegen:generate
rem
rem -P include-liquibase-plugin
rem -Dspring.profiles.active=oleg
