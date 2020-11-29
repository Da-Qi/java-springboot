package com.zhao.mapper;

import com.zhao.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    /**
     * 添加订单
     */
    void addOrder(Order order);

    /**
     * 通过用户id查找订单
     */
    List<Order> findOrdersByUserId(@Param("user_id") String userid);

    /**
     * 通过订单编号查找订单
     */
    Order findOrderByOrderId(String id);

    /**
     * 查询所有订单
     */
    List<Order> findAllOrders();
}
