package com.springboot.model;

import java.util.Date;

public class UserAttention
{
  private Integer id;
  private Integer userId;
  private Integer attentionId;
  private Date createTime;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
  }
  
  public Integer getAttentionId()
  {
    return this.attentionId;
  }
  
  public void setAttentionId(Integer attentionId)
  {
    this.attentionId = attentionId;
  }
  
  public Date getCreateTime()
  {
    return this.createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
}
