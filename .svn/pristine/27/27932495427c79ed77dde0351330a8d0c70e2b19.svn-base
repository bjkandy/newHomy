package com.hanson.service;

import java.util.List;
import java.util.Map;

import com.hanson.common.util.Page;
import com.hanson.model.Users;


public interface UsersService {
	/**
	 * 分页查询
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<Map<String, Object>> findUsersPage(Page<Map<String, Object>> page, Map<String, Object> map);
	
	/**
	 * 查询用户
	 * @param users
	 * @return
	 */
	public List<Users> findUsers(Users users);
	
	/**
	 * 保存用户
	 * @param users
	 */
	public void saveUsers(Users users);
	
	/**
	 * 更新用户
	 * @param users
	 */
	public void updateUsers(Users users);
	
	/**
	 * 重置密码
	 * @param users
	 */
	public void updatePassword(Users users);
	
	/**
	 * 根据用户名和密码查询用户
	 * @param users
	 * @return
	 */
	public List<Users> findUsersLogin(Users users);
	
}
