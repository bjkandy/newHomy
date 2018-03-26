package com.hanson.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.ProductinfoMapper;
import com.hanson.model.Productinfo;
import com.hanson.service.ProductinfoService;

@Service
public class ProductinfoServiceImp implements ProductinfoService {

	@Autowired
	private ProductinfoMapper productinfoMapper;
	
	@Override
	public void saveProductinfo(Productinfo productinfo) {
		productinfoMapper.saveProductinfo(productinfo);
	}

	@Override
	public Productinfo findProductinfoByPId(Productinfo productinfo) {
		// TODO Auto-generated method stub
		return productinfoMapper.findProductinfoByPId(productinfo);
	}

	@Override
	public void updateProductinfo(Productinfo productinfo) {
		productinfoMapper.updateProductinfo(productinfo);
	}

}
