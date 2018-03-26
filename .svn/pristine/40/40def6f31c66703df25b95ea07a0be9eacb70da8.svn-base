package com.hanson.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.AuthoritiesMapper;
import com.hanson.model.Authorities;
import com.hanson.model.Users;
import com.hanson.service.AuthoritiesService;

@Service
public class AuthoritiesServiceImp implements AuthoritiesService {
	@Autowired
	private AuthoritiesMapper authoritiesMapper;

	@Override
	public List<Authorities> findAuthoritiesList(Authorities authorities) {
		return authoritiesMapper.findAuthoritiesList(authorities);
	}

	@Override
	public void saveAuthorities(Authorities authorities) {
		authoritiesMapper.saveAuthorities(authorities);
	}

	@Override
	public void updateAuthorities(Authorities authorities) {
		authoritiesMapper.updateAuthorities(authorities);
	}

	@Override
	public void deleteAuthorities(Authorities authorities) {
		authoritiesMapper.deleteAuthorities(authorities);
	}

	@Override
	public Authorities findAuthoritiesById(Authorities authorities) {
		return authoritiesMapper.findAuthoritiesById(authorities);
	}

	@Override
	public List<Authorities> findAuthoritiesRoot(Authorities authorities) {
		return authoritiesMapper.findAuthoritiesRoot(authorities);
	}

	@Override
	public List<Authorities> findAuthoritiesByUserId(Users users) {
		
		return authoritiesMapper.findAuthoritiesByUserId(users);
	}
	
}
