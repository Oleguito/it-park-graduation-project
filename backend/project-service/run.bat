chcp 65001
cls
rem call mvn clean
rem call mvn -P include-liquibase-plugin liquibase:update
cls
call mvn protobuf:compile
cls
call mvn protobuf:compile-custom
cls
call mvn spring-boot:run

rem mvn jooq-codegen:generate
rem


    rem -P include-liquibase-plugin
    rem -Dspring.profiles.active=oleg
