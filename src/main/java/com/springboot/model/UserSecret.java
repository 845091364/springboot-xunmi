package com.springboot.model;

import java.util.Date;

public class UserSecret
{
  private Integer id;
  private Integer top;
  private String content;
  private String position;
  public String getPosition() {
	return position;
}

public void setPosition(String position) {
	this.position = position;
}

private String pic;
  public String getPic() {
	return pic;
}

public void setPic(String pic) {
	this.pic = pic;
}

public Integer getTop() {
	return top;
}

public void setTop(Integer top) {
	this.top = top;
}

private Integer userId;
  private Date createTime;
  private Integer openCount;
  private Integer commentCount;
  private Integer praiseCount;
  public Integer getOpenCount() {
	return openCount;
}

public void setOpenCount(Integer openCount) {
	this.openCount = openCount;
}

public Integer getCommentCount() {
	return commentCount;
}

public void setCommentCount(Integer commentCount) {
	this.commentCount = commentCount;
}

public Integer getPraiseCount() {
	return praiseCount;
}

public void setPraiseCount(Integer praiseCount) {
	this.praiseCount = praiseCount;
}

public String getLabel() {
	return label;
}

public void setLabel(String label) {
	this.label = label;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

private String label;
  private String title;
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getContent()
  {
    return this.content;
  }
  
  public void setContent(String content)
  {
    this.content = content;
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
