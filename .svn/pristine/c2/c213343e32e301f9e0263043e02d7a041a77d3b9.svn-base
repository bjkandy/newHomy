package com.hanson.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.common.util.Page;
import com.hanson.mapper.OrderMapper;
import com.hanson.model.odr.Order;
import com.hanson.model.odr.Txis;
import com.hanson.model.u.Company;
import com.hanson.service.OrderService;
import common.Arith;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public List<Order> getUniqueBy(Order or) {
		return orderMapper.getUniqueBy(or);
	}

	@Override
	public int orderfahuo(String orderid) {
		return orderMapper.orderfahuo(orderid);
	}
	@Override
	public List<Map<String ,Object>> transactionAnalysis(Map<String, Object> map)
	{
		return orderMapper.transactionAnalysis(map);
	}
	@Override
	public Map<String,Object> transactionAnalysisCount(Map<String, Object> map){
		return orderMapper.transactionAnalysisCount(map);
	}
	@Override
	public int ordersc(String idString) {
		return orderMapper.ordersc(idString);
	}

	@Override
	public int insertjs(Txis ts) {
		return orderMapper.insertjs(ts);
	}

	@Override
	public List<Order> queryid(Order or) {
		return orderMapper.queryid(or);
	}

	/*public HSSFWorkbook exportExcel(List<Order> lisOrder) {
		//声明一个工作簿  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet("汇美订单信息表");  
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
        cell.setCellStyle(style);
        cell.setCellValue("订单信息表"); 
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
        cell2.setCellValue("商户编号"); 
        
        HSSFCell cell3 = row2.createCell(1);
        cell3.setCellStyle(teststyle); 
        cell3.setCellStyle(stylejiacu);
        cell3.setCellValue("订单号"); 
        
        HSSFCell cell4 = row2.createCell(2);
        cell4.setCellStyle(teststyle); 
        cell4.setCellStyle(stylejiacu);
        cell4.setCellValue("客户昵称"); 
        
        HSSFCell cell5 = row2.createCell(3);
        cell5.setCellStyle(teststyle); 
        cell5.setCellStyle(stylejiacu);
        cell5.setCellValue("订单总金额"); 
        
        HSSFCell cell6 = row2.createCell(4);
        cell6.setCellStyle(teststyle); 
        cell6.setCellStyle(stylejiacu);
        cell6.setCellValue("商品总金额"); 
        
        HSSFCell cell7 = row2.createCell(5);
        cell7.setCellStyle(teststyle); 
        cell7.setCellStyle(stylejiacu);
        cell7.setCellValue("数量"); 
        
        
        
        HSSFCell cell8 = row2.createCell(5);
        cell8.setCellStyle(teststyle); 
        cell8.setCellStyle(stylejiacu);
        cell8.setCellValue("订单状态"); 
        
        HSSFCell cell8_1 = row2.createCell(6);
        cell8_1.setCellStyle(teststyle); 
        cell8_1.setCellStyle(stylejiacu);
        cell8_1.setCellValue("支付状态"); 
        
        
        HSSFCell cell9 = row2.createCell(7);
        cell9.setCellStyle(teststyle); 
        cell9.setCellStyle(stylejiacu);
        cell9.setCellValue("提现状态"); 
        
        HSSFCell cell10 = row2.createCell(8);
        cell10.setCellStyle(teststyle); 
        cell10.setCellStyle(stylejiacu);
        cell10.setCellValue("创建日期"); 
        
        HSSFCell cell11 = row2.createCell(9);
        cell11.setCellStyle(teststyle); 
        cell11.setCellStyle(stylejiacu);
        cell11.setCellValue("支付方式"); 
        
        HSSFCell cell12 = row2.createCell(10);
        cell12.setCellStyle(teststyle); 
        cell12.setCellStyle(stylejiacu);
        cell12.setCellValue("备注"); 
        
       
        for (int i = 0; i < lisOrder.size(); i++) {
        	HSSFRow rowInfo = sheet.createRow(2+i);
            HSSFCell cell32 = rowInfo.createCell(0);
            cell32.setCellStyle(teststyle); 
            cell32.setCellValue(lisOrder.get(i).getQyid() == null?0:lisOrder.get(i).getQyid()); 
            
            HSSFCell cell33 = rowInfo.createCell(1);
            cell33.setCellStyle(teststyle); 
            cell33.setCellValue(lisOrder.get(i).getOrderid()); 
            
            HSSFCell cell34 = rowInfo.createCell(2);
            cell34.setCellStyle(teststyle); 
            cell34.setCellValue(lisOrder.get(i).getCuname()); 
            
            HSSFCell cell35 = rowInfo.createCell(3);
            cell35.setCellStyle(teststyle); 
            cell35.setCellValue(lisOrder.get(i).getTotalprice() == null?0.00:lisOrder.get(i).getTotalprice()); 
            
            HSSFCell cell36 = rowInfo.createCell(4);
            cell36.setCellStyle(teststyle); 
            cell36.setCellValue(lisOrder.get(i).getProductotallprice() == null?0.00:lisOrder.get(i).getProductotallprice()); 
            
            HSSFCell cell37 = rowInfo.createCell(5);
            cell37.setCellStyle(teststyle); 
            cell37.setCellValue("");//lisOrder.get(i).getNumber() 
            
            HSSFCell cell38 = rowInfo.createCell(5);
            cell38.setCellStyle(teststyle); 
            //订单状态 ：（20," 未生产"25,"生产完毕"30,"已发货"110, "已关闭"200,"无效订单"）
            if(lisOrder.get(i).getOrderstatus() == 20 || lisOrder.get(i).getOrderstatus() == 0){
            	cell38.setCellValue("未生产"); 
            }else if(lisOrder.get(i).getOrderstatus() == 25){
            	cell38.setCellValue("生产完毕"); 
            }else if(lisOrder.get(i).getOrderstatus() == 30){
            	cell38.setCellValue("已发货"); 
            }else if(lisOrder.get(i).getOrderstatus() == 110){
            	cell38.setCellValue("已关闭"); 
            }else if(lisOrder.get(i).getOrderstatus() == 200){
            	cell38.setCellValue("无效订单"); 
            }else{
            	cell38.setCellValue("");
            }
            
            //支付状态(支付状态（0, "待支付"1,"支付中"10, "支付成功"100, "支付失败"）)
            HSSFCell cell38_1 = rowInfo.createCell(6);
            cell38_1.setCellStyle(teststyle); 
            if(lisOrder.get(i).getPaystatus() == 0){
            	cell38_1.setCellValue("待支付"); 
            }else if(lisOrder.get(i).getPaystatus() == 1){
            	cell38_1.setCellValue("支付中"); 
            }else if(lisOrder.get(i).getPaystatus() == 10){
            	cell38_1.setCellValue("支付成功"); 
            }else if(lisOrder.get(i).getPaystatus() == 100){
            	cell38_1.setCellValue("支付失败"); 
            }else{
            	cell38_1.setCellValue("");
            }
            
            //提现状态10.未提现  20.提现中40.成功提现
            HSSFCell cell300 = rowInfo.createCell(7);
            cell300.setCellStyle(teststyle);
            if(lisOrder.get(i).getTxStatus() == 10 || lisOrder.get(i).getTxStatus() == 0){
            	cell300.setCellValue("未提现");
            }else if(lisOrder.get(i).getTxStatus() == 20){
            	cell300.setCellValue("提现中");
            }else if(lisOrder.get(i).getTxStatus() == 40){
            	cell300.setCellValue("成功提现");
            }else{
            	cell300.setCellValue("");
            }
            
            HSSFCell cell39 = rowInfo.createCell(8);
            cell39.setCellStyle(teststyle); 
            cell39.setCellValue(lisOrder.get(i).getCreatedate()); 
           
            
            HSSFCell cell40 = rowInfo.createCell(9);
            //支付方式（0支付宝 1微支付）
            if(lisOrder.get(i).getPaytype() == 0){
            	cell40.setCellValue("支付宝"); 
            }else if(lisOrder.get(i).getPaytype() == 1){
            	cell40.setCellValue("微支付"); 
            }
            
            HSSFCell cell41 = rowInfo.createCell(10);
            cell41.setCellStyle(teststyle); 
            cell41.setCellValue(lisOrder.get(i).getRemark()); 
            
		}
        return workbook;
	}*/
	
	public HSSFWorkbook exportExcelNew(List<Map<String, Object>> lisOrder) {
		//声明一个工作簿  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet("汇美订单信息表");  
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
        cell.setCellStyle(style);
        cell.setCellValue("订单信息表"); 
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
        //商户编号	  订单编号	用户昵称	订单状态	订单金额	商品数量	配送方式	生产状态	邮费金额	收货人	联系电话	地址	快递公司	快递单号	下单时间

        HSSFRow row2 = sheet.createRow(1);
        HSSFCell cell2 = row2.createCell(0);
        cell2.setCellStyle(teststyle); 
        cell2.setCellStyle(stylejiacu);
        cell2.setCellValue("商户编号"); 
        
        
        
        HSSFCell cell3 = row2.createCell(1);
        cell3.setCellStyle(teststyle); 
        cell3.setCellStyle(stylejiacu);
        cell3.setCellValue("订单编号"); 
        
        HSSFCell cell4 = row2.createCell(2);
        cell4.setCellStyle(teststyle); 
        cell4.setCellStyle(stylejiacu);
        cell4.setCellValue("用户昵称"); 
        
        HSSFCell cell4_1 = row2.createCell(3);
        cell4_1.setCellStyle(teststyle); 
        cell4_1.setCellStyle(stylejiacu);
        cell4_1.setCellValue("订单状态"); 
        
        HSSFCell cell5 = row2.createCell(4);
        cell5.setCellStyle(teststyle); 
        cell5.setCellStyle(stylejiacu);
        cell5.setCellValue("订单金额"); 
        
        HSSFCell cell6 = row2.createCell(5);
        cell6.setCellStyle(teststyle); 
        cell6.setCellStyle(stylejiacu);
        cell6.setCellValue("商品数量"); 
        
        HSSFCell cell8 = row2.createCell(6);
        cell8.setCellStyle(teststyle); 
        cell8.setCellStyle(stylejiacu);
        cell8.setCellValue("配送方式"); 
        
        HSSFCell cell8_1 = row2.createCell(7);
        cell8_1.setCellStyle(teststyle); 
        cell8_1.setCellStyle(stylejiacu);
        cell8_1.setCellValue("生产状态"); 
        
        
        HSSFCell cell9 = row2.createCell(8);
        cell9.setCellStyle(teststyle); 
        cell9.setCellStyle(stylejiacu);
        cell9.setCellValue("邮费金额"); 
        
        HSSFCell cell10 = row2.createCell(9);
        cell10.setCellStyle(teststyle); 
        cell10.setCellStyle(stylejiacu);
        cell10.setCellValue("收货人"); 
        
        HSSFCell cell11 = row2.createCell(10);
        cell11.setCellStyle(teststyle); 
        cell11.setCellStyle(stylejiacu);
        cell11.setCellValue("联系电话"); 
        
        HSSFCell cell12 = row2.createCell(11);
        cell12.setCellStyle(teststyle); 
        cell12.setCellStyle(stylejiacu);
        cell12.setCellValue("地址"); 
      
        HSSFCell cell14 = row2.createCell(12);
        cell14.setCellStyle(teststyle); 
        cell14.setCellStyle(stylejiacu);
        cell14.setCellValue("快递公司");
        
        HSSFCell cell15 = row2.createCell(13);
        cell15.setCellStyle(teststyle); 
        cell15.setCellStyle(stylejiacu);
        cell15.setCellValue("快递单号");
        
        HSSFCell cell16 = row2.createCell(14);
        cell16.setCellStyle(teststyle); 
        cell16.setCellStyle(stylejiacu);
        cell16.setCellValue("下单时间");
        
        HSSFCell cell17 = row2.createCell(15);
        cell17.setCellStyle(teststyle); 
        cell17.setCellStyle(stylejiacu);
        cell17.setCellValue("商品名称");
        
        HSSFCell cell18 = row2.createCell(16);
        cell18.setCellStyle(teststyle); 
        cell18.setCellStyle(stylejiacu);
        cell18.setCellValue("套餐类型");
        
        HSSFCell cell19 = row2.createCell(17);
        cell19.setCellStyle(teststyle); 
        cell19.setCellStyle(stylejiacu);
        cell19.setCellValue("产品数量");
        
        HSSFCell cell20 = row2.createCell(18);
        cell20.setCellStyle(teststyle); 
        cell20.setCellStyle(stylejiacu);
        cell20.setCellValue("产品金额");
        
        HSSFCell cell21 = row2.createCell(19);
        cell21.setCellStyle(teststyle); 
        cell21.setCellStyle(stylejiacu);
        cell21.setCellValue("尺寸");
        
        HSSFCell cell22 = row2.createCell(20);
        cell22.setCellStyle(teststyle); 
        cell22.setCellStyle(stylejiacu);
        cell22.setCellValue("材质");
        
        HSSFCell cell23 = row2.createCell(21);
        cell23.setCellStyle(teststyle); 
        cell23.setCellStyle(stylejiacu);
        cell23.setCellValue("是否过塑");
        
        HSSFCell cell24 = row2.createCell(22);
        cell24.setCellStyle(teststyle); 
        cell24.setCellStyle(stylejiacu);
        cell24.setCellValue("颜色");
        
        HSSFCell cell25 = row2.createCell(23);
        cell25.setCellStyle(teststyle); 
        cell25.setCellStyle(stylejiacu);
        cell25.setCellValue("备注");
        
        int startRow = 0;//合并开始行号  
        int endRow = 0;//合并结束行号  
        int rowNum = 2;//行号
        
        for (int i = 0; i < lisOrder.size(); i++) {
        	Map<String, Object> listin = lisOrder.get(i);
        	HSSFRow rowInfo = sheet.createRow(2+i);
        	//商户编号
            HSSFCell cell32 = rowInfo.createCell(0);
            cell32.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell32.setCellStyle(teststyle); 
            cell32.setCellValue(Integer.valueOf(listin.get("qyid") == null?"":listin.get("qyid").toString()));
            
            //订单编号
            HSSFCell cell33 = rowInfo.createCell(1);
            cell33.setCellStyle(teststyle); 
            cell33.setCellValue(listin.get("orderid") == null?"":listin.get("orderid").toString()); 
            
            //用户昵称
            HSSFCell cell34 = rowInfo.createCell(2);
            cell34.setCellStyle(teststyle); 
            cell34.setCellValue(listin.get("cuname") == null?"":listin.get("cuname").toString()); 
            
            //订单状态(支付状态（0, "待支付"1,"支付中"10, "支付成功"100, "支付失败"）)
            HSSFCell cell38_1 = rowInfo.createCell(3);
            cell38_1.setCellStyle(teststyle); 
            if(listin.get("paystatus") != null){
            	if("0".equals(listin.get("paystatus").toString())){
                	cell38_1.setCellValue("待支付"); 
                }else if("1".equals(listin.get("paystatus").toString())){
                	cell38_1.setCellValue("支付中"); 
                }else if("10".equals(listin.get("paystatus").toString())){
                	cell38_1.setCellValue("支付成功"); 
                }else if("100".equals(listin.get("paystatus").toString())){
                	cell38_1.setCellValue("支付失败"); 
                }else{
                	cell38_1.setCellValue("");
                }
            }else{
            	cell38_1.setCellValue("");
            }
            
            
            //订单金额
            HSSFCell cell36 = rowInfo.createCell(4);
            cell36.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell36.setCellStyle(teststyle); 
            try {
				cell36.setCellValue(new DecimalFormat().parse(listin.get("totalprice") == null ? "0.00" : listin.get("totalprice").toString()).doubleValue());
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
            
            //商品数量(暂时屏蔽影响效率) 
            HSSFCell cell37 = rowInfo.createCell(5);
            cell37.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell37.setCellStyle(teststyle); 
            cell37.setCellValue(Integer.valueOf(listin.get("num") == null ? "0" : listin.get("num").toString()));//lisOrder.get(i).getNumber();   
            
            //配送方式，1为快递配送，2为上门自取
            HSSFCell cell37_1 = rowInfo.createCell(6);
            cell37_1.setCellStyle(teststyle); 
            if(listin.get("expresstype") != null){
            	if("1".equals(listin.get("expresstype").toString())){
                	cell37_1.setCellValue("快递配送"); 
                }else if("2".equals(listin.get("expresstype").toString())){
                	cell37_1.setCellValue("上门自取"); 
                }
            }else{
            	cell37_1.setCellValue("");
            }
            
          
            //生产状态 ：（20," 未生产"25,"生产完毕"30,"已发货"110, "已关闭"200,"无效订单"）
            HSSFCell cell38 = rowInfo.createCell(7);
            cell38.setCellStyle(teststyle); 
            if(listin.get("orderstatus") != null){
            	if("20".equals(listin.get("orderstatus").toString()) || "0".equals(listin.get("orderstatus").toString())){
                	cell38.setCellValue("未生产"); 
                }else if("25".equals(listin.get("orderstatus").toString())){
                	cell38.setCellValue("生产完毕"); 
                }else if("35".equals(listin.get("orderstatus").toString())){
                	cell38.setCellValue("已发货"); 
                }else if("100".equals(listin.get("orderstatus").toString())){
                	cell38.setCellValue("已关闭"); 
                }else if("200".equals(listin.get("orderstatus").toString())){
                	cell38.setCellValue("无效订单"); 
                }else{
                	cell38.setCellValue("");
                }
            }else{
            	cell38.setCellValue("");
            }
            
            //邮费金额
            HSSFCell cell39_1 = rowInfo.createCell(8);
            cell39_1.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell39_1.setCellStyle(teststyle); 
            if(listin.get("postagePrice") != null){
            	cell39_1.setCellValue(Double.valueOf(listin.get("postagePrice").toString())); 
            }
            
            //收货人
            HSSFCell cell39 = rowInfo.createCell(9);
            cell39.setCellStyle(teststyle); 
            if(listin.get("shipname") != null){
            	cell39.setCellValue(listin.get("shipname").toString()); 
            }else{
            	cell39.setCellValue(""); 
            }
            
          	//联系电话
            HSSFCell cell39_2 = rowInfo.createCell(10);
            cell39_2.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell39_2.setCellStyle(teststyle); 
            if(listin.get("phone") != null){
            	cell39_2.setCellValue(Double.valueOf(listin.get("phone").toString())); 
            }else{
            	cell39_2.setCellValue(""); 
            }
            
            //地址
            HSSFCell cell39_3 = rowInfo.createCell(11);
            cell39_3.setCellStyle(teststyle); 
            if(listin.get("shipaddress") != null){
            	cell39_3.setCellValue(listin.get("shipaddress").toString()); 
            }else{
            	cell39_3.setCellValue(""); 
            }
            
            //快递公司
            HSSFCell cell39_4 = rowInfo.createCell(12);
            cell39_4.setCellStyle(teststyle); 
            if(listin.get("ExpressCompanyName") != null){
            	cell39_4.setCellValue(listin.get("ExpressCompanyName").toString()); 
            }else{
            	cell39_4.setCellValue(""); 
            }
            
            //快递单号
            HSSFCell cell39_5 = rowInfo.createCell(13);
            cell39_5.setCellStyle(teststyle); 
            if(listin.get("expressno") != null){
            	cell39_5.setCellValue(listin.get("expressno").toString()); 
            }else{
            	cell39_5.setCellValue(""); 
            }
            
            //下单时间
            HSSFCell cell39_6 = rowInfo.createCell(14);
            cell39_6.setCellStyle(teststyle); 
            if(listin.get("createdate") != null){
            	cell39_6.setCellValue(listin.get("createdate").toString()); 
            }else{
            	cell39_6.setCellValue(""); 
            }
            
            //商品名称
            HSSFCell cell39_7 = rowInfo.createCell(15);
            cell39_7.setCellStyle(teststyle); 
            if(listin.get("name") != null){
            	cell39_7.setCellValue(listin.get("name").toString()); 
            }else{
            	cell39_7.setCellValue(""); 
            }
            
            //套餐类型
            HSSFCell cell39_8 = rowInfo.createCell(16);
            cell39_8.setCellStyle(teststyle); 
            if(listin.get("producttype") != null){
            	if("10".equals(listin.get("producttype").toString())){
            		cell39_8.setCellValue("照片冲印"); 
            	}else if("20".equals(listin.get("producttype").toString())){
            		cell39_8.setCellValue("精品相框"); 
            	}
            }else{
            	cell39_8.setCellValue(""); 
            }
            
            //产品数量
            HSSFCell cell39_10 = rowInfo.createCell(17);
            cell39_10.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell39_10.setCellStyle(teststyle); 
            if(listin.get("number") != null){
            	cell39_10.setCellValue(Integer.valueOf(listin.get("number").toString())); 
            }else{
            	cell39_10.setCellValue(""); 
            }
            
            //产品金额
            HSSFCell cell39_9 = rowInfo.createCell(18);
            cell39_9.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell39_9.setCellStyle(teststyle); 
            if(listin.get("productPrice") != null){
            	try {
					cell39_9.setCellValue(new DecimalFormat().parse(listin.get("productPrice") == null ? "0.00" : listin.get("productPrice").toString()).doubleValue());
				} catch (ParseException e) {
					e.printStackTrace();
				} //new BigDecimal().doubleValue()
            }else{
            	cell39_9.setCellValue(""); 
            }
            
            //尺寸
            HSSFCell cell39_11 = rowInfo.createCell(19);
            cell39_11.setCellStyle(teststyle); 
            if(listin.get("size") != null){
            	cell39_11.setCellValue(listin.get("size").toString()); 
            }else{
            	cell39_11.setCellValue(""); 
            }
            
            //材质
            HSSFCell cell39_12 = rowInfo.createCell(20);
            cell39_12.setCellStyle(teststyle); 
            if(listin.get("specinfo") != null){
            	cell39_12.setCellValue(listin.get("specinfo").toString()); 
            }else{
            	cell39_12.setCellValue(""); 
            }
            
            //是否过塑
            HSSFCell cell39_13 = rowInfo.createCell(21);
            cell39_13.setCellStyle(teststyle); 
            if(listin.get("plastic") != null){
            	//是否过塑1:过塑0:不过塑
            	if("1".equals(listin.get("plastic").toString())){
            		cell39_13.setCellValue("过塑"); 
            	}else{
            		cell39_13.setCellValue("不过塑"); 
            	}
            }else{
            	cell39_13.setCellValue(""); 
            }
            
            //颜色
            HSSFCell cell39_14 = rowInfo.createCell(22);
            cell39_14.setCellStyle(teststyle); 
            if(listin.get("color") != null){
            	cell39_14.setCellValue(listin.get("color").toString()); 
            }else{
            	cell39_14.setCellValue(""); 
            }
            
            //备注
            HSSFCell cell39_15 = rowInfo.createCell(23);
            cell39_15.setCellStyle(teststyle); 
            if(listin.get("remark") != null){
            	cell39_15.setCellValue(listin.get("remark").toString()); 
            }else{
            	cell39_15.setCellValue(""); 
            }
            
            if(i != 0){  
            	if(lisOrder.get(i-1).get("orderid").equals(lisOrder.get(i).get("orderid"))){
            		endRow++;//则合并结束行号+1  
            		for(int j = 0; j < 15; j++){
            			sheet.addMergedRegion(new CellRangeAddress(  
                                startRow,
                                endRow,  
                                j,
                                j
                                ));//合并单元格 
            		}
            		
            		//合并备注
            		sheet.addMergedRegion(new CellRangeAddress(  
                        startRow,
                        endRow,
                        23,
                        23
                    ));//合并单元格 
            		
            	}else{
            		startRow = rowNum;
                    endRow = rowNum;
            	}
            	
            }
            rowNum++;
            
            
		}
        return workbook;
	}

	@Override
	public List<Company> queryje(Company cp) {
		return orderMapper.queryje(cp);
	}

	@Override
	public int ordertx(Order or) {
		return orderMapper.ordertx(or);
	}

	@Override
	public List<Order> findListByQYId(Map<String, Object> map) {
		return orderMapper.findListByQYId(map);
	}

	@Override
	public List<Order> findListByWhere(Map<String, Object> map) {
		return orderMapper.findListByWhere(map);
	}
	
	
	@Override
	public Map<String, Object> findPhotoaddnumber(Map<String, Object> map) {
		return orderMapper.findPhotoaddnumber(map);
	}

	@Override
	public Map<String, Object> findCountByWhere(Map<String, Object> map) {
		return orderMapper.findCountByWhere(map);
	}

	@Override
	public Page<Map<String, Object>> findOrderPage(
			Page<Map<String, Object>> page, Map<String, Object> map) {
		//分页查询通用参数
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
		//统计数量
		long totalCount = orderMapper.countTotles(map);
		page.setTotalCount(totalCount);
		//填充数据
		if(totalCount >0){
			page.setResult(orderMapper.search(map));
		}
		return page;
	}

	@Override
	public List<Order> getOrdersExcel(Order or) {
		return orderMapper.getOrdersExcel(or);
	}

	@Override
	public List<Map<String, Object>> getOrdersExcelNew(Order order) {
		return orderMapper.getOrdersExcelNew(order);
	}

	@Override
	public Order findOrderById(Order order) {
		return orderMapper.findOrderById(order);
	}
	
	
}
