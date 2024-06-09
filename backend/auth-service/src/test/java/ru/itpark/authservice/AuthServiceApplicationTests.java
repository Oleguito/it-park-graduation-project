package ru.itpark.authservice;

import lombok.Value;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import ru.itpark.authservice.domain.user.User;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
class AuthServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void someTest() {

        String userName = "authservice";
        String password = "12345";
        String url = "jdbc:postgresql://collaborative-project-postgres-db:5432/authservicedb";

        System.out.println("Перед подключением JOOQ");

        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            System.out.println("подключились connected");
            DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
            Result<Record> result = create.select().from("users").fetch();
            System.out.println("Количество записей: " + result.size());
            System.out.println("Записи: " + result);
        }

        // For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
