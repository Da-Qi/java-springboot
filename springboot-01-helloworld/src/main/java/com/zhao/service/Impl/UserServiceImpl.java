package com.zhao.service.Impl;

import com.zhao.mapper.UserMapper;
import com.zhao.pojo.User;
import com.zhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean register(User user) {
        try {
            userMapper.registerUser(user);
            return true;
        } catch (Exception e) {
            System.out.println("注册失败 ： " + e.toString());
            return false;
        }
    }

    @Override
    public User login(String username, String password) {
        return userMapper.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public User findUserByNickName(String nickname) {
        return userMapper.findUserByNickName(nickname);
    }

    @Override
    public void changeUserImageUrl(String imageUrl, long user_id) {
        userMapper.changeUserImageUrl(imageUrl,user_id);
    }

    @Override
    public boolean ifUserExist(String username) {
        int count = userMapper.findUserByName(username);
        System.out.println("count=" + count);
        return count != 0;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            userMapper.updateUser(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
