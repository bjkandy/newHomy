package com.hanson.mapper;

import java.util.List;

import com.hanson.model.Authorities;
import com.hanson.model.Users;

public interface AuthoritiesMapper {
	/**
	 * 条件查询权限列表（没有条件查询所有）
	 * @return
	 */
	public List<Authorities> findAuthoritiesList(Authorities authorities);
	
	/**
	 * 新增权限项
	 * @param authorities
	 */
	public void saveAuthorities(Authorities authorities);
	
	/**
	 * 编辑权限
	 * @param authorities
	 */
	public void updateAuthorities(Authorities authorities);
	
	/**
	 * 删除权限
	 * @param authorities
	 */
	public void deleteAuthorities(Authorities authorities);
	
	/**
	 * 根据ID查询权限
	 * @param authorities
	 * @return
	 */
	public Authorities findAuthoritiesById(Authorities authorities);
	
	/**
	 * 查询根目录
	 * @param authorities
	 * @return
	 */
	public List<Authorities> findAuthoritiesRoot(Authorities authorities);
	
	/**
	 * 查询用户权限
	 * @param users
	 * @return
	 */
	public List<Authorities> findAuthoritiesByUserId(Users users);
	
	
}
