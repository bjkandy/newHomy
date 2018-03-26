package com.hanson.model.odr;

import java.util.Date;

public class Yuyue {
	private Integer id;
	private Integer cuid;//用户id
	private String openid;//微信用户唯一识别号
	private Integer qyid;//企业id,
	private Integer timelotid;
	private String province;//预约的企业所在的省
	private String city;//预约的企业所在的市
	private String area;//预约的企业所在的区
	private String address;//地址
	private String orderid;//预约编号
	private String odrname;//项目名称
	private String cuname;//客户名称
	private Integer paytype;//支付方式（0支付宝 1微支付 3线下付款）
	private String paystatus;//支付状态：0：未支付，1：已支付：
	private String createdate;//创建时间
	private String createdateyuyuebegin;//预约服务开始时间
	private String createdateyuyueend;//预约服务结束时间
	private int status;//订单状态
	private Double amount;//总价
	private Double fee;//优惠金额
	private Integer quantity;//实际金额
	private String expresscode;//收货人联系电话
	private String email;//邮箱
	private String ordertype;//拍摄方式:10:去门店拍 20:上门外拍
	private Integer photographerid;//摄影师id
	private Integer verificationcode;//验证码
	private Integer bdelete;//是否删除
	private char sex;
	private String tradetime;//下单时间
	private Integer porjectid;//项目id
	private Date verificationtime;//核销时间
	private Integer reserve1;//预留字段
	private Integer reserve2;
	private String reserve3;
	private String  reserve4;
	
	private String companyname;
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCuid() {
		return cuid;
	}
	public void setCuid(Integer cuid) {
		this.cuid = cuid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getQyid() {
		return qyid;
	}
	public void setQyid(Integer qyid) {
		this.qyid = qyid;
	}
	public Integer getTimelotid() {
		return timelotid;
	}
	public void setTimelotid(Integer timelotid) {
		this.timelotid = timelotid;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOdrname() {
		return odrname;
	}
	public void setOdrname(String odrname) {
		this.odrname = odrname;
	}
	public String getCuname() {
		return cuname;
	}
	public void setCuname(String cuname) {
		this.cuname = cuname;
	}
	public Integer getPaytype() {
		return paytype;
	}
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	public String getPaystatus() {
		return paystatus;
	}
	public void setPaystatus(String paystatus) {
		this.paystatus = paystatus;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getCreatedateyuyuebegin() {
		return createdateyuyuebegin;
	}
	public void setCreatedateyuyuebegin(String createdateyuyuebegin) {
		this.createdateyuyuebegin = createdateyuyuebegin;
	}
	public String getCreatedateyuyueend() {
		return createdateyuyueend;
	}
	public void setCreatedateyuyueend(String createdateyuyueend) {
		this.createdateyuyueend = createdateyuyueend;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getExpresscode() {
		return expresscode;
	}
	public void setExpresscode(String expresscode) {
		this.expresscode = expresscode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public Integer getPhotographerid() {
		return photographerid;
	}
	public void setPhotographerid(Integer photographerid) {
		this.photographerid = photographerid;
	}
	public Integer getVerificationcode() {
		return verificationcode;
	}
	public void setVerificationcode(Integer verificationcode) {
		this.verificationcode = verificationcode;
	}
	public Integer getBdelete() {
		return bdelete;
	}
	public void setBdelete(Integer bdelete) {
		this.bdelete = bdelete;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getTradetime() {
		return tradetime;
	}
	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}
	public Integer getPorjectid() {
		return porjectid;
	}
	public void setPorjectid(Integer porjectid) {
		this.porjectid = porjectid;
	}
	public Date getVerificationtime() {
		return verificationtime;
	}
	public void setVerificationtime(Date verificationtime) {
		this.verificationtime = verificationtime;
	}
	public Integer getReserve1() {
		return reserve1;
	}
	public void setReserve1(Integer reserve1) {
		this.reserve1 = reserve1;
	}
	public Integer getReserve2() {
		return reserve2;
	}
	public void setReserve2(Integer reserve2) {
		this.reserve2 = reserve2;
	}
	public String getReserve3() {
		return reserve3;
	}
	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}
	public String getReserve4() {
		return reserve4;
	}
	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	
}
