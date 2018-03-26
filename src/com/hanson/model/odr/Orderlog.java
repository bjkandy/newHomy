package com.hanson.model.odr;

import java.util.Date;

public class Orderlog {
private Integer id;
private String orderid;//订单id
private String optname;//操作人
private Date createtime;//
private String content;//内容
private String accounttype;//操作人类型
private Integer optid;//操作人id
private Integer logstatus;//日志状态
private Integer bdelete;//是否删除
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
public String getOptname() {
	return optname;
}
public void setOptname(String optname) {
	this.optname = optname;
}
public Date getCreatetime() {
	return createtime;
}
public void setCreatetime(Date createtime) {
	this.createtime = createtime;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getAccounttype() {
	return accounttype;
}
public void setAccounttype(String accounttype) {
	this.accounttype = accounttype;
}
public Integer getOptid() {
	return optid;
}
public void setOptid(Integer optid) {
	this.optid = optid;
}
public Integer getLogstatus() {
	return logstatus;
}
public void setLogstatus(Integer logstatus) {
	this.logstatus = logstatus;
}
public Integer getBdelete() {
	return bdelete;
}
public void setBdelete(Integer bdelete) {
	this.bdelete = bdelete;
}

}
