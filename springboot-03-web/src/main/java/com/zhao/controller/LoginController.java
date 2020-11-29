package com.zhao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username ,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){
        if (username!=null && "123456".equals(password)){
            session.setAttribute("user",username);
            return "redirect:/main.html";
        }else {
            map.put("msg","用户名和密码错误");
            return "login";
        }

    }
}
