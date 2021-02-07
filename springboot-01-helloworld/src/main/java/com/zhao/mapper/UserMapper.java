package com.zhao.mapper;

import com.zhao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    void registerUser(User user);

    User findUserByUsernameAndPassword(String username , String password);

    void updateUser(User user);

    User findUserById(String id);

    int findUserByName(String username);

    List<User> findAllUsers();

    User findUserByNickName(String nickname);

    void changeUserImageUrl(String imageUrl, long user_id);
}
