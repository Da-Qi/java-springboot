package com.zhao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private long id;
  private String username;
  private String password;
  private String gender;
  private String email;
  private String telephone;
  private String introduce;
  private String activeCode;
  private long state;
  private String role;
  private Date registerTime;

}
