package com.hanson.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.common.util.Page;
import com.hanson.mapper.SeconduserMapper;
import com.hanson.model.Seconduser;
import com.hanson.service.SeconduserService;
import com.hanson.serviceModel.SeconduserQuery;

@Service
public class SeconduserServiceImp implements SeconduserService {

	@Autowired
	private SeconduserMapper seconduserMapper;
	
	public List<Seconduser> queryUserList(Seconduser seconduser) throws Exception {
		
		return	seconduserMapper.findUserList(seconduser);
	}

	@Override
	public Seconduser getUniqueBy(String openid) {
		
		return seconduserMapper.getUniqueBy(openid);
	}

	public Seconduser save(Seconduser seconduser) {
		return seconduserMapper.save(seconduser);
	}

	public Integer findFansadd(Map<String, Object> map) {
		return seconduserMapper.findFansadd(map);
	}

	@Override
	public Page<Map<String, Object>> queryUserListPage(
			Page<Map<String, Object>> page, Map<String, Object> map) {
		//分页查询通用参数
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
		//统计数量
		long totalCount = seconduserMapper.countTotles(map);
		page.setTotalCount(totalCount);
		//填充数据
		if(totalCount >0){
			page.setResult(seconduserMapper.search(map));
		}
		return page;
	}

	@Override
	public Map<String, Object> tadeilUserData(Map<String, Object> map) {
		return seconduserMapper.tadeilUserData(map);
	}

}
