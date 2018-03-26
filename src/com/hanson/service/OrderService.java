package com.hanson.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hanson.common.util.Page;
import com.hanson.model.Seconduser;
import com.hanson.model.odr.Order;
import com.hanson.model.odr.Txis;
import com.hanson.model.u.Company;

public interface OrderService {
	public List<Order> getUniqueBy(Order or);
	public int orderfahuo(String orderid);
	public int ordersc(String idString);
	public int insertjs(Txis ts);
	public List<Order> queryid(Order or);
	//public HSSFWorkbook exportExcel(List<Order> lisOrder);
	public HSSFWorkbook exportExcelNew(List<Map<String, Object>> lisOrders);
	public List<Company> queryje(Company cp);
	public int ordertx(Order or);
	
	
	/**
	 * 查询根据企业和时间查询订单
	 * @param map
	 * @return
	 */
	public List<Order> findListByQYId(Map<String, Object> map);
	
	/**
	 * 查询根据条件查询订单
	 * @param map
	 * @return
	 */
	public List<Order> findListByWhere(Map<String, Object> map);
	
	public Map<String, Object> findCountByWhere(Map<String, Object> map);
	
	/**
	 * 统计某段时间内增量订单数和照片数
	 * @param map
	 * @return
	 */
	public Map<String, Object> findPhotoaddnumber(Map<String, Object> map);
	
	/**
	 * 分页查询订单信息
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<Map<String, Object>> findOrderPage(Page<Map<String, Object>> page, Map<String, Object> map);
	public  List<Map<String ,Object>> transactionAnalysis(Map<String, Object> map);
	public Map<String,Object> transactionAnalysisCount(Map<String, Object> map);
	/**
	 * 订单导出excel
	 * @param or
	 * @return
	 */
	public List<Order> getOrdersExcel(Order or);
	
	/**
	 * 订单导出新
	 * @param order
	 * @return
	 */
	public List<Map<String, Object>> getOrdersExcelNew(Order order);
	
	/**
	 * 查询单个订单
	 * @param order
	 * @return
	 */
	public Order findOrderById(Order order);
	
}
