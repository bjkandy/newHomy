package com.hanson.common.util;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.OutputStream;  
import java.util.Collection;  
import java.util.Map;
  
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
  
 
  
public class ExcelUtils {  
    protected File file;  
    protected OutputStream os;  
    protected Workbook book = null;  
    public ExcelUtils() {  
        super();  
    }  
      
    public ExcelUtils(File file) throws IOException, InvalidFormatException {  
        super();  
        this.file = file;  
        if(!file.exists()) {  
            file.createNewFile();  
        }  
        os = new FileOutputStream(file);  
        book = new XSSFWorkbook();  
        Sheet sheet = book.createSheet("user");  
          
        String[] title = {"用户名", "密码", "昵称"};  
        Row titleRow = sheet.createRow(0);  
        for(int i = 0; i < title.length; i++) {  
            Cell cell = titleRow.createCell(i + 1);  
            cell.setCellValue(title[i]);  
        }  
    }  
      
    public void Write(Map<String,Object> user) throws IOException {  
        Sheet sheet = book.getSheet("user");  
        int lastRowNum = sheet.getLastRowNum();  
        Row currentRow = sheet.createRow(lastRowNum + 1);  
        currentRow.createCell(0).setCellFormula("ROW() - 1");  
        currentRow.createCell(1).setCellValue((String)user.get("username"));  
        currentRow.createCell(2).setCellValue((String)user.get("username"));  
        currentRow.createCell(3).setCellValue((String)user.get("username"));  
    }  
    public void Write(Collection<Map<String,Object>> data) throws IOException {  
        for(Map<String,Object> u : data) {  
            this.Write(u);  
        }  
    }  
      
    public void Write(Map<String,Object>... users) throws IOException {  
        for(Map<String,Object> u : users) {  
            this.Write(u);  
        }  
    }  
      
    public void Extract() throws IOException {  
        book.write(os);  
//        book.close();  
    }  
 
}    