package com.hanson.mapper;

import java.util.List;
import java.util.Map;

import com.hanson.model.odr.Order;
import com.hanson.model.odr.Txis;
import com.hanson.model.u.Company;

public interface OrderMapper {
	
	

	/**
	 * 模糊查询
	 * @param Order
	 * @return
	 */
	public List<Order> getUniqueBy(Order or);
	
	
	
	/**
	 * 根据id查询
	 * @param Order
	 * @return
	 */
	public List<Order> queryid(Order or);
	
	
	
	/**
	 * 修改状态已发货
	 * @param Order
	 * @return
	 */
	public int orderfahuo(String orderid);
	
	
	
	
	/**
	 * 修改状态生产完毕
	 * @param Order
	 * @return
	 */
	public int ordersc(String idString);
	
	
	
	
	/**
	 * 发起结算
	 * @param Order
	 * @return
	 */
	public int insertjs(Txis ts);
	
	
	
	/**
	 * 查询企业信息
	 * @param Order
	 * @return
	 */
	public List<Company> queryje(Company cp);
	
	
	
	/**
	 * 修改提现状态
	 * @param Order
	 * @return
	 */
	public int ordertx(Order or);
	
	/**
	 * 查询根据企业和时间查询订单
	 * @param map
	 * @return
	 */
	public List<Order> findListByQYId(Map<String, Object> map);
	
	/**
	 * 查询根据时间查询订单
	 * @param map
	 * @return
	 */
	public List<Order> findListByWhere(Map<String, Object> map);

	public Map<String, Object> findCountByWhere(Map<String, Object> map);
	
	/**
	 * 统计某段时间内增量订单和照片数量
	 * @param map
	 * @return
	 */
	public Map<String, Object> findPhotoaddnumber(Map<String, Object> map);
	
	/**
	 * 分页查询-查询数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> search(Map<String, Object> map);
	
	/**
	 * 分页查询-统计总数
	 * @param map
	 * @return
	 */
	public long countTotles(Map<String, Object> map);
	
	/**
	 * 订单导出Excel
	 * @param or
	 * @return
	 */
	public List<Order> getOrdersExcel(Order or);
	
	/**
	 * 订单导出Excel
	 * @param order
	 * @return
	 */
	public List<Map<String, Object>> getOrdersExcelNew(Order order);
	
	public List<Map<String,Object>> transactionAnalysis(Map<String, Object> map);
	public Map<String,Object> transactionAnalysisCount(Map<String,Object> map);
	/**
	 * 查询订单信息
	 * @param order
	 * @return
	 */
	public Order findOrderById(Order order);
	
}
