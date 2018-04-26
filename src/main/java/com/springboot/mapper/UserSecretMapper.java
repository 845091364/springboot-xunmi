package com.springboot.mapper;

import com.springboot.model.OpenSecret;
import com.springboot.model.Questions;
import com.springboot.model.QuestionsAnswer;
import com.springboot.model.UserSecret;

import java.util.HashMap;
import java.util.List;

public  interface UserSecretMapper
{
  public  int insert(UserSecret paramUserSecret);
  
  public  UserSecret getUserSecret(List<OpenSecret> list);
  
  public  List<OpenSecret> getOpenSecret(OpenSecret paramOpenSecret);
  
  public  void insertOpenSecret(OpenSecret paramOpenSecret);
  
  public  List<Questions> getQuestions();
  
  public  List<QuestionsAnswer> getAnswer(int paramInt);
  
  public  List<HashMap<String, Object>> getTop();
  
}
