package com.example.starwriting;

import java.sql.*;
import java.time.LocalDate;


public class UserDao {
    public void get() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/stardb","root","pass"
        );
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM user_tb"
        );

        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            System.out.println(rs.getString("user_id"));
        }

        rs.close();
        ps.close();
        conn.close();
    }
    public void add(String user_id, String password, String name,String birthday,String email,String phone_num,String nickname) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/stardb","root","pass"
        );
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO user_tb(user_id, password, name, birthday, " +
                        "email, phone_num, nickname, tier, since_from,id) values(?,?,?,?,?,?,?,?,?,?)"
        );

        LocalDate now = LocalDate.now();

        ps.setString(1, user_id);
        ps.setString(2,password);
        ps.setString(3,name);
        ps.setString(4,birthday);
        ps.setString(5, email);
        ps.setString(6,phone_num);
        ps.setString(7,nickname);
        ps.setString(8, "bronze");
        ps.setString(9,now.toString());
        ps.setString(10, String.valueOf(Math.random() * 1000000));

        int status = ps.executeUpdate();
        ps.close();
        conn.close();
    }
}
