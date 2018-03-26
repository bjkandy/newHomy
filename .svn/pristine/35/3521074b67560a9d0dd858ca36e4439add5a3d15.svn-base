package com.hanson.mapper;

import java.util.List;
import java.util.Map;

import com.hanson.model.Specvalue;

public interface SpecvalueMapper {
	
	public List<Specvalue> findUniqueByPid(Long productid);
	
	public long save(Specvalue bean);
	
	public long updateBatch(List<Map<String, Object>> list);
	public long updateproductBatch(Map<String, Object> map);
	
	/**
	 * 修改产品规格属性
	 * @param specvalue
	 */
	public void updateSpecvalue(Specvalue specvalue);
	
	public void deleteSpecvalue(Specvalue specvalue);
	
	
	
}
