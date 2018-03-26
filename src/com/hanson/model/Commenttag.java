package com.hanson.model;

import java.io.Serializable;
import java.util.Date;

public class Commenttag implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String tagcontent;
	private Date createtime;
	private Integer flag;
	private Integer starlevel;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTagcontent() {
		return tagcontent;
	}
	public void setTagcontent(String tagcontent) {
		this.tagcontent = tagcontent;
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
	public Integer getStarlevel() {
		return starlevel;
	}
	public void setStarlevel(Integer starlevel) {
		this.starlevel = starlevel;
	}
	
}
