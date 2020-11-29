package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  private long id;
  private String name;
  private double price;
  private String category;
  private long pnum;
  private String imgurl;
  private String description;

}
