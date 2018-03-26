package com.hanson.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.DataCenterMapper;
import com.hanson.service.DataCenterService;
import common.CommonServiceImpl;
import common.ServiceCommonException;
import common.StringUtils;

@Service
public class DataCenterServiceImpl implements DataCenterService {
	
	@Autowired
	private DataCenterMapper dataCenterMapper;

	
	/**
	 * 商户数据查询
	 * @param String
	 * @return
	 */
	public String merchantDataCount(Map<String ,Object> data){
		
		CommonServiceImpl.checkPageParam(data);
		if(StringUtils.isBlank((String)data.get("startTime"))){
			throw new ServiceCommonException("4001","startTime ：起始时间不能为空！");
		}
		if(StringUtils.isBlank((String)data.get("endTime"))){
			throw new ServiceCommonException("4001","endTime ：截止时间不能为空！");
		}
		if(Integer.parseInt(String.valueOf(data.get("dateType"))) != 0 && Integer.parseInt(String.valueOf(data.get("dateType"))) != 1){
			throw new ServiceCommonException("4001","dateType:"+Integer.parseInt((String)data.get("dateType"))+"...时间类型错误！");
		}else{
			data.put("dateType", Integer.parseInt(String.valueOf(data.get("dateType"))));
		}
		if((int)(Integer)data.get("dateType") == 1){
			data.put("startTime", String.valueOf(data.get("startTime"))+"-00");
			data.put("endTime", String.valueOf(data.get("endTime"))+"-00");
		}
		
		String re = dataCenterMapper.merchantDataCount(data);
		if(re.isEmpty()){
			throw new ServiceCommonException("4001","商户数据数量为空！");
		}
	
		return re;
	}
	/**
	 * 商户数据查询
	 * @param map
	 * @return
	 */
	public List<Map<String ,Object>> merchantData(Map<String ,Object> data){
		
		CommonServiceImpl.checkPageParam(data);
		if(StringUtils.isBlank((String)data.get("startTime"))){
			throw new ServiceCommonException("4001","startTime ：起始时间不能为空！");
		}
		if(StringUtils.isBlank((String)data.get("endTime"))){
			throw new ServiceCommonException("4001","endTime ：截止时间不能为空！");
		}
		if(data.get("dateType") == null || (Integer.parseInt(String.valueOf(data.get("dateType"))) != 0 && Integer.parseInt(String.valueOf(data.get("dateType"))) != 1)){
			throw new ServiceCommonException("4001","dateType:"+Integer.parseInt((String)data.get("dateType"))+"...时间类型错误！");
		}else{
			data.put("dateType", Integer.parseInt(String.valueOf(data.get("dateType"))));
		}
		if((int)(Integer)data.get("dateType") == 1){
			data.put("startTime", String.valueOf(data.get("startTime"))+"-00");
			data.put("endTime", String.valueOf(data.get("endTime"))+"-00");
		}
		
		List<Map<String,Object>> re = dataCenterMapper.merchantData(data);
		if(re.isEmpty()){
			throw new ServiceCommonException("4001","商户数据为空！");
		}
		LinkedList<Map<String,Object>> link = new LinkedList<Map<String,Object>>();
		//10为区域制作中心;20为推广中心;30为推广员;40为经销商;50为门店
		for(Map<String ,Object> i : re){
			
			if(!StringUtils.isBlank((String)data.get("active")) && ((String)data.get("active")).equals("1")){
				if(Integer.parseInt(String.valueOf(i.get("newUserNumber"))) == 0 && Integer.parseInt(String.valueOf(i.get("odrNumber"))) == 0){
					continue;
				}			
			}

			if(((String)i.get("companytype")).equals("10")){
				i.put("companytype", "区域制作中心");
			}else if(((String)i.get("companytype")).equals("20")){
				i.put("companytype", "推广中心");
			}else if(((String)i.get("companytype")).equals("30")){
				i.put("companytype", "推广员");
			}else if(((String)i.get("companytype")).equals("40")){
				i.put("companytype", "经销商");
			}else if(((String)i.get("companytype")).equals("50")){
				i.put("companytype", "门店");
			}else {
				i.put("companytype", "");
			}
			
			i.put("id",((Integer)i.get("id")).toString());
			i.put("newUserNumber", ((BigDecimal)i.get("newUserNumber")).toString());
			i.put("userNumber", ((Long)i.get("userNumber")).toString());
			i.put("payOdrNumber", ((BigDecimal)i.get("payOdrNumber")).toString());
			i.put("odrNumber", ((Long)i.get("odrNumber")).toString());
			i.put("createdate", new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format((Date)i.get("createdate")));
			i.put("odrTotalPrice", ((BigDecimal)i.get("odrTotalPrice")).doubleValue()>0.0?((BigDecimal)i.get("odrTotalPrice")).doubleValue()/100:0.0);
			i.put("payOdrTotalprice",((BigDecimal)i.get("payOdrTotalprice")).doubleValue()>0.0?((BigDecimal)i.get("payOdrTotalprice")).doubleValue()/100:0.0);
			
			link.add(i);
			
		}
		
		return link;
	}
	
	
	/**
	 * 商户数据分析查询
	 * @param map
	 * @return
	 */
	public String merchantDataAnalyzeCount(Map<String ,Object> data){
		
		//数据校验
		CommonServiceImpl.checkPageParam(data);
		if(StringUtils.isBlank((String)data.get("startTime"))){
			throw new ServiceCommonException("4001","startTime ："+(String)data.get("startTime")+"起始时间不能为空！");
		}
		if(StringUtils.isBlank((String)data.get("endTime"))){
			throw new ServiceCommonException("4001","endTime ："+(String)data.get("endTime")+"截止时间不能为空！");
		}
		if(Integer.parseInt(String.valueOf(data.get("dateType"))) != 0 && Integer.parseInt(String.valueOf(data.get("dateType"))) != 1){
			throw new ServiceCommonException("4001","dateType:"+Integer.parseInt((String)data.get("dateType"))+"...时间类型错误！");
		}else{
			data.put("dateType", Integer.parseInt(String.valueOf(data.get("dateType"))));
		}
		if((int)(Integer)data.get("dateType") == 1){
			data.put("startTime", String.valueOf(data.get("startTime"))+"-00");
			data.put("endTime", String.valueOf(data.get("endTime"))+"-00");
		}
		//商户数据数量
		String re = dataCenterMapper.merchantDataAnalyzeCount(data);
		if(re.isEmpty()){
			throw new ServiceCommonException("4001","商户数据分析数量为空！");
		}
		return re;
	}
	/**
	 * 商户数据分析查询
	 * @param map
	 * @return
	 */
	public Map<String ,Object> merchantDataAnalyze(Map<String ,Object> data){
		
		//数据校验
		CommonServiceImpl.checkPageParam(data);
		if(StringUtils.isBlank((String)data.get("startTime"))){
			throw new ServiceCommonException("4001","startTime ："+(String)data.get("startTime")+"起始时间不能为空！");
		}
		if(StringUtils.isBlank((String)data.get("endTime"))){
			throw new ServiceCommonException("4001","endTime ："+(String)data.get("endTime")+"截止时间不能为空！");
		}
		if(Integer.parseInt(String.valueOf(data.get("dateType"))) != 0 && Integer.parseInt(String.valueOf(data.get("dateType"))) != 1){
			throw new ServiceCommonException("4001","dateType:"+Integer.parseInt((String)data.get("dateType"))+"...时间类型错误！");
		}else{
			data.put("dateType", Integer.parseInt(String.valueOf(data.get("dateType"))));
		}
		if((int)(Integer)data.get("dateType") == 1){
			data.put("startTime", String.valueOf(data.get("startTime")));
			data.put("endTime", String.valueOf(data.get("endTime")));
		}
		//商户数据分析
		List<Map<String,Object>> re = dataCenterMapper.merchantDataAnalyze(data);
		if(re.isEmpty()){
			throw new ServiceCommonException("4001","商户数据分析为空！");
		}
		//当前时间段之前的商户数量
		String merchantNumber = dataCenterMapper.oldMerchantNumber(data);
		if(StringUtils.isBlank(merchantNumber)){
			merchantNumber = "0";
		}
		int currentMerchantNumber = Integer.parseInt(merchantNumber);
		int totalNewMerchantNumber = 0;
		int totalQyNumber = 0;
		int totalPrometerNumber = 0;
		int totalProductNumber = 0;
		int totalMerchantNumber = 0;
		int totalDistributNumber = 0;
		int totalActiveMerchandNumber = 0;
		for(int i=re.size()-1;i>=0;i--){
			currentMerchantNumber = currentMerchantNumber+((Long)re.get(i).get("newMerchantNumber")).intValue(); 
			re.get(i).put("currentMerchantNumber", currentMerchantNumber);
			
			totalNewMerchantNumber = totalNewMerchantNumber+((Long)re.get(i).get("newMerchantNumber")).intValue(); 
			totalQyNumber = totalQyNumber+((BigDecimal)re.get(i).get("qyNumber")).intValue(); 
			totalPrometerNumber = totalPrometerNumber+((BigDecimal)re.get(i).get("prometerNumber")).intValue(); 
			totalProductNumber = totalProductNumber+((BigDecimal)re.get(i).get("productNumber")).intValue(); 
			totalMerchantNumber = totalMerchantNumber+((BigDecimal)re.get(i).get("merchantNumber")).intValue(); 
			totalDistributNumber = totalDistributNumber+((BigDecimal)re.get(i).get("distributNumber")).intValue(); 
			totalActiveMerchandNumber = totalActiveMerchandNumber+((Long)re.get(i).get("activeMerchandNumber")).intValue(); 
			
		}
		Map<String ,Object> res = new HashMap<String,Object>();
//		List<Map<String,Object>> result =  new ArrayList<Map<String,Object>>();
//		if((int)(Integer)data.get("startLin") < re.size()){
//			int startLin = (int)(Integer)data.get("startLin");
//			for(int i=startLin;i<startLin+(int)(Integer)data.get("pageSize") && i<re.size();i++){
//				result.add(re.get(i));
//			}
//		}else{
//			throw new ServiceCommonException("4001","数据为空！");
//		}
//		res.put("rows", result);
		res.put("rows", re);
//		res.put("total", re.size());
		res.put("currentMerchantNumber", currentMerchantNumber);
		res.put("totalNewMerchantNumber", totalNewMerchantNumber);
		res.put("totalQyNumber", totalQyNumber);
		res.put("totalPrometerNumber", totalPrometerNumber);
		res.put("totalProductNumber", totalProductNumber);
		res.put("totalMerchantNumber", totalMerchantNumber);
		res.put("totalDistributNumber", totalDistributNumber);
		res.put("totalActiveMerchandNumber", totalActiveMerchandNumber);
		return res;
		
	}
	
	/**
	 * 商户数据分析查询导出excel
	 * @param map
	 * @return
	 */
	public List<Map<String ,Object>> merchantDataAnalyzeExcel(Map<String ,Object> data){
		
		//数据校验
		if(StringUtils.isBlank((String)data.get("startTime"))){
			throw new ServiceCommonException("4001","startTime ："+(String)data.get("startTime")+"起始时间不能为空！");
		}
		if(StringUtils.isBlank((String)data.get("endTime"))){
			throw new ServiceCommonException("4001","endTime ："+(String)data.get("endTime")+"截止时间不能为空！");
		}
		if(Integer.parseInt(String.valueOf(data.get("dateType"))) != 0 && Integer.parseInt(String.valueOf(data.get("dateType"))) != 1){
			throw new ServiceCommonException("4001","dateType:"+Integer.parseInt((String)data.get("dateType"))+"...时间类型错误！");
		}else{
			data.put("dateType", Integer.parseInt(String.valueOf(data.get("dateType"))));
		}
		if((int)(Integer)data.get("dateType") == 1){
			data.put("startTime", String.valueOf(data.get("startTime"))+"-00");
			data.put("endTime", String.valueOf(data.get("endTime"))+"-00");
		}
		//商户分析数据
		List<Map<String,Object>> re = dataCenterMapper.merchantDataAnalyzeExcel(data);
		if(re.isEmpty()){
			throw new ServiceCommonException("4001","商户分析数据为空！");
		}
		//当前时间段之前的商户数量
		String merchantNumber = dataCenterMapper.oldMerchantNumber(data);
		if(StringUtils.isBlank(merchantNumber)){
			merchantNumber = "0";
		}
		int currentMerchantNumber = Integer.parseInt(merchantNumber);
		for(int i=re.size()-1;i>=0;i--){
			currentMerchantNumber = currentMerchantNumber+((Long)re.get(i).get("newMerchantNumber")).intValue(); 
			re.get(i).put("currentMerchantNumber", currentMerchantNumber);
		}
		
		return re;
	}
	
	
}
