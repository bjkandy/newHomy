package com.hanson.mapper;

import java.util.List;
import java.util.Map;

import com.hanson.model.StoreProperty;

public interface StorePropertyMapper {
	
  public StoreProperty Findbycompanyid(Long qyid);
  public Integer insertStoreProperty(StoreProperty bean);
  public Integer setauthority(StoreProperty bean);
  public List<Map<String, Object>> SearchStoreVips(Map<String, Object> map);
  public long SelectCountvips (Map<String, Object> map);
  public Integer updatechargeMoney(StoreProperty bean);
}
