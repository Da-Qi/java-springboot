package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//房车
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Limo {
    public int id;
    public String name;
    public Double rent;
    public String img_url;
    public int merchant_id;
    public String details;
    public LimoType type;
    public int like_number;
    public String add_time;
}
