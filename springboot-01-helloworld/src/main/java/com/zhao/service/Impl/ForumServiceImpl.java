package com.zhao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhao.mapper.ForumMapper;
import com.zhao.pojo.Comment;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.pojo.Post;
import com.zhao.service.ForumService;
import com.zhao.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumMapper forumMapper;
    @Override
    public PageResult selectPostByPageQuery(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Post> posts = forumMapper.selectPostByPageQuery();
        return PageUtils.getPageResult(new PageInfo<>(posts));
    }

    @Override
    public PageResult selectPostByCategory(PageRequest pageRequest, String searchKey) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Post> posts = forumMapper.selectPostByCategory(searchKey);
        return PageUtils.getPageResult(new PageInfo<>(posts));
    }

    @Override
    public List<String> selectTopTenCategory() {
        return forumMapper.selectTopTenCategory();
    }

    @Override
    public Post selectPostById(int id) {
        return forumMapper.selectPostById(id);
    }

    @Override
    public List<Comment> selectCommentsByPostId(int post_id) {
        return forumMapper.selectCommentsByPostId(post_id);
    }

    @Override
    public int selectDepthOfComment(int post_id) {
        return forumMapper.selectMaxDepth(post_id);
    }

    @Override
    public void addComment(HashMap<String, Object> map) {
        forumMapper.addComment(map);
    }

    @Override
    public void addWatchCount(int post_id) {
        forumMapper.addWatchCount(post_id);
    }

    @Override
    public boolean commentPraise(int comment_id, int liked_user_id, int like_user_id) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("like_user_id", like_user_id);
        map.put("liked_user_id", liked_user_id);
        map.put("comment_id", comment_id);
        map.put("state", 0);

        //先判断是否赞过这个人的评论
        int count = forumMapper.selectPraiseIfExists(map);

        if (count == 0){
            forumMapper.commentPraise(map);

            //评论的点赞数+1
            forumMapper.addCommentPraiseCount(comment_id);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean commentReport(int comment_id, int reported_user_id, int report_user_id, String reason) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("report_user_id", report_user_id);
        map.put("reported_user_id", reported_user_id);
        map.put("comment_id", comment_id);
        map.put("state", 0);
        map.put("reason", reason);

        //先判断是否举报过这个人的评论
        int count = forumMapper.selectReportIfExists(map);

        if (count == 0){
            forumMapper.commentReport(map);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void reduceCommentPraiseCount(int comment_id) {
        forumMapper.reduceCommentPraiseCount(comment_id);
    }

    @Override
    public int getCommentPraiseCount(int comment_id) {
        return forumMapper.getCommentPraiseCount(comment_id);
    }

}
