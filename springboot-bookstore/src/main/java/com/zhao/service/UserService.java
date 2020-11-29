package com.zhao.service;

import com.zhao.exception.UserException;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.pojo.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;


public interface UserService {
    /**
     * 注册功能
     */
    void register(User user) throws UserException, NoSuchAlgorithmException;

    /**
     * 激活功能
     */
    void activeUser(String activeCode) throws UserException;

    /**
     * 登录功能
     */
    User login(String username,String password) throws UserException;

    /**
     * 更改用户信息
     */
    void modifyUserInfo(User user);

    /**
     * 根据用户id寻找用户
     */
    User findUserById(String id);

    /**
     * 查找所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);
}
