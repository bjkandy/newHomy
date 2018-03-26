package com.hanson.controller;
import java.io.OutputStream;
import java.math.BigDecimal;
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

import com.hanson.common.util.Page;
import com.hanson.model.Company;
import com.hanson.model.odr.Order;
import com.hanson.model.odr.Orderdetail;
import com.hanson.service.CompanyService;
import com.hanson.service.OrderService;
import com.hanson.service.OrderdetailService;
import com.hanson.service.SeconduserService;
@Controller
@RequestMapping("/merchantsort")
public class MerchantsortController {
	
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
		return "company/shzlsjtj";
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
			List<String> listdate = GetAllTheDayOfThisMonth(new Date());
			s = listdate.get(0) + " 00:00:00";//获取第一天
			s1 = listdate.get(listdate.size()-1) + " 23:59:59";//获取最后一天
		}else{
			s = s + " 00:00:00";
			s1=s1 + " 23:59:59";
		}
		List listout = new ArrayList();
		List listin = new ArrayList();
		listin = findinfo(s , s1);
		listout.add(listin);
		request.getSession().setAttribute("listout", listout);
		try {
			JSONObject jsonData = parseBeanJsonList(listout);
			return jsonData.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private JSONObject parseBeanJsonList(List listout){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		List listin = new ArrayList();
		for(int i = 0; i < listout.size(); i++) {
			JSONObject map = new JSONObject();
			listin = (List) listout.get(i);
			map.put("date", listin.get(0));//时间
			map.put("startcomanyamount", listin.get(1));//起始商户数（Long）
			map.put("addcompanyamount", listin.get(2));//净增商户数（Long）
			map.put("addordernumber", listin.get(3));//订单增量(int)
			map.put("addorderamount", listin.get(4));//订单增量金额(int)
			map.put("payedaddorder", listin.get(5));//已支付订单数(Long)
			map.put("payedordermoney", listin.get(6));//已支付订单金额（Double）
			map.put("addPhoto", listin.get(7));//照片增量（int）
			map.put("payedAddPhoto", listin.get(8));//已支付增量照片数（int）
			
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		return jsonData;
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
		
		return paramMap;
	}
	
	//查询一行的信息
	@SuppressWarnings("unchecked")
	public List findinfo(String firstdate,String lastdate){
		List listinfo = new ArrayList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("WdatePickerEnd", firstdate);
		Long startcomanyamount = companyService.countTotles(map);//起始商户数
		
		map.put("WdatePickerStart", firstdate);
		map.put("WdatePickerEnd", lastdate);
		Long addcompanyamount = companyService.countTotles(map);//净增商户数
		
		map.clear();
		map.put("WdatePickerStart", firstdate);
		map.put("WdatePickerEnd", lastdate);
		Map<String, Object> mapAddOrder = orderService.findCountByWhere(map);
		Long addordernumber =(Long)mapAddOrder.get("number");//订单增量
		BigDecimal addorderamount = (BigDecimal)mapAddOrder.get("totalprice");//订单增量金额
		
		
		map.clear();
		map.put("WdatePickerEnd", lastdate);
		map.put("WdatePickerStart", firstdate);
		int[] payStatusArray = {10};
		map.put("payStatusArray", payStatusArray);
		Map<String, Object> payedAddOrderMap = orderService.findCountByWhere(map);
		Long payedaddorder = (Long)payedAddOrderMap.get("number");//订单增量（已支付）
		BigDecimal payedordermoney = (BigDecimal)payedAddOrderMap.get("totalprice");//订单增量（已支付金额）
		
		map.clear();
		map.put("WdatePickerStart", firstdate);
		map.put("WdatePickerEnd", lastdate);
		Map<String, Object> mapAddOrderAndPhoto = orderService.findPhotoaddnumber(map);
		BigDecimal addPhoto = (BigDecimal)mapAddOrderAndPhoto.get("photoaddnumber");//照片增量
		
		map.clear();
		map.put("WdatePickerStart", firstdate);
		map.put("WdatePickerEnd", lastdate);
		int[] payStatusArray1 = {10};
		map.put("payStatusArray", payStatusArray1);
		Map<String, Object> payedOrderAndPhoto = orderService.findPhotoaddnumber(map);
		BigDecimal payedAddPhoto = (BigDecimal)payedOrderAndPhoto.get("photoaddnumber");//照片增量(已支付)
		
		listinfo.add(0, firstdate + "~" +lastdate);//日期
		listinfo.add(1,startcomanyamount);//起始商户数
		listinfo.add(2, addcompanyamount);//净增商户数
		listinfo.add(3, addordernumber);//订单增量
		listinfo.add(4, addorderamount);//订单增量金额
		listinfo.add(5, payedaddorder);//已支付订单数
		listinfo.add(6, payedordermoney);//已支付订单金额
		listinfo.add(7, addPhoto);//增量照片数
		listinfo.add(8, payedAddPhoto);//已支付增量照片数
		
		return listinfo;
	}
	
	//根据订单查找订单详情里的照片数
	public int countphoto(List<Orderdetail> list){
		int photonumber = 0;
		for (Orderdetail orderdetail : list) {
			photonumber += orderdetail.getNumber();
		}
		return photonumber;
	}
	
	// 根据订单list查询这些订单的总金额
	public Double countallordermoney(List<Order> list) {
		double money = 0.0;
		for (Order order : list) {
			money += order.getTotalprice();
		}
		return money/100;
	}
	
	public Date parse1(String s) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.parse(s);
	}
	
	//获取月份的最后一天
	public Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }
	
	//获取两个时间之间的所有月份
	public static List getMonthBetween(String minDate, String maxDate) throws ParseException {
		 List result = new ArrayList();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
		
		 Calendar min = Calendar.getInstance();
		 Calendar max = Calendar.getInstance();
		  
		 min.setTime(sdf.parse(minDate));
		 min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		 
		 max.setTime(sdf.parse(maxDate));
		 max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
		
		 Calendar curr = min;
		 while (curr.before(max)) {
			 result.add(sdf.format(curr.getTime()));
			 curr.add(Calendar.MONTH, 1);
		 }
		 
		 return result;
	}
	
	//获取月份所有天数
	public List GetAllTheDayOfThisMonth(Date date){
		List<Date> list = getAllTheDateOftheMonth(date);
		List list1 = new ArrayList();
		String strdate;
		for(Date date1: list){
			strdate = ChangeType(date1);
			list1.add(strdate);
		}
		return list1;
	}

	public String ChangeType(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String sDate=sdf.format(date);
//		System.out.println(sDate);
		return sDate;
	} 
	
	public List<Date> getAllTheDateOftheMonth(Date date) {
		List<Date> list = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);

		int month = cal.get(Calendar.MONTH);
		while(cal.get(Calendar.MONTH) == month){
			list.add(cal.getTime());
			cal.add(Calendar.DATE, 1);
		}
		return list;
	}
	
	//将日期转换为字符串
	public String format(Date date1) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(date1);
		return date;
	}
	
	// 日期转字符串
	public String DatetoString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// java.util.Date date=new java.util.Date();
		String str = sdf.format(date);
		return str;
	}
	
	private JSONObject parseBeanJsonList(Page<Map<String, Object>> pagerPage, String s, String s1){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
//		int allfansnum = 0;//总粉丝数
		Double allmoneyamount = (double)0;//总订单金额
		Double allmoneyptotal = (double)0;//总商品金额
		for(Map<String, Object> bean: pagerPage.getResult()) {
			JSONObject map = new JSONObject();
			map.put("id", bean.get("id"));//商户id
			map.put("companyname", bean.get("companyname"));//商户名 
			String[] allid = getallid(Long.valueOf(bean.get("id").toString()));//拼接id
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("WdatePickerStart", s); 
			paramMap.put("WdatePickerEnd", s1);
			paramMap.put("allid", allid);
			Integer fansnum = seconduserService.findFansadd(paramMap);//粉丝增量
			map.put("fansnum", fansnum);
			//订单总金额，商品总金额，订单数
//			Long ordernum = orderService.countByWhere("qyid in " + allid, null);//订单数
			List<Order> orderlist = orderService.findListByQYId(paramMap);//查订单
			Double moneyamount = countmoney(orderlist,"amount")/100;//订单总金额
			Double moneyptotal = countmoney(orderlist,"ptotal")/100;//商品总金额
			int ordernum = orderlist.size();//订单数
			map.put("moneyamount", moneyamount);//订单总金额
			map.put("moneyptotal", moneyptotal);//商品总金额
			map.put("ordernum", ordernum);//订单数
//			allfansnum += fansnum;
			allmoneyamount += moneyamount; 
			allmoneyptotal += moneyptotal;
			
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", pagerPage.getTotalCount());	
		return jsonData;
	}
	
	public Double countmoney(List<Order> list,String type){
		Double money = (double) 0;
		if(list != null && list.size() > 0){
			for (Order order : list) {
				if(type.equals("amount")){
					money += order.getTotalprice();
				}
				else if(type.equals("ptotal")){
					money += order.getProductotallprice();
				}
				else{}
			}
		}
		return money;
	}
	
	//商户和下级推广员id集合
	public String[] getallid(Long id){
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
			String bName = "商户增量数据统计信息表.xls";
			String s = new String(bName.getBytes("UTF-8"),"ISO8859-1");
			response.setHeader("Content-Disposition", "attach; filename="+s);
			List list = Prexsl(request);
			HSSFWorkbook exportExcel = exportExcel(list);
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
	
	private List Prexsl(HttpServletRequest request) throws Exception{
		//获取日期
		String s = request.getParameter("WdatePickerStart");
		String s1 = request.getParameter("WdatePickerEnd");
		//判断开始时间，结束时间
		if(StringUtils.isEmpty(s) || StringUtils.isEmpty(s1)){
			List<String> listdate = GetAllTheDayOfThisMonth(new Date());
			s = listdate.get(0) + " 00:00:00";//获取第一天
			s1 = listdate.get(listdate.size()-1) + " 23:59:59";//获取最后一天
		}else{
			s = s + " 00:00:00";
			s1=s1 + " 23:59:59";
		}
		List listout = new ArrayList();
		List listin = new ArrayList();
		listin = findinfo(s , s1);
		listout.add(listin);
		return listout;
	}
	
	public HSSFWorkbook exportExcel(List list) {
		//声明一个工作簿  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet("商户增量数据统计信息表");  
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
        HSSFCell cellbt5 = row.createCell(12);
        cellbt5.setCellStyle(style);
        cell.setCellStyle(style);
        cell.setCellValue("商户增量数据统计信息表"); 
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
        
        HSSFCell cell2 = row2.createCell(0);
        cell2.setCellStyle(stylejiacu);
        cell2.setCellValue("日期"); 
        
        HSSFCell cell3 = row2.createCell(1);
        cell3.setCellStyle(stylejiacu);
        cell3.setCellValue("起始商户数"); 
        
        HSSFCell cell4 = row2.createCell(2);
        cell4.setCellStyle(stylejiacu);
        cell4.setCellValue("净增商户数"); 
        
        HSSFCell cell5= row2.createCell(3);
        cell5.setCellStyle(stylejiacu);
        cell5.setCellValue("订单总数"); 
        
        HSSFCell cell6 = row2.createCell(4);
        cell6.setCellValue("订单增量"); 
        
        HSSFCell cell7 = row2.createCell(5);
        cell7.setCellStyle(stylejiacu);
        cell7.setCellValue("订单总额"); 
        
        HSSFCell cell8 = row2.createCell(6);
        cell8.setCellStyle(stylejiacu);
        cell8.setCellValue("实际订单数"); 
        
        HSSFCell cell9 = row2.createCell(7);
        cell9.setCellStyle(stylejiacu);
        cell9.setCellValue("实际订单增量"); 
        
        HSSFCell cell10 = row2.createCell(8);
        cell10.setCellStyle(stylejiacu);
        cell10.setCellValue("实际金额"); 
        
        HSSFCell cell11= row2.createCell(9);
        cell11.setCellStyle(stylejiacu);
        cell11.setCellValue("照片数量"); 
        
        HSSFCell cell13 = row2.createCell(10);
        cell13.setCellStyle(stylejiacu);
        cell13.setCellValue("照片增量"); 
        
        for (int i = 0; i < list.size(); i++) {
        	List listin = (List) list.get(i);
        	
        	HSSFRow rowInfo = sheet.createRow(2+i);
            HSSFCell cell32 = rowInfo.createCell(0);
            cell32.setCellStyle(teststyle); 
            cell32.setCellValue((String) listin.get(0)); 
            
            HSSFCell cell33 = rowInfo.createCell(1);
            cell33.setCellStyle(teststyle); 
            cell33.setCellValue((Long) listin.get(1)); 
            
            HSSFCell cell34 = rowInfo.createCell(2);
            cell34.setCellStyle(teststyle); 
            cell34.setCellValue((Long) listin.get(2)); 
            
            HSSFCell cell35 = rowInfo.createCell(3);
            cell35.setCellStyle(teststyle); 
            cell35.setCellValue((Integer) listin.get(3)); 
            
            HSSFCell cell36 = rowInfo.createCell(4);
            cell36.setCellStyle(teststyle); 
            cell36.setCellValue((Integer) listin.get(4)); 
            
            HSSFCell cell37 = rowInfo.createCell(5);
            cell37.setCellStyle(teststyle); 
            cell37.setCellValue((Double) listin.get(5));
            
            HSSFCell cell38 = rowInfo.createCell(6);
            cell38.setCellStyle(teststyle); 
            cell38.setCellValue((Integer) listin.get(6)); 
            
            HSSFCell cell39 = rowInfo.createCell(7);
            cell39.setCellStyle(teststyle); 
            cell39.setCellValue((Integer) listin.get(7)); 
            
            HSSFCell cell40 = rowInfo.createCell(8);
            cell40.setCellStyle(teststyle); 
            cell40.setCellValue((Double) listin.get(8)); 
            
            HSSFCell cell41 = rowInfo.createCell(9);
            cell41.setCellStyle(teststyle); 
            cell41.setCellValue((Integer) listin.get(9)); 
            
            HSSFCell cell42 = rowInfo.createCell(10);
            cell42.setCellStyle(teststyle); 
            cell42.setCellValue((Integer) listin.get(10)); 
            
		}

        return workbook;
	}
	
}
