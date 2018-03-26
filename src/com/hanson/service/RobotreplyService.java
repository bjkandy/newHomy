package com.hanson.service;

import java.util.List;

import com.hanson.model.Emoticon;
import com.hanson.model.Robotreply;

public interface RobotreplyService {
	/**
	 * 查询关键字回复
	 * 
	 * @param rulename
	 *            关键字或规则名称
	 * @return
	 */
	public List<Robotreply> findRobotreplyList(String rulename);

	/**
	 * 新增关键字回复
	 * 
	 * @param robotreply
	 * @return
	 */
	public int insertKey(Robotreply robotreply);

	/**
	 * 修改关键字回复
	 * 
	 * @param robotreply
	 * @return
	 */
	public int editKey(Robotreply robotreply);

	/**
	 * 删除关键字回复
	 * 
	 * @param id
	 * @return
	 */
	public int deleteKey(Integer id);

	/**
	 * 查询关键字回复详情
	 * 
	 * @param id
	 * @return
	 */
	public Robotreply queryKeyDetail(Integer id);

	/**
	 * 查询收到消息回复
	 * 
	 * @return
	 */
	public Robotreply receiveTheMessage();

	/**
	 * 修改收到消息回复
	 * 
	 * @param robotreply
	 * @return
	 */
	public int editReceiveTheMessage(Robotreply robotreply);

	/**
	 * 查询表情包
	 * 
	 * @return
	 */
	public List<Emoticon> emoticonList();
}
