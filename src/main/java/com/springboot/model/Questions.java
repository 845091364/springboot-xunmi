package com.springboot.model;

import java.util.List;

public class Questions
{
  private Integer id;
  private String title;
  private List<QuestionsAnswer> questionsAnswer;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public List<QuestionsAnswer> getQuestionsAnswer()
  {
    return this.questionsAnswer;
  }
  
  public void setQuestionsAnswer(List<QuestionsAnswer> questionsAnswer)
  {
    this.questionsAnswer = questionsAnswer;
  }
}
