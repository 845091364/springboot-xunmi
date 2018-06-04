package com.springboot.model;

public class PraiseSecret {
	private Integer id;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
