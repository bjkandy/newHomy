package com.hanson.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hanson.mapper.SpecvalueMapper;
import com.hanson.model.Specvalue;
import com.hanson.service.SpecvalueService;

@Service
public class SpecvalueServiceImp implements SpecvalueService {

	@Autowired
	private SpecvalueMapper specvalueMapper;
	
	/**
	 * 精确查找-根据ID
	 * @param productid
	 * @return
	 */
	public List<Specvalue> findUniqueByPid(Long productid){
		return specvalueMapper.findUniqueByPid(productid);
	}
	
	public void updateBatch(List<Map<String, Object>> list){
		if(list !=null && list.size() > 0){
			specvalueMapper.updateBatch(list);
		}
	}
	public void updateproductBatch(Map<String, Object> map){
		specvalueMapper.updateproductBatch(map);
	}
	
	@Override
	public long save(Specvalue specvalue) {
		return specvalueMapper.save(specvalue);
	}

	@Override
	public void updateSpecvalue(Specvalue specvalue) {
		specvalueMapper.updateSpecvalue(specvalue);
	}

	@Override
	public void deleteSpecvalue(Specvalue specvalue) {
		specvalueMapper.deleteSpecvalue(specvalue);
	}

}
