package com.zhao;

import com.zhao.mapper.LimoMapper;
import com.zhao.pojo.Limo;
import com.zhao.pojo.Merchant;
import com.zhao.pojo.PageRequest;
import com.zhao.pojo.PageResult;
import com.zhao.service.LimoService;
import com.zhao.service.MerchantService;
import com.zhao.service.RouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Springboot01HelloworldApplicationTests {

    @Autowired
    public LimoMapper limoMapper;
    @Test
    void contextLoads() {
        List<Limo> limos = limoMapper.selectAll();
        limos.forEach(limo -> System.out.println(limo));
    }

    @Autowired
    public LimoService limoService;
    @Test
    void test1() {
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageSize(4);
        pageRequest.setPageNum(0);
        PageResult pageResult = limoService.selectLimo("THEME", pageRequest);
        pageResult.getContent().forEach(limo -> System.out.println(limo));
    }


    @Autowired
    public MerchantService merchantService;
    @Test
    void test2() {
        List<Merchant> merchants = merchantService.selectAll();
        merchants.forEach(merchant -> System.out.println(merchant));
    }

    @Autowired
    public RouteService routeService;
    @Test
    void test3() {
       
    }
}
