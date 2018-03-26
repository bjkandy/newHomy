package com.hanson.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hanson.common.util.Page;
import com.hanson.model.Company;
import com.hanson.model.odr.Order;
import com.hanson.model.odr.Txjs;

public interface TxjsService {
	/**
	 *  查询提现列表
	 * @param tx
	 * @return
	 */
 List<Map<String,Object>> getTxjs(Map<String,Object> tx);

 /**
	 *  下载查询提现列表
	 * @param tx
	 * @return
	 */
	public List<Map<String,Object>> downloadTx(Map<String,Object> tx);
	/**
	 * 打款
	 * @param tx
	 */
	public void daKuan(Txjs tx);
	
	/**
	 * 根据提现ID查询该提现单的商户信息
	 * @param tx
	 */
	public Company findCompanyByTxid(Txjs tx);
	
	/**
	 * 根据提现ID查询提现订单
	 * @param tx
	 * @return
	 */
	public List<Order> findOrdersByTxid(Txjs tx);
	
	/**
	 * 分页查询提现信息
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<Map<String, Object>> findTxjsPage(Page<Map<String, Object>> page, Map<String, Object> map);
	
	/**
	 * 订单导出excel
	 * @param or
	 * @return
	 */
	public List<Txjs> getTxjsExcel(Txjs txjs);
	
	/**
	 * 导出Excel
	 * @param lisOrder
	 * @return
	 */
	public HSSFWorkbook exportExcel(List<Txjs> lisTxjs) ;
	
	/**
	 * 根据提现编号查询订单
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<Map<String, Object>> findOrderPage(Page<Map<String, Object>> page, Map<String, Object> map);
	
	/**
	 * 返现管理导出excel
	 * @param txjs
	 * @return
	 */
	public List<Map<String, Object>> getTxjsExcelNew(Txjs txjs);
	
	public HSSFWorkbook exportExcelNew(List<Map<String, Object>> listTxjs);
	
}
