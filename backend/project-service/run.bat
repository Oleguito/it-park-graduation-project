rem cls
chcp 65001

cls
rem rmdir /s /q target 

rem cls
rem call mvn -P include-liquibase-plugin liquibase:update

rem cls
rem call mvn jooq-codegen:generate

rem cls
call mvn spring-boot:run -P generate-jooq-manually

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
