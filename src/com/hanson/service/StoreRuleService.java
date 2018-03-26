package com.hanson.service;

import com.hanson.model.StoreRule;

public interface StoreRuleService {
	 public Integer InsertStorerule(StoreRule bean);
	 
	 public StoreRule GetBean(Integer qyid);
}
