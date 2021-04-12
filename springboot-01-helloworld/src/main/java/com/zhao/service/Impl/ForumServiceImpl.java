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
}
