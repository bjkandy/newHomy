package com.hanson.model;
import java.util.Date;
/**
 * 企业与服务项目对应表
 * 
 * @author sunzb
 * 
 */
public class ServiceprojectCompany  {
	private Long id;
	private Long projectid;// 服务项目id
	private Integer qyid;// 项目所属企业id
	private double addproductmoney;//项目加拍优惠金额
	private Integer monthsalecount;// 项目月销量
	private Integer totalsale;// 项目总销量
	private Double originalprice;//项目原价
	private Double presentmoney;// 项目现价
	private Integer appointmentcount;//项目预约次数
	private Integer shootingtype;//项目拍摄类型
	private Integer updownframestatus;// 上下架状态(0:下架 1:上架)
	private Date newdate;// 创建时间
	private String remarks;// 项目备注
	private Integer bdelete;// 是否删除(0:已删除 1:未删除)
	private String area;//项目所属区域
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getProjectid() {
		return projectid;
	}
	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}
	public Integer getQyid() {
		return qyid;
	}
	public void setQyid(Integer qyid) {
		this.qyid = qyid;
	}
	public double getAddproductmoney() {
		return addproductmoney;
	}
	public void setAddproductmoney(double addproductmoney) {
		this.addproductmoney = addproductmoney;
	}
	public Integer getMonthsalecount() {
		return monthsalecount;
	}
	public void setMonthsalecount(Integer monthsalecount) {
		this.monthsalecount = monthsalecount;
	}
	public Integer getTotalsale() {
		return totalsale;
	}
	public void setTotalsale(Integer totalsale) {
		this.totalsale = totalsale;
	}
	public Double getPresentmoney() {
		return presentmoney;
	}
	public void setPresentmoney(Double presentmoney) {
		this.presentmoney = presentmoney;
	}
	
	public Double getOriginalprice() {
		return originalprice;
	}
	public void setOriginalprice(Double originalprice) {
		this.originalprice = originalprice;
	}
	public Integer getAppointmentcount() {
		return appointmentcount;
	}
	public void setAppointmentcount(Integer appointmentcount) {
		this.appointmentcount = appointmentcount;
	}
	public Integer getShootingtype() {
		return shootingtype;
	}
	public void setShootingtype(Integer shootingtype) {
		this.shootingtype = shootingtype;
	}
	public Integer getUpdownframestatus() {
		return updownframestatus;
	}
	public void setUpdownframestatus(Integer updownframestatus) {
		this.updownframestatus = updownframestatus;
	}
	public Date getNewdate() {
		return newdate;
	}
	public void setNewdate(Date newdate) {
		this.newdate = newdate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getBdelete() {
		return bdelete;
	}
	public void setBdelete(Integer bdelete) {
		this.bdelete = bdelete;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	
	
}
