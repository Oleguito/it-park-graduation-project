package ru.itpark.authservice;

import lombok.Value;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

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

        // Connection is the only JDBC resource that we need
        // PreparedStatement and ResultSet are handled by jOOQ, internally
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            System.out.println("подключились connected");
        }

        // For the sake of this tutorial, let's keep exception handling simple
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
