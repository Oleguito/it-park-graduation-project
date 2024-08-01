//package ru.itpark.authservice;
//
//import org.jooq.Record;
//import org.jooq.*;
//import org.jooq.impl.DSL;
//import org.jooq.meta.derby.sys.Sys;
//import org.junit.jupiter.api.Test;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//import ru.itpark.authservice.domain.user.valueobjects.Language;
//import ru.itpark.authservice.tables.records.UsersRecord;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.util.ArrayList;
//import java.util.List;
//
//import static ru.itpark.authservice.Tables.USERS;
//
//class AuthServiceApplicationTests {
//
//    String url = System.getenv("GP_AUTHSERVICE_DB_URL");
////            "jdbc:postgresql://collaborative-project-postgres-db:5432/authservicedb";
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void someTest() {
//
//        if(System.getenv("KEYCLOAK_DEAD").equals(1)) return;
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String userName = "authservice";
//        String password = "12345";
//
//        System.out.println("Перед подключением JOOQ");
//
//        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
//            System.out.println("подключились connected");
//            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
//            Result<Record> result = create.select().from(USERS).fetch();
//            System.out.println("Количество записей: " + result.size());
//            System.out.println("Записи: " + result);
//
//            Result<Record> recordsFromDB = create.select().from(USERS).where(USERS.EMAIL.eq("blabla@gmail.com")).fetch();
//
//            List<Language> languages = new ArrayList<>();
//            languages.add(Language.builder()
//                            .language("ru")
//                            .level("2")
//                    .build());
//            languages.add(Language.builder()
//                            .language("en")
//                            .level("c3")
//                    .build());
//
//            if (recordsFromDB.size() == 0) {
//                UsersRecord someEnv = create.newRecord(USERS);
//                someEnv.setEmail("blabla@gmail.com");
//                someEnv.setFullName("KABACHOK");
//                someEnv.setLanguages(JSONB.valueOf(objectMapper.writeValueAsString(languages)));
////                someEnv.setLanguages(JSONB.valueOf("[{\"language\":\"ru\", \"level\":\"2\"}, {\"language\":\"en\", \"level\":\"c3\"}]"));
//                someEnv.store();
//            }
//
//        }
//
//
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
