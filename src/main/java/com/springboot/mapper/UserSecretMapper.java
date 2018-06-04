package com.springboot.mapper;

import com.springboot.model.OpenSecret;
import com.springboot.model.UserSecret;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public  interface UserSecretMapper
{
  public  int insert(UserSecret paramUserSecret);
  
  public  List<HashMap<String, Object>> getUserSecretByCreateTimeDesc();
  
  public  List<HashMap<String, Object>> getUserSecretByTop(HashMap<String, Object> map);
  
  public  List<OpenSecret> getOpenSecret(OpenSecret paramOpenSecret);
  
  public  void insertOpenSecret(OpenSecret paramOpenSecret);
  
  public  List<HashMap<String, Object>> getTop(@Param(value="type") Integer type);
  
  public  List<HashMap<String, Object>> getPublish(Integer userId);
  
  public  List<HashMap<String, Object>> getOpenSecretAll(Integer userId);
  
  public  UserSecret  getBySecretId(Integer secretId);
  
  public  void deleteBySecretId(Integer secretId);
  
  public  void deleteByOpenSecret(Integer secretId);
  
  public void updateComment(Integer  secretId);
  
  public void updatePraise(Integer  secretId);
  
  
  
}
