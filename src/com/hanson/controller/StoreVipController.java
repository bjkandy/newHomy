package com.hanson.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Page;
import com.hanson.model.Company;
import com.hanson.model.StoreVip;
import com.hanson.service.StorePropertyService;
import com.hanson.service.StoreSaleDetailService;
import com.hanson.service.StoreVipService;

@Controller
@RequestMapping("/reservation")
public class StoreVipController {
	@Autowired
	private StoreVipService storeVipServer;
	@Autowired
	private StoreSaleDetailService storeSaleDetailService;
	@Autowired
	private StorePropertyService storePropertyService;
	
	@RequestMapping("/toreservation")
	public String storelist() {
		return "reservation/merchantsList";
	}
	@RequestMapping("/storeDetail")
	public String storeDetail(HttpServletRequest request, Model model){
		
		return "reservation/reservationDetail";
	}
	
	@RequestMapping("/storeset")
	public String reservationSet(HttpServletRequest request, Model model){
		String storeid=request.getParameter("storeid");
		request.setAttribute("storeid", storeid);
		return "reservation/reservationSet";
	}
	@RequestMapping(value="/GetStoreInfo")
	@ResponseBody
	public String  GetStoreInfo(HttpServletRequest request){
		String storeid=request.getParameter("storeid");
		Company model=storeSaleDetailService.SelectStoreInfo(Long.valueOf(storeid));
		JSONObject jo = new JSONObject();
		
		if(model!=null){
			jo.put("id", model.getId());
			jo.put("companyname", model.getCompanyname());
			jo.put("leftmoney", model.getLeftmoney());
			jo.put("normalaccount", model.getNormalaccount());
			if(model.getCreatetime()!=null){
				jo.put("createtime", model.getCreatetime());
			}
			
			JsonUtils.renderSuccess(jo);
		}else{
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	@RequestMapping(value="/SaleStoresList")
	@ResponseBody
	public String SaleStoresList(HttpServletRequest request) throws Exception{
		//JSONObject jsonData=new JSONObject();
		String storeid=request.getParameter("storeid");
		String page = request.getParameter("page");
		if(page==null){
			page="1";
		}
		String rows = request.getParameter("rows");
		if(rows==null){
			rows="20";
		}
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		//List<StoreSaleDetail> salesList=new ArrayList<StoreSaleDetail>();
		try{
			pagepeper=storeSaleDetailService.SearchStoreSales(pagepeper, filterMap(request));
			JSONObject jsonData = parseJsonList(pagepeper);
			JsonUtils.renderSuccess(jsonData);
			return jsonData.toString();
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
		return null;
	}
	private JSONObject parseVipList(List<StoreVip> vipList){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(StoreVip bean: vipList){
			JSONObject map = new JSONObject();
			map.put("id", bean.getId());
			map.put("", bean);
			map.put("grade", bean.getGrade());
			map.put("chargemoney", bean.getChargemoney());
			map.put("discount", bean.getDiscount());
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		return jsonData;
	}
	
	
	@RequestMapping(value="/VipList")
	@ResponseBody
	public String VipList(HttpServletRequest request){
		JSONObject jsonData=new JSONObject();
		List<StoreVip> vipList=new ArrayList<StoreVip>();
		try{
			vipList=storeVipServer.SelectStoreVip();
		     jsonData = parseVipList(vipList);
			   return jsonData.toString();
		}catch(Exception e){
			e.printStackTrace();
			JsonUtils.renderException(jsonData);
		}
		return jsonData.toString();
	}
	private Map<String, Object> filterMap(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String storeid=request.getParameter("storeid");
		String orderid=request.getParameter("orderid");
		String consumetype=request.getParameter("consumetype");
		if(!StringUtils.isEmpty(storeid)){
			paramMap.put("storeid", storeid);
		}
		if(!StringUtils.isEmpty(orderid)){
			paramMap.put("orderid", orderid);
		}
		if(!StringUtils.isEmpty(consumetype)){
			paramMap.put("consumetype", consumetype);
		}
		
		return paramMap;
	}
	private Map<String, Object> filterParamMap(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String companyid=request.getParameter("companyid");
		String companyname=request.getParameter("companyname");
		if(!StringUtils.isEmpty(companyid)){
			paramMap.put("companyid", companyid);
		}
		if(!StringUtils.isEmpty(companyname)){
			paramMap.put("companyname", companyname);
		}
		//获取日期
		String s=request.getParameter("WdatePickerStart");
		String s1=request.getParameter("WdatePickerEnd");
		//判断开始时间，结束时间
		
		if(!StringUtils.isEmpty(s)){
			s=s+" 00:00:00";
			paramMap.put("WdatePickerStart", s);
		}
		if(!StringUtils.isEmpty(s1)){
			s1=s1+" 23:59:59";
			paramMap.put("WdatePickerEnd", s1);
		}
		return paramMap;		
	}
	private JSONObject parseJsonList(Page<Map<String, Object>> page){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: page.getResult()){
			JSONObject map = new JSONObject();
			map.put("id", bean.get("id"));
			map.put("remark", bean.get("remark"));
			String type=bean.get("consumetype").toString();
			String charg=bean.get("chargemoney").toString();
			if(type.equals("10")){
				map.put("consumetype", "预存充值 ");
				map.put("chargemoney", "+"+Double.valueOf(charg)/100);
				map.put("orderdetail", "<a name='selectview' href='javascript:void(0);' onclick = 'aClick()'>查看</a>");
			}else{
				map.put("consumetype", "生产费用抵扣 ");
				map.put("chargemoney", "-"+Double.valueOf(charg)/100);
				map.put("orderdetail", "<a name='selectview' href='javascript:void(0);' onclick = 'aClick("+bean.get("orderdetail")+")'>查看</a>");
			}
			String date1=bean.get("createtime").toString();
			String lef=bean.get("leftmoney").toString();
			map.put("leftmoney", Double.valueOf(lef)/100);
			map.put("orderid", bean.get("orderid"));
			map.put("storeorderid", bean.get("storeorderid"));
			map.put("companyname", bean.get("companyname"));
			map.put("createtime",date1);
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	private JSONObject parseBeanJsonList(Page<Map<String, Object>> page){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: page.getResult()) {
			JSONObject map = new JSONObject();
			map.put("id", bean.get("id"));
			map.put("companyid", bean.get("companyid"));
			map.put("companyname", bean.get("companyname"));
			map.put("grade", bean.get("grade"));
			String totalmoney=bean.get("totalmoney").toString();
			if(totalmoney!=null){
			  map.put("totalmoney",Double.valueOf(totalmoney)/100);
			}else{
				map.put("totalmoney",0d);
			}
			String leftmoney=bean.get("leftmoney").toString();
			if(leftmoney!=null){
				map.put("leftmoney",Double.valueOf(leftmoney)/100);
			}else{
				map.put("leftmoney",0d);
			}
			
			map.put("selectview", "<a name='selectview' href='javascript:void(0);' onclick = 'aClick("+bean.get("companyid")+")'>查看</a>");
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	
	@RequestMapping(value="/jsonlist")
	@ResponseBody
	public String jsonlist(HttpServletRequest request) throws Exception{
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		try {
			pagepeper =storePropertyService.SearchStoreVips(pagepeper, filterParamMap(request));
			JSONObject jsonData = parseBeanJsonList(pagepeper);
			JsonUtils.renderSuccess(jsonData);
			return jsonData.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/Savestorevip")
	@ResponseBody
	public String  reservationSet(HttpServletRequest request) throws Exception{
		JSONObject jo=new JSONObject();
		String[] viplist=request.getParameter("vips").split(",");
		StoreVip storeVip=null;
		 //遍历  
		try{
			for(String x :viplist){
				 storeVip=new StoreVip();
				 String[] strs= x.split("_");
				 storeVip.setId(Long.valueOf(strs[0]));
			     storeVip.setChargemoney(Double.valueOf(strs[1]));
			     storeVip.setDiscount(Double.valueOf(strs[2]));
			     storeVip.setLastmodifiedtime(new Date());
			     storeVipServer.UpdateStoreVip(storeVip);
			}
		 JsonUtils.renderSuccess(jo);
		}catch(Exception ex){
			ex.printStackTrace();
			JsonUtils.renderException(jo);
		}
		  return  jo.toString();
	}
}
