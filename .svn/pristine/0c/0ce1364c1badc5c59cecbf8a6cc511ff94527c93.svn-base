package com.hanson.service;

import java.util.List;
import java.util.Map;

import com.hanson.common.util.Page;
import com.hanson.model.Company;

public interface CompanyService {
	
	/**
	 * 精确查找-根据ID
	 * @param id
	 * @return
	 */
	public Company findUniqueById(Long id);

	public Company FindCompanybyCode(String code);
	public Integer updateHeadStore(Company bean);
	/**
	 * 统计数量
	 * @param map
	 * @return
	 */
	public Long countTotles(Map<String, Object> map);
	public Integer setstoreauthority(Company company);
	public List<Company> findheadStore(String companyname);
	public Integer deleteCompany(Long id);
	/**
	 * 分页查询
	 * @param page 分页基础类
	 * @param map 查询条件
	 * @return
	 */
	public Page<Map<String, Object>> search(Page<Map<String, Object>> page, Map<String, Object> map);
	
	/**
	 * 保存新公司时的相关辅助操作
	 * @param bean
	 * @throws Exception
	 */
	public void saveNew(Company bean) throws Exception;
	
	public Integer updateMainStore(Company company);
	
	public Integer updatememberrate(Company company);
	
	public Integer createprometerurl(Company bean);
	/**
	 * 创建/更新
	 * @param bean
	 * @throws Exception
	 */
	public void save(Company bean) throws Exception;
	
	public void InsertNewCompany(Company bean) throws Exception;
	public void updatecompany(Company bean);
	/**
	 * 查询所有下属企业
	 * @param parentid
	 * @return
	 */
	public List<Company> findListByPid(Long parentid);
	
	/**
	 * 根据企业名称查询企业
	 * @param parentid
	 * @return
	 */
	public List<Company> findListLikeName(String companyname);
	
	/**
	 * 清理上属企业关联
	 * @param id
	 * @return
	 */
	public Integer clearParentid(Long id);
	
	/**
	 * 根据用户名查询数量
	 * @param map
	 * @return
	 */
	public Integer isLoginname(String loginname);
	
	/**
	 * 查询经销商所属上级--(汇美)
	 * @return
	 */
	public List<Company> findDistributParent(Long id);
	
	/**
	 * 查询区域制作中心和门店所属上级--(经销商)
	 * @return
	 */
	public List<Company> findMerchantParent(Long id);
	
	public List<Company> getprometerCenter(Company model);
	public List<Company> getStoreParent(Company model);
	
	public List<Company> getdistributParent(Company model);
	/**
	 * 查询推广中心所属上级--(商户：生产中心或门店)
	 * @return
	 */
	public List<Company> findPrometerParent(Long id);
	
	public List<Company> findPrometerCenter(Long prometercenterId);
	
	/**
	 * 查询推广员所属上级--(推广中心)
	 * @return
	 */
	public List<Company> findMemberParent();
	
	/**
	 * 查询可被汇美指派的生产中心或者门店
	 * @param company
	 * @return
	 */
	public List<Company> findHymnCenters(Company company);
	
	/**
	 * 同步相框数据(需谨慎操作)
	 * @param companyList
	 * @return
	 */
	public String initFrameData(List<Company> companyList);
	
}
