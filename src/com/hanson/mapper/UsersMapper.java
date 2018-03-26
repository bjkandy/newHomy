package com.hanson.mapper;

import java.util.List;
import java.util.Map;

import com.hanson.model.Users;

public interface UsersMapper {
	/**
	 * 新增用户
	 * @param users
	 */
	public void saveUsers(Users users);
	
	/**
	 * 删除用户
	 * @param users
	 */
	public void deleteUsers(Users users);
	
	/**
	 * 修改用户
	 * @param users
	 */
	public void updateUsers(Users users);
	
	/**
	 * 查询用户
	 * @param users
	 */
	public List<Users> findUsers(Users users);
	
	/**
	 * 重置密码
	 * @param users
	 */
	public void updatePassword(Users users);
	
	/**
	 * 根据登陆名和密码查询用户
	 * @param users
	 * @return
	 */
	public List<Users> findUsersLogin(Users users);
	
	/**
	 * 分页查询-查询数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> search(Map<String, Object> map);
	
	/**
	 * 分页查询-统计总数
	 * @param map
	 * @return
	 */
	public long countTotles(Map<String, Object> map);
	
}
