package com.hanson.model.odr;

import java.util.Date;

public class Orderpay {
private Integer id;
private String orderid;//订单号
private String paystatus;//支付状态：0：未支付，1：已支付：目前代码中写死存0
private Double payamount;//支付金额
private Date createtime;//创建时间
private String paymethod;//支付方式
private String remark;//备注
private String tradeno;//
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
public String getPaystatus() {
	return paystatus;
}
public void setPaystatus(String paystatus) {
	this.paystatus = paystatus;
}
public Double getPayamount() {
	return payamount;
}
public void setPayamount(Double payamount) {
	this.payamount = payamount;
}
public Date getCreatetime() {
	return createtime;
}
public void setCreatetime(Date createtime) {
	this.createtime = createtime;
}
public String getPaymethod() {
	return paymethod;
}
public void setPaymethod(String paymethod) {
	this.paymethod = paymethod;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getTradeno() {
	return tradeno;
}
public void setTradeno(String tradeno) {
	this.tradeno = tradeno;
}
public Integer getBdelete() {
	return bdelete;
}
public void setBdelete(Integer bdelete) {
	this.bdelete = bdelete;
}

}
