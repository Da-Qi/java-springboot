package com.zhao.controller;

import com.zhao.pojo.Limo;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.pojo.Route;
import com.zhao.service.RouteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class RouteController {
    @Autowired
    private RouteService routeService;
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
}
