package com.zhao.service;

import com.zhao.pojo.User;

public interface UserService {
    boolean register(User user);

    User login(String username, String password);

    User findUserByNickName(String nickname);

    void changeUserImageUrl(String imageUrl, int user_id);

    boolean ifUserExist(String username);

    boolean updateUser(User user);

    User findUserById(int user_id);

    int selectAllUser();
}
