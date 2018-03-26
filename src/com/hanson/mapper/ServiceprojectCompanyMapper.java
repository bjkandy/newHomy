package com.hanson.mapper;
import java.util.List;
import java.util.Map;

import com.hanson.model.ServiceprojectCompany;

public interface ServiceprojectCompanyMapper {
	
	public List<ServiceprojectCompany> findListByQyid(Integer qyid);
	
	public Integer SaveprojectCompany(ServiceprojectCompany bean);
	
	public Integer ModifyprojectCompany(ServiceprojectCompany bean);
	
	public List<Map<String,Object>> FindyupaiProducts(Map<String,Object> map);
	
	public Integer deleteProjectCompany(Long projectid);
	
	public List<Map<String, Object>> FindCompanyyuepaiList(Map<String,Object> map);
	
	public Integer modifyprice(Map<String,Object> map);
	
	public ServiceprojectCompany GetOneProject(Map<String,Object> map);
}
