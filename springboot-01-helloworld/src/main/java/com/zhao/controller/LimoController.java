package com.zhao.controller;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.zhao.pojo.Limo;
import com.zhao.service.LimoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class LimoController {
    @Autowired
    private LimoService limoService;

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
}
