package com.zhao.controller;

import com.zhao.exception.UserException;
import com.zhao.pojo.User;
import com.zhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    //登录
    @PostMapping("/login")
    public String login(@RequestParam("username") String username ,
                        @RequestParam("password") String password,
                        Map<String,Object> map,
                        HttpSession session){
        try {
            User user = service.login(username, password);
            session.setAttribute("user",user);
            return "redirect:/main.html";
        } catch (UserException e) {
            map.put("msg",e.getMessage());
            return "login";
        }
    }

    //登出
    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        //清除session
        request.getSession().invalidate();

        //清除Cookie
        Cookie cookie = new Cookie("userId",String.valueOf(-1));
        cookie.setPath("/BookStore/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
        return "redirect:/login";
    }

    //激活
    @RequestMapping("/active")
    public String activeUser(String activeCode){
        try {
            service.activeUser(activeCode);
        } catch (UserException e) {
            e.printStackTrace();
            return "404";
        }
        return "login";
    }

    //注册
    @RequestMapping("/register")
    public String register(String checkCode, HttpServletRequest request, Model model, User user){
        //获取验证码
        String checkCode_session = (String) request.getSession().getAttribute("checkCode_session");
        if (!checkCode.equals(checkCode_session)){
            model.addAttribute("checkCode_err","验证码输入不一致");
            //重新注册
            return "user/register";
        }else {
            //设置激活码
            user.setActiveCode(UUID.randomUUID().toString());
            user.setRole("普通用户");
            user.setRegisterTime(new Date());
            System.out.println(user);
            //2.注册
            try {
                service.register(user);
                //3. 返回结果
                //3.1 成功进入界面
                return "user/registerSuccess";
            } catch (UserException | NoSuchAlgorithmException e) {
                model.addAttribute("register",e.getMessage());
                //重新注册
                return "user/register";
            }

        }

    }

}
