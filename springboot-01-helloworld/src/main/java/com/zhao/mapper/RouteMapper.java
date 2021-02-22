package com.zhao.mapper;

import com.zhao.pojo.Route;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RouteMapper {

    List<Route> selectRouteByPlace(String place);
}
