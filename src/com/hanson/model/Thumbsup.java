package com.hanson.model;

import java.io.Serializable;

public class Thumbsup  implements Serializable  {
	private static final long serialVersionUID = 1L;
	private Integer id; 
	private Integer commentid;
	private Integer userid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCommentid() {
		return commentid;
	}
	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	
}
