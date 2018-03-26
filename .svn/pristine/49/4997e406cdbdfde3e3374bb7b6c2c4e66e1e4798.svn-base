package com.hanson.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.StoreVipMapper;
import com.hanson.model.StoreVip;
import com.hanson.service.StoreVipService;
@Service
public class StoreVipServiceImpl implements StoreVipService {
	@Autowired
	private StoreVipMapper storeVipMapper;
	
	public Integer InsertVipRule(StoreVip bean){
		return storeVipMapper.InsertVipRule(bean);
	}
	public Integer UpdateStoreVip(StoreVip model){
		return storeVipMapper.UpdateStoreVip(model);
	}
	public List<StoreVip> SelectStoreVip(){
		return storeVipMapper.SelectStoreVip();
	}
}
