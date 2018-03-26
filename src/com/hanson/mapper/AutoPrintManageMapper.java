package com.hanson.mapper;

import java.util.List;
import java.util.Map;


public interface AutoPrintManageMapper {

	/**
	 * 自助打印订单管理查询
	 * @param data
	 * @return
	 */
	public List<Map<String,Object>> autoPrintOrder(Map<String,Object> data);
	/**
	 * 自助打印订单管理数量查询
	 * @param data
	 * @return
	 */
	public String autoPrintOrderCount(Map<String,Object> data);
	
	/**
	 * 自助打印订单详情
	 * @param data
	 * @return
	 */
	public Map<String,Object> autoPrintOrderInfo(Map<String,Object> data);
	/**
	 * 自助打印订单导出excel
	 * @param data
	 * @return
	 */
	public List<Map<String,Object>> autoPrintOrderExcel(Map<String,Object> data);
	
}
