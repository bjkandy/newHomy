package com.hanson.model.odr;

public class Ordership {
private Integer id;
private String orderid;//订单号
private String shipname;//收货人名称
private String shipaddress;//收货地址
private String provincecode;//省id
private String province;//省
private String citycode;//市id
private String city;//市
private String areacode;//区id
private String area;//区
private String phone;//收货人电话
private Integer bdelete;//是否删除
private Integer isdefault;//1为默认收获地址
private String expressno;//快递单号
private String ExpressCompanyName;//快递公司名称
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
public String getShipname() {
	return shipname;
}
public void setShipname(String shipname) {
	this.shipname = shipname;
}
public String getShipaddress() {
	return shipaddress;
}
public void setShipaddress(String shipaddress) {
	this.shipaddress = shipaddress;
}
public String getProvincecode() {
	return provincecode;
}
public void setProvincecode(String provincecode) {
	this.provincecode = provincecode;
}
public String getProvince() {
	return province;
}
public void setProvince(String province) {
	this.province = province;
}
public String getCitycode() {
	return citycode;
}
public void setCitycode(String citycode) {
	this.citycode = citycode;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getAreacode() {
	return areacode;
}
public void setAreacode(String areacode) {
	this.areacode = areacode;
}
public String getArea() {
	return area;
}
public void setArea(String area) {
	this.area = area;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Integer getBdelete() {
	return bdelete;
}
public void setBdelete(Integer bdelete) {
	this.bdelete = bdelete;
}
public Integer getIsdefault() {
	return isdefault;
}
public void setIsdefault(Integer isdefault) {
	this.isdefault = isdefault;
}
public String getExpressno() {
	return expressno;
}
public void setExpressno(String expressno) {
	this.expressno = expressno;
}
public String getExpressCompanyName() {
	return ExpressCompanyName;
}
public void setExpressCompanyName(String expressCompanyName) {
	ExpressCompanyName = expressCompanyName;
}

}
