package com.zhao.controller;

import com.zhao.pojo.User;
import com.zhao.service.Impl.UserServiceImpl;
import com.zhao.service.UserService;
import com.zhao.util.UserUtils;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/register", produces = "application/json;charset=utf-8")
    public String userRegister(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //判断验证码是否正确
        //正确
        if (check != null && check.equalsIgnoreCase(checkcode_server)) {
            User user = new User();
            //封装对象
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String nickname = request.getParameter("nickname");
            String telephone = request.getParameter("telephone");
            String gender = request.getParameter("gender");
            user.setUser_name(username);
            user.setUser_password(password);
            user.setUser_nickname(nickname);
            user.setUser_telephone(telephone);
            user.setUser_gender(gender);
            if (gender.equals("男")) {
                user.setUser_img_url("/image_user/boy_default.jpg");
            }
            user.setUser_img_url("/image_user/girl_default.jpg");
            user.setUser_register_time(new Date());
            //注册
            boolean flag = userService.register(user);
            if (flag) {
                jsonObject.put("flag", true);
                jsonObject.put("errorMsg", "注册成功");
            } else {
                jsonObject.put("flag", false);
                jsonObject.put("errorMsg", "注册失败");
            }
            //结果写会
            return jsonObject.toString();
        }
        //验证码错误
        jsonObject.put("errorMsg", "验证码错误");
        jsonObject.put("flag", false);
        return jsonObject.toString();
    }


    @RequestMapping(value = "/user/login", produces = "application/json;charset=utf-8")
    public String userLogin(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        JSONObject resultObject = new JSONObject();
        String check = jsonObject.getString("check");
        String checkcode_server = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
        if (check != null && check.equalsIgnoreCase(checkcode_server)) {
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            User user = userService.login(username, password);
            if (user != null) {
                resultObject.put("msg", "登录成功");
                resultObject.put("flag", true);
                resultObject.put("nickname", user.user_nickname);
                //user的信息存在session中
                request.getSession().setAttribute("user", user);
            } else {
                resultObject.put("msg", "用户名或密码错误，请重新登录");
                resultObject.put("flag", false);
            }
            return resultObject.toString();
        }
        //验证码错误
        resultObject.put("msg", "验证码错误，请重新输入");
        resultObject.put("flag", false);
        return resultObject.toString();
    }

    @RequestMapping(value = "/exit")
    public void exit(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @RequestMapping(value = "/user/info", produces = "application/json;charset=utf-8")
    public String userInfo(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        HttpSession session = request.getSession();
        //session为空，用户通过cookie登录
        User user = userService.findUserByNickName(request.getParameter("nickname"));
        // session存入user，达到登录后的效果
        session.setAttribute("user", user);
        jsonObject.put("user_id",user.user_id);
        jsonObject.put("username", user.user_name);
        jsonObject.put("nickname", user.user_nickname);
        jsonObject.put("gender", user.user_gender);
        jsonObject.put("telephone", user.user_telephone);
        jsonObject.put("img_url", user.user_img_url);
        return jsonObject.toString();
    }


    @RequestMapping(value = "/user/exist", produces = "application/json;charset=utf-8")
    public String userExist(@RequestBody JSONObject jsonObject) {
        JSONObject resultObject = new JSONObject();
        String username = jsonObject.getString("username");
        boolean exist = userService.ifUserExist(username);
        //如果存在，说明该用户名已经被占用
        if (exist){
            resultObject.put("flag",false);
            resultObject.put("msg","该用户名已经存在了哦");
        }else {
            //前端返回一个绿色的√，表示可以使用
            resultObject.put("flag",true);
        }
        return resultObject.toString();
    }

    @RequestMapping(value = "/user/update_info", produces = "application/json;charset=utf-8")
    public String userUpdate(@RequestBody JSONObject jsonObject) {
        JSONObject resultObject = new JSONObject();
        String user_id = jsonObject.getString("user_id");
        System.out.println("user_id= " + user_id);
        String username = jsonObject.getString("username");
        String nickname = jsonObject.getString("nickname");
        String telephone = jsonObject.getString("telephone");
        String gender = jsonObject.getString("gender");
        User user = new User();
        user.setUser_id(Long.parseLong(user_id));
        user.setUser_name(username);
        user.setUser_nickname(nickname);
        user.setUser_telephone(telephone);
        user.setUser_gender(gender);
        //更新用户对象
        boolean flag = userService.updateUser(user);
        if (flag){
            resultObject.put("flag",true);
            resultObject.put("msg","修改成功！");
        }else {
            resultObject.put("flag",false);
            resultObject.put("msg","修改失败！");
        }
        return resultObject.toString();

    }

    @RequestMapping(value = "/user/update_image", produces = "application/json;charset=utf-8")
    public void userUpdateImage(HttpServletRequest request) {
        MultipartHttpServletRequest mtsr = (MultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> map = mtsr.getMultiFileMap();
        List<MultipartFile> files = map.get(map.keySet().iterator().next());
        MultipartFile file = files.get(0);
        String name = file.getName();
        int index = name.lastIndexOf(".");
        String suffix = name.substring(index);
        try {
            InputStream fis = file.getInputStream();
            File fileForPath = new File("");
            String absolutePath = fileForPath.getAbsolutePath();
            //获取用户信息
            User user = (User) request.getSession().getAttribute("user");
            //生成新的文件名
            String imageFileName = new UserUtils().getImageFileName(name) + suffix;
            //修改用户的头像存储路径
            userService.changeUserImageUrl("/image_user/" + imageFileName, user.user_id);

            String newFileName = absolutePath + "\\src\\main\\resources\\static\\image_user\\" + imageFileName;
            System.out.println(newFileName);
            File file1 = new File(newFileName);
            FileOutputStream fos = new FileOutputStream(file1);
            IOUtils.copy(fis, fos);
            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
