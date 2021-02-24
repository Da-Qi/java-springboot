package com.zhao.mapper;

import com.zhao.pojo.Limo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LimoMapper {
    List<Limo> selectAll();

    //请求不同类型的房车，配合分页
    List<Limo> selectByType(String type);

    //请求首页的人气排行房车
    List<Limo> getLimoPopularity();

    //请求首页的最新房车
    List<Limo> getLimoNewest();

    //请求首页的主题房车
    List<Limo> getLimoTheme();

    Limo getLimoDetails(int id);

    //增加房车的喜欢数
    void addLimoLikeNumber(int id);

    //减少房车的喜欢数
    void reduceLimoLikeNumber(int id);

    //请求侧边栏的推荐房车
    List<Limo> getLimoRecommend();
}
