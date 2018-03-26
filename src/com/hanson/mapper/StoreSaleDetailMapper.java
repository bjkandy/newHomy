package com.hanson.mapper;
import java.util.List;
import java.util.Map;

import com.hanson.common.util.Page;
import com.hanson.model.Company;
import com.hanson.model.StoreSaleDetail;

public interface StoreSaleDetailMapper {

	public List<StoreSaleDetail> getSalesByStoreId(long storeid);
	
	public long SelectCountStoreSales (Map<String, Object> map);
	
	public List<Map<String, Object>> SearchStoreSales(Map<String, Object> map);
	
	public Company SelectStoreInfo(long storeid);
	
}
