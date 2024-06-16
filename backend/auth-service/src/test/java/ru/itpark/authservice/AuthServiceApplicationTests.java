package ru.itpark.authservice;

import lombok.Value;
import org.jooq.*;
import org.jooq.Record;
//import org.jooq.generated.public_.tables.records.UsersRecord;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.itpark.authservice.domain.user.User;
import ru.itpark.authservice.domain.user.valueobjects.Language;
import ru.itpark.authservice.tables.Users;
 import ru.itpark.authservice.tables.records.UsersRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

//import static org.jooq.generated.public_.Tables.*;
//
//import static org.jooq.generated.public_.Tables.USERS;
import static ru.itpark.authservice.tables.Users.USERS;

class AuthServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void someTest() {

        ObjectMapper objectMapper = new ObjectMapper();

        // hello
        String userName = "authservice";
        String password = "12345";
        String url = "jdbc:postgresql://collaborative-project-postgres-db:5432/authservicedb";

        System.out.println("Перед подключением JOOQ");

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            System.out.println("подключились connected");
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
            Result<Record> result = create.select().from(USERS).fetch();
            System.out.println("Количество записей: " + result.size());
            System.out.println("Записи: " + result);

            Result<Record> recordsFromDB = create.select().from(USERS).where(USERS.EMAIL.eq("blabla@gmail.com")).fetch();

            List<Language> languages = new ArrayList<>();
            languages.add(Language.builder()
                            .language("ru")
                            .level("2")
                    .build());
            languages.add(Language.builder()
                            .language("en")
                            .level("c3")
                    .build());

            if (recordsFromDB.size() == 0) {
                UsersRecord someEnv = create.newRecord(USERS);
                someEnv.setEmail("blabla@gmail.com");
                someEnv.setFullName("KABACHOK");
                someEnv.setLanguages(JSONB.valueOf(objectMapper.writeValueAsString(languages)));
//                someEnv.setLanguages(JSONB.valueOf("[{\"language\":\"ru\", \"level\":\"2\"}, {\"language\":\"en\", \"level\":\"c3\"}]"));
                someEnv.store();
            }

        }


        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
