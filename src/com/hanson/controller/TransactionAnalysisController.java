package com.hanson.controller;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hanson.service.OrderService;
import com.hanson.util.StringUtil;

import common.CommonServiceImpl;
import common.Result;

@Controller
@RequestMapping("statistic")
public class TransactionAnalysisController {
	final Logger logger = LoggerFactory.getLogger(TransactionAnalysisController.class);
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/businessDataExcel")
	@ResponseBody
	public String businessDataExcel(String startDate,String endDate,String judgeDayOrMonths,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		String bName = "交易数据统计表.xls";
		String s = new String(bName.getBytes("UTF-8"),"ISO8859-1");
		response.setHeader("Content-Disposition", "attach; filename="+s);
		
		if(startDate.equals(",")){
			return "";
		}
		startDate=startDate.replace(",",""); 
		endDate=endDate.replace(",", "");
		List<Map<String ,Object>> totalResultMap = null;
		if(judgeDayOrMonths.trim().equals("day")){
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("createdate", startDate);
				map.put("enddate", endDate);
				map.put("selecttype", 1);
				totalResultMap = orderService.transactionAnalysis(map);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(judgeDayOrMonths.trim().equals("month")){
			try {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("createdate", startDate+"-00");
				map.put("enddate", endDate+"-00");
				map.put("selecttype", 0);
				totalResultMap = orderService.transactionAnalysis(map);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		HSSFWorkbook exportExcel = exportExcel(totalResultMap);
		OutputStream out = response.getOutputStream();
		try {
			exportExcel.write(out);
			System.out.println("导出成功。。。。。。");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}
	
	public HSSFWorkbook exportExcel(List<Map<String, Object>> list) {
		//声明一个工作簿  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet("交易统计表");  
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
        CellRangeAddress region1 = new CellRangeAddress(0,0, (short) 0, (short) 12); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列 
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
        HSSFCell cellbt5 = row.createCell(9);
        cellbt5.setCellStyle(style);
        
        HSSFCell cellbt6 = row.createCell(5);
        cellbt6.setCellStyle(style);
        
        HSSFCell cellbt7 = row.createCell(6);
        cellbt7.setCellStyle(style);
        
        cell.setCellStyle(style);
        cell.setCellValue("交易统计表"); 
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
        cell2.setCellValue("日期"); 
        
        HSSFCell cell3 = row2.createCell(1);
        cell3.setCellStyle(teststyle); 
        cell3.setCellStyle(stylejiacu);
        cell3.setCellValue("订单总数"); 
        
        HSSFCell cell4 = row2.createCell(2);
        cell4.setCellStyle(teststyle); 
        cell4.setCellStyle(stylejiacu);
        cell4.setCellValue("付款订单总数"); 
        
        HSSFCell cell5 = row2.createCell(3);
        cell5.setCellStyle(teststyle); 
        cell5.setCellStyle(stylejiacu);
        cell5.setCellValue("订单总金额"); 
        
        HSSFCell cell6 = row2.createCell(4);
        cell6.setCellStyle(teststyle); 
        cell6.setCellStyle(stylejiacu);
        cell6.setCellValue("付款订单总金额"); 
        
        HSSFCell cell7 = row2.createCell(5);
        cell7.setCellStyle(teststyle); 
        cell7.setCellStyle(stylejiacu);
        cell7.setCellValue("下单人数"); 
        
        HSSFCell cell8 = row2.createCell(6);
        cell8.setCellStyle(teststyle); 
        cell8.setCellStyle(stylejiacu);
        cell8.setCellValue("付款人数"); 
        
        for (int i = 0; i < list.size(); i++) {
        	HSSFRow rowInfo = sheet.createRow(2+i);
            HSSFCell cell32 = rowInfo.createCell(0);
            cell32.setCellStyle(teststyle); 
            cell32.setCellValue(list.get(i).get("createtime").toString()); 
            
            HSSFCell cell33 = rowInfo.createCell(1);
            cell33.setCellStyle(teststyle); 
            cell33.setCellValue(Integer.valueOf(list.get(i).get("odrid").toString())); 
            
            HSSFCell cell34 = rowInfo.createCell(2);
            cell34.setCellStyle(teststyle); 
            cell34.setCellValue(Integer.valueOf(list.get(i).get("payOdrNumber").toString())); 
            
            HSSFCell cell35 = rowInfo.createCell(3);
            cell35.setCellStyle(teststyle);
            
            float allprice= Float.valueOf(list.get(i).get("allprice").toString());
            cell35.setCellValue(allprice/100); 
            
            HSSFCell cell36 = rowInfo.createCell(4);
            cell36.setCellStyle(teststyle); 
            float sourceF = Float.valueOf(list.get(i).get("payOdrTotalprice").toString());
        
            cell36.setCellValue(sourceF/100); 
            
            HSSFCell cell37 = rowInfo.createCell(5);
            cell37.setCellStyle(teststyle); 
            cell37.setCellValue(Integer.valueOf(list.get(i).get("allorderperson").toString()));
            
            HSSFCell cell38 = rowInfo.createCell(6);
            cell38.setCellStyle(teststyle); 
            cell38.setCellValue(Integer.valueOf(list.get(i).get("payorderperson").toString()));
		}
        return workbook;
	}
	
	
	@RequestMapping(value="trananlysis",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String transactionAnalysis(String startDate,String endDate,String judgeDayOrMonths,HttpServletResponse response){
		if(startDate.equals(",")){
			return "";
		}
		startDate=startDate.replace(",",""); 
		endDate=endDate.replace(",", "");
		Map<String, Object> resultList = null;
		List<Map<String ,Object>> totalResultMap = null;
		if(judgeDayOrMonths.trim().equals("day")){
			try {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("createdate", startDate );
				map1.put("enddate", endDate);
				map1.put("selecttype", 1);
				resultList = orderService.transactionAnalysisCount(map1);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("createdate", startDate);
				map.put("enddate", endDate);
				map.put("selecttype", 1);
				totalResultMap = orderService.transactionAnalysis(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(judgeDayOrMonths.trim().equals("month")){
			try {
				
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("createdate", startDate+"-00");
				map1.put("enddate", endDate+"-00");
				map1.put("selecttype", 0);
				resultList = orderService.transactionAnalysisCount(map1);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("createdate", startDate+"-00");
				map.put("enddate", endDate+"-00");
				map.put("selecttype", 0);
				totalResultMap = orderService.transactionAnalysis(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/*for (Map<String, Object> map : resultList) {
			System.out.println(map.get("date")+"\t"+map.get("number")+"\t"+map.get("newfans")+"\t"+map.get("numberoffansonfoot")+"\t"+map.get("netfannumber") +"\t"+map.get("cumulativefans"));
		}*/
		JSONObject resultObj = new JSONObject();
		resultObj.put("resultListMap",resultList);
		resultObj.put("totalResultMap", totalResultMap);
		return resultObj.toString();
		
	}
	@RequestMapping("analysis")
	public String analysis(){
		return "statistic/trananlysis";
	}
	public String ifmonth(String date){
		String monthdate = null;
		switch (date.split("-")[1]) {
			case "01":
			case "03":
			case "05":
			case "07":
			case "08":
			case "10":
			case "12":
				monthdate = date + "-31 23:59:59";
				break;
			default:
				monthdate = date + "-30 23:59:59";
				break;
		}
		return monthdate;
	}
	
	private static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
	    ArrayList<String> result = new ArrayList<String>();
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
	
	public static String timeStamp2Date(Date seconds,String format) throws ParseException {  
    	SimpleDateFormat sdf = new java.text.SimpleDateFormat(format); 
    	String s1 = sdf.format(seconds); //2015-02-09 format()才是格式化 Date t1 = null; t1 = sdf.parse(s1);//Mon Feb 09 00:00:00 CST 2015 parse()的话是转成Date类型
        return s1.toString();  
    }
	
	private static List<Date> dateSplit(Date startDate, Date endDate) throws Exception {
	    if (!startDate.before(endDate))
	        throw new Exception("开始时间应该在结束时间之后");
	    Long spi = endDate.getTime() - startDate.getTime();
	    Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

	    List<Date> dateList = new ArrayList<Date>();
	    dateList.add(endDate);
	    for (int i = 1; i <= step; i++) {
	        dateList.add(java.sql.Date.valueOf(timeStamp2Date(new Date(dateList.get(i - 1).getTime() - (24 * 60 * 60 * 1000)),"yyyy-MM-dd")));// 比上一天减一
	    }
	    return dateList;
	}
}
