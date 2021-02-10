package com.zhao;

import com.zhao.mapper.LimoMapper;
import com.zhao.pojo.Limo;
import com.zhao.pojo.PageRequest;
import com.zhao.service.LimoService;
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
        List<Limo> limos = limoService.selectLimo("GENERAL",pageRequest);
        limos.forEach(limo -> System.out.println(limo));
    }

}
