package com.hanson.service;
import java.util.List;
import java.util.Map;

import com.hanson.common.util.Page;
import com.hanson.model.Company;
import com.hanson.model.StoreSaleDetail;

public interface StoreSaleDetailService {
	public List<StoreSaleDetail> getSalesByStoreId(long storeid);
	
	public long SelectCountStoreSales (Map<String, Object> map);
	
	public Page<Map<String, Object>> SearchStoreSales(Page<Map<String, Object>> page, Map<String, Object> map);
	
	public Company SelectStoreInfo(long storeid);
}
