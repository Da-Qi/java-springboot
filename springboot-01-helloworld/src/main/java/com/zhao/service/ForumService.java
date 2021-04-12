package com.zhao.service;

import com.zhao.pojo.Comment;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.pojo.Post;

import java.util.HashMap;
import java.util.List;

public interface ForumService {

    PageResult selectPostByPageQuery(PageRequest pageRequest);

    PageResult selectPostByCategory(PageRequest pageRequest, String searchKey);

    List<String> selectTopTenCategory();

    Post selectPostById(int id);

    List<Comment> selectCommentsByPostId(int post_id);

    int selectDepthOfComment(int post_id);

    void addComment(HashMap<String, Object> map);
}
