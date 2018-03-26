package com.hanson.mapper;

import java.util.List;

import com.hanson.model.Authorities;
import com.hanson.model.Roles;
import com.hanson.model.RolesAuthorities;

public interface RolesMapper {
	/**
	 * 查询角色
	 * @param roles
	 * @return
	 */
	public List<Roles> findRoles(Roles roles);
	
	/**
	 * 保存角色
	 * @param roles
	 */
	public void saveRoles(Roles roles);
	
	/**
	 * 根据ID查询角色
	 * @param roles
	 * @return
	 */
	public Roles findRolesById(Roles roles);
	
	/**
	 * 编辑角色
	 * @param roles
	 */
	public void updateRolese(Roles roles);
	
	/**
	 * 根据ID删除角色
	 * @param roles
	 */
	public void deleteRolesById(Roles roles);
	
	/**
	 * 获取角色下的权限
	 * @param roles
	 */
	public List<Authorities> findAuthorities(Roles roles);
	
	/**
	 * 删除角色下的权限
	 * @param roles
	 */
	public void deleteRolesAuthorities(Roles roles);
	
	/**
	 * 批量插入角色权限
	 * @param rolesAuthoritiesList
	 */
	public void saveRolesAuthorities(List<RolesAuthorities> rolesAuthoritiesList);
	
}
