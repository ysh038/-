package com.example.starwriting.PostController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @GetMapping ("/api/post")
    public String get(){
        System.out.println("황깅ㄴ");
        return "확인0";
    }
}
