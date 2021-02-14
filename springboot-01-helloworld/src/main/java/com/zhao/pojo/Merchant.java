package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {
    public int id;
    public String name;
    public String telephone;
    public String place;
    public String date;
    public String details;
}
