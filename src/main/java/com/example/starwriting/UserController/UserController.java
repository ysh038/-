package com.example.starwriting.UserController;

import com.example.starwriting.UserDao;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
public class UserController {
    UserDao user = new UserDao();

    @GetMapping("/")
    public String get() {
        System.out.println("localhost:8000/에 Get 요청");
        return "user";
    }

    @GetMapping("/api")
    public String getUser() throws SQLException, ClassNotFoundException {
        System.out.println("/api Get User 요청 들어옴");
        user.get();
        return "user";
    }

    @PostMapping("/api/test")
    public Object getTest(@RequestBody Map<String, String> param){
        System.out.println("ID : " + param.get("id"));
        System.out.println("password : " + param.get("password"));
        return param;
    }

    @PostMapping("/api/post")
    public String postUser(@RequestBody Map<String, String> param) throws SQLException, ClassNotFoundException {
        System.out.println("Post User 요청 들어옴 : " + param);

        /* user_id, password, name, birthday, email, phone_num, nickname, tier, since_from,id */
        user.add(param.get("user_id"),param.get("password"), param.get("name"), param.get("birthday"), param.get("email"), param.get("phone_num"),param.get("nickname"));

        return "생성완료";
    }
}
