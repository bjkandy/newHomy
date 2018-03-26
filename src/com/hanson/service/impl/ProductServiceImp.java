package com.hanson.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hanson.common.util.Page;
import com.hanson.mapper.ProductMapper;
import com.hanson.model.Product;
import com.hanson.model.ProductVO;
import com.hanson.service.ProductService;
import common.ServiceCommonException;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	/**
	 * 精确查找-根据ID
	 * @param id
	 * @return
	 */
	public Product findUniqueById(Long id){
		return productMapper.findUniqueById(id);
	}
	
	/**
	 * 精确查找-根据ID
	 * @param id
	 * @return
	 */
	public Product findUniqueByQyId(Long id){
		return productMapper.findUniqueByQyid(id);
	}
	public List<ProductVO> findPhotoFrame(){
        return productMapper.findPhotoFrame();		
	}
	public List<ProductVO> FindalbumProduct(){
       return productMapper.FindalbumProduct();		
	}
	public List<ProductVO> FindStoreProductbywhere(Map<String,Object> map){
		return productMapper.FindStoreProductbywhere(map);
	}
	public ProductVO SelectProductByName(Map<String,Object> map){
		return productMapper.SelectProductByName(map);
	}
	/**
	 * 创建/更新
	 * @param bean
	 * @throws Exception
	 */
	public void save(Product bean) throws Exception {
		if(bean.getId() ==null){
			productMapper.save(bean);
		}else{//ID存在调用更新方法
//			productMapper.updateOne(bean);
		}
	}

	@Override
	public Page<Map<String, Object>> findProductPage(
			Page<Map<String, Object>> page, Map<String, Object> map) {
		//分页查询通用参数
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
		//统计数量
		long totalCount = productMapper.countTotles(map);
		page.setTotalCount(totalCount);
		//填充数据
		if(totalCount >0){
			page.setResult(productMapper.search(map));
		}
		return page;
	}

	@Override
	public void deleteProduct(Product product) {
		productMapper.deleteProduct(product);
	}

	@Override
	public Integer saveProduct(ProductVO productVO) {
		return productMapper.saveProduct(productVO);
	}

	@Override
	public ProductVO findProductById(ProductVO productVO) {
		return productMapper.findProductById(productVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) {
		productMapper.updateProduct(productVO);
	}
	

	
	/**
	 * 导出产品数据excel
	 * @param data
	 * @return
	 */
	public byte[] productExcel(Map<String,Object> data){
		
		List<Map<String,Object>> re = productMapper.productExcel(data);
		if(re.isEmpty()){
			throw new ServiceCommonException("4001","产品数据为空！");
		}
		List<Map<String,Object>> res = productMapper.productInfoExcel(data);
		if(res.isEmpty()){
			throw new ServiceCommonException("4001","产品详情数据为空！");
		}
		for(Map<String,Object> i : re){
			List<Map<String,Object>> info = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> j : res){
				if(String.valueOf(i.get("ID")).equals(String.valueOf(j.get("id")))){
					info.add(j);
				}
			}
			i.put("detail", info);
			i.put("detailSize", info.size());
		}
		 // 创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("产品表");  
        // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFFont font = wb.createFont();
        font.setFontName("仿宋_GB2312");
        
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
        font.setFontHeightInPoints((short) 10);
        style.setFont(font);//设置字体
        
        HSSFCellStyle style1 = wb.createCellStyle();  
        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中  
        style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中 
//        style1.setAlignment(HSSFCellStyle.ALIGN_LEFT); //居左
        for(int i = 0 ; i < 18; i++){
        	sheet.setColumnWidth(i, 3000);
        	sheet.setDefaultColumnStyle(i, style1);
        }
        HSSFCell cell = row.createCell(0);  
        cell.setCellValue("序号");  
        cell.setCellStyle(style);  
        cell = row.createCell(1);  
        cell.setCellValue("商品编号");   
        cell.setCellStyle(style);  
        cell = row.createCell(2);  
        cell.setCellValue("商品名称");   
        cell.setCellStyle(style);  
        cell = row.createCell(3);  
        cell.setCellValue("商品类型 ");  
        cell.setCellStyle(style);  
        cell = row.createCell(4);  
        cell.setCellValue("月销量");  
        cell.setCellStyle(style);
        cell = row.createCell(5);  
        cell.setCellValue("总销量");  
        cell.setCellStyle(style);
        cell = row.createCell(6);  
        cell.setCellValue("标准价(元)");  
        cell.setCellStyle(style);
        cell = row.createCell(7);  
        cell.setCellValue("是否可用");  
        cell.setCellStyle(style);
        cell = row.createCell(8);  
        cell.setCellValue("创建时间");  
        cell.setCellStyle(style);
        
        cell = row.createCell(9);  
        cell.setCellValue("材质");  
        cell.setCellStyle(style);
        cell = row.createCell(10);  
        cell.setCellValue("尺寸");  
        cell.setCellStyle(style);
        cell = row.createCell(11);  
        cell.setCellValue("颜色");  
        cell.setCellStyle(style);
        cell = row.createCell(12);  
        cell.setCellValue("商品价格");  
        cell.setCellStyle(style);
        cell = row.createCell(13);  
        cell.setCellValue("套餐价格");  
        cell.setCellStyle(style);
        cell = row.createCell(14);  
        cell.setCellValue("过塑价格");  
        cell.setCellStyle(style);
        cell = row.createCell(15);  
        cell.setCellValue("库存");  
        cell.setCellStyle(style);
        cell = row.createCell(16);  
        cell.setCellValue("月销量");  
        cell.setCellStyle(style);
        cell = row.createCell(17);  
        cell.setCellValue("总销量");  
        cell.setCellStyle(style);
        
//        for (int i = 0; i < re.size(); i++){  
//            row = sheet.createRow((int) i+1 );  
//            Map<String,Object> product = (Map<String,Object>) re.get(i);  
//            // 创建单元格，并设置值  
//            row.createCell(0).setCellValue(i+1);
//            row.createCell(1).setCellValue(String.valueOf(product.get("ID")));
//            row.createCell(2).setCellValue(String.valueOf(product.get("name"))); 
//            row.createCell(3).setCellValue(String.valueOf(product.get("producttype")));
//            row.createCell(4).setCellValue(String.valueOf(product.get("monthsellcount")));
//            row.createCell(5).setCellValue(String.valueOf(product.get("totalsellcount")));  
//            row.createCell(6).setCellValue(String.valueOf(product.get("standardprice")));
//            row.createCell(7).setCellValue(String.valueOf(product.get("status")));
//            row.createCell(8).setCellValue(String.valueOf(product.get("createtime")));
//        }
        for (int i = 0, lin = 1; i < re.size(); i++,lin++){  
            row = sheet.createRow((int) lin );  
            Map<String,Object> product = (Map<String,Object>) re.get(i);  
            // 创建单元格，并设置值  
	          row.createCell(0).setCellValue(i+1);
	          row.createCell(1).setCellValue(String.valueOf(product.get("ID")));
	          row.createCell(2).setCellValue(String.valueOf(product.get("name"))); 
	          row.createCell(3).setCellValue(String.valueOf(product.get("producttype")));
	          row.createCell(4).setCellValue(String.valueOf(product.get("monthsellcount")));
	          row.createCell(5).setCellValue(String.valueOf(product.get("totalsellcount")));  
	          row.createCell(6).setCellValue(String.valueOf(product.get("standardprice")));
	          row.createCell(7).setCellValue(String.valueOf(product.get("status")));
	          row.createCell(8).setCellValue(String.valueOf(product.get("createtime")));
            
            @SuppressWarnings("unchecked")
			List<Map<String,Object>> det = (List<Map<String,Object>>)product.get("detail");
            if(null !=product.get("detail") && !det.isEmpty()){
            
	             row.createCell(9).setCellValue(String.valueOf(det.get(0).get("specinfo")));
            	 row.createCell(10).setCellValue(String.valueOf(det.get(0).get("size")));
            	 row.createCell(11).setCellValue(String.valueOf(det.get(0).get("color")));
            	 row.createCell(12).setCellValue(String.valueOf(det.get(0).get("price")));
            	 row.createCell(13).setCellValue(String.valueOf(det.get(0).get("packageprice")));
            	 row.createCell(14).setCellValue(String.valueOf(det.get(0).get("plasticprice")));
            	 row.createCell(15).setCellValue(String.valueOf(det.get(0).get("inventory")));
            	 row.createCell(16).setCellValue(String.valueOf(det.get(0).get("mounthcount")));
            	 row.createCell(17).setCellValue(String.valueOf(det.get(0).get("totalcount")));
            	 det.remove(0);
            	 //每个订单的详情数量
            	
	            for(Map<String,Object> k : det){
	              	 lin++;
	            	 row = sheet.createRow((int) lin );
	            	 row.createCell(9).setCellValue(String.valueOf(k.get("specinfo")));
	            	 row.createCell(10).setCellValue(String.valueOf(k.get("size")));
	            	 row.createCell(11).setCellValue(String.valueOf(k.get("color")));
	            	 row.createCell(12).setCellValue(String.valueOf(k.get("price")));
	            	 row.createCell(13).setCellValue(String.valueOf(k.get("packageprice")));
	            	 row.createCell(14).setCellValue(String.valueOf(k.get("plasticprice")));
	            	 row.createCell(15).setCellValue(String.valueOf(k.get("inventory")));
	            	 row.createCell(16).setCellValue(String.valueOf(k.get("mounthcount")));
	            	 row.createCell(17).setCellValue(String.valueOf(k.get("totalcount")));
	            }
            }
            int size = Integer.parseInt(String.valueOf(product.get("detailSize")));
            for(int j = 0;j < 9 ; j++){
            	if(size > 0){
            		sheet.addMergedRegion(new CellRangeAddress((lin+1-size), (short)lin, j, (short)j));//合并单元格
            	}
        	}
//            if(size > 0){
//            	sheet.addMergedRegion(new CellRangeAddress((lin+1-size), (short)lin, 17, (short)17));//合并单元格
//            }
        }  
        byte[] byt =null;
        try{  
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);
            byt = os.toByteArray();
            os.flush();
            os.close();
        }catch (Exception e){  
            e.printStackTrace();  
        } 
        return byt;
	}

	@Override
	public Page getProductsList(Page page,Map map) {
		map.put("BEGIN_ROW", (page.getPageNo()-1) * page.getPageSize());
		map.put("PAGE_SIZE", page.getPageSize());
		map.put("ORDER_BY_MENU", page.getOrderBy());
		map.put("ORDER_BY_DESC", page.getOrder());
	    long totalCount =productMapper.getProductCount(map);
		page.setTotalCount(totalCount);
		if(totalCount>0){			
			List<Map<String,Object>> result= productMapper.getProductsList(map);
			for(Map<String,Object> res:result) {
				if(res.get("createtime")!=null)
				{
				res.put("createtime",res.get("createtime").toString());
				}
			}
			page.setResult(result);
		}
		return page;
	}

	@Override
	public List<Map<String, Object>> getAllCategories() {
		// TODO Auto-generated method stub
		return productMapper.getAllCategories();
	}

	@Override
	public List<Map<String, Object>> getCategoryNameAndId() {
		// TODO Auto-generated method stub
		return productMapper.getCategoryNameAndId();
	}      
}
