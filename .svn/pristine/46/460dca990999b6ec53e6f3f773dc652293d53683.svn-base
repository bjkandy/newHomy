package com.hanson.service;

import java.util.List;
import java.util.Map;

import com.hanson.common.util.Page;
import com.hanson.model.Seconduser;
import com.hanson.serviceModel.SeconduserQuery;


public interface SeconduserService {
	/**
	 * 分页查询列表
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<Map<String, Object>> queryUserListPage(Page<Map<String, Object>> page, Map<String, Object> map);
	
	/**
	 * 查询列表
	 * @param seconduser
	 * @return
	 * @throws Exception
	 */
	public List<Seconduser> queryUserList(Seconduser seconduser) throws Exception;
	
	
	/**
	 * 根据openid 查询用户信息
	 * @param openid
	 * @return
	 */
	public Seconduser getUniqueBy(String openid);
	
	/**
	 * 保存微信用户信息
	 * @param seconduser
	 * @return
	 */
	public Seconduser save(Seconduser seconduser);
	
	/**
	 * 查询粉丝增量
	 * @param map
	 * @return
	 */
	public Integer findFansadd(Map<String, Object> map);
	
	/**
	 * 粉丝分析
	 * @param map
	 * @return
	 */
	public Map<String, Object> tadeilUserData(Map<String, Object> map);
}
