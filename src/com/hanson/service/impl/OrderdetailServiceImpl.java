package com.hanson.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.OrderdetailMapper;
import com.hanson.model.odr.Orderdetail;
import com.hanson.service.OrderdetailService;

import common.ServiceCommonException;
import common.StringUtils;
@Service
public class OrderdetailServiceImpl implements OrderdetailService{
	@Autowired
	private OrderdetailMapper orderdetailMapper;

	@Override
	public List<Orderdetail> findListByOid(String orderid) {
		return orderdetailMapper.findListByOid(orderid);
	}
	
	@Override
	public List<Map<String,Object>> orderDetailFrame(Map<String,Object> orderid){
		return orderdetailMapper.orderDetailFrame(orderid);
		
	}
	
	@Override
	public List<Map<String,Object>> orderDetailPhoto(Map<String,Object> orderid){
		return orderdetailMapper.orderDetailPhoto(orderid);
	}
	
	@Override
	public Map<String, Object> orderDetail(Map<String,Object> map){
		return orderdetailMapper.orderDetail(map);
	}
	/**
	 * 条件 下载订单（图片）
	 * @param status                       
	 * @param qyid
	 * @param specinfo
	 * @return Result<？> 
	 */
	@Override
	 public List<Map<String,Object>> downloadOrder(String orderid){
		 if(StringUtils.isBlank(orderid)){
			 throw new ServiceCommonException("4001","订单id为空！");
		 }
		 List<Map<String,Object>> data = orderdetailMapper.downloadOrder(orderid);
		 if(data.isEmpty() || data.get(0).isEmpty()){
			 throw new ServiceCommonException("4001","订单详情数据为空！");
		 }
		 return data;
	 }
}
