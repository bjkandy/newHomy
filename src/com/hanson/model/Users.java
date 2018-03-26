package com.hanson.model;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String login_name;
	private String password;
	private String name;
	private String email;
	private String locked;
	private String usertype;
	private Long qyid;
	private String activecode;
	private Integer sex;
	private Date birthday;
	private String address;
	private String remark;
	private String useravatar;
	private String validstr;
	private String phone;
	private String roleName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Long getQyid() {
		return qyid;
	}
	public void setQyid(Long qyid) {
		this.qyid = qyid;
	}
	public String getActivecode() {
		return activecode;
	}
	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUseravatar() {
		return useravatar;
	}
	public void setUseravatar(String useravatar) {
		this.useravatar = useravatar;
	}
	public String getValidstr() {
		return validstr;
	}
	public void setValidstr(String validstr) {
		this.validstr = validstr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
