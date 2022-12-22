package com.example.starwriting;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
class StarWritingApplicationTests {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/stardb?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
    private static final String USER = "root";
    private static final String PASSWORD = "pass";

    @Test
    public void testConnection() throws Exception{

        Class.forName(DRIVER);
        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
