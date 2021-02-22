package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    public int id;
    public String name;
    public String place;
    public double price;
    public String details;
    public int like_number;
    public String img_url;
    public String img_details;
    public String add_time;
    public int merchant_id;
}
