package com.zhao.controller;

import com.zhao.pojo.*;
import com.zhao.service.MerchantService;
import com.zhao.service.RouteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class RouteController {
    @Autowired
    private RouteService routeService;

    @Autowired
    private MerchantService merchantService;

    //查询不同地区的的房车列表
    @RequestMapping(value = "/route/pageQuery", produces = "application/json;charset=utf-8")
    public String limoPageQuery(HttpServletRequest request){
        String currentPage = request.getParameter("currentPage");
        if (currentPage.equals("")){
            currentPage = "1";
        }
        String name = request.getParameter("name");
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(Integer.parseInt(currentPage));
        // 每页显示6条数据
        pageRequest.setPageSize(6);
        // 获得查询结果
        PageResult pageResult = routeService.selectRouteByPlace(name, pageRequest);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalPage",pageResult.getTotalPages());
        jsonObject.put("totalCount",pageResult.getTotalSize());
        jsonObject.put("currentPage",pageResult.getPageNum());

        JSONArray jsonArray = new JSONArray();
        List<Route> result = pageResult.getContent();
        result.forEach(route -> {
            JSONObject limoObject = new JSONObject();
            limoObject.put("image",route.img_url);
            limoObject.put("name",route.name);
            limoObject.put("details",route.details);
            limoObject.put("price",route.price);
            limoObject.put("id",route.id);
            jsonArray.add(limoObject);
        });
        jsonObject.put("list",jsonArray);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/route/{place}/{id}", produces = "application/json;charset=utf-8")
    public String getRouteDetails(@PathVariable String place, @PathVariable int id) {
        JSONObject jsonObject = new JSONObject();
        Route route = routeService.getRouteDetails(id);
        //确定面包屑的值
        switch (place) {
            case "beijing":
                jsonObject.put("breadcrumb", "北京");
                break;
            case "global":
                jsonObject.put("breadcrumb", "全球自由行");
                break;
            case "hongkong":
                jsonObject.put("breadcrumb", "香港");
                break;
            case "macao":
                jsonObject.put("breadcrumb", "澳门");
                break;
            case "northAmerican":
                jsonObject.put("breadcrumb", "北美");
                break;
            case "team":
                jsonObject.put("breadcrumb", "组队游");
                break;
            case "yunnan":
                jsonObject.put("breadcrumb", "云南");
                break;
            case "favoriterank":
                jsonObject.put("breadcrumb", "收藏排行榜");
                break;
            default:
                break;
        }
        jsonObject.put("name", route.name);
        jsonObject.put("img_url", route.img_url);
        jsonObject.put("like_number", route.like_number);
        jsonObject.put("rent", route.price);
        jsonObject.put("details", route.details);
        jsonObject.put("img_details", route.img_details);
        int merchant_id = route.merchant_id;
        Merchant merchant = merchantService.selectById(merchant_id);
        jsonObject.put("merchant_name", merchant.name);
        jsonObject.put("telephone", merchant.telephone);
        jsonObject.put("place", merchant.place);
        return jsonObject.toString();
    }


    @RequestMapping(value = "/route/favoriterank", produces = "application/json;charset=utf-8")
    public String limoFavoriteRank(HttpServletRequest request){
        String currentPage = request.getParameter("currentPage");
        if (currentPage==null || currentPage.equals("")){
            currentPage = "1";
        }
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(Integer.parseInt(currentPage));
        // 每页显示6条数据
        pageRequest.setPageSize(6);
        // 获得查询结果
        PageResult pageResult = routeService.selectRouteFavoriteRank(pageRequest);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalPage",pageResult.getTotalPages());
        jsonObject.put("totalCount",pageResult.getTotalSize());
        jsonObject.put("currentPage",pageResult.getPageNum());

        JSONArray jsonArray = new JSONArray();
        List<Route> result = pageResult.getContent();
        result.forEach(route -> {
            JSONObject limoObject = new JSONObject();
            limoObject.put("image",route.img_url);
            limoObject.put("name",route.name);
            limoObject.put("details",route.details);
            limoObject.put("price",route.price);
            limoObject.put("id",route.id);
            limoObject.put("like_number",route.like_number);
            jsonArray.add(limoObject);
        });
        jsonObject.put("list",jsonArray);
        return jsonObject.toString();
    }

    @RequestMapping(value = "/route/isFavorite", produces = "application/json;charset=utf-8")
    public boolean ifLimoFavorite(@Param("id") int id, HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            return routeService.ifRouteFavorite(id, user.user_id);
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value = "/route/addFavorite", produces = "application/json;charset=utf-8")
    public boolean addFavorite(@Param("id") int id, HttpServletRequest request) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            System.out.println(user);
            routeService.addRouteFavorite(id, user.user_id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value = "/route/removeFavorite", produces = "application/json;charset=utf-8")
    public void removeFavorite(@Param("id") int id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        routeService.removeFavorite(id, user.user_id);
    }
}
