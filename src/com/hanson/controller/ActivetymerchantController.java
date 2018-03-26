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
@RequestMapping("/activetymerchant")
public class ActivetymerchantController {
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private OrderdetailService orderdetailService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private SeconduserService seconduserService;
	
	/**
	 * 跳转到列表页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request) throws Exception{
		return "company/hysh";
	}

	/**
	 * 企业信息查询-活躍商户
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/jsonlist")
	@ResponseBody
	public String jsonlist(HttpServletRequest request) throws Exception{
		//获取日期
		String s=request.getParameter("date");
		String firstday = null;
		String lastday = null;
		if(s!=null&&!s.equals("")){//选择了日期
			firstday = s + "-01 00:00:00";//当月第一天(String)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date date = sdf.parse(s);
			lastday = format(getLastDayOfMonth(date));//获取当月最后一天
		}else{//没有选择日期，获取当前日期
			Date date = new Date();//获取当前日期
			s = format1(date);
			List listdate = GetAllTheDayOfThisMonth(date);//获取当月所有天
			firstday = (String) listdate.get(0) + " 00:00:00";//获取第一天
			lastday = (String) listdate.get(listdate.size()-1) + " 23:59:59";//获取最后一天
		}
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("id");
			pagepeper.setOrder(Page.ASC);
		}
		try {
			pagepeper = companyService.search(pagepeper, filterParamMap(request));
			
			JSONObject jsonData = getinfo(s, firstday, lastday, pagepeper);
			JsonUtils.renderSuccess(jsonData);
			return jsonData.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//查询粉丝增量，订单增量，照片增量
	public JSONObject getinfo(String s,String firstday, String lastday, Page<Map<String, Object>> page){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: page.getResult()) {
			JSONObject jo = new JSONObject();
			jo.put("date", s);
			jo.put("companyid", bean.get("id"));//公司id
			jo.put("companyname", bean.get("companyname"));//公司名
			
			String[] allid = getallid(Long.valueOf(bean.get("id").toString()));
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("WdatePickerStart", firstday); 
			paramMap.put("WdatePickerEnd", lastday);
			paramMap.put("allid", allid);
			Integer fansadd = seconduserService.findFansadd(paramMap);//粉丝增量
			List<Order> listorder = orderService.findListByQYId(paramMap);//查订单
			Double amountadd = countmoney(listorder, "amount")/100;//查订单金额增量
			int photoadd = photoadd(listorder);//查照片增量
			
			jo.put("fansadd", fansadd);//粉丝增量
			jo.put("amountadd", amountadd);//订单增量
			jo.put("photoadd", photoadd);//照片增量
			
			jsonArg.add(jo);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());
		
		return jsonData;
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
	
	public int photoadd(List<Order> list){
		int photoadd = 0;
		for (Order order : list) {
			List<Orderdetail>  listorderdetail = orderdetailService.findListByOid(order.getOrderid());
			for (Orderdetail orderdetail : listorderdetail) {
				photoadd += orderdetail.getNumber();
			}
		}
		return photoadd;
	}
	
	public Double countmoney(List<Order> list,String type){
		Double money = (double) 0;
		if(list != null && list.size() > 0){
			for (Order order : list) {
				if(type.equals("amount")){
					money += order.getTotalprice();
				}else if(type.equals("ptotal")){
					money += order.getProductotallprice();
				}else{}
			}
		}
		return money;
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
	
	//将日期转换为字符串
	public String format(Date date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strdate = null; //初始化date
		strdate = sdf.format(date);
		return strdate;
	}
	
	//将日期转换为字符串
	public String format1(Date date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String strdate = null; //初始化date
		strdate = sdf.format(date);
		return strdate;
	}
	
	//获取月份的最后一天
	private Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }
	
	//获取月份所有天数
	private List GetAllTheDayOfThisMonth(Date date){
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
	
	@RequestMapping(value = "exportExcel")//TODO 导出EXCLE
	public String exportExcel(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("octets/stream"); 
		response.setContentType("applicationnd.ms-excel");  
		try {
			String bName = "汇美活跃商户表.xls";
			String s = new String(bName.getBytes("UTF-8"),"ISO8859-1");
			response.setHeader("Content-Disposition", "attach; filename="+s);
			JSONArray list = Prexsl(request);
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
	
	private JSONArray Prexsl(HttpServletRequest request) throws Exception{
		//获取日期
		String s=request.getParameter("date");
		String firstday = null;
		String lastday = null;
		if(s!=null&&!s.equals("")){//选择了日期
			firstday = s + "-01 00:00:00";//当月第一天(String)
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date date = sdf.parse(s);
			lastday = format(getLastDayOfMonth(date));//获取当月最后一天
		}
		else{//没有选择日期，获取当前日期
			Date date = new Date();//获取当前日期
			s = format1(date);
			List listdate = GetAllTheDayOfThisMonth(date);//获取当月所有天
			firstday = (String) listdate.get(0) + " 00:00:00";//获取第一天
			lastday = (String) listdate.get(listdate.size()-1) + " 23:59:59";//获取最后一天
		}
		
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(1);
		pagepeper.setPageSize(companyService.countTotles(filterParamMap(request)).intValue());
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("id");
			pagepeper.setOrder(Page.ASC);
		}
		try {
			pagepeper = companyService.search(pagepeper, filterParamMap(request));
			
			JSONObject jsonData = getinfo(s, firstday, lastday, pagepeper);
			
			return jsonData.getJSONArray("rows");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public HSSFWorkbook exportExcel(JSONArray list) {
		//声明一个工作簿  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet("活跃商户信息表");  
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
        CellRangeAddress region1 = new CellRangeAddress(0,0, (short) 0, (short) 5); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列 
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
        cell.setCellValue("活跃商户信息表"); 
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
        cell2.setCellStyle(teststyle); 
        cell2.setCellStyle(stylejiacu);
        cell2.setCellValue("时间"); 
        
        HSSFCell cell3 = row2.createCell(1);
        cell3.setCellStyle(teststyle); 
        cell3.setCellStyle(stylejiacu);
        cell3.setCellValue("公司ID"); 
        
        HSSFCell cell4 = row2.createCell(2);
        cell4.setCellStyle(teststyle); 
        cell4.setCellStyle(stylejiacu);
        cell4.setCellValue("公司名称"); 
        
        HSSFCell cell5 = row2.createCell(3);
        cell5.setCellStyle(teststyle); 
        cell5.setCellStyle(stylejiacu);
        cell5.setCellValue("粉丝增量"); 
        
        HSSFCell cell6 = row2.createCell(4);
        cell6.setCellStyle(teststyle); 
        cell6.setCellStyle(stylejiacu);
        cell6.setCellValue("订单金额增量"); 
        
        HSSFCell cell7 = row2.createCell(5);
        cell7.setCellStyle(teststyle); 
        cell7.setCellStyle(stylejiacu);
        cell7.setCellValue("照片增量"); 
        
        Long fansadd = (long)0;//粉丝增量
		Double amountadd = 0.0; //订单金额增量
		int photoadd = 0; //照片赠刚
		Long allfansadd = (long)0;//总粉丝增量
		Double allamountadd = (double)0;//总订单增量
		int allphotoadd = 0;//总照片增量
        
        for (int i = 0; i < list.size(); i++) {
        	JSONObject item = list.getJSONObject(i);
        	
        	HSSFRow rowInfo = sheet.createRow(2+i);
            HSSFCell cell32 = rowInfo.createCell(0);
            cell32.setCellStyle(teststyle); 
            cell32.setCellValue(item.getString("date")); 
            
            HSSFCell cell33 = rowInfo.createCell(1);
            cell33.setCellStyle(teststyle); 
            cell33.setCellValue(item.getLong("companyid")); 
            
            HSSFCell cell34 = rowInfo.createCell(2);
            cell34.setCellStyle(teststyle); 
            cell34.setCellValue(item.getString("companyname")); 
            
            HSSFCell cell35 = rowInfo.createCell(3);
            cell35.setCellStyle(teststyle); 
            cell35.setCellValue(item.getLong("fansadd")); 
            
            HSSFCell cell36 = rowInfo.createCell(4);
            cell36.setCellStyle(teststyle); 
            cell36.setCellValue(item.getDouble("amountadd")); 
            
            HSSFCell cell37 = rowInfo.createCell(5);
            cell37.setCellStyle(teststyle); 
            cell37.setCellValue(item.getInt("photoadd")); 
            
            fansadd = item.getLong("fansadd");//粉丝增量
    		amountadd = item.getDouble("amountadd"); //订单金额增量
    		photoadd = item.getInt("photoadd"); //照片赠刚
            
            allfansadd += fansadd;
            allamountadd += amountadd;
            allphotoadd += photoadd;
		}
        
        HSSFRow rowInfo = sheet.createRow(2+list.size());
        
        HSSFCell cell34 = rowInfo.createCell(2);
        cell34.setCellStyle(teststyle); 
        cell34.setCellValue("合计："); 
        
        HSSFCell cell35 = rowInfo.createCell(3);
        cell35.setCellStyle(teststyle); 
        cell35.setCellValue(allfansadd); 
        
        HSSFCell cell36 = rowInfo.createCell(4);
        cell36.setCellStyle(teststyle); 
        cell36.setCellValue(allamountadd); 
        
        HSSFCell cell37 = rowInfo.createCell(5);
        cell37.setCellStyle(teststyle); 
        cell37.setCellValue(allphotoadd); 
        
        return workbook;
	}
	
}
