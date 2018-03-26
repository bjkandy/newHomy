package com.hanson.model;

import java.io.Serializable;
import java.util.Date;

public class Replycontent implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer qyid;
	private String content;
	
	private Date createtime;
	private Integer flag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQyid() {
		return qyid;
	}
	public void setQyid(Integer qyid) {
		this.qyid = qyid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
	
}
