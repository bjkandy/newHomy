package com.hanson.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.common.util.Page;
import com.hanson.mapper.UsersMapper;
import com.hanson.model.Users;
import com.hanson.service.UsersService;

@Service
public class UsersServiceImp implements UsersService {
	@Autowired
	private UsersMapper usersMapper;

	@Override
	public List<Users> findUsers(Users users) {
		return usersMapper.findUsers(users);
	}

	@Override
	public void saveUsers(Users users) {
		usersMapper.saveUsers(users);
	}

	@Override
	public void updateUsers(Users users) {
		usersMapper.updateUsers(users);
	}

	@Override
	public void updatePassword(Users users) {
		usersMapper.updatePassword(users);
	}

	@Override
	public List<Users> findUsersLogin(Users users) {
		return usersMapper.findUsersLogin(users);
	}

	@Override
	public Page<Map<String, Object>> findUsersPage(
			Page<Map<String, Object>> page, Map<String, Object> map) {
		//分页查询通用参数
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
		//统计数量
		long totalCount = usersMapper.countTotles(map);
		page.setTotalCount(totalCount);
		//填充数据
		if(totalCount >0){
			page.setResult(usersMapper.search(map));
		}
		return page;
	}
}
