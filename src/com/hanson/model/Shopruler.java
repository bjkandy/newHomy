package com.hanson.model;

import java.util.Date;

public class Shopruler {
	private Long id;// 自增长id
	private Integer qyid;// 企业id
	private Integer timelot;// 时间间隔
	private String amstarttime;// 项目上午预约开始时间
	private String amendtime;// 项目上午预约结束时间
	private String pmstarttime;// 项目下午预约开始时间
	private String pmendtime;// 项目下午预约结束时间
	private String evstarttime;// 项目晚上预约开始时间
	private String evendtime;// 晚上预约结束时间
	private String area;// 店铺所属位置
	private Integer appointcount;// 店铺预约次数
	private Integer bdelete;// 删除标识
	private Date createtime;// 创建时间

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
