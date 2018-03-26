package com.hanson.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.ProvincialMapper;
import com.hanson.model.City;
import com.hanson.model.Province;
import com.hanson.service.ProvincialService;


@Service
public class ProvincialServiceImp implements ProvincialService {

	@Autowired
	private ProvincialMapper provincialMapper;
	
    public List<Province> getProvincials() {
    	return provincialMapper.findProvinceList();
    }

    public Province getProvincialByCitycode(String city_code) {
    	return provincialMapper.findProvinceByCid(city_code);
    }

    public List<City> findCityByCode(String province_code) {
    	return provincialMapper.findCityList(province_code);
    }
    @Override
    public City GetcityModel(Integer id){
    	return provincialMapper.GetcityModel(id);
    }
}
