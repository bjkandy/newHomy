package com.hanson.model;

import java.util.List;

/**
 * 
 * @Description: 用户表 user
 * 
 */
public class RolesAuthorities  {
	private Long role_id;
	private Long authority_id;
	
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	public Long getAuthority_id() {
		return authority_id;
	}
	public void setAuthority_id(Long authority_id) {
		this.authority_id = authority_id;
	}
}
