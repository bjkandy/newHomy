package com.hanson.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.StoreRuleMapper;
import com.hanson.model.StoreRule;
import com.hanson.service.StoreRuleService;

@Service
public class StoreRuleServiceImp implements StoreRuleService{

	@Autowired
	private StoreRuleMapper storeRuleMapper; 
	
	@Override
	public Integer InsertStorerule(StoreRule bean) {
		return storeRuleMapper.InsertStorerule(bean);
	}
	@Override
	public StoreRule GetBean(Integer qyid){
       return storeRuleMapper.GetBean(qyid);		
	}

}
