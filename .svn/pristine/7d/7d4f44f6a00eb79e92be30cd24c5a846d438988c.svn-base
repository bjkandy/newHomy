package com.hanson.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hanson.model.Company;

public interface UcompanyMapper {
	
	/**
	 * 精确查找-根据ID
	 * @param id
	 * @return
	 */
	public Company findUniqueById(Long id);
	
   public Company FindCompanybyCode(String code);
   
   
	/**
	 * 查询所有下属企业
	 * @param parentid
	 * @return
	 */
	public List<Company> findListByPid(Long parentid);
	
	public List<Company> findheadStore(@Param(value="companyname") String companyname );
	public Integer createprometerurl(Company bean);
	public Integer setstoreauthority(Company company);
	public Integer updateMainStore(Company company);
	public Integer updatememberrate(Company company);
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
	
	public Integer deleteCompany(Long id);
	/**
	 * 数据录入-新增
	 * @param Company
	 * @return
	 */
	public Integer insert(Company bean) throws Exception;
	
	/**
	 * 数据录入-更新
	 * @param Company
	 * @return
	 */
	public Integer updateOne(Company bean) throws Exception;
	
	public Integer updateHeadStore(Company bean);
	
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
	 * 查询经销商所属上级
	 * @return
	 */
	public List<Company> findDistributParent(Long id);
	
	/**
	 * 查询区域制作中心和门店所属上级
	 * @return
	 */
	public List<Company> findMerchantParent(Long id);
	
	public List<Company> getprometerCenter(Company model);
	public List<Company> getStoreParent(Company model);
	public List<Company> getdistributParent(Company model);
	/**
	 * 查询推广中心所属上级
	 * @return
	 */
	public List<Company> findPrometerParent(Long id);
	
	/**
	 * 查询可被汇美指定的生产中心(指定给推广中心)
	 * @param Company
	 * @return
	 */
	public List<Company> findHymnCenters(Company Company);
	
	/**
	 * 查询推广员所属上级
	 * @return
	 */
	public List<Company> findMemberParent();
	
	/**
	 * 查询生产中心或者商户(仅供给改部分商户初始化相框数据使用)
	 * @return
	 */
	public List<Company> searchMerchant();
	
}
