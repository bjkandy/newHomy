package com.hanson.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.common.util.Page;
import com.hanson.mapper.StorePropertyMapper;
import com.hanson.model.StoreProperty;
import com.hanson.service.StorePropertyService;

@Service
public class StorePropertyServiceImp implements StorePropertyService{
    @Autowired
	private StorePropertyMapper storePropertyMapper;
    
	@Override
	public StoreProperty Findbycompanyid(Long qyid) {
		return storePropertyMapper.Findbycompanyid(qyid);
	}
	
	@Override
	public Integer insertStoreProperty(StoreProperty bean){
		return storePropertyMapper.insertStoreProperty(bean);
	}
	@Override
	public Integer setauthority(StoreProperty bean){
		return storePropertyMapper.setauthority(bean);
	}
	@Override
	public Integer updatechargeMoney(StoreProperty bean){
		return storePropertyMapper.updatechargeMoney(bean);
	}
	@Override
	public Page<Map<String, Object>> SearchStoreVips(Page<Map<String, Object>> page, Map<String, Object> map){
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
		long totalCount =storePropertyMapper.SelectCountvips(map);
		page.setTotalCount(totalCount);
		if(totalCount >0){
			page.setResult(storePropertyMapper.SearchStoreVips(map));
		}
		return page;
	}
	@Override
	public long SelectCountvips (Map<String, Object> map){
		return storePropertyMapper.SelectCountvips(map);
	}
}
