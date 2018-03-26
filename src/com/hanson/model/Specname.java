package com.hanson.model;


//@Entity
//@Optlog(alias="规格名称")
//@Table(name = "pdt_specname")
public class Specname {
	private Long id;
	private String name;
	private String catalogid;
	private Integer sortno;
	private Integer bdelete;    // 删除位，数据库保存时，0为未删除，1为已被删除 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(String catalogid) {
		this.catalogid = catalogid;
	}
	public Integer getSortno() {
		return sortno;
	}
	public void setSortno(Integer sortno) {
		this.sortno = sortno;
	}
	public Integer getBdelete() {
		return bdelete;
	}
	public void setBdelete(Integer bdelete) {
		this.bdelete = bdelete;
	}
	
	
	private Boolean checked = false;
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
