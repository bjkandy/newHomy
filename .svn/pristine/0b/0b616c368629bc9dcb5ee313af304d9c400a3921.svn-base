package com.hanson.mapper;
import java.util.List;
import java.util.Map;

import com.hanson.model.Seconduser;
import com.hanson.serviceModel.SeconduserQuery;

public interface SeconduserMapper {
	public List<Seconduser> findUserList(Seconduser seconduser);
	
	/**
	 * 根据openid 查询用户信息
	 * @param openid
	 * @return
	 */
	public Seconduser getUniqueBy(String openid);
	
	/**
	 * 保存用户信息
	 * @param openid
	 * @return
	 */
	public Seconduser save(Seconduser seconduser);
	
	/**
	 * 查询粉丝增量
	 * @param map
	 * @return
	 */
	public Integer findFansadd(Map<String, Object> map);
	
	/**
	 * 分页查询-查询数据
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> search(Map<String, Object> map);
	
	/**
	 * 分页查询-统计总数
	 * @param map
	 * @return
	 */
	public long countTotles(Map<String, Object> map);
	
	/**
	 * 粉丝分析
	 * @param map
	 * @return
	 */
	public Map<String, Object> tadeilUserData(Map<String, Object> map);
}
