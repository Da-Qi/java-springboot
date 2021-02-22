package com.zhao.controller;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.zhao.pojo.*;
import com.zhao.service.LimoService;
import com.zhao.service.MerchantService;
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
public class LimoController {
    @Autowired
    private LimoService limoService;

    @Autowired
    private MerchantService merchantService;

    @RequestMapping(value = "/limo/popularity", produces = "application/json;charset=utf-8")
    public String getLimoPopularity(){
        JSONArray array = new JSONArray();
        List<Limo> limos = limoService.getLimoPopularity();
        limos.forEach(limo -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",limo.id);
            jsonObject.put("name",limo.name);
            jsonObject.put("rent",limo.rent);
            jsonObject.put("like_number",limo.like_number);
            jsonObject.put("img_url",limo.img_url);
            array.add(jsonObject);
        });
        return array.toString();
    }

    @RequestMapping(value = "/limo/newest", produces = "application/json;charset=utf-8")
    public String getLimoNewest(){
        JSONArray array = new JSONArray();
        List<Limo> limos = limoService.getLimoNewest();
        limos.forEach(limo -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",limo.id);
            jsonObject.put("name",limo.name);
            jsonObject.put("rent",limo.rent);
            jsonObject.put("like_number",limo.like_number);
            jsonObject.put("img_url",limo.img_url);
            array.add(jsonObject);
        });
        return array.toString();
    }

    @RequestMapping(value = "/limo/theme", produces = "application/json;charset=utf-8")
    public String getLimoTheme(){
        JSONArray array = new JSONArray();
        List<Limo> limos = limoService.getLimoTheme();
        limos.forEach(limo -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",limo.id);
            jsonObject.put("name",limo.name);
            jsonObject.put("rent",limo.rent);
            jsonObject.put("like_number",limo.like_number);
            jsonObject.put("img_url",limo.img_url);
            array.add(jsonObject);
        });
        return array.toString();
    }

    @RequestMapping(value = "/limo/index", produces = "application/json;charset=utf-8")
    public String getLimoDetails(@Param("id") int id){
        JSONObject jsonObject = new JSONObject();
        Limo limo = limoService.getLimoDetails(id);
        jsonObject.put("breadcrumb","首页");
        jsonObject.put("name",limo.name);
        jsonObject.put("img_url",limo.img_url);
        jsonObject.put("like_number",limo.like_number);
        jsonObject.put("rent",limo.rent);
        jsonObject.put("details",limo.details);
        jsonObject.put("img_details",limo.img_details);
        int merchant_id = limo.merchant_id;
        Merchant merchant = merchantService.selectById(merchant_id);
        jsonObject.put("merchant_name",merchant.name);
        jsonObject.put("telephone",merchant.telephone);
        jsonObject.put("place",merchant.place);
        return jsonObject.toString();
    }


    @RequestMapping(value = "/limo/isFavorite", produces = "application/json;charset=utf-8")
    public boolean ifLimoFavorite(@Param("id") int id,HttpServletRequest request){
        try {
            User user = (User) request.getSession().getAttribute("user");
            return limoService.ifLimoFavorite(id,user.user_id);
        }catch (Exception e){
            System.out.println("需要用户回到首页");
            return false;
        }
    }

    @RequestMapping(value = "/limo/addFavorite", produces = "application/json;charset=utf-8")
    public void addFavorite(@Param("id") int id,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        limoService.addFavorite(id,user.user_id);
    }

    @RequestMapping(value = "/limo/removeFavorite", produces = "application/json;charset=utf-8")
    public void removeFavorite(@Param("id") int id,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        limoService.removeFavorite(id,user.user_id);
    }

    //查询不同类型的房车列表
    @RequestMapping(value = "/limo/pageQuery", produces = "application/json;charset=utf-8")
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
        PageResult pageResult = limoService.selectLimo(name, pageRequest);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalPage",pageResult.getTotalPages());
        jsonObject.put("totalCount",pageResult.getTotalSize());
        jsonObject.put("currentPage",pageResult.getPageNum());

        JSONArray jsonArray = new JSONArray();
        List<Limo> result = pageResult.getContent();
        result.forEach(limo -> {
            JSONObject limoObject = new JSONObject();
            limoObject.put("image",limo.img_url);
            limoObject.put("name",limo.name);
            limoObject.put("details",limo.details);
            limoObject.put("price",limo.rent);
            limoObject.put("id",limo.id);
            jsonArray.add(limoObject);
        });
        jsonObject.put("list",jsonArray);
        return jsonObject.toString();
    }
}
