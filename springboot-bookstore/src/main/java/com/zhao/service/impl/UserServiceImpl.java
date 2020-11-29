package com.zhao.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhao.exception.UserException;
import com.zhao.mapper.UserMapper;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.pojo.User;
import com.zhao.service.UserService;
import com.zhao.utils.PageUtils;
import com.zhao.utils.SendJMail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;

    /**
     * 注册用户
     *
     * @param user
     */
    public void register(User user) throws UserException{
        //首先判断用户名是否存在
        User user1 = userDao.findUserByName(user.getUsername());
        if (user1 != null) {
            //该用户名已经存在
            throw new UserException("该用户名已经存在啦");
        } else {
            //往数据库添加用户
            //加密密码
            String password = user.getPassword();
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            md5.update(password.getBytes());
            user.setPassword(new BigInteger(1, md5.digest()).toString(16));
            userDao.addUser(user);
            String link = "http://localhost:8081/BookStore/active?activeCode=" + user.getActiveCode();
            String html = "<a href=\"" + link + "\">欢迎" + user.getUsername() + "注册网上书城账号，请点击激活</a>";
            //发送激活邮件
            SendJMail.sendMail(user.getEmail(), html);
        }

    }

    /**
     * 激活用户
     *
     * @param activeCode
     */
    public void activeUser(String activeCode) throws UserException {
        //1. 查询激活码的用户是否存在
        User user = userDao.findUserByActiveCode(activeCode);
        if (user == null) {
            throw new UserException("该用户不存在，无法激活");
        } else if (user != null && user.getState() == 1) {
            throw new UserException("该用户已经是激活状态");
        }
        //2. 激活用户
        userDao.updateActiveState(activeCode);
    }

    /**
     * 登录功能
     *
     * @param username
     * @param password
     */
    public User login(String username, String password) throws UserException{
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(password.getBytes());
        String passwordTemp = new BigInteger(1, md5.digest()).toString(16);
        System.out.println(passwordTemp);
        //1. 查询
        User user = userDao.findUserByUsernameAndPassword(username, passwordTemp);
        //2. 判断
        if (user == null) {
            throw new UserException("用户名或密码不正确");
        } else if (user.getState() == 0) {
            throw new UserException("用户未激活，请先登录邮箱激活");
        }
        return user;

    }

    //修改用户信息
    public void modifyUserInfo(User user) {
        userDao.updateUser(user);
    }

    //通过id查找用户
    @Override
    public User findUserById(String id) {
        try {
            return userDao.findUserById(id);
        } catch (Exception e) {
            return null;
        }
    }

    //查询所有用户
    @Override
    public List<User> findAll() {
        return userDao.findAllUsers();
    }

    //查询对应页数据
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(getPageInfo(pageRequest));
    }

    private PageInfo<User> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userDao.findUserByPage();
        return new PageInfo<User>(users);
    }


}
