package com.hanson.service.impl;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanson.mapper.AutoPrintManageMapper;

import common.CommonServiceImpl;
import common.ServiceCommonException;
import common.StringUtils;

@Service
public class AutoPrintManageServiceImpl {

	@Autowired
	private AutoPrintManageMapper autoPrintManageMapper;
	
	/**
	 * 自助打印订单管理查询
	 * @param data
	 * @return
	 */
	public List<Map<String,Object>> autoPrintOrder(Map<String,Object> data){
		
		//数据较验
		CommonServiceImpl.merchantidIsBlank(data.get("merchantid"));
		CommonServiceImpl.checkPageParam(data);
		//查询订单数据
		List<Map<String,Object>> re = autoPrintManageMapper.autoPrintOrder(data);
		if(re.isEmpty()){
			throw new ServiceCommonException("4001","订单数据为空！");
		}
		return re;
	}
	
	/**
	 * 自助打印订单管理数量查询
	 * @param data
	 * @return
	 */
	public String autoPrintOrderCount(Map<String,Object> data){
		
		//数据较验
		CommonServiceImpl.merchantidIsBlank(data.get("merchantid"));
		//查询订单数据数量
		String re = autoPrintManageMapper.autoPrintOrderCount(data);
		if(null == re){
			throw new ServiceCommonException("4001","订单数据为空！");
		}
		return re;
	}
	/**
	 * 自助打印订单详情
	 * @param data
	 * @return
	 */
	public Map<String,Object> autoPrintOrderInfo(Map<String,Object> data){
		
		//数据较验
		if(StringUtils.isBlank((String)data.get("orderid"))){
			throw new ServiceCommonException("4001","订单id为空！");
		}
		Map<String,Object> re = autoPrintManageMapper.autoPrintOrderInfo(data);
		if(re.isEmpty()){
			throw new ServiceCommonException("4001","订单详情为空！");
		}
		return re;
	}
	/**
	 * 自助打印订单导出excel
	 * @param data
	 * @return
	 */
	public List<Map<String,Object>> autoPrintOrderExcel(Map<String,Object> data){
		
		//数据较验
		CommonServiceImpl.merchantidIsBlank(data.get("merchantid"));
		//查询订单数据
		List<Map<String,Object>> re = autoPrintManageMapper.autoPrintOrderExcel(data);
		if(re.isEmpty()){
			throw new ServiceCommonException("4001","订单数据为空！");
		}
		return re;
	}
	
	/**
	 * C#条件  导出自助打印订单excel
	 * @param orderid
	 * @param txStatus
	 * @param createdate
	 * @param qyid
	 * @param pageSize
	 * @param pageNo
	 * @return Result<List<Map<String,Object>>> 
	 */
	 public String autoOrderExcel(List<Map<String,Object>> orderList,String excelUrl){
		 // 创建一个webbook，对应一个Excel文件  
	        HSSFWorkbook wb = new HSSFWorkbook();  
	        // 在webbook中添加一个sheet,对应Excel文件中的sheet  
	        HSSFSheet sheet = wb.createSheet("自助打印订单表");  
	        // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        HSSFRow row = sheet.createRow((int) 0);  
	        // 创建单元格，并设置值表头 设置表头居中  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	  
	        HSSFCell cell = row.createCell(0);  
	        cell.setCellValue("序号");  
	        cell.setCellStyle(style);  
	        cell = row.createCell(1);  
	        cell.setCellValue("商户ID");   
	        cell.setCellStyle(style);  
	        cell = row.createCell(2);  
	        cell.setCellValue("设备ID");  
	        cell.setCellStyle(style);  
	        cell = row.createCell(3);  
	        cell.setCellValue("设备名");  
	        cell.setCellStyle(style);
	        cell = row.createCell(4);  
	        cell.setCellValue("省");  
	        cell.setCellStyle(style);
	        cell = row.createCell(5);  
	        cell.setCellValue("市");  
	        cell.setCellStyle(style);
	        cell = row.createCell(6);  
	        cell.setCellValue("区/县");  
	        cell.setCellStyle(style);
	        cell = row.createCell(7);  
	        cell.setCellValue("支付状态");  
	        cell.setCellStyle(style);
	        cell = row.createCell(8);  
	        cell.setCellValue("提现状态");  
	        cell.setCellStyle(style);
	        cell = row.createCell(9);  
	        cell.setCellValue("下单时间");  
	        cell.setCellStyle(style);
	        for (int i = 0; i < orderList.size(); i++)  
	        {  
	            row = sheet.createRow((int) i + 1);  
	            Map<String,Object> orderdata = (Map<String,Object>) orderList.get(i);  
	            // 创建单元格，并设置值  
	            row.createCell(0).setCellValue(i+1);  
	            row.createCell(1).setCellValue((int)(Integer)orderdata.get("merchantid"));  
	            row.createCell(2).setCellValue((Long)orderdata.get("id"));  
	            row.createCell(3).setCellValue((String)orderdata.get("equipmentname"));
	            row.createCell(4).setCellValue((String)orderdata.get("provincename"));
	            row.createCell(5).setCellValue((String)orderdata.get("cityname"));
	            row.createCell(6).setCellValue((String)orderdata.get("districtname"));
	            
	            if((int)(Integer)orderdata.get("paystatus") == 0){
	            	 row.createCell(7).setCellValue("未提现");
	            }else if((int)(Integer)orderdata.get("paystatus") == 10){
	            	 row.createCell(7).setCellValue("未提现");
	            }else{
	            	row.createCell(7).setCellValue("null");
	            }
	            
	            if(Integer.parseInt((String)orderdata.get("merchanfetstatus")) == 10){
	            	 row.createCell(8).setCellValue("未提现");
	            }else if(Integer.parseInt((String)orderdata.get("merchanfetstatus")) == 20){
	            	 row.createCell(8).setCellValue("提现中");
	            }else if(Integer.parseInt((String)orderdata.get("merchanfetstatus")) == 40){
	            	 row.createCell(8).setCellValue("提现成功");
	            }else{
	            	 row.createCell(8).setCellValue("null");
	            }
	            row.createCell(9).setCellValue(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format((Date)orderdata.get("createdate")));
	          }  
	        String realPath = null;
	        // 第六步，将文件存到指定位置  
	        try  
	        {  
	        	//在服务器创建临时数据文件
	        	realPath = excelUrl+"autoOrderExcel.xls";
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
	
}
