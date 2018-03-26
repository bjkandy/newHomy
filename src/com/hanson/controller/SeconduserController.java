package com.hanson.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Page;
import com.hanson.model.Seconduser;
import com.hanson.service.SeconduserService;
import com.hanson.serviceModel.SeconduserQuery;

@Controller
@RequestMapping("/user")
public class SeconduserController {

	@Autowired
	private SeconduserService seconduserService;
	
	@RequestMapping(value="/userList")
	@ResponseBody
	public String list(Seconduser seconduser,HttpServletRequest request, HttpServletResponse response) throws Exception{
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
			pagepeper = seconduserService.queryUserListPage(pagepeper, filterParamMap(request));
			JSONObject jsonData = parseBeanJsonList(pagepeper);
			JsonUtils.renderSuccess(jsonData);
			return jsonData.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 构建模糊查询条件及验证
	 */
	private Map<String, Object> filterParamMap(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String nickname = request.getParameter("nickname");
		if(!StringUtils.isEmpty(nickname)){
			paramMap.put("nickname", nickname);
		}
		return paramMap;
	}
	
	
	/**
	 * 构建返回参数
	 */
	private JSONObject parseBeanJsonList(Page<Map<String, Object>> page){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: page.getResult()) {
			JSONObject map = new JSONObject();
			map.put("id", bean.get("id"));
			map.put("nickname", bean.get("nickname"));
			map.put("mobile", bean.get("mobile"));
			map.put("email", bean.get("email"));
			
			if(bean.get("sex")!=null&&!bean.get("sex").equals("")){
				switch(Integer.valueOf(bean.get("sex").toString())){
				 case 1 :map.put("sex", "男");break;
				 case 2 :map.put("sex", "女");break;
				 default:map.put("sex", "未知");break;
				}
			}else{
				map.put("sex", "");
			}
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	
	
	/**
	 * 页面跳转
	 * @return
	 */
	@RequestMapping("/user")
	public String yuyue(){
		return "member/member";
	}
}
