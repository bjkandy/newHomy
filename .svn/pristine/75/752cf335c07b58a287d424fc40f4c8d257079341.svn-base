package com.hanson.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanson.service.DataCenterService;

import common.CommonServiceImpl;
import common.Result;
import common.ServiceCommonException;
import common.StringUtils;


@Controller
@RequestMapping("/dataCenter")
public class DataCenterController {
	
	final Logger logger = LoggerFactory.getLogger(DataCenterController.class);
	@Autowired
	private DataCenterService dataCenterService;
	@Value("${netApi.downloadImg.excel}")
	private String excelUrl;
	
	/**
	 * 商户数据查询
	 * @param id 产品id
	 * @param companyname  产品名称
	 * @praam companytype  产品类型
	 * @param startTime 查询开始时间
	 * @param endTime  查询的截止时间
	 * @param pageSize 每页数量
	 * @param pageNo  页码
	 * @param dateType 时间类型(0：按日分  1：按月分)
	 * @param active 是否选择活跃用户(0：全部用户  1：活跃用户)
	 * @return Result<?>
	 */
	@RequestMapping("/merchantData")
	@ResponseBody
	public Result<?> merchantData(HttpServletRequest request,HttpServletResponse response){
		
		Map<String ,Object> data = new HashMap<String,Object>();
		data.put("id", request.getParameter("id"));
		data.put("companyname", request.getParameter("companyname"));
		data.put("companytype", request.getParameter("companytype"));
		data.put("startTime", request.getParameter("startTime"));
		data.put("endTime", request.getParameter("endTime"));
		data.put("pageSize", request.getParameter("pageSize"));
		data.put("pageNo", request.getParameter("pageNo"));
		data.put("dateType", request.getParameter("dateType"));
		data.put("active", request.getParameter("active"));
		
		if(!String.valueOf(request.getParameter("companyname")).isEmpty()){
			try{
				data.put("companyname", new String(String.valueOf(data.get("companyname")).getBytes("iso8859-1"),"utf-8"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//查询数据数量
		String count = dataCenterService.merchantDataCount(data);
		if(count.equals("0") || StringUtils.isBlank(count)){
			throw new ServiceCommonException("4001","商户数据为空！");
		}
		//查询数据
		List<Map<String ,Object>> res = dataCenterService.merchantData(data);
		Map<String ,Object> re = new HashMap<String,Object>();
		re.put("rows", res);
		re.put("total", Integer.parseInt(count));
		return Result.getSuccess(re);
	}
	
	/**
	 * 商户数据查询excel
	 * @param id 产品id
	 * @param companyname  产品名称
	 * @praam companytype  产品类型
	 * @param startTime 查询开始时间
	 * @param endTime  查询的截止时间
	 * @param dateType 时间类型(0：按日分  1：按月分)
	 * @param active 是否选择活跃用户(0：全部用户  1：活跃用户)
	 * @return Result<?>
	 */
	@RequestMapping("/merchantDataExcel")
	@ResponseBody
	public Result<?> merchantDataExcel(HttpServletRequest request,HttpServletResponse response){
		
		Map<String ,Object> data = new HashMap<String,Object>();
		data.put("id", request.getParameter("id"));
		data.put("companyname", request.getParameter("companyname"));
		data.put("companytype", request.getParameter("companytype"));
		data.put("startTime", request.getParameter("startTime"));
		data.put("endTime", request.getParameter("endTime"));
		data.put("dateType", request.getParameter("dateType"));
		data.put("pageSize", "9999999");
		data.put("pageNo", "1");
		data.put("active", request.getParameter("active"));
		if(!String.valueOf(request.getParameter("companyname")).isEmpty()){
			try{
				data.put("companyname", new String(String.valueOf(data.get("companyname")).getBytes("iso8859-1"),"utf-8"));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//查询数据
		List<Map<String ,Object>> res = dataCenterService.merchantData(data);
		//创建列名
		List<String> name = new ArrayList<String>();
		name.add("id");
		name.add("companyname");
		name.add("newUserNumber");
		name.add("userNumber");
		name.add("payOdrNumber");
		name.add("odrNumber");
		name.add("payOdrTotalprice");
		name.add("odrTotalPrice");
		name.add("createdate");
		name.add("companytype");
		name.add("provincename");
		name.add("mobile");
		name.add("phone");
		
		List<String> chname = new ArrayList<String>();
		chname.add("商户编号");
		chname.add("商户名称");
		chname.add("新增粉丝数");
		chname.add("累计粉丝数");
		chname.add("付款订单总数");
		chname.add("订单总数");
		chname.add("付款订单总金额(元)");
		chname.add("订单总金额(元)");
		chname.add("注册时间");
		chname.add("商户类型");
		chname.add("省");
		chname.add("手机");
		chname.add("电话");
		
		int[] width = new int[14];
		width[0]=2000;
		width[1]=3000;
		width[2]=5000;
		width[3]=4000;
		width[4]=4000;
		width[5]=4000;
		width[6]=4000;
		width[7]=6000;
		width[8]=5000;
		width[9]=6000;
		width[10]=4000;
		width[11]=4000;
		width[12]=5000;
		width[13]=5000;
		
		//文件名
    	String realPath = excelUrl+"merchantDataExcel";
		//写数据到Excel 在服务器创建临时数据文件
		CommonServiceImpl.createExcel(res,name,chname,width,realPath);
		//下载excel
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String fileName= time+"-merchantDataExcel-"+System.currentTimeMillis();
		CommonServiceImpl.downloadExcel(request, response, fileName, realPath);
		
		return Result.newSuccessResult();
	}
	
	/**
	 * 商户数据分析查询
	 * @param startTime 查询开始时间
	 * @param endTime  查询的截止时间
	 * @param pageSize 每页数量
	 * @param pageNo  页码
	 * @param dateType 时间类型(0：按日分  1：按月分)
	 * @return Result<?>
	 */
	@RequestMapping("/merchantDataAnalyze")
	@ResponseBody
	public Result<?> merchantDataAnalyze(HttpServletRequest request,HttpServletResponse response){
		
		Map<String ,Object> data = new HashMap<String,Object>();
		data.put("startTime", request.getParameter("startTime"));
		data.put("endTime", request.getParameter("endTime"));
		data.put("pageSize", request.getParameter("pageSize"));
		data.put("pageNo", request.getParameter("pageNo"));
		data.put("dateType", request.getParameter("dateType"));
	
		//查询数据数量
		String count = dataCenterService.merchantDataAnalyzeCount(data);
		if(count.equals("0") || StringUtils.isBlank(count)){
			throw new ServiceCommonException("4001","商户分析数据为空！");
		}
		//查询数据
		Map<String ,Object> res = dataCenterService.merchantDataAnalyze(data);
		res.remove("allrows");
		res.put("total", Integer.parseInt(count));
		
		return Result.getSuccess(res);
	}
	
	/**
	 * 商户数据分析查询导出excel
	 * @param startTime 查询开始时间
	 * @param endTime  查询的截止时间
	 * @param dateType 时间类型(0：按日分  1：按月分)
	 * @return Result<?>
	 */
	@RequestMapping("/merchantDataAnalyzeExcel")
	@ResponseBody
	public Result<?> merchantDataAnalyzeExcel(HttpServletRequest request,HttpServletResponse response){
		
		Map<String ,Object> data = new HashMap<String,Object>();
		data.put("startTime", request.getParameter("startTime"));
		data.put("endTime", request.getParameter("endTime"));
		data.put("dateType", request.getParameter("dateType"));
		
		//查询数据
		List<Map<String ,Object>> res = dataCenterService.merchantDataAnalyzeExcel(data);
		
		//创建列名
		List<String> name = new ArrayList<String>();
		name.add("datetime");
		name.add("currentMerchantNumber");
		name.add("newMerchantNumber");
		name.add("activeMerchandNumber");
		name.add("merchantNumber");
		name.add("distributNumber");
		name.add("productNumber");
		name.add("prometerNumber");
		name.add("qyNumber");
		
		List<String> chname = new ArrayList<String>();
		chname.add("日期");
		chname.add("累计商户数");
		chname.add("新增商户数");
		chname.add("活跃商户数");
		chname.add("门店");
		chname.add("经销商");
		chname.add("区域制作中心");
		chname.add("推广中心");
		chname.add("推广员");
		
		int[] width = new int[8];
		width[0]=4000;
		width[1]=4000;
		width[2]=4000;
		width[3]=4000;
		width[4]=4000;
		width[5]=4000;
		width[6]=4000;
		width[7]=4000;
		
		//文件名
    	String realPath = excelUrl+"merchantDataAnalyzeExcel";
		//写数据到Excel 在服务器创建临时数据文件
		CommonServiceImpl.createExcel((List<Map<String,Object>>)res,name,chname,width,realPath);
		//下载excel
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String fileName= time+"-merchantDataAnalyzeExcel-"+System.currentTimeMillis();
		CommonServiceImpl.downloadExcel(request, response, fileName, realPath);
		
		return Result.newSuccessResult();
		
	}
	
	
	@RequestMapping("/merchant")
	public String merchant(){
		System.out.println(" 跳转jsp --> 商户数据分析");
		return  "dataAnalyse/merchant";
	}
	@RequestMapping("/business")
	public String business(){
		System.out.println(" 跳转jsp --> 交易分析");
		return "dataAnalyse/business";
	}
	@RequestMapping("/merchantDatajsp")
	public String merchantDatajsp(){
		System.out.println(" 跳转jsp --> 商户数据统计");
		return "dataAnalyse/merchantData";
	}
}
