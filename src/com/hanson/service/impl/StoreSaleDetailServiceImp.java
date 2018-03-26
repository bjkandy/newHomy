package com.hanson.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.common.util.Page;
import com.hanson.mapper.StoreSaleDetailMapper;
import com.hanson.model.Company;
import com.hanson.model.StoreSaleDetail;
import com.hanson.service.StoreSaleDetailService;
@Service
public class StoreSaleDetailServiceImp implements StoreSaleDetailService {
	@Autowired
	private StoreSaleDetailMapper storeSaleDetailMapper;
	
	@Override
	public List<StoreSaleDetail> getSalesByStoreId(long storeid) {
		return storeSaleDetailMapper.getSalesByStoreId(storeid);
	}
	@Override
	public long SelectCountStoreSales (Map<String, Object> map){
		return storeSaleDetailMapper.SelectCountStoreSales(map);
	}
	
	@Override
	public Page<Map<String, Object>> SearchStoreSales(Page<Map<String, Object>> page, Map<String, Object> map){
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
		long totalCount =storeSaleDetailMapper.SelectCountStoreSales(map);
		page.setTotalCount(totalCount);
		if(totalCount >0){
			page.setResult(storeSaleDetailMapper.SearchStoreSales(map));
		}
		return page;
	}
	@Override
	public Company SelectStoreInfo(long storeid){
		return storeSaleDetailMapper.SelectStoreInfo(storeid);
	}

}
