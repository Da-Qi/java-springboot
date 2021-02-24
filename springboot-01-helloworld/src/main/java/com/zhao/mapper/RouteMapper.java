package com.zhao.mapper;

import com.zhao.pojo.PageResult;
import com.zhao.pojo.Route;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RouteMapper {

    List<Route> selectRouteByPlace(String place);

    Route getRouteDetails(int id);

    List<Route> selectRouteFavoriteRank();

    void addRouteLikeNumber(int id);

    void reduceRouteLikeNumber(int id);
}
