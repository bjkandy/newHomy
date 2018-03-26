package common;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.stereotype.Service;

import common.ServiceCommonException;


@Service
public class CommonServiceImpl {

	
	/**
	 * 判断qyid是否空白 null和""
	 * 
	 * @param str
	 * @return
	 */
	public static void qyidIsBlank(Object qyid) {
		if(qyid==null || qyid== ""){
			throw new ServiceCommonException("4001","企业ID为空");
		}
	}
	/**
	 * 判断qyid是否空白 null和""
	 * 
	 * @param str
	 * @return
	 */
	public static void merchantidIsBlank(Object qyid) {
		if(qyid==null || qyid== ""){
			throw new ServiceCommonException("4001","商户ID为空");
		}
	}
	/**
	 * 判断qyid是否空白 null和""
	 * 
	 * @param str
	 * @return
	 */
	public static void IsBlank(String str) {
		if(str==null || str== ""){
			throw new ServiceCommonException("4001","字符串为空！");
		}
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
		
		data.put("startLin", (Integer.parseInt(String.valueOf(data.get("pageNo")))-1)*Integer.parseInt(String.valueOf(data.get("pageSize"))));
		data.put("pageSize",Integer.parseInt(String.valueOf(data.get("pageSize"))));
		data = res;
		return res;
	}
	 /**
	 * 条件  下载excel
	 * @param List
	 */
	 public static void downloadExcel(HttpServletRequest request,HttpServletResponse response,String fileName,String downFile){
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
	 
	 
	 /**
	 *下载excel
	 * @param List
	 */
	 public static void downloadExcel(HttpServletRequest request,HttpServletResponse response,String fileName,byte[] bt){
		 String str = "attachment;filename=";
		 String name="";
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/vnd.ms-excel");
		 InputStream input = new ByteArrayInputStream(bt);
		 InputStream fis = input;
		 try {
	         name = java.net.URLEncoder.encode(fileName+".xls","UTF-8");
	         response.addHeader("Content-Disposition", str + new String(name.getBytes("UTF-8"),"UTF-8"));
//		         fis = new FileInputStream(downFile);
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
	 
	/***
     * 获取 request 中 json 字符串的内容
     * 
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static String getRequestJsonString(HttpServletRequest request)
            throws IOException {
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
            return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");
        // POST
        } else {
            return getRequestPostStr(request);
        }
    }
    
    /**      
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException      
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }
    
	/**
	 * C#条件  导出excel
	 * @param List<Map<String,Object>> data  数据
	 * @param List<String> chname 列名
	 * @param List<String> name 列字段名
	 * @param int[] columnWidth 列宽
	 * @param String realPath 临时存放路径
	 * @return void 
	 */
	 public static void createExcel(List<Map<String,Object>> data,List<String> name ,List<String> chname,int[] columnWidth, String realPath){
		 // 创建一个webbook，对应一个Excel文件  
	        HSSFWorkbook wb = new HSSFWorkbook();  
	        // 在webbook中添加一个sheet,对应Excel文件中的sheet  
	        HSSFSheet sheet = wb.createSheet("数据表");  
	        // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        HSSFRow row = sheet.createRow((int) 0);  
	        // 创建单元格，并设置值表头 设置表头居中  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
			//style.setFillForegroundColor((short) 13);// 设置背景色
			// style.setWrapText(true);//设置自动换行
	        for(int m=0;m<columnWidth.length;m++){
	        	 sheet.setColumnWidth(m, columnWidth[m]);
			}
	        
	        sheet.setDefaultRowHeight((short)15);
	        HSSFFont font = wb.createFont();
	        font.setFontName("仿宋_GB2312");
	        
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
	        font.setFontHeightInPoints((short) 10);
	        
	        style.setFont(font);//设置字体
//	        //设置填充模式
//	        style.setFillPattern(HSSFCellStyle.FINE_DOTS);
//	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//	        style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
	        HSSFCell cell = null;
	        cell = row.createCell(0);  
 	        cell.setCellValue("序号");  
 	        cell.setCellStyle(style);
	        int j = 1;
	        for(String i : chname){
	        	cell = row.createCell(j);  
	 	        cell.setCellValue(i);  
	 	        cell.setCellStyle(style);
	 	        j++;
	        }
            // 创建单元格，并设置值  
	        for (int i = 0; i < data.size(); i++)  
	        {  
	            row = sheet.createRow((int) i + 1);  
	            Map<String,Object> ro = (Map<String,Object>) data.get(i);  
	          
	            row.createCell(0).setCellValue(i+1);
                int k = 1;
	            for(String s : name){
            		row.createCell(k).setCellValue(String.valueOf(ro.get(s)));
	            	k++;
	            }
	          }  
	        // 第六步，将文件存到指定位置  
	        try  
	        {  
	            FileOutputStream fout = new FileOutputStream(realPath);  
	            wb.write(fout); 
	            fout.flush();
	            fout.close();  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        } 
	    }  
    
	 
	 
	 
    /**      
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException      
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }
    
 
	
}
