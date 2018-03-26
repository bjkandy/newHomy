package com.hanson.service.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.common.util.Md5Util;
import com.hanson.common.util.Page;
import com.hanson.common.util.SysConstant;
import com.hanson.mapper.ProductMapper;
import com.hanson.mapper.ProductinfoMapper;
import com.hanson.mapper.ServiceprojectCompanyMapper;
import com.hanson.mapper.SpecvalueMapper;
import com.hanson.mapper.StorePropertyMapper;
import com.hanson.mapper.UcompanyMapper;
import com.hanson.mapper.UserMapper;
import com.hanson.model.Company;
import com.hanson.model.Product;
import com.hanson.model.ProductVO;
import com.hanson.model.Productinfo;
import com.hanson.model.Specvalue;
import com.hanson.model.StoreProperty;
import com.hanson.model.User;
import com.hanson.service.CompanyService;

@Service
public class CompanyServiceImp implements CompanyService {

	@Autowired
	private UcompanyMapper ucompanyMapper;
	@Autowired
	private ServiceprojectCompanyMapper serviceprojectCompanyMapper;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private SpecvalueMapper specvalueMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ProductinfoMapper productinfoMapper;
	@Autowired
	private StorePropertyMapper storePropertyMapper;
	
	/**
	 * 精确查找-根据ID
	 * @param id
	 * @return
	 */
	public Company findUniqueById(Long id){
		return ucompanyMapper.findUniqueById(id);
	}
	
	public Integer deleteCompany(Long id){
		return ucompanyMapper.deleteCompany(id);
	}
	public Long countTotles(Map<String, Object> map){
		//统计数量
		return ucompanyMapper.countTotles(map);
	}
	public List<Company> findheadStore(String companyname){
		return ucompanyMapper.findheadStore(companyname);
	}
	public Page<Map<String, Object>> search(Page<Map<String, Object>> page, Map<String, Object> map){
		//分页查询通用参数
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
		//统计数量
		long totalCount = ucompanyMapper.countTotles(map);
		page.setTotalCount(totalCount);
		//填充数据
		if(totalCount >0){
			page.setResult(ucompanyMapper.search(map));
		}
		return page;
	}
	
	public Integer setstoreauthority(Company company){
		return ucompanyMapper.setstoreauthority(company);
	}
	public Integer createprometerurl(Company bean){
		return  ucompanyMapper.createprometerurl(bean);
	}
	/**
	 * 创建/更新
	 * @param bean
	 * @throws Exception
	 */
	
	public void updatecompany(Company bean){
		try{
		ucompanyMapper.updateOne(bean);  
		long qyid=bean.getId();
		StoreProperty storeProperty=storePropertyMapper.Findbycompanyid(qyid);
		if(storeProperty==null){
		storeProperty=new StoreProperty();
		storeProperty.setCompanyid(qyid);
		storeProperty.setChargemoney(0d);
		storeProperty.setChargerebate(0d);
		storeProperty.setMerchantrate(0d);
		storeProperty.setCreatetime(new Date());
		storePropertyMapper.insertStoreProperty(storeProperty);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", bean.getCompanyname());
		map.put("loginName", bean.getCode());
		map.put("email", bean.getEmail());
		map.put("address", bean.getAddress());
		map.put("qyid", bean.getId());
		userMapper.updateQyUser(map);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void save(Company bean) throws Exception {
		if(bean.getId() ==null){
			ucompanyMapper.insert(bean);
		}else{
			ucompanyMapper.updateOne(bean);  
			long qyid=bean.getId();
			StoreProperty storeProperty=storePropertyMapper.Findbycompanyid(qyid);
			if(storeProperty==null){
			storeProperty=new StoreProperty();
			storeProperty.setCompanyid(qyid);
			storeProperty.setChargemoney(0d);
			storeProperty.setChargerebate(0d);
			storeProperty.setMerchantrate(0d);
			storeProperty.setCreatetime(new Date());
			storePropertyMapper.insertStoreProperty(storeProperty);
			}
			//更新用户表
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", bean.getCompanyname());
			map.put("loginName", bean.getCode());
			map.put("email", bean.getEmail());
			map.put("address", bean.getAddress());
			map.put("qyid", bean.getId());
			userMapper.updateQyUser(map);
			//生成产品信息,产品附加信息,产品规格信息
			//----------------------------汇美相框<start>------------------------------------------------------
			//查询该商家是否已有相框产品
			/*Map<String, Object> productMap = new HashMap<String, Object>();
			productMap.put("qyid", bean.getId());
			productMap.put("producttype", 20);
			productMap.put("BEGIN_ROW", 0);
			productMap.put("PAGE_SIZE", 200);
			List<Map<String, Object>> productList = productMapper.search(productMap);*/
			//公司信息编辑页面勾选了相框
			/*if(bean.getPhotoframeenable() !=null && bean.getPhotoframeenable() == 1){
				if(productList != null && productList.size() > 0){
					//已有相框产品,什么也不做
				}else{
					//没有相框则新增记录到产品表, 产品附加信息表,产品属性规格表
					List<ProductVO> pmodelList = productMapper.findPhotoFrame();    // 回去汇美(管理后台设定的相框);
					if(pmodelList != null && pmodelList.size() > 0){
						for(ProductVO pd: pmodelList){
							List<Specvalue> pdSList = specvalueMapper.findUniqueByPid(pd.getID().longValue());     // 获得默认相框的规格属性
							//查询产品附加信息
							Productinfo productinfoT = new Productinfo();
							productinfoT.setProductid(pd.getID());
							productinfoT = productinfoMapper.findProductinfoByPId(productinfoT);
							//保存产品信息
							pd.setQyid(bean.getId().intValue());
							pd.setID(null);
							pd.setSellcount(0);
							productMapper.saveProduct(pd);
							//保存产品附加属性
							productinfoT.setProductid(pd.getID());
							productinfoMapper.saveProductinfo(productinfoT);
							//保存产品规格属性信息
							Integer pdID = pd.getID();
							for(Specvalue spec : pdSList) {
								spec.setProductid(pdID.longValue());
								spec.setSalecount(0);
								specvalueMapper.save(spec);
							}
						}
					}
				}
			}*/
			
		}
	}
	
	public Integer updateMainStore(Company company){
		return ucompanyMapper.updateMainStore(company);
	}
	public Integer updatememberrate(Company company){
		return ucompanyMapper.updatememberrate(company);
	}
	public Company FindCompanybyCode(String code){
		return ucompanyMapper.FindCompanybyCode(code);
	}
	public Integer updateHeadStore(Company bean){
		return ucompanyMapper.updateHeadStore(bean);
	}
	/**
	 * 保存新公司时的相关辅助操作
	 * @param bean
	 * @throws Exception
	 */
	public void InsertNewCompany(Company bean) throws Exception{
		bean.setCreatetime(new Date());
		bean.setPreferential(59d);
		bean.setProvincePostage(6d);
		bean.setNationPostage(6d);
		bean.setCityPostage(6d);
		bean.setPhotorate(1d);
		ucompanyMapper.insert(bean);
		bean.setHeadoffice(bean.getId());
		ucompanyMapper.updateHeadStore(bean);
		StoreProperty storeProperty=new StoreProperty();
		storeProperty.setCompanyid(bean.getId());
		storeProperty.setChargemoney(0d);
		storeProperty.setChargerebate(0d);
		storeProperty.setMerchantrate(0d);
		storeProperty.setCreatetime(new Date());
		storePropertyMapper.insertStoreProperty(storeProperty);
		User u=new User();
		u.setName(bean.getCompanyname());
		u.setLoginName(bean.getCode());
		u.setUsertype("1");//企业管理员
		String password=bean.getCode();
		u.setPassword(Md5Util.encoderByMd5(password));
		u.setEmail(bean.getEmail());
		u.setAddress(bean.getAddress());
		u.setQyid(bean.getId());
		u.setSex(0);
		userMapper.insert(u);
		
	}
	public void saveNew(Company bean) throws Exception {
		Date newdate = new Date();
		bean.setCreatetime(new Date());
		bean.setPreferential(59d);
		bean.setProvincePostage(6d);
		bean.setNationPostage(6d);
		bean.setCityPostage(6d);
		bean.setPhotorate(1d);
		//bean.setCompanyimg("uploadpic2/bagualing.png");
		save(bean);         // 保存公司信息
		Long qyid = bean.getId();
		bean.setHeadoffice(bean.getId());
		ucompanyMapper.updateHeadStore(bean);
		
		StoreProperty storeProperty=new StoreProperty();
		storeProperty.setCompanyid(qyid);
		storeProperty.setChargemoney(0d);
		storeProperty.setChargerebate(0d);
		storeProperty.setMerchantrate(0d);
		storeProperty.setCreatetime(new Date());
		storePropertyMapper.insertStoreProperty(storeProperty);
		User u=new User();
		u.setName(bean.getCompanyname());
		u.setLoginName(bean.getCode());
		u.setUsertype("2");//企业管理员
		String password=bean.getCode();
		u.setPassword(Md5Util.encoderByMd5(password));
		u.setEmail(bean.getEmail());
		u.setAddress(bean.getAddress());
		u.setQyid(bean.getId());
		u.setSex(0);
		userMapper.insert(u);
		/* 增加对应的冲印商品 */
		/*String modelid = SysConstant.DEFAULT_PHOTOFINISHING_ID; // 获得默认冲印商品id
		String catalogid = SysConstant.DEFAULT_PHOTOFINISHING_CATALOG; // 获得默认冲印商品id
		String qyidString = SysConstant.DEFAULT_QY_ID;*/
		/*if(!StringUtils.isEmpty(qyidString)){
//			Long qyidLong = new Long(qyidString);
			
//			List<ServiceprojectCompany> serviceprojectCompany = serviceprojectCompanyMapper.findListByQyid(qyidLong);
//			for (ServiceprojectCompany serviceprojectCompany2 : serviceprojectCompany) {
//				ServiceprojectCompany beans= new ServiceprojectCompany();
//				beans.setQyid(qyid.intValue());
//				beans.setProjectid(serviceprojectCompany2.getProjectid());
//				beans.setDeposit(serviceprojectCompany2.getDeposit());
//				beans.setTotalamount(serviceprojectCompany2.getTotalamount());
//				beans.setAppointmentcount(serviceprojectCompany2.getAppointmentcount());
//				beans.setShootingtype(serviceprojectCompany2.getShootingtype());
//				beans.setUpdownframestatus(serviceprojectCompany2.getUpdownframestatus());
//				beans.setNewdate(newdate);
//				beans.setBdelete(serviceprojectCompany2.getBdelete());
//				serviceprojectCompanyMapper.save(beans);//添加服务
//			}
		}*/
		
		/*if(!StringUtils.isEmpty(modelid)){
			Long pid = new Long(modelid);
			
			//----------------------------照片冲印<start>------------------------------------------------------
			Product pmodel = productMapper.findUniqueById(pid);    // 获得默认冲印产品entity
			Product product = new Product();
			product.setQyid(qyid);
			product.setName(pmodel.getName());
			product.setCatalogid(catalogid);
			product.setStatus(pmodel.getStatus());
			product.setScore(pmodel.getScore());
			product.setSortno(pmodel.getSortno());
			product.setBdelete(pmodel.getBdelete());
			product.setSpecnameids(pmodel.getSpecnameids());
			product.setKeywords(pmodel.getKeywords());
			product.setIsrecommend("0");
			productMapper.save(product);
					
			 增加对应的规格 
			Long productid = product.getId();
			List<Specvalue> list = specvalueMapper.findUniqueByPid(pid);     // 获得默认冲印产品的规格及价格
			for(Specvalue spec : list) {
				Specvalue svalue = new Specvalue();
				svalue.setProductid(productid);
				svalue.setIsvalid(spec.getIsvalid());
				svalue.setPrice(spec.getPrice());
				svalue.setOldprice(spec.getOldprice());
				svalue.setInventory(spec.getInventory());
				svalue.setIsdefault(spec.getIsdefault());
				svalue.setBdelete(spec.getBdelete());
				svalue.setProductioncost(0.5);
				svalue.setSalecount(1);
				svalue.setSize(spec.getSize());
				svalue.setSpecinfo(spec.getSpecinfo());
				specvalueMapper.save(svalue);
			}
			//----------------------------照片冲印<end>------------------------------------------------------
		}*/
		//----------------------------汇美相框<start>------------------------------------------------------
		/*if(bean.getPhotoframeenable() !=null && bean.getPhotoframeenable() == 1){
			List<ProductVO> pmodelList = productMapper.findPhotoFrame();    // 回去汇美(管理后台设定的相框);
			if(pmodelList != null && pmodelList.size() > 0){
				for(ProductVO pd: pmodelList){
					List<Specvalue> pdSList = specvalueMapper.findUniqueByPid(pd.getID().longValue());     // 获得默认相框的规格属性
					
					//查询产品附加信息
					Productinfo productinfoT = new Productinfo();
					productinfoT.setProductid(pd.getID());
					productinfoT = productinfoMapper.findProductinfoByPId(productinfoT);
					
					//保存产品
					pd.setQyid(qyid.intValue());
					pd.setID(null);
					pd.setSellcount(0);
					productMapper.saveProduct(pd);
					
					//保存产品附加属性
					productinfoT.setProductid(pd.getID());
					productinfoMapper.saveProductinfo(productinfoT);
					
					
					//保存产品规格
					Integer pdID = pd.getID();
					for(Specvalue spec : pdSList) {
						spec.setProductid(pdID.longValue());
						spec.setSalecount(0);
						specvalueMapper.save(spec);
					}
				}
			}
		}*/
		//----------------------------汇美相框<end>------------------------------------------------------
		
		
		
		
		
	}

	public List<Company> findListByPid(Long parentid) {
		return ucompanyMapper.findListByPid(parentid);
	}

	@Override
	public List<Company> findListLikeName(String companyname) {
		return ucompanyMapper.findListLikeName(companyname);
	}

	@Override
	public Integer clearParentid(Long id) {
		return ucompanyMapper.clearParentid(id);
	}

	@Override
	public Integer isLoginname(String loginname) {
		return userMapper.isLoginname(loginname);
	}

	@Override
	public List<Company> findDistributParent(Long id) {
		return ucompanyMapper.findDistributParent(id);
	}

	@Override
	public List<Company> findMerchantParent(Long id) {
		return ucompanyMapper.findMerchantParent(id);
	}
	@Override
	public List<Company> getdistributParent(Company model){
		return ucompanyMapper.getdistributParent(model);
	}
	@Override
	public List<Company> getprometerCenter(Company model){
		return ucompanyMapper.getprometerCenter(model);
	}
	@Override
	public List<Company> getStoreParent(Company model){
		return ucompanyMapper.getStoreParent(model);
	}
	@Override
	public List<Company> findPrometerParent(Long id) {
		return ucompanyMapper.findPrometerParent(id);
	}
	
	@Override
	public List<Company> findPrometerCenter(Long prometercenterId){
		return ucompanyMapper.findPrometerParent(prometercenterId);
	}
	
	@Override
	public List<Company> findHymnCenters(Company company) {
		return ucompanyMapper.findHymnCenters(company);
	}

	@Override
	public List<Company> findMemberParent() {
		return ucompanyMapper.findMemberParent();
	}

	/**
	 * 同步相框数据(需谨慎操作)
	 */
	/*@Override
	public String initFrameData(List<Company> companyList) {
		try {
			//查询门店或者生产中心的企业
			companyList = ucompanyMapper.searchMerchant();
			
			
			for(Company bean: companyList){
				//----------------------------汇美相框<start>------------------------------------------------------
				//查询该商家是否已有相框产品
				Map<String, Object> productMap = new HashMap<String, Object>();
				productMap.put("qyid", bean.getId());
				productMap.put("producttype", 20);
				productMap.put("BEGIN_ROW", 0);
				productMap.put("PAGE_SIZE", 20000);
				List<Map<String, Object>> productList = productMapper.search(productMap);
				//公司信息编辑页面勾选了相框
				if(bean.getPhotoframeenable() !=null && bean.getPhotoframeenable() == 1){
					if(productList != null && productList.size() > 0){
						//商户已有相框数据
						
						//查询汇美相框
						List<ProductVO> pmodelList = productMapper.findPhotoFrame();    // 查询汇美(管理后台设定的相框(qyid=148));
						
						for(ProductVO pv: pmodelList){
							productMapper.updateProduct(pv);
						}
						
						
						//productMapper.updateProduct();
						
						
						
						
						
						
						
						
						
						
						
					}else{
						//商户还没有相框数据
						
						//没有相框则新增记录到产品表, 产品附加信息表,产品属性规格表
						List<ProductVO> pmodelList = productMapper.findPhotoFrame();    // 回去汇美(管理后台设定的相框);
						if(pmodelList != null && pmodelList.size() > 0){
							for(ProductVO pd: pmodelList){
								List<Specvalue> pdSList = specvalueMapper.findUniqueByPid(pd.getID().longValue());     // 获得默认相框的规格属性
								
								//查询产品附加信息
								Productinfo productinfoT = new Productinfo();
								productinfoT.setProductid(pd.getID());
								productinfoT = productinfoMapper.findProductinfoByPId(productinfoT);
								
								//保存产品信息
								pd.setQyid(bean.getId().intValue());
								pd.setID(null);
								pd.setSellcount(0);
								productMapper.saveProduct(pd);
								
								//保存产品附加属性
								productinfoT.setProductid(pd.getID());
								productinfoMapper.saveProductinfo(productinfoT);
								
								//保存产品规格属性信息
								Integer pdID = pd.getID();
								for(Specvalue spec : pdSList) {
									spec.setProductid(pdID.longValue());
									spec.setSalecount(0);
									specvalueMapper.save(spec);
								}
							}
						}
					}
				}
				//----------------------------汇美相框<end>------------------------------------------------------
				
			}
			return "ok";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		
		
	}*/
	
	/*@Override
	public String initFrameData(List<Company> companyList) {
		try {
			//查询门店或者生产中心的企业
			companyList = ucompanyMapper.searchMerchant();
			
			
			
			
			for(Company bean: companyList){
				//----------------------------汇美相框<start>------------------------------------------------------
				//查询该商家是否已有相框产品
				Map<String, Object> productMap = new HashMap<String, Object>();
				productMap.put("qyid", bean.getId());
				productMap.put("producttype", 20);
				productMap.put("BEGIN_ROW", 0);
				productMap.put("PAGE_SIZE", 1);
				List<Map<String, Object>> productList = productMapper.search(productMap);
				//公司信息编辑页面勾选了相框
				if(bean.getPhotoframeenable() !=null && bean.getPhotoframeenable() == 1){
					if(productList != null && productList.size() > 0){
						//商户已有相框数据
						
						//查询汇美相框
						
						//没有相框则新增记录到产品表, 产品附加信息表,产品属性规格表
						List<ProductVO> pmodelList = productMapper.findPhotoFrame();    // 回去汇美(管理后台设定的相框);
						
						//productMapper.updateProduct();
						if(pmodelList != null && pmodelList.size() > 0){
							for(ProductVO pd: pmodelList){
								//List<Specvalue> pdSList = specvalueMapper.findUniqueByPid(pd.getID().longValue());     // 获得默认相框的规格属性
								
								//查询产品附加信息
								Productinfo productinfoT = new Productinfo();
								productinfoT.setProductid(pd.getID());
								productinfoT = productinfoMapper.findProductinfoByPId(productinfoT);
								
								//更新产品信息---------------<start>
								
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("qyid", bean.getId());
								map.put("name", pd.getName());
								map.put("BEGIN_ROW", 0);
								map.put("PAGE_SIZE", 1);
								
								//查询产品(old)
								List<Map<String, Object>> pvList = productMapper.search(map);
								if(pvList != null && pvList.size() > 0){
									pd.setID(Integer.parseInt(pvList.get(0).get("id").toString()));
									pd.setQyid(Integer.parseInt(bean.getId().toString()));
									pd.setSellcount(null);
									pd.setStatus(null);
									pd.setStandardprice(null);
									pd.setPlastic(null);
									productMapper.updateProduct(pd);
								}
								
								//查询产品附件信息(old)
								Productinfo pdi = new Productinfo();
								pdi.setProductid(Integer.parseInt(pvList.get(0).get("id").toString()));
								pdi = productinfoMapper.findProductinfoByPId(pdi);
								if(pdi != null){
									//更新产品附加属性
									productinfoT.setId(pdi.getId());
									productinfoT.setProductid(pdi.getProductid());
									
									productinfoMapper.updateProductinfo(productinfoT);
								}
								
								
								//跟新产品规格信息比较复杂(要考虑增删改的情况)
								//查询当前产品下的规格属性
								List<Specvalue> specvalueList = specvalueMapper.findUniqueByPid(pd.getID().longValue());
								
								//查询商户规格属性
								List<Specvalue> oldSpeList = new ArrayList<Specvalue>();
								if(pvList != null && pvList.size() > 0){
									oldSpeList = specvalueMapper.findUniqueByPid(Long.parseLong(pvList.get(0).get("id").toString()));
								}
								
								if(specvalueList != null && specvalueList.size() > 0){
									//第一次双循环修改并删除数据
									for(Specvalue oldSpe: oldSpeList){
										boolean flag = false;
										String oldSize = oldSpe.getSize();
										String oldColor = oldSpe.getColor();
										for(Specvalue newSpe: specvalueList){
											String newSize = newSpe.getSize();
											String newColor = newSpe.getColor();
											if(oldSize.equals(newSize) && oldColor.equals(newColor)){
												//specvalueService.updateSpecvalue(newSpe);
												newSpe.setPrice(null);
												newSpe.setPackageprice(null);
												newSpe.setOldprice(null);
												newSpe.setPlastic(null);
												newSpe.setPlasticprice(null);
												newSpe.setIsvalid(null);
												specvalueMapper.updateSpecvalue(newSpe);
												flag = false;
												break;
											}else{
												flag = true;
											}
										}
										if(flag){
											//删除旧的产品规格属性
											//specvalueService.deleteSpecvalue(oldSpe);
											specvalueMapper.deleteSpecvalue(oldSpe);
										}
									}
									
									//第二次双循环新增数据
									for(Specvalue newSpe: specvalueList){
										boolean flag = false;
										String newSize = newSpe.getSize();
										String newColor = newSpe.getColor();
										for(Specvalue oldSpe: oldSpeList){
											String oldSize = oldSpe.getSize();
											String oldColor = oldSpe.getColor();
											if(oldSize.equals(newSize) && oldColor.equals(newColor)){
												flag = false;
												break;
											}else{
												flag = true;
											}
										}
										if(flag){
											//新增新的产品规格属性
											newSpe.setProductid(Long.parseLong(pvList.get(0).get("id").toString()));
											specvalueMapper.save(newSpe);
										}
									}
									
								}
							}
						}
					}else{
						//商户还没有相框数据
						//没有相框则新增记录到产品表, 产品附加信息表,产品属性规格表
						List<ProductVO> pmodelList = productMapper.findPhotoFrame();    // 回去汇美(管理后台设定的相框);
						
						if(pmodelList != null && pmodelList.size() > 0){
							for(ProductVO pd: pmodelList){
								List<Specvalue> pdSList = specvalueMapper.findUniqueByPid(pd.getID().longValue());     // 获得默认相框的规格属性
								
								//查询产品附加信息
								Productinfo productinfoT = new Productinfo();
								productinfoT.setProductid(pd.getID());
								productinfoT = productinfoMapper.findProductinfoByPId(productinfoT);
								
								//保存产品信息
								pd.setQyid(bean.getId().intValue());
								pd.setID(null);
								pd.setSellcount(0);
								productMapper.saveProduct(pd);
								
								//保存产品附加属性
								productinfoT.setProductid(pd.getID());
								productinfoMapper.saveProductinfo(productinfoT);
								
								//保存产品规格属性信息
								Integer pdID = pd.getID();
								for(Specvalue spec : pdSList) {
									spec.setProductid(pdID.longValue());
									spec.setSalecount(0);
									specvalueMapper.save(spec);
								}
							}
						}
					}
				}
				//----------------------------汇美相框<end>------------------------------------------------------
				
			}
			return "ok";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		
		
	}*/
	
	@Override
	public String initFrameData(List<Company> companyList) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println("=============================="+df.format(new Date())+"===============================同步数据开始<start>");
		try {
			//查询门店或者生产中心的企业
			companyList = ucompanyMapper.searchMerchant();
			
			//没有相框则新增记录到产品表, 产品附加信息表,产品属性规格表
			List<ProductVO> pmodelList = productMapper.findPhotoFrame();    // 回去汇美(管理后台设定的相框);
			if(pmodelList != null && pmodelList.size() > 0){
				for(ProductVO pd: pmodelList){
					//查询产品附加信息
					Productinfo productinfoT = new Productinfo();
					productinfoT.setProductid(pd.getID());
					productinfoT = productinfoMapper.findProductinfoByPId(productinfoT);
					pd.setProductinfo(productinfoT);
					List<Specvalue> specvalueSList = specvalueMapper.findUniqueByPid(pd.getID().longValue());     // 获得默认相框的规格属性
					if(specvalueSList != null && specvalueSList.size() > 0){
						pd.setSpecvalueList(specvalueSList);
					}
				}
				
			}
			
			//循环商户表同步数据
			/*if(pmodelList != null && pmodelList.size() > 0){
				for(Company bean: companyList){
					//商户还没有相框数据
					for(ProductVO pd: pmodelList){
						
						//保存产品信息
						pd.setQyid(bean.getId().intValue());
						pd.setID(null);
						pd.setSellcount(0);
						productMapper.saveProduct(pd);
						
						//保存产品附加属性
						Productinfo productinfoT = pd.getProductinfo();
						productinfoT.setProductid(pd.getID());
						productinfoMapper.saveProductinfo(productinfoT);
						
						//保存产品规格属性信息
						Integer pdID = pd.getID();
						List<Specvalue> speList = pd.getSpecvalueList();
						if(speList != null && speList.size() > 0){
							for(Specvalue spec : speList) {
								spec.setProductid(pdID.longValue());
								spec.setSalecount(0);
								specvalueMapper.save(spec);
							}
						}
						
					}
				}
			}*/
//			if(pmodelList != null && pmodelList.size() > 0){
//				for(ProductVO pd: pmodelList){
//					List<Map<String ,Object>> data = new ArrayList<Map<String ,Object>>();
//					for(Company bean: companyList){
//						Map<String ,Object> i = new HashMap<String ,Object>();
//						i.put("ID", null);
//						i.put("companyid", bean.getId());
//						i.put("name", pd.getName());
//						i.put("mainimg", pd.getMainimg());
//						data.add(i);
//					}
//					
//				}
				
				List<Map<String ,Object>> data = new ArrayList<Map<String ,Object>>();
				Map<String ,Object> i = new HashMap<String ,Object>();
				i.put("ID", null);
				i.put("name", "789");
				i.put("qyid", "591");
				i.put("isrecommend", 1);
				i.put("status", 1);
				i.put("score", 1);
				i.put("sort", 1);
				i.put("bdelete", 1);
				i.put("specnameids", 1);
				i.put("standardprice", 1);
				i.put("mainimg", 1);
				i.put("scrollimg", 1);
				i.put("brand", 1);
				data.add(i);
				Map<String ,Object> j = new HashMap<String ,Object>();
				i.put("ID", null);
				i.put("name", "789");
				i.put("qyid", "591");
				i.put("isrecommend", 1);
				i.put("status", 1);
				i.put("score", 1);
				i.put("sort", 1);
				i.put("bdelete", 1);
				i.put("specnameids", 1);
				i.put("standardprice", 1);
				i.put("mainimg", 1);
				i.put("scrollimg", 1);
				i.put("brand", 1);
				
				data.add(j);
				
				List<Map<String ,Object>> a = productMapper.saveProductList(data);
				System.out.print(a);
				
				
				/*for(Company bean: companyList){
					//商户还没有相框数据
					for(ProductVO pd: pmodelList){
						//保存产品信息
						pd.setQyid(bean.getId().intValue());
						pd.setID(null);
						pd.setSellcount(0);
						productMapper.saveProduct(pd);
						//保存产品附加属性
						Productinfo productinfoT = pd.getProductinfo();
						productinfoT.setProductid(pd.getID());
						productinfoMapper.saveProductinfo(productinfoT);
						//保存产品规格属性信息
						Integer pdID = pd.getID();
						List<Specvalue> speList = pd.getSpecvalueList();
						if(speList != null && speList.size() > 0){
							for(Specvalue spec : speList) {
								spec.setProductid(pdID.longValue());
								spec.setSalecount(0);
//								specvalueMapper.save(spec);
							}
						}
					}
				}*/
//			}
			
			System.out.println("=============================="+df.format(new Date())+"===============================同步数据开始<start>");
			return "ok";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "fail";
		}
		
		
	}
	
	

}
