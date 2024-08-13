chcp 65001

cls

rem cls
rem call mvn clean

rem rem cls
rem call mvn compile

rem rem cls
rem call mvn -P include-liquibase-plugin -Dspring.profiles.active=oleg liquibase:update

rem rem cls
rem call mvn jooq-codegen:generate

rem cls
rem call mvn package

rem cls
call mvn spring-boot:run


rem call mvn protobuf:compile
rem call mvn protobuf:compile-custom

rem -P include-liquibase-plugin
rem -Dspring.profiles.active=oleg
