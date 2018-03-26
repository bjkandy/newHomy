package com.hanson.model;

import java.io.Serializable;
import java.util.Date;

public class ServiceProject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  Long id;//自增长字段
	private Integer qyid;//企业id(新增2018-3-14)
	private String  productTag;//产品的标签,确认产品的唯一性
	private  String projectname;//约拍项目中文名
	private String englishname;//约拍项目英文名
	private Integer shootingtype;//拍摄类型
	private double totalamount;//项目总金额
	private double deposit;//项目定金
	private Integer updownframestatus;//项目上下架状态
	private String firstbanner;//首页项目的banner图
	private String detailpic;//项目详情页的轮播图
	private String examplepic;//案例展示图
	private String orderpic;//项目生成的订单缩略图
	private String serviceinfo;//项目的服务说明(图片展示)
	private String mainfunction;//项目的用途
	private String specialcontent;//项目的专业特设内容
	private String addservice;//项目的增值服务
	private Integer salesvolume;//项目的总销量
	private String projecttype;//项目类型
	private Date createdate;//项目创建日期
	private Integer bdelete;//项目是否删除 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQyid() {
		return qyid;
	}
	public void setQyid(Integer qyid) {
		this.qyid = qyid;
	}
	public String getProductTag() {
		return productTag;
	}
	public void setProductTag(String productTag) {
		this.productTag = productTag;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getEnglishname() {
		return englishname;
	}
	public void setEnglishname(String englishname) {
		this.englishname = englishname;
	}
	public Integer getShootingtype() {
		return shootingtype;
	}
	public void setShootingtype(Integer shootingtype) {
		this.shootingtype = shootingtype;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	public double getDeposit() {
		return deposit;
	}
	public void setDeposit(double deposit) {
		this.deposit = deposit;
	}
	public Integer getUpdownframestatus() {
		return updownframestatus;
	}
	public void setUpdownframestatus(Integer updownframestatus) {
		this.updownframestatus = updownframestatus;
	}
	public String getFirstbanner() {
		return firstbanner;
	}
	public void setFirstbanner(String firstbanner) {
		this.firstbanner = firstbanner;
	}
	public String getDetailpic() {
		return detailpic;
	}
	public void setDetailpic(String detailpic) {
		this.detailpic = detailpic;
	}
	public String getExamplepic() {
		return examplepic;
	}
	public void setExamplepic(String examplepic) {
		this.examplepic = examplepic;
	}
	public String getOrderpic() {
		return orderpic;
	}
	public void setOrderpic(String orderpic) {
		this.orderpic = orderpic;
	}
	public String getServiceinfo() {
		return serviceinfo;
	}
	public void setServiceinfo(String serviceinfo) {
		this.serviceinfo = serviceinfo;
	}
	public String getMainfunction() {
		return mainfunction;
	}
	public void setMainfunction(String mainfunction) {
		this.mainfunction = mainfunction;
	}
	public String getSpecialcontent() {
		return specialcontent;
	}
	public void setSpecialcontent(String specialcontent) {
		this.specialcontent = specialcontent;
	}
	public String getAddservice() {
		return addservice;
	}
	public void setAddservice(String addservice) {
		this.addservice = addservice;
	}
	public Integer getSalesvolume() {
		return salesvolume;
	}
	public void setSalesvolume(Integer salesvolume) {
		this.salesvolume = salesvolume;
	}
	public String getProjecttype() {
		return projecttype;
	}
	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Integer getBdelete() {
		return bdelete;
	}
	public void setBdelete(Integer bdelete) {
		this.bdelete = bdelete;
	}
	
}
