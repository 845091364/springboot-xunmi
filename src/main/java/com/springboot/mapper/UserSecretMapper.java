package com.springboot.mapper;

import com.springboot.model.OpenSecret;
import com.springboot.model.Questions;
import com.springboot.model.QuestionsAnswer;
import com.springboot.model.UserSecret;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public  interface UserSecretMapper
{
  public  int insert(UserSecret paramUserSecret);
  
  public  UserSecret getUserSecret(List<OpenSecret> list);
  
  public  List<OpenSecret> getOpenSecret(OpenSecret paramOpenSecret);
  
  public  void insertOpenSecret(OpenSecret paramOpenSecret);
  
  public  List<Questions> getQuestions();
  
  public  List<QuestionsAnswer> getAnswer(Integer id);
  
  public  List<HashMap<String, Object>> getTop(@Param(value="type") Integer type);
  
  public  List<HashMap<String, Object>> getPublish(Integer userId);
  
  public  List<HashMap<String, Object>> getOpenSecretAll(Integer userId);
  
  public  UserSecret  getBySecretId(Integer secretId);
  
  public  void deleteBySecretId(Integer secretId);
  
  public  void deleteByOpenSecret(Integer secretId);
  
  
}
