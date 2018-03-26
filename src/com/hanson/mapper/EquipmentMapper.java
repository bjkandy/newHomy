package com.hanson.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hanson.model.Equipment;



public interface EquipmentMapper {
	
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
	public List<Map<String, Object>> pagingQuery(Map<String, Object> map);
	
	/**
	 * 查询设备总数
	 * @param equipment
	 * @return
	 */
	public long count( Map<String, Object> map);
	
	/**
	 * 新增设备
	 * @param equipment
	 * @return
	 */
	public int insertEquipment(Equipment equipment);
}
