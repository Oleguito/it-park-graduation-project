package oleg

import com.fasterxml.jackson.databind.ObjectMapper
import org.jooq.DSLContext
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.jooq.SQLDialect

import org.jooq.JSONB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import ru.itpark.authservice.domain.user.User
import ru.itpark.authservice.domain.user.converters.LanguageConverter
import ru.itpark.authservice.domain.user.valueobjects.DateInfo
import ru.itpark.authservice.domain.user.valueobjects.Language
import ru.itpark.authservice.tables.records.UsersRecord
import spock.lang.Specification
import ru.itpark.authservice.domain.user.Role
import ru.itpark.authservice.tables.Users

import java.time.LocalDateTime

import static ru.itpark.authservice.Tables.USERS


import java.sql.Connection
import java.sql.DriverManager

class LearnJOOQTests extends Specification {

    @Autowired
    LanguageConverter languageConverter = new LanguageConverter()

    ObjectMapper objectMapper = new ObjectMapper()

    def "Expect 1 == 1"() {
        expect:
        1 == 1
    }

    def "Create a user in db"() {

        try(Connection connection = DriverManager.getConnection(
                System.getenv("GP_AUTHSERVICE_DB_URL"),
                System.getenv("GP_AUTHSERVICE_DB_USERNAME"),
                System.getenv("GP_AUTHSERVICE_DB_PASSWORD"),
        )) {
            DSLContext dsl = DSL.using(connection, SQLDialect.POSTGRES);


//            dsl.deleteFrom(USERS)
//                    .where(USERS.EMAIL.eq("tenzoriator@rambler.ru"))
//                    .execute();

            User user = dsl.insertInto(USERS)
                    .columns(USERS.FULL_NAME, USERS.EMAIL, USERS.LOGIN, USERS.LANGUAGES, USERS.ROLE, USERS.CREATED_AT, USERS.DELETED_AT)
                    .values(
                            "Oleguito Swagbucks",
                            "tenzoriator@rambler.ru",
                            "Oleguito",
                            JSONB.valueOf(objectMapper.writeValueAsString(
                                    List.of(Language.builder()
                                            .language("English")
                                            .level("B2")
                                            .build())
                            )),
                            Role.USER.toString(),
                            LocalDateTime.now(),
                            null
                    )
                    .returningResult(USERS.ID, USERS.FULL_NAME, USERS.EMAIL, USERS.LOGIN, USERS.LANGUAGES, USERS.ROLE, USERS.CREATED_AT, USERS.DELETED_AT)
                    .fetchOne()
                    .into(User.class)
//                    .map(record -> User.builder()
//                            .id(record.getValue(USERS.ID))
//                            .fullName(record.getValue(USERS.FULL_NAME))
//                            .email(record.getValue(USERS.EMAIL))
//                            .login(record.getValue(USERS.LOGIN))
//                            .languages(languageConverter.convertToEntityAttribute(record.getValue(USERS.LANGUAGES) as String))
//                            .role(Role.valueOf(record.getValue(USERS.ROLE)))
//                            .dateInfo(new DateInfo(record.getValue(USERS.CREATED_AT), record.getValue(USERS.DELETED_AT)))
//                            .build());

            println user
        }

        expect:
        1
    }
}
