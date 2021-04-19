package com.zhao.mapper;

import com.zhao.pojo.Comment;
import com.zhao.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface ForumMapper {
    List<Post> selectPostByPageQuery();

    List<Post> selectPostByCategory(String category);

    List<String> selectTopTenCategory();

    Post selectPostById(int id);

    List<Comment> selectCommentsByPostId(int post_id);

    int selectMaxDepth(int post_id);

    void addComment(HashMap<String, Object> map);

    void addWatchCount(int post_id);

    void commentPraise(HashMap<String, Object> map);

    void addCommentPraiseCount(int comment_id);

    int selectPraiseIfExists(HashMap<String, Object> map);

    int selectReportIfExists(HashMap<String, Object> map);

    void commentReport(HashMap<String, Object> map);

    void reduceCommentPraiseCount(int comment_id);

    int getCommentPraiseCount(int comment_id);

    int getAllComments();

    void addPost(HashMap<String, String> map);
}
