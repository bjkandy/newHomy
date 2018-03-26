package com.hanson.controller;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanson.common.util.JsonUtils;
import com.hanson.model.Company;
import com.hanson.service.CompanyService;
import com.hanson.service.ProductService;
import com.hanson.service.SpecvalueService;

import common.CommonServiceImpl;
import common.Result;

@Controller
@RequestMapping("/initData")
public class SystemController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SpecvalueService specvalueService;
	
	
	
	
	
	
	@RequestMapping(value="/initFrameData")
	@ResponseBody
	public String initFrameData(HttpServletRequest request) throws Exception {	
		JSONObject jo = new JSONObject();
		try {
			
			//companyService.save(company); 
			List<Company> companyList = new ArrayList<Company>();
			companyService.initFrameData(companyList);
			jo.put("code", "ok");
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			e.printStackTrace();
			jo.put("code", "no");
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
}
