package com.springboot.model;

import java.util.Date;

public class User
{
  private Integer id;
  private String source;
  private String photo;
  private String name;
  private String password;
  private String phone;
  private Date createTime;
  private String description;
  private String birthday;
  private Integer sex;
  
  private Integer  attentionCount;
  
  private Integer openCount;
  
  private Integer publishCount;
  
  public Integer getAttentionCount() {
	return attentionCount;
}

public void setAttentionCount(Integer attentionCount) {
	this.attentionCount = attentionCount;
}

public Integer getOpenCount() {
	return openCount;
}

public void setOpenCount(Integer openCount) {
	this.openCount = openCount;
}

public Integer getPublishCount() {
	return publishCount;
}

public void setPublishCount(Integer publishCount) {
	this.publishCount = publishCount;
}

public String getPhoto()
  {
    return this.photo;
  }
  
  public void setPhoto(String photo)
  {
    this.photo = photo;
  }
  
  public String getSource()
  {
    return this.source;
  }
  
  public void setSource(String source)
  {
    this.source = source;
  }
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public Date getCreateTime()
  {
    return this.createTime;
  }
  
  public void setCreateTime(Date createTime)
  {
    this.createTime = createTime;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public void setDescription(String description)
  {
    this.description = description;
  }
  
  public String getBirthday()
  {
    return this.birthday;
  }
  
  public void setBirthday(String birthday)
  {
    this.birthday = birthday;
  }
  
  public Integer getSex()
  {
    return this.sex;
  }
  
  public void setSex(Integer sex)
  {
    this.sex = sex;
  }
}
