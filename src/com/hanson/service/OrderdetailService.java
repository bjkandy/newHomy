package com.hanson.service;

import java.util.List;
import java.util.Map;


import com.hanson.model.odr.Orderdetail;

public interface OrderdetailService {
	
	/**
	 * 查询根据订单号查询订单信息
	 * @param orderid
	 * @return
	 */
	public List<Orderdetail> findListByOid(String orderid);
	
	public List<Map<String,Object>> orderDetailPhoto(Map<String,Object> orderid);
	
	public List<Map<String,Object>> orderDetailFrame(Map<String,Object> orderid);
	
	public Map<String, Object> orderDetail(Map<String,Object> data);
	/**
	 * C#条件 下载订单（图片）
	 * @param status                       
	 * @param qyid
	 * @param specinfo
	 * @return Result<？> 
	 */
	 public List<Map<String,Object>> downloadOrder(String orderid);
}
