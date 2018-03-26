package com.hanson.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import com.hanson.service.impl.AutoPrintManageServiceImpl;

import common.CommonServiceImpl;
import common.JsonUtils;

@Controller
@RequestMapping("/autoPrint")
public class AutoPrintManageController {

	
	final Logger logger = LoggerFactory.getLogger(TxjsController.class);
	@Autowired
	private AutoPrintManageServiceImpl autoPrintManageServiceImpl;
	@Value("${netApi.downloadImg.excel}")
	private String excelUrl;
	

	/**
	 * 自助打印订单管理查询
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/autoPrintOrder")
	public List<Map<String,Object>> autoPrintOrder(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("merchantid", request.getParameter("merchantid"));//商户id
		data.put("id", request.getParameter("id"));//设备id
		data.put("paystatus", request.getParameter("paystatus"));//支付状态
		data.put("merchanfetstatus", request.getParameter("merchanfetstatus"));//提现状态
		data.put("orderid", request.getParameter("orderid"));//订单号
		data.put("txid", request.getParameter("txid"));//提现编号
		data.put("provincename", request.getParameter("provincename"));//省
		data.put("cityname", request.getParameter("cityname"));//市
		data.put("districtname", request.getParameter("districtname"));//区
		data.put("startTime", request.getParameter("startTime"));//开始时间
		data.put("endTime", request.getParameter("endTime"));//截止时间
		data.put("pageNo", request.getParameter("pageNo"));//第几页
		data.put("pageSize", request.getParameter("pageSize"));//每页数量
		
		//订单数量
		String count = autoPrintManageServiceImpl.autoPrintOrderCount(data);
		//订单数据
		List<Map<String,Object>> res = autoPrintManageServiceImpl.autoPrintOrder(data);
		Map<String,Object> re = new HashMap<String,Object>();
		re.put("rows", res);
		re.put("count", count);
//		JSONArray txJson = JSONArray.fromObject(re);
         String json = JsonUtils.toJson(re);
		PrintWriter out = response.getWriter();
		try{
			out.write(json);
		}catch(Exception e){
			logger.error("orderfahuo: "+e);
			e.printStackTrace();
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
		return null;
		
		
	}

	/**
	 * 自助打印订单详情
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/autoPrintOrderInfo")
	public Map<String,Object> autoPrintOrderInfo(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("orderid", request.getParameter("orderid"));//订单号
		
		//订单数据
		Map<String,Object> res = autoPrintManageServiceImpl.autoPrintOrderInfo(data);
		Map<String,Object> re = new HashMap<String,Object>();
		re.put("rows", res);

		String json = JsonUtils.toJson(re);
		PrintWriter out = response.getWriter();
		try{
			out.write(json);
		}catch(Exception e){
			logger.error("orderfahuo: "+e);
			e.printStackTrace();
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
		return null;
	}

	/**
	 * 自助打印订单导出excel
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/autoPrintOrderExcel")
	public List<Map<String,Object>> autoPrintOrderExcel(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("merchantid", request.getParameter("merchantid"));//商户id
		data.put("id", request.getParameter("id"));//设备id
		data.put("paystatus", request.getParameter("paystatus"));//支付状态
		data.put("merchanfetstatus", request.getParameter("merchanfetstatus"));//提现状态
		data.put("orderid", request.getParameter("orderid"));//订单号
		data.put("txid", request.getParameter("txid"));//提现编号
		data.put("provincename", request.getParameter("provincename"));//省
		data.put("cityname", request.getParameter("cityname"));//市
		data.put("districtname", request.getParameter("districtname"));//区
		data.put("startTime", request.getParameter("startTime"));//开始时间
		data.put("endTime", request.getParameter("endTime"));//截止时间
		
		//订单数据
		List<Map<String,Object>> res = autoPrintManageServiceImpl.autoPrintOrderExcel(data);
		
		//写数据到Excel
		String path = autoPrintManageServiceImpl.autoOrderExcel(res,excelUrl);
		//下载excel
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String fileName= time+"-distributionOrderExcel-"+System.currentTimeMillis();
		CommonServiceImpl.downloadExcel(request, response, fileName, path);

		return null;
	}
	
}
