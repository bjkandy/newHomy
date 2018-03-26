package com.hanson.service;

import java.util.List;
import java.util.Map;

public interface DataCenterService {
	
	/**
	 * 商户数据数量查询
	 * @param map
	 * @return
	 */
	public String merchantDataCount(Map<String ,Object> data);
	/**
	 * 商户数据查询
	 * @param map
	 * @return
	 */
	public List<Map<String ,Object>> merchantData(Map<String ,Object> data);
	/**
	 * 商户数据分析查询
	 * @param map
	 * @return
	 */
	public Map<String ,Object> merchantDataAnalyze(Map<String ,Object> data);
	/**
	 * 商户数据分析查询
	 * @param map
	 * @return
	 */
	public String merchantDataAnalyzeCount(Map<String ,Object> data);
	
	
	/**
	 * 商户数据分析查询导出excel
	 * @param map
	 * @return
	 */
	public List<Map<String ,Object>> merchantDataAnalyzeExcel(Map<String ,Object> data);
	
	
	
}
