package com.hanson.model.odr;

import java.util.List;

public class OrderExcelVo extends Order{
	private Integer id;// 编号
	private Integer cuid;// 用户id
	private Integer qyid;// 推广员的企业id
	private Integer prometerid;// 推广中心的企业id
	private Integer merchantid; // 商户的企业id
	private Integer distributorid;// 经销商的企业id
	private Integer rootId;// 一级公司id
	private String orderid;// 订单号
	private String cuname;// 客户名称
	private String oname;// 订单名称
	private Integer paytype;// 支付方式（0支付宝 1微支付）
	private Double rebate;// 折扣

	private String createdate;// 创建时间
	private String paydate;// 付款时间
	private Integer orderstatus;// 订单状态 ：（20," 未生产"25,"生产完毕"30,"已发货"40,"已签收"110,
								// "已关闭"200,"无效订单"）
	private Double totalprice;// 订单总价
	private Double fee;// 优惠金额
	private Integer paystatus;// 支付状态（0, "待支付"1,"支付中"10, "支付成功"100, "支付失败"）
	private String remark;// 备注
	private String ordertype;// 订单类型（商品的类型）
	private Integer bdelete;// 是否删除
	private Integer txStatus;// 提现状态:10.未提现 20.提现中（申请后且还未审核） 40.成功提现（财务已经线下打款）
	private Integer erjistatus;// 二级状态(10:未结算 20:已结算 30:待确认)
	private Integer expresstype;// 配送方式，1为快递配送，2为上门自取
	private Integer addressid;// 收货人id
	private Double productotallprice;// 商品总价
	
	//辅助查询
	private Integer photoaddnumber;// 照片增量
	private String merchanttxid;// 生产中心提现编号
	
	
	
	private List<Orderdetail> orderdetails;
	
	public List<Orderdetail> getOrderdetails() {
		return orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	private String psize;
	private int pagesize;// 当前页
	private int pagenumber = 2; // 条数

	private String kdate;// 开始时间
	private String jdate;// 结束时间

	public String getPsize() {
		return psize;
	}

	public void setPsize(String psize) {
		this.psize = psize;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagenumber() {
		return pagenumber;
	}

	public void setPagenumber(int pagenumber) {
		this.pagenumber = pagenumber;
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

	public Integer getQyid() {
		return qyid;
	}

	public void setQyid(Integer qyid) {
		this.qyid = qyid;
	}

	public Integer getRootId() {
		return rootId;
	}

	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCuname() {
		return cuname;
	}

	public void setCuname(String cuname) {
		this.cuname = cuname;
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public Integer getPaytype() {
		return paytype;
	}

	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}

	public Double getRebate() {
		return rebate;
	}

	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}

	public Integer getOrderstatus() {
		return orderstatus;
	}

	public String getPaydate() {
		return paydate;
	}

	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}

	public String getKdate() {
		return kdate;
	}

	public void setKdate(String kdate) {
		this.kdate = kdate;
	}

	public String getJdate() {
		return jdate;
	}

	public void setJdate(String jdate) {
		this.jdate = jdate;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getPaystatus() {
		return paystatus;
	}

	public void setPaystatus(Integer paystatus) {
		this.paystatus = paystatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public Integer getBdelete() {
		return bdelete;
	}

	public void setBdelete(Integer bdelete) {
		this.bdelete = bdelete;
	}

	public Integer getTxStatus() {
		return txStatus;
	}

	public void setTxStatus(Integer txStatus) {
		this.txStatus = txStatus;
	}

	public Integer getErjistatus() {
		return erjistatus;
	}

	public void setErjistatus(Integer erjistatus) {
		this.erjistatus = erjistatus;
	}

	public Integer getExpresstype() {
		return expresstype;
	}

	public void setExpresstype(Integer expresstype) {
		this.expresstype = expresstype;
	}

	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public Double getProductotallprice() {
		return productotallprice;
	}

	public void setProductotallprice(Double productotallprice) {
		this.productotallprice = productotallprice;
	}

	public Integer getPrometerid() {
		return prometerid;
	}

	public void setPrometerid(Integer prometerid) {
		this.prometerid = prometerid;
	}

	public Integer getMerchantid() {
		return merchantid;
	}

	public void setMerchantid(Integer merchantid) {
		this.merchantid = merchantid;
	}

	public Integer getDistributorid() {
		return distributorid;
	}

	public void setDistributorid(Integer distributorid) {
		this.distributorid = distributorid;
	}

	public Integer getPhotoaddnumber() {
		return photoaddnumber;
	}

	public void setPhotoaddnumber(Integer photoaddnumber) {
		this.photoaddnumber = photoaddnumber;
	}

	public String getMerchanttxid() {
		return merchanttxid;
	}

	public void setMerchanttxid(String merchanttxid) {
		this.merchanttxid = merchanttxid;
	}


}
