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

    void addWatchCount(int post_id);

    boolean commentPraise(int comment_id, int liked_user_id, int like_user_id);

    boolean commentReport(int comment_id, int reported_user_id, int report_user_id, String reason);

    void reduceCommentPraiseCount(int comment_id);

    int getCommentPraiseCount(int comment_id);
}
