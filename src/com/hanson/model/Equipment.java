package com.hanson.model;

import java.util.Date;

public class Equipment {
	private Integer id;// 设备ID
	private Integer qyid;// 商户id
	private String companyname;//商户名称
	private String equipmentname;// 设备名称
	private String provincename;// 省
	private String cityname;// 市
	private String districtname;// 区
	private String address;// 详细地址
	private String qrcode;// 二维码
	private String equipmentspec;// 尺寸材质价格
	private Integer openstoc;// 是否开启库存提醒 0:未开启 1:开启 默认为1
	private Date createtime;// 设备创建时间
	private Integer stocknum;// 库存数量
	private Integer bdelete;// 是否删除 0: 已删除 1: 未删除
	private Date createtimeStart;// 开始时间
	private Date createtimeEnd;// 结束时间
	
	//==============================新增json数据
	private String[] size5;//光面五寸
	private String[] size6;//光面六寸
	private String[] size7;//光面七寸
	private String[] size8;//光面八寸
	
	private String[] rsize5;//绒面五寸
	private String[] rsize6;//绒面六寸
	private String[] rsize7;//绒面七寸
	private String[] rsize8;//绒面八寸
	
	public String[] getSize5() {
		return size5;
	}

	public void setSize5(String[] size5) {
		this.size5 = size5;
	}

	public String[] getSize6() {
		return size6;
	}

	public void setSize6(String[] size6) {
		this.size6 = size6;
	}

	public String[] getSize7() {
		return size7;
	}

	public void setSize7(String[] size7) {
		this.size7 = size7;
	}

	public String[] getSize8() {
		return size8;
	}

	public void setSize8(String[] size8) {
		this.size8 = size8;
	}

	public String[] getRsize5() {
		return rsize5;
	}

	public void setRsize5(String[] rsize5) {
		this.rsize5 = rsize5;
	}

	public String[] getRsize6() {
		return rsize6;
	}

	public void setRsize6(String[] rsize6) {
		this.rsize6 = rsize6;
	}

	public String[] getRsize7() {
		return rsize7;
	}

	public void setRsize7(String[] rsize7) {
		this.rsize7 = rsize7;
	}

	public String[] getRsize8() {
		return rsize8;
	}

	public void setRsize8(String[] rsize8) {
		this.rsize8 = rsize8;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Date getCreatetimeStart() {
		return createtimeStart;
	}

	public void setCreatetimeStart(Date createtimeStart) {
		this.createtimeStart = createtimeStart;
	}

	public Date getCreatetimeEnd() {
		return createtimeEnd;
	}

	public void setCreatetimeEnd(Date createtimeEnd) {
		this.createtimeEnd = createtimeEnd;
	}

	public Integer getStocknum() {
		return stocknum;
	}

	public void setStocknum(Integer stocknum) {
		this.stocknum = stocknum;
	}

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

	public String getEquipmentname() {
		return equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public String getProvincename() {
		return provincename;
	}

	public void setProvincename(String provincename) {
		this.provincename = provincename;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public String getEquipmentspec() {
		return equipmentspec;
	}

	public void setEquipmentspec(String equipmentspec) {
		this.equipmentspec = equipmentspec;
	}

	public Integer getOpenstoc() {
		return openstoc;
	}

	public void setOpenstoc(Integer openstoc) {
		this.openstoc = openstoc;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getBdelete() {
		return bdelete;
	}

	public void setBdelete(Integer bdelete) {
		this.bdelete = bdelete;
	}

}
