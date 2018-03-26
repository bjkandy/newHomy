package com.hanson.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Page;
import com.hanson.service.CommentService;
import com.hanson.service.OrderService;
import com.hanson.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
   
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/poductJsonList")
	public void getProductJsonList(HttpServletRequest request,HttpServletResponse response)
	{
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Map<String,String[]> map=request.getParameterMap();
		Map map2=new HashMap<String,String>();
		for(String key:map.keySet())
		{
			map2.put(key, map.get(key)[0]);
		}
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		try {
			  pagepeper=productService.getProductsList(pagepeper, map2);
			  JSONArray jsonArray = JSONArray.fromObject(pagepeper.getResult());
			  JSONObject jsonResult = new JSONObject();  
			  jsonResult.put("total", pagepeper.getTotalCount());  
			  jsonResult.put("rows", jsonArray); 
			  JsonUtils.renderSuccess(jsonResult);
			  response.setContentType("text/json; charset=utf-8");  
			  response.setHeader("Access-Control-Allow-Origin", "*");
			  response.getWriter().write(jsonResult.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/getAllCategories")
	public void getAllCategories(HttpServletRequest request,HttpServletResponse response)
	{
		List result=productService.getAllCategories();
		try {
			response.setContentType("text/json; charset=utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getCategoryNameAndId")
	public void getCategoryNameAndId(HttpServletRequest request,HttpServletResponse response)
	{
		List result=productService.getCategoryNameAndId();
		try {
			response.setContentType("text/json; charset=utf-8"); 
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}	
