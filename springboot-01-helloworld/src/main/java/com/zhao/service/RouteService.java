package com.zhao.service;

import com.zhao.pojo.Limo;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.pojo.Route;

import java.util.List;

public interface RouteService {
    PageResult selectRouteByPlace(String place, PageRequest pageRequest);

    Route getRouteDetails(int id);

    PageResult selectRouteFavoriteRank(PageRequest pageRequest);

    boolean ifRouteFavorite(int id, int user_id);

    void addRouteFavorite(int id, int user_id);

    void removeFavorite(int id, int user_id);

    List<Route> selectIndexRoutes();
}
