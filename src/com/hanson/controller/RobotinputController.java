package com.hanson.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanson.model.Emoticon;
import com.hanson.model.Robotreply;
import com.hanson.service.RobotreplyService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("robotreply")
public class RobotinputController {
	
	@Autowired
	private RobotreplyService robotreplyService;
	
	/**
	 * 关键词回复列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="keyList",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String keyList(HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		
		String rulename = request.getParameter("rulename");
		
		try {
			
			List<Robotreply> robotreplyList = robotreplyService.findRobotreplyList(rulename);
			
			List<Emoticon> emoticon = robotreplyService.emoticonList();
			
			List<Robotreply> robotreplyList2 = new ArrayList<Robotreply>();
			
			for (Robotreply robotreply : robotreplyList) {
				for (Emoticon emoticon2 : emoticon) {
					if(robotreply.getReplycontent().contains(emoticon2.getCode())){
						robotreply.setReplycontent(robotreply.getReplycontent().replace(emoticon2.getCode(), emoticon2.getRemarks()));
					}
				}
				robotreplyList2.add(robotreply);
			}
			
			result.put("success", true);
			result.put("robotreplyList", robotreplyList2);
		
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		return result.toString();
	}
	
	/**
	 * 新增关键词回复
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="insertKey",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String insertKey(Robotreply robotreply,HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		
		if(robotreply == null){
			result.put("success", false);
			result.put("msg", "robotreply is null");
			return result.toString();
		}
		
		try {
			
			robotreplyService.insertKey(robotreply);
			
			result.put("success", true);
			result.put("msg", "新增成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		return result.toString();
	}
	
	/**
	 * 编辑关键词回复
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="editKey",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editKey(Robotreply robotreply,HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		
		if(robotreply == null){
			result.put("success", false);
			result.put("msg", "robotreply is null");
			return result.toString();
		}
		
		if(robotreply.getId() == null){
			result.put("success", false);
			result.put("msg", "id is null");
			return result.toString();
		}
		
		try {
			
			robotreplyService.editKey(robotreply);
			
			result.put("success", true);
			result.put("msg", "修改成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		return result.toString();
	}
	
	/**
	 * 删除关键词回复
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="deleteKey",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteKey(Integer id,HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		
		if(id == null){
			result.put("success", false);
			result.put("msg", "id is null");
			return result.toString();
		}
		
		try {
			
			robotreplyService.deleteKey(id);
			
			result.put("success", true);
			result.put("msg", "删除成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		return result.toString();
	}
	
	/**
	 * 查询关键词回复详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="queryKeyDetail",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String queryKeyDetail(Integer id,HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		
		if(id == null){
			result.put("success", false);
			result.put("msg", "id is null");
			return result.toString();
		}
		
		try {
			
			Robotreply robotreply = robotreplyService.queryKeyDetail(id);
			
			List<Emoticon> emoticon = robotreplyService.emoticonList();
			
			for (Emoticon emoticon2 : emoticon) {
				if(robotreply.getReplycontent().contains(emoticon2.getCode())){
					robotreply.setReplycontent(robotreply.getReplycontent().replace(emoticon2.getCode(), emoticon2.getRemarks()));
				}
			}
			
			
			result.put("success", true);
			result.put("robotreply", robotreply);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		
		return result.toString();
	}
	
	/**
	 * 收到消息回复
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="receiveTheMessage",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String receiveTheMessage(HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		
		try {
			Robotreply robotreply = robotreplyService.receiveTheMessage();
			
			List<Emoticon> emoticon = robotreplyService.emoticonList();
			
			for (Emoticon emoticon2 : emoticon) {
				if(robotreply.getReplycontent().contains(emoticon2.getCode())){
					robotreply.setReplycontent(robotreply.getReplycontent().replace(emoticon2.getCode(), emoticon2.getRemarks()));
				}
			}
			
			result.put("success", true);
			result.put("robotreply", robotreply);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		return result.toString();
	}
	
	/**
	 * 保存或编辑收到消息回复
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="editReceiveTheMessage",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editReceiveTheMessage(Robotreply robotreply,HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		
		if(robotreply == null){
			result.put("success", false);
			result.put("msg", "robotreply is null");
			return result.toString();
		}
		
		try {
			
			robotreplyService.editReceiveTheMessage(robotreply);
			
			result.put("success", true);
			result.put("msg", "修改成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		return result.toString();
	}
	
	/**
	 * 表情list
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="emoticonList",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String emoticonList(HttpServletRequest request,HttpServletResponse response){
		JSONObject result = new JSONObject();
		
		try {
			
			List<Emoticon> emoticon = robotreplyService.emoticonList();
			
			result.put("success", true);
			result.put("emoticon", emoticon);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		return result.toString();
	}

}
