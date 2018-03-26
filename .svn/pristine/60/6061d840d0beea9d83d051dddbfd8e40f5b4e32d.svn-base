package com.hanson.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.RolesMapper;
import com.hanson.model.Authorities;
import com.hanson.model.Roles;
import com.hanson.model.RolesAuthorities;
import com.hanson.service.RolesService;

@Service
public class RolesServiceImp implements RolesService {
	@Autowired
	private RolesMapper rolesMapper;

	@Override
	public List<Roles> findRoles(Roles roles) {
		return rolesMapper.findRoles(roles);
	}

	@Override
	public void saveRoles(Roles roles) {
		rolesMapper.saveRoles(roles);
	}

	@Override
	public Roles findRolesById(Roles roles) {
		return rolesMapper.findRolesById(roles);
	}

	@Override
	public void updateRoles(Roles roles) {
		rolesMapper.updateRolese(roles);
	}

	@Override
	public void deleteRolesById(Roles roles) {
		rolesMapper.deleteRolesById(roles);
	}

	@Override
	public List<Authorities> findAuthorities(Roles roles) {
		return rolesMapper.findAuthorities(roles);
	}

	@Override
	public void deleteRolesAuthorities(Roles roles) {
		rolesMapper.deleteRolesAuthorities(roles);
	}

	@Override
	public void saveRolesAuthorities(List<RolesAuthorities> rolesAuthoritiesList) {
		rolesMapper.saveRolesAuthorities(rolesAuthoritiesList);
	}
}
