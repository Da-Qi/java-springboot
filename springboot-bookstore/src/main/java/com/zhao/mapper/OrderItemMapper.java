package com.zhao.mapper;

import com.zhao.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface OrderItemMapper {
    /**
     * 订单详情传入数据库
     */
    void addItems(List<OrderItem> items);
}
