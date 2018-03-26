package com.hanson.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Page;
import com.hanson.model.odr.Order;
import com.hanson.model.odr.Txis;
import com.hanson.model.u.Company;
import com.hanson.service.OrderService;
import com.hanson.service.OrderdetailService;
import com.hanson.serviceModel.OrderDTO;
import common.Arith;
import common.Result;
import common.ServiceCommonException;
import common.ZipUtils;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Value("${manage.image.view.dir}")
	public String imageViewDir;
	
	
	final Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	private OrderService orderService;
	@Autowired
	OrderdetailService orderdetailService;
	@Value("${netApi.downloadImg.imgurl}")
	private String imgurl;

	/**
	 * 构建模糊查询条件及验证
	 */
	private Map<String, Object> filterParamMap(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String qyid = request.getParameter("qyid");
		String cuname = request.getParameter("cuname");
		String kdate = request.getParameter("kdate");
		String jdate = request.getParameter("jdate");
		String orderstatus = request.getParameter("orderstatus");
		String txStatus = request.getParameter("txStatus");
		String orderid = request.getParameter("orderid");
		String paystatus = request.getParameter("paystatus");
		
		
		if(!StringUtils.isEmpty(qyid)){
			paramMap.put("qyid", qyid);
		}
		if(!StringUtils.isEmpty(cuname)){
			paramMap.put("cuname", cuname);
		}
		if(!StringUtils.isEmpty(kdate)){
			paramMap.put("kdate", kdate);
		}
		if(!StringUtils.isEmpty(jdate)){
			paramMap.put("jdate", jdate);
		}
		if(!StringUtils.isEmpty(orderstatus)){
			paramMap.put("orderstatus", orderstatus);
		}
		if(!StringUtils.isEmpty(txStatus)){
			paramMap.put("txStatus", txStatus);
		}
		if(!StringUtils.isEmpty(orderid)){
			paramMap.put("orderid", orderid);
		}
		if(!StringUtils.isEmpty(paystatus)){
			paramMap.put("paystatus", paystatus);
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
			map.put("ID", bean.get("ID"));
			map.put("cuid", bean.get("cuid"));
			map.put("qyid", bean.get("qyid"));
			map.put("orderid", bean.get("orderid"));
			map.put("cuname", bean.get("cuname"));
			map.put("merchanttxid", bean.get("merchanttxid"));
			map.put("totalprice", bean.get("totalprice"));
			map.put("productotallprice", bean.get("productotallprice"));
			map.put("postage", Arith.sub(bean.get("totalprice").toString(), bean.get("productotallprice").toString()));
			
			map.put("number", bean.get("number"));
			map.put("orderstatus", bean.get("orderstatus"));
			map.put("paystatus", bean.get("paystatus"));
			map.put("txStatus", bean.get("txStatus"));
			map.put("createdate", bean.get("createdate").toString());
			map.put("paytype", bean.get("paytype"));
			map.put("jsje", bean.get("jsje"));
			map.put("fyje", bean.get("fyje"));
			map.put("fybl", bean.get("fybl"));
			map.put("orderdetail", "<a name='orderdetail' href='' orderid="+bean.get("orderid")+">详情</a>,<a name='orderDownload' href='' orderid="+bean.get("orderid")+">下载</a>");
			//map.put("roleName", bean.get("roleName"));
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	
	@RequestMapping("/orderList")
	@ResponseBody
	public String orderList(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		request.getParameterNames();
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("t.createdate");
			pagepeper.setOrder(Page.DESC);
		}
		try {
			pagepeper = orderService.findOrderPage(pagepeper, filterParamMap(request));
			JSONObject jsonData = parseBeanJsonList(pagepeper);
			JsonUtils.renderSuccess(jsonData);
			return jsonData.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// 获得查询条件
		String qyidString = request.getParameter("qyid");
		String orderidString = request.getParameter("orderid");
		String cunameString = request.getParameter("cuname");
		String kdateString = request.getParameter("kdate");
		String jdateString = request.getParameter("jdate");
		String orderstatusString = request.getParameter("orderstatus");
		String txStatusString = request.getParameter("txStatus");
		Order or = new Order();
		if (qyidString == null || qyidString == ""){
			or.setCuid(null);
		}else{
			or.setQyid(Integer.valueOf(qyidString));
		}
			
		if (txStatusString == null || txStatusString == "" || txStatusString.equals("-1")) {
			or.setTxStatus(null);
		} else {
			or.setTxStatus(Integer.valueOf(txStatusString));
		}
		if (orderstatusString == null || orderstatusString == "" || orderstatusString.equals("-1")) {
			or.setOrderstatus(null);
		} else {
			or.setOrderstatus(Integer.valueOf(orderstatusString));
		}

		or.setOrderid(orderidString);
		or.setCuname(cunameString);
		or.setKdate(kdateString);
		or.setJdate(jdateString);

		List<Order> uniqueBy = orderService.getUniqueBy(or);
		JSONArray jsonyy = JSONArray.fromObject(orderJson(uniqueBy));
		PrintWriter out = response.getWriter();
		try{
			out.write(jsonyy.toString());
		}catch(Exception e){
			logger.error("orderList: "+e);
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
		System.out.println(jsonyy.toString());
		return null;*/
	}
	
	public List<OrderDTO> orderJson(List<Order> order){
		List<OrderDTO> orderlist = new ArrayList<OrderDTO>();
		for (Order order2 : order) {
			OrderDTO bean = new OrderDTO();
			bean.setId(order2.getId());
			bean.setQyid(order2.getQyid());
			bean.setPrometerid(order2.getPrometerid());
			bean.setMerchantid(order2.getMerchantid());
			bean.setDistributorid(order2.getDistributorid());
			bean.setOrderid(order2.getOrderid());
			bean.setCuname(order2.getCuname());
			bean.setTotalprice(order2.getTotalprice()/100);
			bean.setProductotallprice(order2.getProductotallprice()/100);
			Integer num = 0;
			for (int i = 0; i < order2.getOrderdetails().size(); i++) {
				num += order2.getOrderdetails().get(i).getNumber();
			}
			bean.setNumber(num);
			bean.setOrderstatus(order2.getOrderstatus());
			bean.setPaystatus(order2.getPaystatus());
			bean.setTxStatus(order2.getTxStatus());
			bean.setCreatedate(order2.getCreatedate());
			bean.setPaytype(order2.getPaytype());
			bean.setFee(order2.getFee());
			orderlist.add(bean);
		}
		return orderlist;
	}

	@RequestMapping("/orderfahuo")
	public String orderfahuo(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject obj = new JSONObject();
		// 获得id
		String idString = request.getParameter("id");
		String struts = request.getParameter("struts");
		if (struts.equals("未生产")) {
			obj.put("success", false);
			obj.put("msg", "未生产状态不能修改为已发货");
		} else if (struts.equals("生产完毕")) {
			int ii = orderService.orderfahuo(idString);
			if (ii > 0) {
				obj.put("success", true);
				obj.put("msg", "修改为已发货状态成功");
			} else {
				obj.put("success", false);
				obj.put("msg", "修改失败");
			}
		} else if (struts.equals("已发货")) {
			obj.put("success", false);
			obj.put("msg", "已发货状态不能重复修改为已发货");
		} else if (struts.equals("已签收")) {
			obj.put("success", false);
			obj.put("msg", "已签收状态不能修改为已发货");
		} else if (struts.equals("已关闭")) {
			obj.put("success", false);
			obj.put("msg", "已关闭状态不能修改为已发货");
		} else if (struts.equals("无效订单")) {
			obj.put("success", false);
			obj.put("msg", "无效订单状态不能修改为已发货");
		}

		PrintWriter out = response.getWriter();
		try{
			out.write(obj.toString());
		}catch(Exception e){
			logger.error("orderfahuo: "+e);
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}

		return null;
	}

	@RequestMapping("/ordersc")
	@ResponseBody
	public String ordersc(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// 获得id
		JSONObject obj = new JSONObject();
		String idString = request.getParameter("id");
		String struts = request.getParameter("struts");
		if (struts.equals("未生产")) {
			int ii = orderService.ordersc(idString);
			if (ii > 0) {
				obj.put("success", true);
				obj.put("msg", "修改为生产完毕状态成功");
			} else {
				obj.put("success", false);
				obj.put("msg", "修改失败");
			}
		} else if (struts.equals("生产完毕")) {
			obj.put("success", false);
			obj.put("msg", "生产完毕状态不能重复修改为生产完毕");
		} else if (struts.equals("已发货")) {
			obj.put("success", false);
			obj.put("msg", "已发货状态不能修改为生产完毕");
		} else if (struts.equals("已签收")) {
			obj.put("success", false);
			obj.put("msg", "已签收状态不能修改为生产完毕");
		} else if (struts.equals("已关闭")) {
			obj.put("success", false);
			obj.put("msg", "已关闭状态不能修改为生产完毕");
		} else if (struts.equals("无效订单")) {
			obj.put("success", false);
			obj.put("msg", "无效订单状态不能修改为生产完毕");
		}
		PrintWriter out = response.getWriter();
		try{
			out.print(obj.toString());
		}catch(Exception e){
			logger.error("ordersc: "+e);
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
		
		return null;
	}

	@RequestMapping("/orderjs")
	public String orderjs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		// 获得id
		String msg = "";
		String id = request.getParameter("id");
		String struts = request.getParameter("struts");
		String txstruts = request.getParameter("txstruts");
		if(struts.equals("已发货")){
		 if(txstruts.equals("未提现")){
			Order or = new Order();
			or.setId(Integer.valueOf(id));
			List<Order> list = orderService.queryid(or);
			Order odr = (Order) list.get(0);
			Company cp=new Company();
			cp.setId(odr.getQyid());
			List<Company> lc=  orderService.queryje(cp);
			Company cp1=(Company)lc.get(0);
			Txis tx = new Txis();
//			qyid`, `paytype`, `createdate`,`status`,  `bdelete` `orderid`
			tx.setQyid(odr.getQyid());
			tx.setPaytype(odr.getPaytype());
			Date date = new Date();
			tx.setCreatedate(date);
			tx.setStatus(odr.getTxStatus());
			tx.setOrderid(odr.getOrderid());
			tx.setAmount(odr.getTotalprice());
			double zje=odr.getTotalprice();
			double sx=cp1.getRebateproportion();
			double sxf=zje*sx;
			tx.setAmountfee(sxf);
			double sjf=zje-sxf;
			tx.setAmountreal(sjf);
			int i=orderService.insertjs(tx);
			Order odr1 =new Order();
			odr1.setId(Integer.valueOf(id));
			  orderService.ordertx(odr1);
			if(i>0){
				msg="结算成功";
			}else{
				msg="结算失败";
			}
		}else{
			msg="只有已发货并且未提现才能结算";
		}
		}else{
			msg="只有已发货并且未提现才能结算";
		}
		PrintWriter out = response.getWriter();
		try{
			out.write(msg);
		}catch(Exception e){
			logger.error("orderjs: "+e);
		}finally{
			if(null != out){
				out.flush();
				out.close();
			}
		}
		
		
		return null;
	}
	
	/*@RequestMapping(value = "deriveExcel")
	public String deriveExcel(HttpServletResponse response){
		response.setContentType("octets/stream"); 
		response.setContentType("applicationnd.ms-excel");  
		try {
			String bName = "汇美订单信息表.xls";
			String s = new String(bName.getBytes("UTF-8"),"ISO8859-1");
			response.setHeader("Content-Disposition", "attach; filename="+s);
			Order or = new Order();
			List<Order> lisOrders = orderService.getUniqueBy(or);
			HSSFWorkbook exportExcel = orderService.exportExcelNew(lisOrders);
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
	}*/
	
	/**
	 * 订单导出excel
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "exportExcel")
	public String exportExcel(Order order, HttpServletResponse response){
		response.setContentType("octets/stream"); 
		response.setContentType("applicationnd.ms-excel");  
		try {
			String bName = "汇美订单信息表.xls";
			String s = new String(bName.getBytes("UTF-8"),"ISO8859-1");
			response.setHeader("Content-Disposition", "attach; filename="+s);
			//Order or = new Order();
			//List<Order> lisOrders = orderService.getOrdersExcel(order);
			List<Map<String, Object>> lisOrders = orderService.getOrdersExcelNew(order);
			//HSSFWorkbook exportExcel = orderService.exportExcel(lisOrders);
			HSSFWorkbook exportExcel = orderService.exportExcelNew(lisOrders);
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

	public String download(){
		
		return null;
	}
	
	//跳转到订单详情页
		@RequestMapping("/orderDetail")
		public String orderDetail(String orderid, HttpServletRequest request, Model model){
			//根据ID查询订单信息
			Order order = new Order();
			order.setOrderid(orderid);
			Order orderRel =  orderService.findOrderById(order);
			//订单属性处理
			String createdate = "";
			String paydate = "";
			if(orderRel.getCreatedate() != null){
				createdate = orderRel.getCreatedate().substring(0, orderRel.getCreatedate().length() -2);
			}
			if(orderRel.getCreatedate() != null){
				paydate = orderRel.getCreatedate().substring(0, orderRel.getCreatedate().length() -2);
			}
			orderRel.setCreatedate(createdate);//下单时间
			orderRel.setPaydate(paydate);//支付时间
			if(orderRel != null){
				model.addAttribute("order", orderRel);
			}
			
			//查询订单明细
			orderid = request.getParameter("orderid");
			if(StringUtils.isEmpty(orderid)){
				logger.debug("订单号为空！");
			}
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("orderid", orderid);
			  List<Map<String,Object>> result = null;
			  List<Map<String,Object>> re = new ArrayList<Map<String,Object>>();
			  try{
				  re = orderdetailService.orderDetailPhoto(data);
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  if(!re.isEmpty()){
				  for(Map<String,Object> i : re){
				      if(((BigDecimal)i.get("price")).doubleValue() != 0.0){
				      	i.put("price", ((BigDecimal)i.get("price")).doubleValue()/100);
				      }else{
				    	  i.put("price", 0.0);
				      }
				      i.put("totalprice", (((BigDecimal)i.get("number")).doubleValue())*((double)(Double)i.get("price")));
				      i.put("colour", null);
				      i.put("packagetype", null);
				  }
			  }
			  result = re;
			  List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
			  try{
				  res = orderdetailService.orderDetailFrame(data);
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  if(!res.isEmpty()){
				  for(Map<String,Object> i : res){
				      if(((BigDecimal)i.get("price")).doubleValue() != 0.0){
				      	i.put("price", ((BigDecimal)i.get("price")).doubleValue()/100);
				      }else{
				    	  i.put("price", 0.0);
				      }
				      i.put("totalprice", ((int)(Integer)i.get("number"))*((double)(Double)i.get("price")));
				      i.put("spec", null);
				      i.put("plastic", null);
				      if(StringUtils.isEmpty((String)i.get("productname"))){
				    	  i.put("packagetype", null);
				    	  i.put("productname", i.get("mainimg"));
				      }else{
				    	  i.put("packagetype", "相框+照片");
				      }
				      result.add(i);
				  }
			  }
			  
			  Map<String,Object> resuldata = new HashMap<String,Object>();
			  try{
				   resuldata =  orderdetailService.orderDetail(data);
			  }catch(Exception e){
				  e.printStackTrace();
			  }
			  if(resuldata == null || resuldata.isEmpty()){
					logger.debug("订单详情为空！");
					return null;
				}else{
					//商品总数量
					int number = 0;
					for(Map<String,Object> i : result){
						if(i.get("number") != null){
							number += Integer.parseInt(i.get("number").toString());
						}
					}
					
					DecimalFormat    df   = new DecimalFormat("######0.00");   
					model.addAttribute("totalprice",  ((BigDecimal)resuldata.get("totalprice")).doubleValue() == 0.0?0.0:df.format(((BigDecimal)resuldata.get("totalprice")).doubleValue()/100));
					model.addAttribute("postage",  ((BigDecimal)resuldata.get("postage")).doubleValue() == 0.0?0.0:df.format(((BigDecimal)resuldata.get("postage")).doubleValue()/100));
					model.addAttribute("detail", result);
					model.addAttribute("resuldata", resuldata);
					model.addAttribute("number", number);
					
				}
			  
			model.addAttribute("imageViewDir", imageViewDir);
			  
			return "order/ddxq";
		}

		
		
		/**
		 * 条件 下载订单（图片）
		 * @param orderid                       
		 * @return Result<？> 
		 */
		@RequestMapping(value="/downloadOrder")
		@ResponseBody
		public Result<?> downloadOrder(HttpServletRequest request,HttpServletResponse response) throws IOException, FileNotFoundException {
			
			String orderid = request.getParameter("orderid");
			List<Map<String, Object>> data = null;
		    data = orderdetailService.downloadOrder(orderid);
		    String tomcatHomePath =  "/mnt/tomcat7weixinproject";
		    		//System.getProperty("catalina.home");
		    Map<String,File> srcFile = new HashMap<String,File>();
		    int j = 1;
		    int k = 1;
		    Date d = new Date();  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String dateNowStr = sdf.format(d); 
	        String cuname = dateNowStr+"-"+String.valueOf(data.get(0).get("cuname"));
		    for(Map<String,Object> detil : data){
		    	StringBuilder name = null;
		    	if(((String)detil.get("producttype")).equals("10")){
		    		name = new StringBuilder((String)detil.get("size")).append("-").append((String)detil.get("spec")).append("-").append(detil.get("number")).append("张-").append(detil.get("cuname")).append("-").append(detil.get("id")).append(".jpg");
		    		StringBuilder oldPath = new StringBuilder(tomcatHomePath).append(imgurl).append(detil.get("proimg"));
		 			File file = new File(oldPath.toString());
		 			//分类打包文件名
		 			StringBuilder packagename = new StringBuilder();
		 			if(String.valueOf(detil.get("plastic")).equals("1")){
		 				packagename = packagename.append(String.valueOf(detil.get("size"))).append("-").append(String.valueOf(detil.get("spec"))).append("-").append("过塑");
		 			}else{
		 				packagename = packagename.append(String.valueOf(detil.get("size"))).append("-").append(String.valueOf(detil.get("spec"))).append("-").append("不过塑");
		 			}
		 			StringBuilder pathName = new StringBuilder(String.valueOf(cuname)).append("-").append(orderid).append(File.separator).append("照片冲印").append(File.separator).append(packagename).append(File.separator).append(name);
		 			srcFile.put(pathName.toString(), file);
		    	}else if(((String)detil.get("producttype")).equals("20")){
		    		StringBuilder oldPath = null;
		    		StringBuilder pathName = null;
		    		File file = null;
		    		String fileName = new StringBuilder(String.valueOf(detil.get("size"))).append(String.valueOf(detil.get("color"))).append(k++).toString();
		    		if(!StringUtils.isBlank((String)detil.get("proimg"))){
			    		name = new StringBuilder((String)detil.get("name")).append("-").append(String.valueOf(detil.get("size"))).append("-").append(detil.get("color")).append("-").append(detil.get("number")).append("张-").append(detil.get("cuname")).append("-").append(detil.get("productid")).append("-").append(detil.get("id")).append(".jpg");
			    	    oldPath = new StringBuilder(tomcatHomePath).append(imgurl).append(detil.get("proimg"));
			 		    file = new File(oldPath.toString());
			 		    pathName = new StringBuilder(cuname).append("-").append(orderid).append(File.separator).append("相框").append(File.separator).append(fileName).append(File.separator).append(name);
			 			srcFile.put(pathName.toString(), file);
		    		}
		 			//相框原图   
		 			oldPath = new StringBuilder(tomcatHomePath).append(imgurl).append(detil.get("mainimg"));
		 			file = new File(oldPath.toString());
		 		    pathName = new StringBuilder(cuname).append("-").append(orderid).append(File.separator).append("相框").append(File.separator).append(fileName).append(File.separator).append(detil.get("id")).append(".jpg");
		 		    srcFile.put(pathName.toString(), file);
		    	}else if(((String)detil.get("producttype")).equals("40")){
		    		name = new StringBuilder((String.valueOf(detil.get("name"))).replace(" ", "_")).append("-").append((String.valueOf(detil.get("size"))).replace("*", "x")).append("-").append(detil.get("pageNum")).append("-").append(detil.get("productid")).append("-").append(detil.get("id"));
		    		StringBuilder picname = null;
		    		if(Integer.parseInt(String.valueOf(detil.get("rank"))) == 0){
		    			 picname = new StringBuilder((String.valueOf(detil.get("name"))).replace(" ", "_")).append("-").append("首页").append(".jpg");
		    		}else if(Integer.parseInt(String.valueOf(detil.get("rank"))) == 1){
		    			 picname = new StringBuilder((String.valueOf(detil.get("name"))).replace(" ", "_")).append("-").append("尾页").append(".jpg");
		    		}else if(Integer.parseInt(String.valueOf(detil.get("rank"))) == 2){
		    			 picname = new StringBuilder((String.valueOf(detil.get("name"))).replace(" ", "_")).append("-").append("内页").append(j++).append(".jpg");
		    		}else{
		    			throw new ServiceCommonException("4001"," rank: 相册图片位置数据错误！");
		    		}
		    		StringBuilder oldPath = new StringBuilder(tomcatHomePath).append(imgurl).append(detil.get("imagePath"));
		 			File file = new File(oldPath.toString());
		 			
		 			StringBuilder pathName = new StringBuilder(cuname).append("-").append(orderid).append(File.separator).append("相册").append(File.separator).append(name).append(File.separator).append(picname.toString());
		 			srcFile.put(pathName.toString(), file);
		    	}
		    	
		    }
		    	String zipName = new StringBuilder(cuname).append("-").append(orderid).append(".zip").toString();
		    	zipName = new String(zipName.getBytes(), "ISO-8859-1");
		        response.setContentType("APPLICATION/OCTET-STREAM; charset=utf-8"); 
		        response.setHeader("Content-Disposition","attachment; filename="+zipName);
		        ZipOutputStream out = new ZipOutputStream(response.getOutputStream());
		        try {
		 			Set<String> key = srcFile.keySet();
		 			for(String i : key){
		 				if(!srcFile.get(i).exists()){
		 					logger.error(srcFile.get(i).getAbsolutePath()+".................................................文件不存在!");
		 					continue;
		 				}
		 				ZipUtils.doCompress(srcFile.get(i), out, i);
		 			}
//	                response.flushBuffer();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }finally{
		        	 out.flush();
		            out.close();
		        }
		        return Result.newSuccessResult();
		}

	@RequestMapping("/index")
	public String login() {
		return "WEB/index";
	}

	@RequestMapping("/order")
	public String order() {
		return "order/ddgl";
	}
	
	

}
