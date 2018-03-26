package com.hanson.service;

import java.util.Map;

import com.hanson.common.util.Page;
import com.hanson.model.Equipment;


public interface EquipmentService {
	/**
	 * 根据id 查询设备信息
	 * @param id
	 * @return
	 */
	public Equipment get(Integer id);
	
	/**
	 * 设备列表查询 
	 * @param equipment
	 * @return
	 */
	public Page<Map<String, Object>> pagingQuery(Page<Map<String, Object>> pagepeper, Map<String, Object> map);
	
	/**
	 * 新增设备
	 * @param equipment
	 * @return
	 */
	public int insertEquipment(Equipment equipment);
	
}
