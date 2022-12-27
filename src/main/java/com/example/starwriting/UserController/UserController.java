package com.example.starwriting.UserController;

import com.example.starwriting.UserDao;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    UserDao user = new UserDao();

    @GetMapping("/api/users")
    public List getUser() throws SQLException, ClassNotFoundException {
        System.out.println("Get User 요청 들어옴");
        JSONArray userInfo = user.getName();

        for(int i = 0; i < userInfo.length(); i++){
            System.out.println(userInfo.get(i));
        }

        return userInfo.toList();
    }

//    @RequestMapping("/request-param-v1")
//    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String username = request.getParameter("username");
//        // String[] usernames = request.getParameterValues("username"); // 이름이 같은 복수 파라미터 조회
//        int age = Integer.parseInt(request.getParameter("age"));
//
//        log.info("username={}, age={}", username, age);
//        response.getWriter().write("ok");
//    }

    // 이거 쿼리스트링으로 파라메터가 올때 안올떄 구분해서 위에 getUser메소드랑 합쳐야될듯
    // 둘다 매핑한 uri가 똑같아서 오류생김 -> 둘다 /api/users로 매핑이되어있음
    // 아래걸 /api/users?name={name} 이런식으로 하면 되나?? 이건 모르겠음
//    @GetMapping("/api/users")
//    public void getSpecificUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
//        System.out.println("Get 특정User 요청 들어옴");
//
//        String name = request.getParameter("name");
//        // String[] usernames = request.getParameterValues("username"); // 이름이 같은 복수 파라미터 조회
//        System.out.println(name);
//    }

    @PostMapping("/api/test")
    public Object getTest(@RequestBody Map<String, String> param){
        System.out.println("ID : " + param.get("id"));
        System.out.println("password : " + param.get("password"));
        return param;
    }

    // user 테이블 한명 추가
    @PostMapping("/api/users")
    public String postUser(@RequestBody Map<String, String> param) throws SQLException, ClassNotFoundException {
        System.out.println("Post User 요청 들어옴 : " + param);

        /* user_id, password, name, birthday, email, phone_num, nickname, tier, since_from,id */
        user.add(param.get("user_id"),param.get("password"), param.get("name"), param.get("birthday"), param.get("email"), param.get("phone_num"),param.get("nickname"));

        return "생성완료";
    }

    // user 테이블 전체 삭제
    @DeleteMapping("/api/posts")
    public void deleteUser() throws SQLException, ClassNotFoundException {
        System.out.println("Delete User 요청 들어옴");
        user.delete();
    }
}
