package com.springboot.model;

public class QuestionsAnswer
{
  private Integer id;
  private String questionsId;
  private String answer;
  private Integer status;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getQuestionsId()
  {
    return this.questionsId;
  }
  
  public void setQuestionsId(String questionsId)
  {
    this.questionsId = questionsId;
  }
  
  public String getAnswer()
  {
    return this.answer;
  }
  
  public void setAnswer(String answer)
  {
    this.answer = answer;
  }
  
  public Integer getStatus()
  {
    return this.status;
  }
  
  public void setStatus(Integer status)
  {
    this.status = status;
  }
}
