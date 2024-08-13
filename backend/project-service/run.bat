cls
chcp 65001

cls
call mvn clean 

rem cls
call mvn -P include-liquibase-plugin liquibase:update

rem cls
call mvn spring-boot:run


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
