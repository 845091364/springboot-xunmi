package com.springboot.model;

import java.util.Date;

public class CommentSecret {
	private Integer id;
	private String content;
	private Date createTime;
	private Integer userId;
	private Integer secretId;
	private Integer commentId;
	private Integer commentCount;
	private Integer userToUserId;
	private String address;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getUserToUserId() {
		return userToUserId;
	}
	public void setUserToUserId(Integer userToUserId) {
		this.userToUserId = userToUserId;
	}
	private Integer praiseStatus;
	public Integer getPraiseStatus() {
		return praiseStatus;
	}
	public void setPraiseStatus(Integer praiseStatus) {
		this.praiseStatus = praiseStatus;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	private Integer praise;
	
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
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
