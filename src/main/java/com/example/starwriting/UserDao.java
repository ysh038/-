package com.example.starwriting;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.json.JSONArray;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao {
    public JSONArray getName() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/stardb","root","pass"
        );
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM user_tb"
        );

        ResultSet rs = ps.executeQuery();
        JSONArray jarr = new JSONArray();

        while(rs.next()){
            JSONObject obj = new JSONObject();
            String user_id = rs.getString("user_id");
            String name  = rs.getString("name");
            String password = rs.getString("password");
            Date birthday = rs.getDate("birthday");
            String email = rs.getString("email");
            String phone_num = rs.getString("phone_num");
            String nickname = rs.getString("nickname");
            String tier = rs.getString("tier");
            Date since_from = rs.getDate("since_from");
            String id = rs.getString("id");

            obj.put("user_id",user_id);
            obj.put("name",name);
            obj.put("password",password);
            obj.put("birthday",birthday);
            obj.put("email",email);
            obj.put("phone_num",phone_num);
            obj.put("nickname",nickname);
            obj.put("tier",tier);
            obj.put("since_from",since_from);
            obj.put("id",id);

            jarr.put(obj);
        }
        rs.close();
        ps.close();
        conn.close();

        return jarr;
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

    public void delete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/stardb","root","pass"
        );
        PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM user_tb"
        );

        ps.execute();
    }
}
