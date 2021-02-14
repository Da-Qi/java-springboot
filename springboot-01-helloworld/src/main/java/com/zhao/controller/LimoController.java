package com.zhao.controller;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.zhao.pojo.Limo;
import com.zhao.pojo.Merchant;
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
}
