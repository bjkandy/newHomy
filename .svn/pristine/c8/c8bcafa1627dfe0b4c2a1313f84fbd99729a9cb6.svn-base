package com.hanson.controller;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
 import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Page;
import com.hanson.model.Company;
import com.hanson.model.odr.Order;
import com.hanson.model.odr.Orderdetail;
import com.hanson.service.CompanyService;
import com.hanson.service.OrderService;
import com.hanson.service.OrderdetailService;
import com.hanson.service.SeconduserService;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SeconduserService seconduserService;
	@Autowired
	private OrderdetailService orderdetailService;
	
	/**
	 * 跳转到列表页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request) throws Exception{
		return "company/shsjtj";
	}

	//查询商户数据统计增量(当月所有天)
	@RequestMapping(value="/increment")	//TODO increment
	@ResponseBody
	public String increment(HttpServletRequest request) throws ParseException{
		String comid = request.getParameter("comid");
		Long companyid = Long.valueOf(comid);
		 
		request.getSession().setAttribute("nowcompanyid", companyid);
			
		//获取日期
		String s = request.getParameter("WdatePickerStart");
		String s1 = request.getParameter("WdatePickerEnd");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//判断开始时间，结束时间，設置初始时间
		if(StringUtils.isEmpty(s) || StringUtils.isEmpty(s1)){
			s1 = sdf.format(new Date());
			s = s1.substring(0, 8) + "01";
		}
		
		List<String> daystrlist = getBetweenDates(sdf.parse(s), sdf.parse(s1));
		
		JSONArray jsonArg = new JSONArray();
		JSONObject object = new JSONObject();
		Company company = companyService.findUniqueById(companyid);
		//循环当月每天，查找对应的信息
		for(int i = 0; i < daystrlist.size(); i++){
			JSONObject object1 = MerchantList1(company, daystrlist.get(i).toString());
			object1.put("day", daystrlist.get(i).toString());
			jsonArg.add(object1);
		}
		object.put("rows", jsonArg);
		object.put("total", daystrlist.size());
		return object.toString();
	 }
	//写有一个方法，专门查找某个商户某天的信息	商户ID	商户名	粉丝数	订单总金额	商品总金额	订单数量
	private JSONObject MerchantList1(Company company, String day){
		 String s = day + " 00:00:00";
		 String s1 = day + " 23:59:59";
		 String id = company.getId().toString();
		 String companyname = company.getCompanyname();
		 String[] allid = getallid(Long.valueOf(id));//拼接id
		
		 JSONObject map = findDataOne(allid, s, s1);
		 map.put("id", id);//商户id
		 map.put("companyname", companyname);//商户名 
		 
		 return map;
	 }
	

	private String ChangeType(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sDate=sdf.format(date);
		return sDate;
	} 
	
	/**
	 * 获取两个日期之间的日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return 日期集合
	 */
	private List<String> getBetweenDates(Date start, Date end) {
		List<String> result = new ArrayList<String>();
		if(start.equals(end)){
			result.add(ChangeType(start));
		}else if(end.before(start)){
			
		}else{
			result.add(ChangeType(start));
			
		    Calendar tempStart = Calendar.getInstance();
		    tempStart.setTime(start);
		    tempStart.add(Calendar.DAY_OF_YEAR, 1);
		    
		    Calendar tempEnd = Calendar.getInstance();
		    tempEnd.setTime(end);
		    while (tempStart.before(tempEnd)) {
		        result.add(ChangeType(tempStart.getTime()));
		        tempStart.add(Calendar.DAY_OF_YEAR, 1);
		    }
		    result.add(ChangeType(end));
		}
		
	    return result;
	}
	
	/**
	 * 企业信息查询-活躍商户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/jsonlist")	//TODO jsonlist
	@ResponseBody
	public String jsonlist(HttpServletRequest request) throws Exception{

		//获取日期
		String s = request.getParameter("WdatePickerStart");
		String s1 = request.getParameter("WdatePickerEnd");
		//判断开始时间，结束时间
		if(StringUtils.isEmpty(s) || StringUtils.isEmpty(s1)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			s1 = sdf.format(new Date()) + " 23:59:59";
			s = s1.substring(0, 8) + "01 00:00:00";
		}else{
			s = s + " 00:00:00";
			s1=s1 + " 23:59:59";
		}
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("ucompany.id");
			pagepeper.setOrder(Page.ASC);
		}
		try {
			pagepeper = companyService.search(pagepeper, filterParamMap(request));
			
			JSONObject jsonData = parseBeanJsonList(pagepeper ,s,s1);
			JsonUtils.renderSuccess(jsonData);
			return jsonData.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 构建模糊查询条件及验证
	 */
	private Map<String, Object> filterParamMap(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String companyname = request.getParameter("companyname");
		if(!StringUtils.isEmpty(companyname)){
			paramMap.put("companyname", companyname);
		}
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			paramMap.put("id", id);
		}
		
		return paramMap;
	}
	
	private JSONObject findDataOne(String[] allid, String s, String s1){
		JSONObject map = new JSONObject();
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("allid", allid);
		//粉丝总量
		Integer fansnumAll = seconduserService.findFansadd(paramMap);
		map.put("fansnumall", fansnumAll);
		
		paramMap.put("WdatePickerStart", s); 
		paramMap.put("WdatePickerEnd", s1);
		//粉丝增量
		Integer fansnum = seconduserService.findFansadd(paramMap);
		map.put("fansnum", fansnum);
		//查询订单
		paramMap.put("paramId", allid[0]);
		List<Order> orderlist = orderService.findListByQYId(paramMap);
		//订单相关数据统计
		countOrder(map, orderlist);
		return map;
	}
	
	private JSONObject parseBeanJsonList(Page<Map<String, Object>> pagerPage, String s, String s1){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: pagerPage.getResult()) {
			String id = bean.get("id").toString();
			String companyname = bean.get("companyname").toString();
			String[] allid = getallid(Long.valueOf(id));//拼接id
			
			JSONObject map = findDataOne(allid, s, s1);
			map.put("id", id);//商户id
			map.put("companyname", companyname);//商户名 
			
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", pagerPage.getTotalCount());	
		return jsonData;
	}
	
	private JSONObject countOrder(JSONObject map, List<Order> orderlist){
		Double moneyamountall = 0d;	//下单总金额
		Double moneyamount = 0d;	//支付总金额
		Double moneyptotal = 0d;	//商品总金额
		long ordernumall = 0;	//下单总数量
		long ordernum = 0;		//支付订单数量
		long photoaddall = 0;	//下单照片数量
		long photoadd = 0;	//支付照片数量
		for(Order order : orderlist){
			List<Orderdetail>  listorderdetail = orderdetailService.findListByOid(order.getOrderid());
			for (Orderdetail orderdetail : listorderdetail) {
				photoaddall += orderdetail.getNumber();
				if(order.getPaystatus().intValue() == 100 ){//支付成功
					photoadd += orderdetail.getNumber();
				}
			}
			if(order.getPaystatus().intValue() == 100 ){//支付成功
				ordernum ++;
				moneyamount += order.getTotalprice();
			}
			photoaddall ++;
			ordernumall ++;
			moneyptotal += order.getProductotallprice();
			moneyamountall += order.getTotalprice();
		}
		map.put("moneyamountall", moneyamountall);//订单总金额
		map.put("moneyamount", moneyamount);//商品总金额
		map.put("moneyptotal", moneyptotal);//订单数
		map.put("ordernumall", ordernumall);//查照片增量
		map.put("ordernum", ordernum);//订单总金额
		map.put("photoaddall", photoaddall);//商品总金额
		map.put("photoadd", photoadd);//订单数
		return map;
	}
	
	
	
	//商户和下级推广员id集合
	private String[] getallid(Long id){
		List<Company> list = companyService.findListByPid(id);
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		for(Company item : list){
			sb.append(",").append(item.getId());
		}
		return sb.toString().split(",");
	} 
	
	@RequestMapping(value = "exportExcel")//TODO 列表页导出
	public String exportExcel(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("octets/stream"); 
		response.setContentType("applicationnd.ms-excel");  
		try {
			String bName = "商户数据统计表.xls";
			String s = new String(bName.getBytes("UTF-8"),"ISO8859-1");
			response.setHeader("Content-Disposition", "attach; filename="+s);
			JSONObject data = Prexsl(request);
			HSSFWorkbook exportExcel = exportExcel(data);
			OutputStream out = response.getOutputStream();
			try {
				exportExcel.write(out);
				System.out.println("导出成功。。。。。。");
			} catch (Exception e) { 
				e.printStackTrace();
			}finally{
				out.close();
			}
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}
	
	private JSONObject Prexsl(HttpServletRequest request) throws Exception{
		//获取日期
		String s = request.getParameter("WdatePickerStart");
		String s1 = request.getParameter("WdatePickerEnd");
		//判断开始时间，结束时间
		if(StringUtils.isEmpty(s) || StringUtils.isEmpty(s1)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			s1 = sdf.format(new Date()) + " 23:59:59";
			s = s1.substring(0, 8) + "01 00:00:00";
		}else{
			s = s + " 00:00:00";
			s1=s1 + " 23:59:59";
		}
		
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(1);
		pagepeper.setPageSize(companyService.countTotles(filterParamMap(request)).intValue());
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("ucompany.id");
			pagepeper.setOrder(Page.ASC);
		}
		try {
			pagepeper = companyService.search(pagepeper, filterParamMap(request));
			
			JSONObject jsonData = parseBeanJsonList(pagepeper ,s,s1);
			jsonData.put("times", s +"~" +s1);
			return jsonData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private HSSFWorkbook exportExcel(JSONObject data) {
		JSONArray list = data.getJSONArray("rows");
		String times = data.getString("times");
		//声明一个工作簿  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet("商户数据统计信息表");  
        //设置表格默认列宽度为15个字符  
        sheet.setDefaultColumnWidth(16);  
        sheet.setColumnWidth(6, 20*350);
        //生成一个样式，用来设置标题样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        //设置这些样式     
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        
        //生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        font.setFontHeightInPoints((short) 30);
        
        //把字体应用到当前的样式  
        style.setFont(font);  
        HSSFFont fontt = workbook.createFont();  
        fontt.setColor(HSSFColor.BLACK.index);  
        fontt.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        
        HSSFFont headerFont1 = (HSSFFont) workbook.createFont();  
        headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//      cell4.setCellStyle(stylejiacu);加粗样式
        HSSFCellStyle stylejiacu = workbook.createCellStyle();  
        stylejiacu.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        stylejiacu.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        stylejiacu.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        stylejiacu.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        stylejiacu.setFont(headerFont1);
        stylejiacu.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        stylejiacu.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        
        // 生成并设置另一个样式,用于设置内容样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        // 把字体应用到当前的样式  
        style2.setFont(font2); 
        
        //测试样式
        //创建样式
        HSSFCellStyle teststyle = workbook.createCellStyle(); 
        
        teststyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        teststyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        teststyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        teststyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        teststyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        teststyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //合并列
        CellRangeAddress region1 = new CellRangeAddress(0,0, (short) 0, (short) 10); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列 
        sheet.addMergedRegion(region1);
        //创建行
        HSSFRow row = sheet.createRow(0);
        //创建列
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        
        HSSFCell cellbt1 = row.createCell(1);
        cellbt1.setCellStyle(style);
        HSSFCell cellbt2 = row.createCell(2);
        cellbt2.setCellStyle(style);
        HSSFCell cellbt3 = row.createCell(3);
        cellbt3.setCellStyle(style);
        HSSFCell cellbt4 = row.createCell(4);
        cellbt4.setCellStyle(style);
        HSSFCell cellbt5 = row.createCell(5);
        cellbt5.setCellStyle(style);
        cell.setCellStyle(style);
        cell.setCellValue("商户数据统计信息表"); 
        row.setHeightInPoints(50);
        
        HSSFCellStyle styleHH = workbook.createCellStyle();
        styleHH.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        styleHH.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        styleHH.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        styleHH.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框   
        styleHH.setWrapText(true);
        //第二行
        HSSFCellStyle teststyle2 = workbook.createCellStyle(); 
        teststyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        teststyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        teststyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        teststyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框   
        teststyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        HSSFRow row2 = sheet.createRow(1);
        
        HSSFCell cell0 = row2.createCell(0);
        cell0.setCellStyle(teststyle); 
        cell0.setCellStyle(stylejiacu);
        cell0.setCellValue("公司ID"); 
        
        HSSFCell cell1 = row2.createCell(1);
        cell1.setCellStyle(teststyle); 
        cell1.setCellStyle(stylejiacu);
        cell1.setCellValue("公司名称"); 
        
        HSSFCell cell2 = row2.createCell(2);
        cell2.setCellStyle(teststyle); 
        cell2.setCellStyle(stylejiacu);
        cell2.setCellValue("粉丝数"); 
        
        HSSFCell cell3 = row2.createCell(3);
        cell3.setCellStyle(teststyle); 
        cell3.setCellStyle(stylejiacu);
        cell3.setCellValue("粉丝增量"); 
        
        HSSFCell cell4= row2.createCell(4);
        cell4.setCellStyle(teststyle); 
        cell4.setCellStyle(stylejiacu);
        cell4.setCellValue("下单总金额"); 
        
        HSSFCell cell5= row2.createCell(5);
        cell5.setCellStyle(teststyle); 
        cell5.setCellStyle(stylejiacu);
        cell5.setCellValue("支付总金额"); 
        
        HSSFCell cell6 = row2.createCell(6);
        cell6.setCellStyle(teststyle); 
        cell6.setCellStyle(stylejiacu);
        cell6.setCellValue("商品总金额"); 
        
        HSSFCell cell7 = row2.createCell(7);
        cell7.setCellStyle(teststyle); 
        cell7.setCellStyle(stylejiacu);
        cell7.setCellValue("下单总数量"); 
        
        HSSFCell cell8 = row2.createCell(8);
        cell8.setCellStyle(teststyle); 
        cell8.setCellStyle(stylejiacu);
        cell8.setCellValue("支付订单数量"); 
        
        HSSFCell cell9 = row2.createCell(9);
        cell9.setCellStyle(teststyle); 
        cell9.setCellStyle(stylejiacu);
        cell9.setCellValue("下单照片数量"); 
        
        HSSFCell cell10 = row2.createCell(10);
        cell10.setCellStyle(teststyle); 
        cell10.setCellStyle(stylejiacu);
        cell10.setCellValue("支付照片数量"); 
        
        HSSFCell cell11 = row2.createCell(11);
        cell11.setCellStyle(teststyle); 
        cell11.setCellStyle(stylejiacu);
        cell11.setCellValue("时间段"); 
        
        long fansnumall = 0;	//粉丝总数
        long fansnum = 0;	//粉丝增量
        Double moneyamountall = 0d;	//下单总金额
		Double moneyamount = 0d;	//支付总金额
		Double moneyptotal = 0d;	//商品总金额
		long ordernumall = 0;	//下单总数量
		long ordernum = 0;		//支付订单数量
		long photoaddall = 0;	//下单照片数量
		long photoadd = 0;	//支付照片数量
        
        for (int i = 0; i < list.size(); i++) {
        	JSONObject item = list.getJSONObject(i);
        	
        	HSSFRow rowInfo = sheet.createRow(2+i);
            HSSFCell cell_data0 = rowInfo.createCell(0);
            cell_data0.setCellStyle(teststyle); 
            cell_data0.setCellValue(item.getLong("id")); 
            
            HSSFCell cell_data1 = rowInfo.createCell(1);
            cell_data1.setCellStyle(teststyle); 
            cell_data1.setCellValue(item.getString("companyname")); 
            
            HSSFCell cell_data2 = rowInfo.createCell(2);
            cell_data2.setCellStyle(teststyle); 
            cell_data2.setCellValue(item.getLong("fansnumall")); 
            
            HSSFCell cell_data3 = rowInfo.createCell(3);
            cell_data3.setCellStyle(teststyle); 
            cell_data3.setCellValue(item.getDouble("fansnum")); 
            
            HSSFCell cell_data4 = rowInfo.createCell(4);
            cell_data4.setCellStyle(teststyle); 
            cell_data4.setCellValue(item.getDouble("moneyamountall")); 
            
            HSSFCell cell_data5 = rowInfo.createCell(5);
            cell_data5.setCellStyle(teststyle); 
            cell_data5.setCellValue(item.getLong("moneyamount")); 
            
            HSSFCell cell_data6 = rowInfo.createCell(6);
            cell_data6.setCellStyle(teststyle); 
            cell_data6.setCellValue(item.getString("moneyptotal")); 
            
            HSSFCell cell_data7 = rowInfo.createCell(7);
            cell_data7.setCellStyle(teststyle); 
            cell_data7.setCellValue(item.getLong("ordernumall")); 
            
            HSSFCell cell_data8 = rowInfo.createCell(8);
            cell_data8.setCellStyle(teststyle); 
            cell_data8.setCellValue(item.getDouble("ordernum")); 
            
            HSSFCell cell_data9 = rowInfo.createCell(9);
            cell_data9.setCellStyle(teststyle); 
            cell_data9.setCellValue(item.getDouble("photoaddall")); 
            
            HSSFCell cell_data10 = rowInfo.createCell(10);
            cell_data10.setCellStyle(teststyle); 
            cell_data10.setCellValue(item.getLong("photoadd")); 
            
            HSSFCell cell_data11 = rowInfo.createCell(11);
            cell_data11.setCellStyle(teststyle); 
            cell_data11.setCellValue(times); 
            
            fansnumall += item.getLong("fansnumall");	//粉丝总数
            fansnum += item.getLong("fansnum");	//粉丝增量
            moneyamountall += item.getDouble("moneyamountall");	//下单总金额
    		moneyamount += item.getDouble("moneyamount");	//支付总金额
    		moneyptotal += item.getDouble("moneyptotal");	//商品总金额
    		ordernumall += item.getLong("moneyptotal");	//下单总数量
    		ordernum += item.getLong("ordernum");		//支付订单数量
    		photoaddall += item.getLong("photoaddall");	//下单照片数量
    		photoadd += item.getLong("photoadd");	//支付照片数量
		}
        
        HSSFRow rowInfo = sheet.createRow(2+list.size());
        
        HSSFCell cell_count1 = rowInfo.createCell(1);
        cell_count1.setCellStyle(teststyle); 
        cell_count1.setCellValue("总计："); 
        
        HSSFCell cell_count2 = rowInfo.createCell(2);
        cell_count2.setCellStyle(teststyle); 
        cell_count2.setCellValue(fansnumall); 
        
        HSSFCell cell_count3 = rowInfo.createCell(3);
        cell_count3.setCellStyle(teststyle); 
        cell_count3.setCellValue(fansnum); 
        
        HSSFCell cell_count4 = rowInfo.createCell(4);
        cell_count4.setCellStyle(teststyle); 
        cell_count4.setCellValue(moneyamountall); 
        
        HSSFCell cell_count5 = rowInfo.createCell(5);
        cell_count5.setCellStyle(teststyle); 
        cell_count5.setCellValue(moneyamount); 
        
        HSSFCell cell_count6 = rowInfo.createCell(6);
        cell_count6.setCellStyle(teststyle); 
        cell_count6.setCellValue(moneyptotal); 
        
        HSSFCell cell_count7 = rowInfo.createCell(7);
        cell_count7.setCellStyle(teststyle); 
        cell_count7.setCellValue(ordernumall); 
        
        HSSFCell cell_count8 = rowInfo.createCell(8);
        cell_count8.setCellStyle(teststyle); 
        cell_count8.setCellValue(ordernum); 
        
        HSSFCell cell_count9 = rowInfo.createCell(9);
        cell_count9.setCellStyle(teststyle); 
        cell_count9.setCellValue(photoaddall); 
        
        HSSFCell cell_data10 = rowInfo.createCell(10);
        cell_data10.setCellStyle(teststyle); 
        cell_data10.setCellValue(photoadd); 
        
        return workbook;
	}
	
	@RequestMapping(value = "exportXlsMerchantList")//TODO 模态框列表页导出
	public String exportXlsMerchantList(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("octets/stream"); 
		response.setContentType("applicationnd.ms-excel");  
		try {
			String bName = "商户详细数据统计表.xls";
			String s = new String(bName.getBytes("UTF-8"),"ISO8859-1");
			response.setHeader("Content-Disposition", "attach; filename="+s);
			JSONArray list = prepareXcel(request);
			HSSFWorkbook exportExcel = exportExcelMerchantList(list);
			OutputStream out = response.getOutputStream();
			try {
				exportExcel.write(out);
				System.out.println("导出成功。。。。。。");
			} catch (Exception e) { 
				e.printStackTrace();
			}finally{
				out.close();
			}
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}
	
	private JSONArray prepareXcel(HttpServletRequest request) throws Exception{
		Long companyid = (Long) request.getSession().getAttribute("nowcompanyid");
		
		//获取日期
		String s = request.getParameter("WdatePickerStart");
		String s1 = request.getParameter("WdatePickerEnd");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//判断开始时间，结束时间，設置初始时间
		if(StringUtils.isEmpty(s) || StringUtils.isEmpty(s1)){
			s1 = sdf.format(new Date());
			s = s1.substring(0, 8) + "01";
		}
		
		List<String> daystrlist = getBetweenDates(sdf.parse(s), sdf.parse(s1));
		
		JSONArray jsonArg = new JSONArray();
		Company company = companyService.findUniqueById(companyid);
		//循环当月每天，查找对应的信息
		for(int i = 0; i < daystrlist.size(); i++){
			JSONObject object1 = MerchantList1(company, daystrlist.get(i).toString());
			object1.put("day", daystrlist.get(i).toString());
			jsonArg.add(object1);
		}
		return jsonArg;
	}
	
	private HSSFWorkbook exportExcelMerchantList(JSONArray list) {
		//声明一个工作簿  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet("商户详细数据统计表");  
        //设置表格默认列宽度为15个字符  
        sheet.setDefaultColumnWidth(16);  
        sheet.setColumnWidth(6, 20*350);
        //生成一个样式，用来设置标题样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        //设置这些样式     
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        
        //生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        font.setFontHeightInPoints((short) 30);
        
        //把字体应用到当前的样式  
        style.setFont(font);  
        HSSFFont fontt = workbook.createFont();  
        fontt.setColor(HSSFColor.BLACK.index);  
        fontt.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        
        HSSFFont headerFont1 = (HSSFFont) workbook.createFont();  
        headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//      cell4.setCellStyle(stylejiacu);加粗样式
        HSSFCellStyle stylejiacu = workbook.createCellStyle();  
        stylejiacu.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        stylejiacu.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        stylejiacu.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        stylejiacu.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        stylejiacu.setFont(headerFont1);
        stylejiacu.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        stylejiacu.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        
        // 生成并设置另一个样式,用于设置内容样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        // 把字体应用到当前的样式  
        style2.setFont(font2); 
        
        //测试样式
        //创建样式
        HSSFCellStyle teststyle = workbook.createCellStyle(); 
        
        teststyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        teststyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        teststyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        teststyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        teststyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        teststyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //合并列
        CellRangeAddress region1 = new CellRangeAddress(0,0, (short) 0, (short) 11); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列 
        sheet.addMergedRegion(region1);
        //创建行
        HSSFRow row = sheet.createRow(0);
        //创建列
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        
        HSSFCell cellbt1 = row.createCell(1);
        cellbt1.setCellStyle(style);
        HSSFCell cellbt2 = row.createCell(2);
        cellbt2.setCellStyle(style);
        HSSFCell cellbt3 = row.createCell(3);
        cellbt3.setCellStyle(style);
        HSSFCell cellbt4 = row.createCell(4);
        cellbt4.setCellStyle(style);
        HSSFCell cellbt5 = row.createCell(12);
        cellbt5.setCellStyle(style);
        cell.setCellStyle(style);
        cell.setCellValue("商户详细数据统计表"); 
        row.setHeightInPoints(50);
        
        HSSFCellStyle styleHH = workbook.createCellStyle();
        styleHH.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        styleHH.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        styleHH.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        styleHH.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框   
        styleHH.setWrapText(true);
        //第二行
        HSSFCellStyle teststyle2 = workbook.createCellStyle(); 
        teststyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        teststyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        teststyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        teststyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框   
        teststyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        HSSFRow row2 = sheet.createRow(1);
        
        HSSFCell cell0 = row2.createCell(0);
        cell0.setCellStyle(teststyle); 
        cell0.setCellStyle(stylejiacu);
        cell0.setCellValue("公司ID"); 
        
        HSSFCell cell1 = row2.createCell(1);
        cell1.setCellStyle(teststyle); 
        cell1.setCellStyle(stylejiacu);
        cell1.setCellValue("公司名称"); 
        
        HSSFCell cell2 = row2.createCell(2);
        cell2.setCellStyle(teststyle); 
        cell2.setCellStyle(stylejiacu);
        cell2.setCellValue("粉丝数"); 
        
        HSSFCell cell3 = row2.createCell(3);
        cell3.setCellStyle(teststyle); 
        cell3.setCellStyle(stylejiacu);
        cell3.setCellValue("粉丝增量"); 
        
        HSSFCell cell4= row2.createCell(4);
        cell4.setCellStyle(teststyle); 
        cell4.setCellStyle(stylejiacu);
        cell4.setCellValue("下单总金额"); 
        
        HSSFCell cell5= row2.createCell(5);
        cell5.setCellStyle(teststyle); 
        cell5.setCellStyle(stylejiacu);
        cell5.setCellValue("支付总金额"); 
        
        HSSFCell cell6 = row2.createCell(6);
        cell6.setCellStyle(teststyle); 
        cell6.setCellStyle(stylejiacu);
        cell6.setCellValue("商品总金额"); 
        
        HSSFCell cell7 = row2.createCell(7);
        cell7.setCellStyle(teststyle); 
        cell7.setCellStyle(stylejiacu);
        cell7.setCellValue("下单总数量"); 
        
        HSSFCell cell8 = row2.createCell(8);
        cell8.setCellStyle(teststyle); 
        cell8.setCellStyle(stylejiacu);
        cell8.setCellValue("支付订单数量"); 
        
        HSSFCell cell9 = row2.createCell(9);
        cell9.setCellStyle(teststyle); 
        cell9.setCellStyle(stylejiacu);
        cell9.setCellValue("下单照片数量"); 
        
        HSSFCell cell10 = row2.createCell(10);
        cell10.setCellStyle(teststyle); 
        cell10.setCellStyle(stylejiacu);
        cell10.setCellValue("支付照片数量"); 
        
        HSSFCell cell11 = row2.createCell(11);
        cell11.setCellStyle(teststyle); 
        cell11.setCellStyle(stylejiacu);
        cell11.setCellValue("时间段"); 
        
        long fansnum = 0;	//粉丝增量
        Double moneyamountall = 0d;	//下单总金额
		Double moneyamount = 0d;	//支付总金额
		Double moneyptotal = 0d;	//商品总金额
		long ordernumall = 0;	//下单总数量
		long ordernum = 0;		//支付订单数量
		long photoaddall = 0;	//下单照片数量
		long photoadd = 0;	//支付照片数量
        
        for (int i = 0; i < list.size(); i++) {
        	JSONObject item = list.getJSONObject(i);
        	
        	HSSFRow rowInfo = sheet.createRow(2+i);
            HSSFCell cell_data0 = rowInfo.createCell(0);
            cell_data0.setCellStyle(teststyle); 
            cell_data0.setCellValue(item.getLong("id")); 
            
            HSSFCell cell_data1 = rowInfo.createCell(1);
            cell_data1.setCellStyle(teststyle); 
            cell_data1.setCellValue(item.getString("companyname")); 
            
            HSSFCell cell_data2 = rowInfo.createCell(2);
            cell_data2.setCellStyle(teststyle); 
            cell_data2.setCellValue(item.getLong("fansnumall")); 
            
            HSSFCell cell_data3 = rowInfo.createCell(3);
            cell_data3.setCellStyle(teststyle); 
            cell_data3.setCellValue(item.getDouble("fansnum")); 
            
            HSSFCell cell_data4 = rowInfo.createCell(4);
            cell_data4.setCellStyle(teststyle); 
            cell_data4.setCellValue(item.getDouble("moneyamountall")); 
            
            HSSFCell cell_data5 = rowInfo.createCell(5);
            cell_data5.setCellStyle(teststyle); 
            cell_data5.setCellValue(item.getLong("moneyamount")); 
            
            HSSFCell cell_data6 = rowInfo.createCell(6);
            cell_data6.setCellStyle(teststyle); 
            cell_data6.setCellValue(item.getString("moneyptotal")); 
            
            HSSFCell cell_data7 = rowInfo.createCell(7);
            cell_data7.setCellStyle(teststyle); 
            cell_data7.setCellValue(item.getLong("ordernumall")); 
            
            HSSFCell cell_data8 = rowInfo.createCell(8);
            cell_data8.setCellStyle(teststyle); 
            cell_data8.setCellValue(item.getDouble("ordernum")); 
            
            HSSFCell cell_data9 = rowInfo.createCell(9);
            cell_data9.setCellStyle(teststyle); 
            cell_data9.setCellValue(item.getDouble("photoaddall")); 
            
            HSSFCell cell_data10 = rowInfo.createCell(10);
            cell_data10.setCellStyle(teststyle); 
            cell_data10.setCellValue(item.getLong("photoadd")); 
            
            HSSFCell cell_data11 = rowInfo.createCell(11);
            cell_data11.setCellStyle(teststyle); 
            cell_data11.setCellValue(item.getString("day")); 
            
            fansnum += item.getLong("fansnum");	//粉丝增量
            moneyamountall += item.getDouble("moneyamountall");	//下单总金额
    		moneyamount += item.getDouble("moneyamount");	//支付总金额
    		moneyptotal += item.getDouble("moneyptotal");	//商品总金额
    		ordernumall += item.getLong("moneyptotal");	//下单总数量
    		ordernum += item.getLong("ordernum");		//支付订单数量
    		photoaddall += item.getLong("photoaddall");	//下单照片数量
    		photoadd += item.getLong("photoadd");	//支付照片数量
		}
        
        HSSFRow rowInfo = sheet.createRow(2+list.size());
        
        HSSFCell cell_count1 = rowInfo.createCell(1);
        cell_count1.setCellStyle(teststyle); 
        cell_count1.setCellValue("总计："); 
        
        HSSFCell cell_count3 = rowInfo.createCell(3);
        cell_count3.setCellStyle(teststyle); 
        cell_count3.setCellValue(fansnum); 
        
        HSSFCell cell_count4 = rowInfo.createCell(4);
        cell_count4.setCellStyle(teststyle); 
        cell_count4.setCellValue(moneyamountall); 
        
        HSSFCell cell_count5 = rowInfo.createCell(5);
        cell_count5.setCellStyle(teststyle); 
        cell_count5.setCellValue(moneyamount); 
        
        HSSFCell cell_count6 = rowInfo.createCell(6);
        cell_count6.setCellStyle(teststyle); 
        cell_count6.setCellValue(moneyptotal); 
        
        HSSFCell cell_count7 = rowInfo.createCell(7);
        cell_count7.setCellStyle(teststyle); 
        cell_count7.setCellValue(ordernumall); 
        
        HSSFCell cell_count8 = rowInfo.createCell(8);
        cell_count8.setCellStyle(teststyle); 
        cell_count8.setCellValue(ordernum); 
        
        HSSFCell cell_count9 = rowInfo.createCell(9);
        cell_count9.setCellStyle(teststyle); 
        cell_count9.setCellValue(photoaddall); 
        
        HSSFCell cell_data10 = rowInfo.createCell(10);
        cell_data10.setCellStyle(teststyle); 
        cell_data10.setCellValue(photoadd); 
        
        return workbook;
	}
}
