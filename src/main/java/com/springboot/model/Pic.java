package com.springboot.model;

import java.util.Date;

public class Pic {
	private String id;
	private String pic;
	private String oldPic;
	private Date createTime;
	public String getId() {
		return id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getOldPic() {
		return oldPic;
	}
	public void setOldPic(String oldPic) {
		this.oldPic = oldPic;
	}
}
