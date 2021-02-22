package com.zhao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhao.mapper.RouteMapper;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.pojo.Route;
import com.zhao.service.RouteService;
import com.zhao.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteMapper routeMapper;

    @Override
    public PageResult selectRouteByPlace(String place, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Route> routes =  routeMapper.selectRouteByPlace(place);
        PageResult pageResult = PageUtils.getPageResult(new PageInfo<>(routes));
        return pageResult;
    }
}
