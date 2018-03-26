package com.hanson.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StoreVip {
	private Long id;
	private Double chargemoney;// charge money
	private Integer grade;// store grade
	private Double discount;// store discount
	private Date lastmodifiedtime;// store's record modify time
	private Date createtime;// store's record create time

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getChargemoney() {
		return chargemoney;
	}

	public void setChargemoney(Double chargemoney) {
		this.chargemoney = chargemoney;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getDiscount() {
		DecimalFormat df = new DecimalFormat("#.00"); 
		return df.format(discount);
	//	return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getLastmodifiedtime() {
		SimpleDateFormat formatter; 
	    formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
	    String ctime = formatter.format(this.lastmodifiedtime); 
	    return ctime;
	}

	public void setLastmodifiedtime(Date lastmodifiedtime) {
		this.lastmodifiedtime = lastmodifiedtime;
	}

	public String getCreatetime() {
		SimpleDateFormat formatter; 
	    formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss"); 
	    String ctime = formatter.format(this.createtime); 
	    return ctime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}
