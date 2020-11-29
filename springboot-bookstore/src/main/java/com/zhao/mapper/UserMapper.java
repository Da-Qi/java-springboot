package com.zhao.mapper;

import com.zhao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper{
    /**
     * 添加一个用户
     */
    void addUser(User user);

    /**
     * 通过激活码找到用户
     */
    User findUserByActiveCode(String activeCode);

    /**
     * 通过激活码更新用户状态
     */
    void updateActiveState(String activeCode);

    /**
     * 通过用户名和密码查找用户
     */
    User findUserByUsernameAndPassword(String username , String password);

    /**
     * 更新用户信息
     */
    void updateUser(User user);

    /**
     * 根据id查找用户
     */
    User findUserById(String id);

    /**
     *
     */
    User findUserByName(String username);

    /**
     * 查询所有用户
     */
    List<User> findAllUsers();

    /**
     * 分页查询用户
     * @return
     */
    List<User> findUserByPage();
}
