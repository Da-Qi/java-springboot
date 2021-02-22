package com.zhao.service;

import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;

public interface RouteService {
    PageResult selectRouteByPlace(String place, PageRequest pageRequest);
}
