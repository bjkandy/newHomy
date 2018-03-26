package com.hanson.mapper;
import java.util.List;

import com.hanson.model.City;
import com.hanson.model.Province;

public interface ProvincialMapper {
	
	/**
	 * 省份查询-获取所有省份
	 * @return
	 */
	public List<Province> findProvinceList();
	
	/**
	 * 市查询-根据省ID
	 * @param provinceid
	 * @return
	 */
	public List<City> findCityList(String provinceid);
	
	/**
	 * 所属省份查询-根据城市ID
	 * @param cityid
	 * @return
	 */
	public Province findProvinceByCid(String cityid);
	
	public City GetcityModel(Integer id);
}
