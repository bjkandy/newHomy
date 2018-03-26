package com.hanson.mapper;
import java.util.Map;

import com.hanson.model.User;

public interface UserMapper {
	
	/**
	 * 保存用户信息
	 * @param openid
	 * @return
	 */
	public void insert(User user);
	
	/**
	 * 更新企业用户信息
	 * @param map
	 * @return
	 */
	public Integer updateQyUser(Map<String, Object> map);
	
	/**
	 * 根据用户名查询数量
	 * @param map
	 * @return
	 */
	public Integer isLoginname(String loginname);
}
