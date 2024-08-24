rem chcp 65001

rem cls

cls
del /s /q target

rem rem cls
rem call mvn compile

cls
call mvn -P include-liquibase-plugin -Dspring.profiles.active=oleg liquibase:update

rem rem cls
call mvn jooq-codegen:generate

rem cls
rem call mvn package

rem cls
call mvn spring-boot:run


rem call mvn protobuf:compile
rem call mvn protobuf:compile-custom

rem -P include-liquibase-plugin
rem -Dspring.profiles.active=oleg
