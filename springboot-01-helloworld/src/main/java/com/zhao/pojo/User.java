package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    public int user_id;
    public String user_name;
    public String user_password;
    public String user_nickname;
    public String user_telephone;
    public String user_gender;
    public String user_img_url;
    public Date user_register_time;
    public short user_status;
}
