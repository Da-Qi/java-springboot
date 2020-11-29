package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private String id;
  private double money;
  private String receiverAddress;
  private String receiverName;
  private String receiverPhone;
  private long paystate;
  private java.sql.Timestamp ordertime;

  //外键关系，设置一个对象
  private User user;

  //便于传递订单中详情参数
  private List<OrderItem> items;

}
