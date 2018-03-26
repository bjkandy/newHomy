package com.hanson.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.UsersRolesMapper;
import com.hanson.model.Users;
import com.hanson.service.UsersRolesService;

@Service
public class UsersRolesServiceImpl implements UsersRolesService {
	@Autowired
	private UsersRolesMapper usersRolesMapper;


	@Override
	public void deleteByUserId(Users users) {
		usersRolesMapper.deleteByUserId(users);
	}

	@Override
	public void saveUsersRoles(Users users) {
		// TODO Auto-generated method stub
		usersRolesMapper.saveUsersRoles(users);
	}
	
	
}
