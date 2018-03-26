package com.hanson.service;

import java.util.List;
import java.util.Map;

import com.hanson.model.Specvalue;

public interface SpecvalueService {
	
	/**
	 * 精确查找-根据ID
	 * @param productid
	 * @return
	 */
	public List<Specvalue> findUniqueByPid(Long productid);

	public void updateBatch(List<Map<String, Object>> list);
	
	public long save(Specvalue specvalue);
	
	public void updateproductBatch(Map<String, Object> map);
	/**
	 * 修改产品规格属性
	 * @param specvalue
	 */
	public void updateSpecvalue(Specvalue specvalue);
	
	/**
	 * 删除产品规格属性
	 * @param specvalue
	 */
	public void deleteSpecvalue(Specvalue specvalue);
	
	
}
