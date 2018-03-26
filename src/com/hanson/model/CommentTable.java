package com.hanson.model;

import java.io.Serializable;
import java.util.Date;

public class CommentTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer qyid;
	private Integer productid;
	private String orderid;
	private Integer orderdetailid;
	
	private String nickname;
	private Integer userid;
	private String content;
	private Date createtime;
	private String picture;
	private Integer starlevel;
	private Integer nod;
    private String commenttab;
    private Integer replystatus;
    private String rcontent;
    private Date replytime;
    private String replypicture;
    private Integer flag;
    private Integer ishide;
    private Integer commentflag;
    
    
    private Integer commentid;


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


	public Integer getProductid() {
		return productid;
	}


	public void setProductid(Integer productid) {
		this.productid = productid;
	}


	public String getOrderid() {
		return orderid;
	}


	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	public Integer getOrderdetailid() {
		return orderdetailid;
	}


	public void setOrderdetailid(Integer orderdetailid) {
		this.orderdetailid = orderdetailid;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Integer getUserid() {
		return userid;
	}


	public void setUserid(Integer userid) {
		this.userid = userid;
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


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public Integer getStarlevel() {
		return starlevel;
	}


	public void setStarlevel(Integer starlevel) {
		this.starlevel = starlevel;
	}


	public Integer getNod() {
		return nod;
	}


	public void setNod(Integer nod) {
		this.nod = nod;
	}


	public String getCommenttab() {
		return commenttab;
	}


	public void setCommenttab(String commenttab) {
		this.commenttab = commenttab;
	}


	public Integer getReplystatus() {
		return replystatus;
	}


	public void setReplystatus(Integer replystatus) {
		this.replystatus = replystatus;
	}


	public String getRcontent() {
		return rcontent;
	}


	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}


	public Date getReplytime() {
		return replytime;
	}


	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}


	public String getReplypicture() {
		return replypicture;
	}


	public void setReplypicture(String replypicture) {
		this.replypicture = replypicture;
	}


	public Integer getFlag() {
		return flag;
	}


	public void setFlag(Integer flag) {
		this.flag = flag;
	}


	public Integer getIshide() {
		return ishide;
	}


	public void setIshide(Integer ishide) {
		this.ishide = ishide;
	}


	public Integer getCommentflag() {
		return commentflag;
	}


	public void setCommentflag(Integer commentflag) {
		this.commentflag = commentflag;
	}


	public Integer getCommentid() {
		return commentid;
	}


	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
	
	
	
}
