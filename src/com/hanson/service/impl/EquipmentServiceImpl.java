package com.hanson.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.common.util.Page;
import com.hanson.mapper.EquipmentMapper;
import com.hanson.model.Equipment;
import com.hanson.service.EquipmentService;


@Service
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	public Equipment get(Integer id) {
		return equipmentMapper.get(id);
	}

	public Page<Map<String, Object>> pagingQuery(Page<Map<String, Object>> page, Map<String, Object> map) {
		
		//分页查询通用参数
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
		
		long totalCount = equipmentMapper.count(map);
		//填充数据
		if(totalCount >0){
			page.setResult(equipmentMapper.pagingQuery(map));
		}
		page.setTotalCount(totalCount);
		
		return page;
	}

	public int insertEquipment(Equipment equipment) {
		return equipmentMapper.insertEquipment(equipment);
	}
}
