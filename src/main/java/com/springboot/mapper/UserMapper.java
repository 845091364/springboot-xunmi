package com.springboot.mapper;

import com.springboot.model.User;
import com.springboot.model.UserAttention;
import java.util.List;

public  interface UserMapper
{
  public  int deleteByPrimaryKey(Integer paramInteger);
  
  public  int insert(User paramUser);
  
  public  int insertSelective(User paramUser);
  
  public  User selectByKey(User paramUser);
  
  public  int updateByPrimaryKey(User paramUser);
  
  public  List<User> getUsers();
  
  public  List<User> getUserAttention(String paramString);
  
  public  int insertUserAttention(UserAttention paramUserAttention);
  
  public  int deleteUserAttention(UserAttention paramUserAttention);
  
  public  UserAttention findUserAttention(UserAttention paramUserAttention);
}
