package com.zhao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhao.mapper.UserMapper;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.pojo.User;
import com.zhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService service;


    @GetMapping("/findPage/{pageNum}/{pageSize}")
    public Object findPage(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize ){
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(pageNum);
        pageRequest.setPageSize(pageSize);
        PageResult page = service.findPage(pageRequest);
        return page.getContent();
    }

    @RequestMapping("/hello")
    public String test(){
        return "helloworld!";
    }

    @RequestMapping("/queryAll")
    public List<User> test1(){
        List<User> allUsers = userMapper.findAllUsers();
        return allUsers;
    }

    @RequestMapping("/testLogin/{username}/{password}")
    public User test2(@PathVariable("username") String username,@PathVariable("password") String password){
        User user = userMapper.findUserByUsernameAndPassword(username, password);
        return user;
    }

}
