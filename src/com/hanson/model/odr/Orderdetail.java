package com.hanson.model.odr;

public class Orderdetail {
	private Integer id;
	private String orderid;// 订单号
	private Integer productid;// 产品id
	private String productdetail;// 商品信息
	private Integer specid;// 规格id
	private Integer price;// 商品价格
	private Integer number;// 不同尺寸对应的数量
	private String productname;// 商品路径
	private Double fee;// 优惠金额
	private String remark;// 备注说明
	private String proimg;// 产品图片
	private Integer bdelete;// 是否删除
	private String size;// 尺寸，7寸
	private String spec;// 材质；如光面、绒面

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

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getProductdetail() {
		return productdetail;
	}

	public void setProductdetail(String productdetail) {
		this.productdetail = productdetail;
	}

	public Integer getSpecid() {
		return specid;
	}

	public void setSpecid(Integer specid) {
		this.specid = specid;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProimg() {
		return proimg;
	}

	public void setProimg(String proimg) {
		this.proimg = proimg;
	}

	public Integer getBdelete() {
		return bdelete;
	}

	public void setBdelete(Integer bdelete) {
		this.bdelete = bdelete;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

}
