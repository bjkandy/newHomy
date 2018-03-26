package com.hanson.model;

import java.io.Serializable;
import java.util.Date;

public class Seconduser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;//用户id
	private Long qyid;
	private String password;
	private String loginname;
	private String nickname;
	private String truename;
	private String email;
	private String emailisactive;
	private Date createtime;
	private String openid;
	private String sex;
	private Date birthday;
	private String province;
	private String city;
	private String address;
	private String channel;
	private Double amount;
	private Date lastlogintime;
	private String lastloginip;
	private String lastloginarea;
	private Double hyjf;
	private Integer rank;
	private Integer islock; //是否锁定 0 否(没有锁定) 1锁定
	private String headimgurl;
	private String mobile;
	private Integer subscribe; //是否还处于订阅状态
	private Integer discount;
	private Integer upstate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQyid() {
		return qyid;
	}
	public void setQyid(Long qyid) {
		this.qyid = qyid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailisactive() {
		return emailisactive;
	}
	public void setEmailisactive(String emailisactive) {
		this.emailisactive = emailisactive;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public String getLastloginip() {
		return lastloginip;
	}
	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}
	public String getLastloginarea() {
		return lastloginarea;
	}
	public void setLastloginarea(String lastloginarea) {
		this.lastloginarea = lastloginarea;
	}
	public Double getHyjf() {
		return hyjf;
	}
	public void setHyjf(Double hyjf) {
		this.hyjf = hyjf;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getIslock() {
		return islock;
	}
	public void setIslock(Integer islock) {
		this.islock = islock;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getUpstate() {
		return upstate;
	}
	public void setUpstate(Integer upstate) {
		this.upstate = upstate;
	}
}
