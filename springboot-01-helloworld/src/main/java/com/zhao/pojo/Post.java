package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    public int id;
    public String title;
    public String description;
    public String body;
    public int user_id;
    public int watch_count;
    public String post_time;
    public String category;
}
