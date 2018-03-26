package com.hanson.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Announce implements Serializable{
	private Integer id;
	private String title;
	private Integer announcetype;//通知类型
	private String bannerurl;
	private Integer recommend;//是否推荐
	private Integer sort;//排序字段
	private Date createtime;
	private String createUser;//发布者
	private  String msgdetailurl;
	private String profile;
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getBannerurl() {
		return bannerurl;
	}
	public void setBannerurl(String bannerurl) {
		this.bannerurl = bannerurl;
	}
	public String getMsgdetailurl() {
		return msgdetailurl;
	}
	public void setMsgdetailurl(String msgdetailurl) {
		this.msgdetailurl = msgdetailurl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAnnouncetype() {
		return announcetype;
	}
	public void setAnnouncetype(Integer announcetype) {
		this.announcetype = announcetype;
	}
	
	
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getCreatetime() {
		SimpleDateFormat formatter; 
	    formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
	    String ctime = formatter.format(this.createtime); 
	    return ctime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	
}
