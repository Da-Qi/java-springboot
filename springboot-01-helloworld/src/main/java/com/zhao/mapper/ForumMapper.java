package com.zhao.mapper;

import com.zhao.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ForumMapper {
    List<Post> selectPostByPageQuery();

    List<Post> selectPostByCategory(String category);

    List<String> selectTopTenCategory();
}
