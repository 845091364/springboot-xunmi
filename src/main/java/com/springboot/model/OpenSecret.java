package com.springboot.model;

import java.util.Date;

public class OpenSecret
{
  private Integer id;
  private Integer secretId;
  private Integer userId;
  private Date createTime;
  private Integer picId;
  public Integer getPicId() {
	return picId;
}

public void setPicId(Integer picId) {
	this.picId = picId;
}

public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public Integer getSecretId()
  {
    return this.secretId;
  }
  
  public void setSecretId(Integer secretId)
  {
    this.secretId = secretId;
  }
  
  public Integer getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(Integer userId)
  {
    this.userId = userId;
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
