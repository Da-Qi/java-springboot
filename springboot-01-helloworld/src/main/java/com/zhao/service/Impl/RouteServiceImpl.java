package com.zhao.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhao.mapper.FavoriteMapper;
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

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public PageResult selectRouteByPlace(String place, PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Route> routes = routeMapper.selectRouteByPlace(place);
        PageResult pageResult = PageUtils.getPageResult(new PageInfo<>(routes));
        return pageResult;
    }

    @Override
    public Route getRouteDetails(int id) {
        return routeMapper.getRouteDetails(id);
    }

    @Override
    public PageResult selectRouteFavoriteRank(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<Route> routes = routeMapper.selectRouteFavoriteRank();
        PageResult pageResult = PageUtils.getPageResult(new PageInfo<>(routes));
        return pageResult;
    }

    @Override
    public boolean ifRouteFavorite(int id, int user_id) {
        String favorite = favoriteMapper.getUserRouteFavorite(user_id);
        if (favorite == null) {
            return false;
        } else {
            String[] split = favorite.split(";");
            boolean flag = false;
            for (String s : split) {
                if (s.equals(String.valueOf(id))){
                    flag = true;
                }
            }
            return flag;
        }
    }

    @Override
    public void addRouteFavorite(int id, int user_id) {
        String routes = favoriteMapper.getUserRouteFavorite(user_id);
        if (routes == null) {
            //查看用户是否存在于收藏表中
            int i = favoriteMapper.ifUserExists(user_id);
            routes = String.valueOf(id);
            if (i == 0) {
                //用户不存在，插入数据
                //该用户还没有开始收藏房车
                favoriteMapper.insertRoutes(routes, user_id);
            } else {
                //用户已经存在，更新即可
                favoriteMapper.updateRoutes(routes, user_id);
            }
        } else {
            //已经收藏了
            routes += ";" + id;
            favoriteMapper.updateRoutes(routes, user_id);
        }
        //路线收藏数量发生变更
        routeMapper.addRouteLikeNumber(id);
    }

    @Override
    public void removeFavorite(int id, int user_id) {
        String routes = favoriteMapper.getUserRouteFavorite(user_id);
        String[] split = routes.split(";");
        String[] new_splits = new String[split.length - 1];
        int t = 0;
        for (int i = 0; i < split.length; i++) {
            if (Integer.parseInt(split[i]) != id){
                new_splits[t] = split[i];
                t++;
            }
        }
        String new_routes = String.join(";", new_splits);
        favoriteMapper.updateRoutes(new_routes, user_id);

        //房车的相应收藏数发生变更
        routeMapper.reduceRouteLikeNumber(id);
    }

    @Override
    public List<Route> selectIndexRoutes() {
        return routeMapper.selectIndexRoutes();
    }

}
