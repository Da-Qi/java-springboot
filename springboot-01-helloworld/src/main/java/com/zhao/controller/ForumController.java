package com.zhao.controller;

import com.zhao.pojo.*;
import com.zhao.service.ForumService;
import com.zhao.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ForumController {
    @Autowired
    private ForumService forumService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/forum/{currentPage}/{searchKey}", produces = "application/json;charset=utf-8")
    public String limoFavoriteRank(HttpServletRequest request, @PathVariable String currentPage, @PathVariable String searchKey){
        if (currentPage==null || currentPage.equals("")){
            currentPage = "1";
        }
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(Integer.parseInt(currentPage));
        // 每页显示6条数据
        pageRequest.setPageSize(6);
        PageResult pageResult = null;
        if (searchKey==null || searchKey.equals("null")){
            // 获得查询结果
            pageResult = forumService.selectPostByPageQuery(pageRequest);
        }else {
            pageResult = forumService.selectPostByCategory(pageRequest,searchKey);
        }


        JSONObject jsonObject = new JSONObject();
        //右侧栏信息相关
        int user_num = userService.selectAllUser();
        jsonObject.put("user_num",user_num);

        //论坛内容相关
        jsonObject.put("totalPage",pageResult.getTotalPages());
        jsonObject.put("totalCount",pageResult.getTotalSize());
        jsonObject.put("currentPage",pageResult.getPageNum());

        JSONArray jsonArray = new JSONArray();
        List<Post> result = pageResult.getContent();
        result.forEach(post -> {
            JSONObject postObject = new JSONObject();
            postObject.put("id",post.id);
            postObject.put("title",post.title);
            postObject.put("description",post.description);
            postObject.put("post_time",post.post_time);
            postObject.put("watch_count",post.watch_count);
            postObject.put("category",post.category);
            int user_id = post.user_id;
            User user = userService.findUserById(user_id);
            postObject.put("user_img_url",user.user_img_url);
            postObject.put("user_nickname",user.user_nickname);
            jsonArray.add(postObject);
        });
        jsonObject.put("list",jsonArray);

        //右侧栏的分类
        List<String> categorys = forumService.selectTopTenCategory();
        JSONArray categoryArray = new JSONArray();
        categorys.forEach(category ->{
            categoryArray.add(category);
        });
        jsonObject.put("categorys",categoryArray);

        return jsonObject.toString();
    }
}
