package com.hanson.model;

import java.util.Date;

public class StoreRule {
	private Long id;
	private Integer qyid;
	private Integer timelot;
	private String amstarttime;
	private String amendtime;
	private String pmstarttime;
	private String pmendtime;
	private String evstarttime;
	private String evendtime;
	private String area;
	private Integer appointcount;
	private Integer bdelete;
	private Date createtime;
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
	public Integer getTimelot() {
		return timelot;
	}
	public void setTimelot(Integer timelot) {
		this.timelot = timelot;
	}
	public String getAmstarttime() {
		return amstarttime;
	}
	public void setAmstarttime(String amstarttime) {
		this.amstarttime = amstarttime;
	}
	public String getAmendtime() {
		return amendtime;
	}
	public void setAmendtime(String amendtime) {
		this.amendtime = amendtime;
	}
	public String getPmstarttime() {
		return pmstarttime;
	}
	public void setPmstarttime(String pmstarttime) {
		this.pmstarttime = pmstarttime;
	}
	public String getPmendtime() {
		return pmendtime;
	}
	public void setPmendtime(String pmendtime) {
		this.pmendtime = pmendtime;
	}
	public String getEvstarttime() {
		return evstarttime;
	}
	public void setEvstarttime(String evstarttime) {
		this.evstarttime = evstarttime;
	}
	public String getEvendtime() {
		return evendtime;
	}
	public void setEvendtime(String evendtime) {
		this.evendtime = evendtime;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getAppointcount() {
		return appointcount;
	}
	public void setAppointcount(Integer appointcount) {
		this.appointcount = appointcount;
	}
	public Integer getBdelete() {
		return bdelete;
	}
	public void setBdelete(Integer bdelete) {
		this.bdelete = bdelete;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
