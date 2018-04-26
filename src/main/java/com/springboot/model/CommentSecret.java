package com.springboot.model;

import java.util.Date;

public class CommentSecret {
	private Integer id;
	private String content;
	private Date createTime;
	private Integer userId;
	private Integer secretId;
	public Integer getSecretId() {
		return secretId;
	}
	public void setSecretId(Integer secretId) {
		this.secretId = secretId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
