package com.hanson.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.ServiceprojectCompanyMapper;
import com.hanson.model.ServiceprojectCompany;
import com.hanson.service.ServiceprojectCompanyService;
@Service
public class ServiceprojectCompanyServiceImp implements ServiceprojectCompanyService {

	@Autowired
	private ServiceprojectCompanyMapper serviceprojectcompanyMapper;
	
	@Override
	public List<ServiceprojectCompany> findListByQyid(Integer qyid){
		return	serviceprojectcompanyMapper.findListByQyid(qyid);
	}
	@Override
	public Integer SaveprojectCompany(ServiceprojectCompany bean){
       return serviceprojectcompanyMapper.SaveprojectCompany(bean);		
	}
	@Override
	public Integer ModifyprojectCompany(ServiceprojectCompany bean){
		return serviceprojectcompanyMapper.ModifyprojectCompany(bean);
	}
	@Override
	public Integer deleteProjectCompany(Long projectid){
		return serviceprojectcompanyMapper.deleteProjectCompany(projectid);
	}
	@Override
	public List<Map<String, Object>> FindCompanyyuepaiList(Map<String,Object> map){
		return serviceprojectcompanyMapper.FindCompanyyuepaiList(map);
	}
	@Override
	public Integer modifyprice(Map<String,Object> map){
		return serviceprojectcompanyMapper.modifyprice(map);
	}
	
	@Override
	public ServiceprojectCompany GetOneProject(Map<String,Object> map){
		return serviceprojectcompanyMapper.GetOneProject(map);
	}
}
