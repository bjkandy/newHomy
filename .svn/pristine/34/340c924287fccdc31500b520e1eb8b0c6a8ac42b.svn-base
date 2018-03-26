package com.hanson.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Page;
import com.hanson.model.Company;
import com.hanson.model.odr.Order;
import com.hanson.model.odr.Txjs;
import com.hanson.service.TxjsService;

@Controller
@RequestMapping("/txjs")
public class TxjsController {
	
	final Logger logger = LoggerFactory.getLogger(TxjsController.class);
	@Autowired
	private TxjsService txjsService;
	
	
	/**
	 * 构建模糊查询条件及验证
	 */
	private Map<String, Object> filterParamMap(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String qyid = request.getParameter("qyid");
		String companyname = request.getParameter("companyname");
		String kdate = request.getParameter("kdate");
		String jdate = request.getParameter("jdate");
		String kdateJs = request.getParameter("kdateJs");
		String jdateJs = request.getParameter("jdateJs");
		String status = request.getParameter("status");
		String checkStatus = request.getParameter("checkStatus");
		String id = request.getParameter("id");
		String orderid = request.getParameter("orderid");
		
		if(!StringUtils.isEmpty(qyid)){
			paramMap.put("qyid", qyid);
		}
		if(!StringUtils.isEmpty(companyname)){
			paramMap.put("companyname", companyname);
		}
		
		if(!StringUtils.isEmpty(status)){
			paramMap.put("status", status);
		}
		if(!StringUtils.isEmpty(checkStatus)){
			paramMap.put("checkStatus", checkStatus);
		}
		if(!StringUtils.isEmpty(kdate)){
			paramMap.put("kdate", kdate);
		}
		if(!StringUtils.isEmpty(jdate)){
			paramMap.put("jdate", jdate);
		}
		if(!StringUtils.isEmpty(kdateJs)){
			paramMap.put("kdateJs", kdateJs);
		}
		if(!StringUtils.isEmpty(jdateJs)){
			paramMap.put("jdateJs", jdateJs);
		}
		if(!StringUtils.isEmpty(id)){
			paramMap.put("id", id);
		}
		if(!StringUtils.isEmpty(orderid)){
			paramMap.put("orderid", orderid);
		}
		
		return paramMap;
	}
	
	/**
	 * 构建返回参数
	 */
	private JSONObject parseBeanJsonList(Page<Map<String, Object>> page){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: page.getResult()) {
			JSONObject map = new JSONObject();
			map.put("ID", bean.get("id"));//提现编号
			map.put("qyid", bean.get("qyid"));//商户编号
			map.put("companyname", bean.get("companyname"));//商户名称
			String amount=bean.get("amount").toString();
			map.put("amount", Double.valueOf(amount));//提现金额
			map.put("orderNumber", bean.get("orderNumber"));//订单数量
		    String status=bean.get("status").toString();
		    String resu="未提现";
		    if(status.equals("10")){
		    	resu="未提现";
		    }else if(status.equals("20")){
		    	resu="提现中";
		    }else if(status.equals("40")){
		    	resu="成功提现";
		    }
		    Object obj=bean.get("txtype");
		    if(obj!=null){
		    	String fanxian=bean.get("txtype").toString();
			    if(fanxian.equals("1")){
			    	fanxian="普通订单提现";
			    }else if(fanxian.equals("2")){
			    	fanxian="会员充值订单提现";	
			    }
			    map.put("txtype", fanxian);
		    }
		    
		    
			map.put("status", resu);//提现状态 status
			map.put("checkStatus", "未审核");//审核状态
			map.put("createdate", bean.get("createdate") == null?"": bean.get("createdate").toString());//申请时间
			map.put("finishdate", bean.get("finishdate") == null?"": bean.get("finishdate").toString());//结算时间
			map.put("orders", "<a  href='' txid="+bean.get("id")+">详情</a>");
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	
	private JSONObject parseBeanJsonList1(Page<Map<String, Object>> page){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: page.getResult()) {
			JSONObject map = new JSONObject();
			map.put("orderid", bean.get("orderid"));
			map.put("cuname", bean.get("cuname"));
			map.put("qyid", bean.get("qyid"));
			map.put("totalprice", bean.get("totalprice"));
			map.put("productotallprice", bean.get("productotallprice"));
			map.put("youfeijine", bean.get("youfeijine"));
			map.put("fyamount", bean.get("fyamount"));
			map.put("jiesuanfangshi", 0);
			map.put("orderstatus", bean.get("orderstatus"));
			map.put("expresstype", bean.get("expresstype"));
			map.put("ExpressCompanyName", bean.get("ExpressCompanyName"));
			map.put("expressno", bean.get("expressno"));
			map.put("merchanfetstatus", bean.get("merchanfetstatus"));
			map.put("createdate", bean.get("createdate") == null?"": bean.get("createdate").toString());//申请时间
			//map.put("orders", "<a href='javascript:void(0);' txid="+bean.get("id")+">审核</a>");
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	
	

	/**
	 * 查询
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/txjsList")
	public String txjsList(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		request.getParameterNames();
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("id");
			pagepeper.setOrder(Page.ASC);
		}
		try {
			//pagepeper = orderService.findOrderPage(pagepeper, filterParamMap(request));
			pagepeper = txjsService.findTxjsPage(pagepeper, filterParamMap(request));
			JSONObject jsonData = parseBeanJsonList(pagepeper);
			JsonUtils.renderSuccess(jsonData);
			return jsonData.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 返现导出excel
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "exportExcel")
	public String exportExcel(Txjs txjs, HttpServletResponse response){
		response.setContentType("octets/stream"); 
		response.setContentType("applicationnd.ms-excel");  
		try {
			String bName = "汇美返现信息表.xls";
			String s = new String(bName.getBytes("UTF-8"),"ISO8859-1");
			response.setHeader("Content-Disposition", "attach; filename="+s);
			//Order or = new Order();
			//List<Txjs> lisTxjs = txjsService.getTxjsExcel(txjs);
			List<Map<String, Object>> lisTxjs = txjsService.getTxjsExcelNew(txjs);
			//HSSFWorkbook exportExcel = txjsService.exportExcel(lisTxjs);
			HSSFWorkbook exportExcel = txjsService.exportExcelNew(lisTxjs);
			OutputStream out = response.getOutputStream();
			try {
				exportExcel.write(out);
				System.out.println("导出成功。。。。。。");
			} catch (Exception e) { 
				logger.error("deriveExcel: "+e);
			}finally{
				if(null != out){
					out.flush();
					out.close();
				}
			}
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 打款
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/daKuan")
	public String daKuan(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		response.setContentType("text/plain; charset=utf-8");
		Txjs tx =new Txjs();
		tx.setId(Integer.valueOf(request.getParameter("id")));
		//根据提现ID查询提现qyid, 根据提现qyid查询商户类型
		Company company = txjsService.findCompanyByTxid(tx);
		tx.setCompanytype(company.getCompanytype());
		
		//根据提现ID查询提现订单
		String resString = "";//结果
		List<Order> orderList = txjsService.findOrdersByTxid(tx);
		if(orderList != null && orderList.size() > 0){
			tx.setOrderList(orderList);
			try {
				txjsService.daKuan(tx);
				resString="打款成功";
			} catch (Exception e) {
				resString="错误";
				e.printStackTrace();
			}
		}
		
		PrintWriter out = response.getWriter();
		out.print(resString);
		out.flush();
		return null;
	}
	
	
	
	
	
	/**
	 * 查询
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/txjsOrderList")
	public String txjsOrderList(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		request.getParameterNames();
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("t2.id");
			pagepeper.setOrder(Page.ASC);
		}
		try {
			//pagepeper = orderService.findOrderPage(pagepeper, filterParamMap(request));
			//pagepeper = orderService.findTxjsPage(pagepeper, filterParamMap(request));
			pagepeper = txjsService.findOrderPage(pagepeper, filterParamMap(request));
			JSONObject jsonData = parseBeanJsonList1(pagepeper);
			JsonUtils.renderSuccess(jsonData);
			return jsonData.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 跳转页面
	 * @return
	 */
	@RequestMapping("/txjs")
	public String txjs(){
		return "txjs/fxgl";
	}
	
	@RequestMapping("/ordersView")
	public String ordersView(HttpServletRequest request, Model model){
		model.addAttribute("id", request.getParameter("id"));
		return "txjs/ordersView";
	}
}
