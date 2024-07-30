chcp 65001

cls
call mvn clean

cls
call mvn -P include-liquibase-plugin liquibase:update

rem cls
rem call mvn protobuf:compile
rem
rem cls
rem call mvn protobuf:compile-custom

cls
call mvn spring-boot:run

rem mvn jooq-codegen:generate
rem


    rem -P include-liquibase-plugin
    rem -Dspring.profiles.active=oleg
