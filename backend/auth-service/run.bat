chcp 65001
cls
call mvn clean
rem call mvn protobuf:compile
rem call mvn protobuf:compile-custom
call mvn spring-boot:run

rem mvn -P include-liquibase-plugin -Dspring.profiles.active=oleg liquibase:update
rem mvn jooq-codegen:generate
rem


    rem -P include-liquibase-plugin
    rem -Dspring.profiles.active=oleg
