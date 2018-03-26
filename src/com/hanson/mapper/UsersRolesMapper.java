package com.hanson.mapper;
import com.hanson.model.Users;

public interface UsersRolesMapper {
	/**
	 * 删除用户角色
	 * @param users
	 */
	public void deleteByUserId(Users users);

	/**
	 * 新增用户角色
	 * @param users
	 */
	public void saveUsersRoles(Users users);
}
