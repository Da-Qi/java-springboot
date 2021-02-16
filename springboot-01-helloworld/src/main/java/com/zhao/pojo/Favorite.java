package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    public int id;
    public String limo_id;
    public String route_id;
    public int user_id;
    public String collect_time;
}
