package com.hanson.mapper;

import java.util.List;
import java.util.Map;

import com.hanson.model.Company;
import com.hanson.model.odr.Order;
import com.hanson.model.odr.Txjs;

public interface TxjsMapper {
	public List<Map<String,Object>> getTxjs(Map<String,Object> tx);
	public List<Map<String,Object>> downloadTx(Map<String,Object> tx);
	
	public String getTxCount(Map<String,Object> tx);
	
	public void daKuan(Txjs tx);
	/**
	 * 跟据提现id查询当前提现者的企业类型
	 * @param tx
	 * @return
	 */
	public Company findCompanyByTxid(Txjs tx);
	
	/**
	 * 根据提现ID查询提现订单
	 * @param tx
	 * @return
	 */
	public List<Order> findOrdersByTxid(Txjs tx);
	
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
	public List<Txjs> getTxjsExcel(Txjs txjs);
	
	/**
	 * 分页查询提现单下的订单(总数)
	 * @param map
	 * @return
	 */
	public long countOrderTotles(Map<String, Object> map);
	
	
	/**
	 * 分页查询提现单下的订单
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> searchOrder(Map<String, Object> map);
	
	/**
	 * 返现管理导出excle
	 * @param txjs
	 * @return
	 */
	public List<Map<String, Object>> getTxjsExcelNew(Txjs txjs);
	
}
