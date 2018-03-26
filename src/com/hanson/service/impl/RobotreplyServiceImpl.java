package com.hanson.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.RobotreplyMapper;
import com.hanson.model.Emoticon;
import com.hanson.model.Robotreply;
import com.hanson.service.RobotreplyService;

@Service
public class RobotreplyServiceImpl implements RobotreplyService {
	
	@Autowired
	private RobotreplyMapper robotreplyMapper;

	@Override
	public List<Robotreply> findRobotreplyList(String rulename) {
		// TODO Auto-generated method stub
		return robotreplyMapper.findRobotreplyList(rulename);
	}

	@Override
	public int insertKey(Robotreply robotreply) {
		// TODO Auto-generated method stub
		return robotreplyMapper.insertKey(robotreply);
	}

	@Override
	public int editKey(Robotreply robotreply) {
		// TODO Auto-generated method stub
		return robotreplyMapper.editKey(robotreply);
	}

	@Override
	public int deleteKey(Integer id) {
		// TODO Auto-generated method stub
		return robotreplyMapper.deleteKey(id);
	}

	@Override
	public Robotreply queryKeyDetail(Integer id) {
		// TODO Auto-generated method stub
		return robotreplyMapper.queryKeyDetail(id);
	}

	@Override
	public Robotreply receiveTheMessage() {
		// TODO Auto-generated method stub
		return robotreplyMapper.receiveTheMessage();
	}

	@Override
	public int editReceiveTheMessage(Robotreply robotreply) {
		// TODO Auto-generated method stub
		return robotreplyMapper.editReceiveTheMessage(robotreply);
	}

	@Override
	public List<Emoticon> emoticonList() {
		// TODO Auto-generated method stub
		return robotreplyMapper.emoticonList();
	}

}
