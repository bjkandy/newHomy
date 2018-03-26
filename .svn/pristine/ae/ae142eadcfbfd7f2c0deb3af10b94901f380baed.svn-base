package com.hanson.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.common.util.Page;
import com.hanson.mapper.TxjsMapper;
import com.hanson.model.Company;
import com.hanson.model.odr.Order;
import com.hanson.model.odr.Txjs;
import com.hanson.service.TxjsService;

@Service
public class TxjsServiceImpl implements  TxjsService{
	@Autowired
    private  TxjsMapper  txjsMapper;
	

	 /**
	 *  查询提现列表
	 * @param tx
	 * @return
	 */
	public List<Map<String,Object>> getTxjs(Map<String,Object> tx) {
		checkPageParam(tx);
		return txjsMapper.getTxjs(tx);
	}
	 /**
	 *  下载查询提现列表
	 * @param tx
	 * @return
	 */
	public List<Map<String,Object>> downloadTx(Map<String,Object> tx) {
		return txjsMapper.downloadTx(tx);
	}
	 /**
	 *  查询提现列表数量
	 * @param tx
	 * @return
	 */
	 public String getTxCount(Map<String,Object> tx){
		
		 return txjsMapper.getTxCount(tx);
	 }
	 public void downloadExcel(HttpServletRequest request,HttpServletResponse response,String fileName,String downFile){
		 String str = "attachment;filename=";
		 String name="";
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/vnd.ms-excel");
		 InputStream fis = null;
		 try {
	         name = java.net.URLEncoder.encode(fileName+".xls","UTF-8");
	         response.addHeader("Content-Disposition", str + new String(name.getBytes("UTF-8"),"UTF-8"));
	         fis = new FileInputStream(downFile);
		 	request.setCharacterEncoding("utf8");
		 	byte[] bytes = new byte[1024];
		 	while(fis.read(bytes) != -1 ) {
		 	        response.getOutputStream().write(bytes);
		         }
		 	response.getOutputStream().flush();
		 	response.getOutputStream().close();
		 } catch (Exception e) {
		 	e.printStackTrace();
		 } finally {
		 	if(fis != null){
		 		try{fis.close();}catch(Exception e){}
		 	}
		 }
	 }
	 public String txExcel(List<Map<String,Object>> txList){
		 // 创建一个webbook，对应一个Excel文件  
	        HSSFWorkbook wb = new HSSFWorkbook();  
	        // 在webbook中添加一个sheet,对应Excel文件中的sheet  
	        HSSFSheet sheet = wb.createSheet("提现记录表");  
	        // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        HSSFRow row = sheet.createRow((int) 0);  
	        // 创建单元格，并设置值表头 设置表头居中  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	  
	        HSSFCell cell = row.createCell(0);  
	        cell.setCellValue("提现编号");  
	        cell.setCellStyle(style);  
	        cell = row.createCell(1);  
	        cell.setCellValue("商户编号");   
	        cell.setCellStyle(style);  
	        cell = row.createCell(2);  
	        cell.setCellValue("商户名称");  
	        cell.setCellStyle(style);  
	        cell = row.createCell(3);  
	        cell.setCellValue("提现金额");  
	        cell.setCellStyle(style);
	        cell = row.createCell(4);  
	        cell.setCellValue("订单数量");  
	        cell.setCellStyle(style);
	        cell = row.createCell(5);  
	        cell.setCellValue("提现状态");  
	        cell.setCellStyle(style);
	        cell = row.createCell(6);  
	        cell.setCellValue("申请时间");  
	        cell.setCellStyle(style);
	        cell = row.createCell(7);  
	        cell.setCellValue("结算时间");  
	        cell.setCellStyle(style);	         
	  
	        for (int i = 0; i < txList.size(); i++)  
	        {  
	            row = sheet.createRow((int) i + 1);  
	            Map<String,Object> txdata = (Map<String,Object>) txList.get(i);  
	            // 创建单元格，并设置值  
	            row.createCell(0).setCellValue((int)(Integer)txdata.get("ID"));  
	            row.createCell(1).setCellValue((int)(Integer)txdata.get("qyid"));  
	            row.createCell(2).setCellValue((String)txdata.get("companyname"));  
	            row.createCell(3).setCellValue(((BigDecimal)txdata.get("amount")).doubleValue());
	            row.createCell(4).setCellValue((Long)txdata.get("ordernum"));
	            if((int)(Integer)txdata.get("status") == 20){
	            	 row.createCell(5).setCellValue("提现中");
	            }
	            if((int)(Integer)txdata.get("status") == 40){
	            	 row.createCell(5).setCellValue("提现成功");
	            }
	            row.createCell(6).setCellValue(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format((Date)txdata.get("createdate")));
	            if(null != txdata.get("finishdate")){
	            	row.createCell(7).setCellValue(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format((Date)txdata.get("finishdate")));
	            }else{
	            	row.createCell(7).setCellValue("null");
	            }
	          }  
	        String realPath = null;
	        // 第六步，将文件存到指定位置  
	        try  
	        {  
	        	//在服务器创建临时数据文件
//	        	realPath = "E:/txTable.xlsx";
	        	realPath = "/mnt/txTable.xls";
//	        	File sf = new File(path);
//				if (!sf.exists()) {
//					sf.mkdirs();
//				}
//				 realPath = sf.getPath(); 
	            FileOutputStream fout = new FileOutputStream(realPath);  
	            wb.write(fout);  
	            fout.close();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        } 
	        return realPath;
	    }  
	 
	 /**
	 * 检查分页参数
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String,Object> checkPageParam(Map<String,Object> data) {
		
		Map<String,Object> res = new HashMap<String,Object>();
		if(data.get("pageSize")==null || data.get("pageSize")== ""){
            data.put("pageSize", "10");
		}
		if(data.get("pageNo")==null || data.get("pageNo")== ""){
			data.put("pageNo", "1");
		}
		
		data.put("startLin", (Integer.parseInt((String)data.get("pageNo"))-1)*Integer.parseInt((String)data.get("pageSize")));
		data.put("pageSize",Integer.parseInt((String)data.get("pageSize")));
		data = res;
		return res;
	}

	@Override
	public void daKuan(Txjs tx) {
		 txjsMapper.daKuan(tx);
	}

	@Override
	public Company findCompanyByTxid(Txjs tx) {
		return txjsMapper.findCompanyByTxid(tx);
	}

	@Override
	public List<Order> findOrdersByTxid(Txjs tx) {
		// TODO Auto-generated method stub
		return txjsMapper.findOrdersByTxid(tx);
	}
	
	@Override
	public Page<Map<String, Object>> findTxjsPage(
			Page<Map<String, Object>> page, Map<String, Object> map) {
		//分页查询通用参数
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
		//统计数量
		long totalCount = txjsMapper.countTotles(map);
		page.setTotalCount(totalCount);
		//填充数据
		if(totalCount >0){
			page.setResult(txjsMapper.search(map));
		}
		return page;
	}
	
	@Override
	public List<Txjs> getTxjsExcel(Txjs txjs) {
		return txjsMapper.getTxjsExcel(txjs);
	}
	
	@Override
	public HSSFWorkbook exportExcel(List<Txjs> lisTxjs) {
		
		
		//声明一个工作簿  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet("汇美提现信息表");  
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
        cell.setCellValue("提现信息表"); 
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
        cell2.setCellValue("提现编号"); 
        
        HSSFCell cell3 = row2.createCell(1);
        cell3.setCellStyle(teststyle); 
        cell3.setCellStyle(stylejiacu);
        cell3.setCellValue("商户编号"); 
        
        HSSFCell cell4 = row2.createCell(2);
        cell4.setCellStyle(teststyle); 
        cell4.setCellStyle(stylejiacu);
        cell4.setCellValue("商户名称"); 
        
        HSSFCell cell5 = row2.createCell(3);
        cell5.setCellStyle(teststyle); 
        cell5.setCellStyle(stylejiacu);
        cell5.setCellValue("订单数量"); 
        
        HSSFCell cell6 = row2.createCell(4);
        cell6.setCellStyle(teststyle); 
        cell6.setCellStyle(stylejiacu);
        cell6.setCellValue("提现状态"); 
        
        HSSFCell cell7 = row2.createCell(5);
        cell7.setCellStyle(teststyle); 
        cell7.setCellStyle(stylejiacu);
        cell7.setCellValue("审核状态"); 
        
        HSSFCell cell8 = row2.createCell(6);
        cell8.setCellStyle(teststyle); 
        cell8.setCellStyle(stylejiacu);
        cell8.setCellValue("申请时间"); 
        
        HSSFCell cell9 = row2.createCell(7);
        cell9.setCellStyle(teststyle); 
        cell9.setCellStyle(stylejiacu);
        cell9.setCellValue("结算时间"); 
        
        for (int i = 0; i < lisTxjs.size(); i++) {
        	HSSFRow rowInfo = sheet.createRow(2+i);
            HSSFCell cell32 = rowInfo.createCell(0);
            cell32.setCellStyle(teststyle); 
            cell32.setCellValue(lisTxjs.get(i).getId()); 
            
            HSSFCell cell33 = rowInfo.createCell(1);
            cell33.setCellStyle(teststyle); 
            cell33.setCellValue(lisTxjs.get(i).getQyid()); 
            
            HSSFCell cell34 = rowInfo.createCell(2);
            cell34.setCellStyle(teststyle); 
            cell34.setCellValue(lisTxjs.get(i).getCompanyname()); 
            
            HSSFCell cell35 = rowInfo.createCell(3);
            cell35.setCellStyle(teststyle); 
            cell35.setCellValue(lisTxjs.get(i).getAmount()); 
            
            HSSFCell cell36 = rowInfo.createCell(4);
            cell36.setCellStyle(teststyle); 
            cell36.setCellValue(lisTxjs.get(i).getOrdernumber()); 
            
            HSSFCell cell37 = rowInfo.createCell(5);
            cell37.setCellStyle(teststyle); 
            Integer status = lisTxjs.get(i).getStatus();
            if(status == 10){
            	cell37.setCellValue("未提现");
            }else if(status == 20){
            	cell37.setCellValue("提现中");
            }else if(status == 40){
            	cell37.setCellValue("成功提现");
            }
            
            HSSFCell cell38 = rowInfo.createCell(6);
            cell38.setCellStyle(teststyle); 
            cell38.setCellValue("未审核"); 
            
            HSSFCell cell39 = rowInfo.createCell(7);
            cell39.setCellStyle(teststyle); 
            cell39.setCellValue(lisTxjs.get(i).getCreatedate()); 
            
            HSSFCell cell40 = rowInfo.createCell(8);
            cell40.setCellStyle(teststyle); 
            cell40.setCellValue(lisTxjs.get(i).getFinishdate() == null?"":lisTxjs.get(i).getFinishdate().toString()); 
            
		}
        return workbook;
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
		long totalCount = txjsMapper.countOrderTotles(map);
		page.setTotalCount(totalCount);
		//填充数据
		if(totalCount >0){
			page.setResult(txjsMapper.searchOrder(map));
		}
		return page;
	}
	@Override
	public List<Map<String, Object>> getTxjsExcelNew(Txjs txjs) {
		return txjsMapper.getTxjsExcelNew(txjs);
	}
	
	@Override
	public HSSFWorkbook exportExcelNew(List<Map<String, Object>> listTxjs) {
		//声明一个工作簿  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet("汇美提现信息表");  
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
        cell.setCellValue("提现信息表"); 
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
        cell2.setCellValue("申请日期"); 
        
        HSSFCell cell3 = row2.createCell(1);
        cell3.setCellStyle(teststyle); 
        cell3.setCellStyle(stylejiacu);
        cell3.setCellValue("提现编号"); 
        
        HSSFCell cell4 = row2.createCell(2);
        cell4.setCellStyle(teststyle); 
        cell4.setCellStyle(stylejiacu);
        cell4.setCellValue("提现金额（元）"); 
        
        HSSFCell cell5 = row2.createCell(3);
        cell5.setCellStyle(teststyle); 
        cell5.setCellStyle(stylejiacu);
        cell5.setCellValue("提现状态"); 
        
        HSSFCell cell6 = row2.createCell(4);
        cell6.setCellStyle(teststyle); 
        cell6.setCellStyle(stylejiacu);
        cell6.setCellValue("商户编号"); 
        
        HSSFCell cell7 = row2.createCell(5);
        cell7.setCellStyle(teststyle); 
        cell7.setCellStyle(stylejiacu);
        cell7.setCellValue("商户名"); 
        
        HSSFCell cell8 = row2.createCell(6);
        cell8.setCellStyle(teststyle); 
        cell8.setCellStyle(stylejiacu);
        cell8.setCellValue("商户类型"); 
        
        		
        HSSFCell cell9 = row2.createCell(7);
        cell9.setCellStyle(teststyle); 
        cell9.setCellStyle(stylejiacu);
        cell9.setCellValue("订单数量"); 
        
        HSSFCell cell10 = row2.createCell(8);
        cell10.setCellStyle(teststyle); 
        cell10.setCellStyle(stylejiacu);
        cell10.setCellValue("用户昵称"); 
        
        HSSFCell cell11 = row2.createCell(9);
        cell11.setCellStyle(teststyle); 
        cell11.setCellStyle(stylejiacu);
        cell11.setCellValue("订单编号");
        
        HSSFCell cell12 = row2.createCell(10);
        cell12.setCellStyle(teststyle); 
        cell12.setCellStyle(stylejiacu);
        cell12.setCellValue("订单金额（元）");
        
        HSSFCell cell13 = row2.createCell(11);
        cell13.setCellStyle(teststyle); 
        cell13.setCellStyle(stylejiacu);
        cell13.setCellValue("产品金额（元）");
        
        HSSFCell cell14 = row2.createCell(12);
        cell14.setCellStyle(teststyle); 
        cell14.setCellStyle(stylejiacu);
        cell14.setCellValue("邮费金额");
        
        HSSFCell cell15 = row2.createCell(13);
        cell15.setCellStyle(teststyle); 
        cell15.setCellStyle(stylejiacu);
        cell15.setCellValue("生产费用");
        
        HSSFCell cell16 = row2.createCell(14);
        cell16.setCellStyle(teststyle); 
        cell16.setCellStyle(stylejiacu);
        cell16.setCellValue("结算方式");
        
        HSSFCell cell17 = row2.createCell(15);
        cell17.setCellStyle(teststyle); 
        cell17.setCellStyle(stylejiacu);
        cell17.setCellValue("结算金额（元）");
        
        HSSFCell cell18 = row2.createCell(16);
        cell18.setCellStyle(teststyle); 
        cell18.setCellStyle(stylejiacu);
        cell18.setCellValue("下单日期");
        
        int startRow = 0;//合并开始行号  
        int endRow = 0;//合并结束行号  
        int rowNum = 2;//行号
        
        for (int i = 0; i < listTxjs.size(); i++) {
        	Map<String, Object> listin = listTxjs.get(i);
        	
        	HSSFRow rowInfo = sheet.createRow(2+i);
            HSSFCell cell32 = rowInfo.createCell(0);
            cell32.setCellStyle(teststyle); 
            if(listin.get("createdate") != null){
            	cell32.setCellValue(listin.get("createdate").toString()); 
            }else{
            	cell32.setCellValue(""); 
            }
            
            
            HSSFCell cell33 = rowInfo.createCell(1);
            cell33.setCellStyle(teststyle); 
            if(listin.get("ID") != null){
            	cell33.setCellValue(listin.get("ID").toString()); 
            }else{
            	cell33.setCellValue(""); 
            }
            
            HSSFCell cell34 = rowInfo.createCell(2);
            cell34.setCellStyle(teststyle);
            if(listin.get("amount") != null){
            	cell34.setCellValue(listin.get("amount").toString()); 
            }else{
            	cell34.setCellValue(""); 
            }
            
            //提现状态:10.未提现  20.提现中 40.成功提现（财务已经线下打款）
            HSSFCell cell35 = rowInfo.createCell(3);
            cell35.setCellStyle(teststyle); 
            if(listin.get("status") != null && "10".equals(listin.get("status").toString())){
            	cell35.setCellValue("未提现"); 
            }else if(listin.get("status") != null && "20".equals(listin.get("status").toString())){
            	cell35.setCellValue("提现中"); 
            }else if(listin.get("status") != null && "40".equals(listin.get("status").toString())){
            	cell35.setCellValue("成功提现"); 
            }else{
            	cell35.setCellValue(""); 
            }
            
            HSSFCell cell36 = rowInfo.createCell(4);
            cell36.setCellStyle(teststyle); 
            if(listin.get("qyid") != null){
            	cell36.setCellValue(listin.get("qyid").toString()); 
            }else{
            	cell36.setCellValue(""); 
            }
            
            HSSFCell cell37 = rowInfo.createCell(5);
            cell37.setCellStyle(teststyle); 
            if(listin.get("companyname") != null){
            	cell37.setCellValue(listin.get("companyname").toString()); 
            }else{
            	cell37.setCellValue(""); 
            }
            
            HSSFCell cell38 = rowInfo.createCell(6);
            cell38.setCellStyle(teststyle); 
            if(listin.get("companytype") != null){
            	if("10".equals(listin.get("companytype").toString())){
            		cell38.setCellValue("区域制作中心");
            	}else if("20".equals(listin.get("companytype").toString())){
            		cell38.setCellValue("推广中心");
            	}else if("30".equals(listin.get("companytype").toString())){
            		cell38.setCellValue("推广员");
            	}else if("40".equals(listin.get("companytype").toString())){
            		cell38.setCellValue("经销商");
            	}else if("50".equals(listin.get("companytype").toString())){
            		cell38.setCellValue("门店");
            	}
            }else{
            	cell38.setCellValue(""); 
            }
            
            HSSFCell cell39 = rowInfo.createCell(7);
            cell39.setCellStyle(teststyle); 
            if(listin.get("ordernum") != null){
            	cell39.setCellValue(listin.get("ordernum").toString()); 
            }else{
            	cell39.setCellValue(""); 
            }
            
            HSSFCell cell40 = rowInfo.createCell(8);
            cell40.setCellStyle(teststyle); 
            if(listin.get("cuname") != null){
            	cell40.setCellValue(listin.get("cuname").toString()); 
            }else{
            	cell40.setCellValue(""); 
            }
            
            HSSFCell cell41 = rowInfo.createCell(9);
            cell41.setCellStyle(teststyle); 
            if(listin.get("orderid") != null){
            	cell41.setCellValue(listin.get("orderid").toString()); 
            }else{
            	cell41.setCellValue(""); 
            }
            
            HSSFCell cell42 = rowInfo.createCell(10);
            cell42.setCellStyle(teststyle); 
            if(listin.get("totalprice") != null){
            	cell42.setCellValue(listin.get("totalprice").toString()); 
            }else{
            	cell42.setCellValue(""); 
            }
            
            HSSFCell cell43 = rowInfo.createCell(11);
            cell43.setCellStyle(teststyle); 
            if(listin.get("productotallprice") != null){
            	cell43.setCellValue(listin.get("productotallprice").toString()); 
            }else{
            	cell43.setCellValue(""); 
            }
            
            HSSFCell cell44 = rowInfo.createCell(12);
            cell44.setCellStyle(teststyle); 
            if(listin.get("postPrice") != null){
            	cell44.setCellValue(listin.get("postPrice").toString()); 
            }else{
            	cell44.setCellValue(""); 
            }
            
            HSSFCell cell45 = rowInfo.createCell(13);
            cell45.setCellStyle(teststyle); 
            if(listin.get("produceprice") != null){
            	cell45.setCellValue(listin.get("produceprice").toString()); 
            }else{
            	cell45.setCellValue("0.00"); 
            }
            
            HSSFCell cell46 = rowInfo.createCell(14);
            cell46.setCellStyle(teststyle); 
            if(listin.get("jsfs") != null){
            	cell46.setCellValue(listin.get("jsfs").toString()); 
            }else{
            	cell46.setCellValue(""); 
            }
            
            HSSFCell cell47 = rowInfo.createCell(15);
            cell47.setCellStyle(teststyle); 
            if(listin.get("jsAmount") != null){
            	cell47.setCellValue(listin.get("jsAmount").toString()); 
            }else{
            	cell47.setCellValue(""); 
            }
            
            HSSFCell cell48 = rowInfo.createCell(16);
            cell48.setCellStyle(teststyle); 
            if(listin.get("ordercreatedate") != null){
            	cell48.setCellValue(listin.get("ordercreatedate").toString()); 
            }else{
            	cell48.setCellValue(""); 
            }
            
            //合并记录
            if(i != 0){ 
            	System.out.println("==================="+listTxjs.get(i-1).get("ID")+""+listTxjs.get(i).get("ID"));
            	
            	if(listTxjs.get(i-1).get("ID").equals(listTxjs.get(i).get("ID"))){
            		endRow++;//则合并结束行号+1  
            		for(int j = 0; j < 8; j++){
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

}
