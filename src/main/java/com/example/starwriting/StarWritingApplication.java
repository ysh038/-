package com.example.starwriting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class StarWritingApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SpringApplication.run(StarWritingApplication.class, args);
    }
}
