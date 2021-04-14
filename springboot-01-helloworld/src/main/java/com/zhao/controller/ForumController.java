package com.zhao.controller;

import com.zhao.pojo.*;
import com.zhao.service.ForumService;
import com.zhao.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
public class ForumController {
    @Autowired
    private ForumService forumService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/forum/{currentPage}/{searchKey}", produces = "application/json;charset=utf-8")
    public String limoFavoriteRank(HttpServletRequest request, @PathVariable String currentPage, @PathVariable String searchKey) {
        if (currentPage == null || currentPage.equals("")) {
            currentPage = "1";
        }
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(Integer.parseInt(currentPage));
        // 每页显示6条数据
        pageRequest.setPageSize(6);
        PageResult pageResult = null;
        if (searchKey == null || searchKey.equals("null")) {
            // 获得查询结果
            pageResult = forumService.selectPostByPageQuery(pageRequest);
        } else {
            pageResult = forumService.selectPostByCategory(pageRequest, searchKey);
        }


        JSONObject jsonObject = new JSONObject();
        //右侧栏信息相关
        int user_num = userService.selectAllUser();
        jsonObject.put("user_num", user_num);

        //论坛内容相关
        jsonObject.put("totalPage", pageResult.getTotalPages());
        jsonObject.put("totalCount", pageResult.getTotalSize());
        jsonObject.put("currentPage", pageResult.getPageNum());

        JSONArray jsonArray = new JSONArray();
        List<Post> result = pageResult.getContent();
        result.forEach(post -> {
            JSONObject postObject = new JSONObject();
            postObject.put("id", post.id);
            postObject.put("title", post.title);
            postObject.put("description", post.description);
            postObject.put("post_time", post.post_time);
            postObject.put("watch_count", post.watch_count);
            postObject.put("category", post.category);
            int user_id = post.user_id;
            User user = userService.findUserById(user_id);
            postObject.put("user_img_url", user.user_img_url);
            postObject.put("user_nickname", user.user_nickname);
            jsonArray.add(postObject);
        });
        jsonObject.put("list", jsonArray);

        //右侧栏的分类
        List<String> categorys = forumService.selectTopTenCategory();
        JSONArray categoryArray = new JSONArray();
        categorys.forEach(category -> {
            categoryArray.add(category);
        });
        jsonObject.put("categorys", categoryArray);

        return jsonObject.toString();
    }

    @RequestMapping(value = "/forumDetails/{id}", produces = "application/json;charset=utf-8")
    public String forumDetail(@PathVariable String id) {
        int post_id = Integer.parseInt(id);
        Post post = forumService.selectPostById(post_id);
        // 查询用户信息
        int user_id = post.user_id;
        User user = userService.findUserById(user_id);

        String body = post.body;
        String category = post.category;
        String post_time = post.post_time;
        String title = post.title;
        int watch_count = post.watch_count;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("user_img_url", user.user_img_url);
        jsonObject.put("user_nickname", user.user_nickname);
        jsonObject.put("category", category);
        jsonObject.put("watch_count", watch_count);
        jsonObject.put("body", body);
        jsonObject.put("post_time", post_time);

        List<Comment> comments = forumService.selectCommentsByPostId(post_id);

        JSONArray jsonArray = new JSONArray();
        for (Comment comment : comments) {
            JSONObject json = new JSONObject();
            json.put("id", comment.id);
            json.put("body", comment.body);
            int userId = comment.user_id;
            User userById = userService.findUserById(userId);
            json.put("user_nickname", userById.user_nickname);
            json.put("user_id", userById.user_id);
            json.put("user_img_url", userById.user_img_url);
            json.put("date", comment.date);
            json.put("depth", comment.depth);
            json.put("praise_count", comment.praise_count);

            jsonArray.add(json);
        }
        jsonObject.put("list", jsonArray);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/comment/add", produces = "application/json;charset=utf-8")
    public boolean addComment(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return false;
        } else {
            HashMap<String, Object> map = new HashMap<>();
            map.put("body", jsonObject.getString("body"));
            map.put("post_id", jsonObject.get("post_id"));
            map.put("user_id", user.user_id);

            int depth = forumService.selectDepthOfComment(Integer.parseInt(jsonObject.getString("post_id")));
            map.put("depth", depth + 1);
            forumService.addComment(map);

            return true;
        }
    }

    //文章浏览量+1
    @RequestMapping(value = "/post/addWatchCount/{id}")
    public void addWatchCount(@PathVariable String id) {
        forumService.addWatchCount(Integer.parseInt(id));
    }


    //评论点赞
    @RequestMapping(value = "/comment/praise/{comment_id}/{user_id}", produces = "application/json;charset=utf-8")
    public String commentPraise(@PathVariable String comment_id, @PathVariable String user_id, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            jsonObject.put("code", 0);
        } else {
            boolean flag = forumService.commentPraise(Integer.parseInt(comment_id), Integer.parseInt(user_id), user.user_id);

            int count = forumService.getCommentPraiseCount(Integer.parseInt(comment_id));
            if (flag) {
                jsonObject.put("code", 1);
            } else {
                jsonObject.put("code", 2);
            }
            jsonObject.put("praise_count", count);
        }
        return jsonObject.toString();
    }

    //评论点踩
    @RequestMapping(value = "/comment/tread/{comment_id}", produces = "application/json;charset=utf-8")
    public String commentTread(@PathVariable String comment_id, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            jsonObject.put("code", 0);
        } else {
            forumService.reduceCommentPraiseCount(Integer.parseInt(comment_id));
            jsonObject.put("code", 1);
            int count = forumService.getCommentPraiseCount(Integer.parseInt(comment_id));
            jsonObject.put("praise_count", count);
        }
        return jsonObject.toString();

    }

    //评论举报
    @RequestMapping(value = "/comment/report/{comment_id}/{user_id}")
    public int commentReport(@PathVariable String comment_id, @PathVariable String user_id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String reason = request.getParameter("reason");
        if (user == null) {
            return 0;
        } else {
            boolean flag = forumService.commentReport(Integer.parseInt(comment_id), Integer.parseInt(user_id), user.user_id, reason);
            return flag ? 1 : 2;
        }
    }

}
