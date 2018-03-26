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

@Controller
@RequestMapping("/comment")
public class CommentController {
	private static Logger logger = Logger.getLogger(CommentController.class);
	
	@Autowired 
	private CommentService commentService;
	
	@Autowired 
	private OrderService orderService;
	@RequestMapping("/jsonList")
	@ResponseBody
    public String getAllComments(HttpServletRequest request,HttpServletResponse response){
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
			  pagepeper =commentService.getPageList(pagepeper, map2);			
			  JSONArray jsonArray = JSONArray.fromObject(pagepeper.getResult());
			  JSONObject jsonResult = new JSONObject();  
			  jsonResult.put("total", pagepeper.getTotalCount());  
			  jsonResult.put("rows", jsonArray); 
			  JsonUtils.renderSuccess(jsonResult);
			  response.setContentType("text/json; charset=utf-8");  
			  response.getWriter().write(jsonResult.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
      return null;
 
	}
	//显示评价
	@RequestMapping("/actionOfComment")
	public void  showComment(HttpServletRequest request,HttpServletResponse response)
	{ 
		  String action=request.getParameter("action");
	 	  String idStr=request.getParameter("ids"); 
	 	  idStr=idStr.replaceAll("\"","");
	 	  idStr=idStr.replace("[", "").replace("]", "");
	 	  if(idStr.contains("on")){
	 		  idStr=idStr.replace("on,", "");
	 	  }
	 	  System.out.println(idStr);
	 	  String ids[]=idStr.split(",");
	 	  
	 	  for(String element:ids){
	 		  System.out.println(element);
	 		  int id=Integer.parseInt(element);
	 		  if(action.equals("show"))//显示评论
	 		  {
	 		 commentService.showComment(id);
	 		  }else if(action.equals("hide")){//隐藏评论
	 	     commentService.hideComment(id);  
	 		  }else if(action.equals("delete")){//删除评论
	 	        commentService.deleteComment(id);	  
	 		  }
	 	  }
	}
	//显示详情
	@RequestMapping("/showCommentDetail")
	public void showCommentDetail(HttpServletRequest request,HttpServletResponse response){
		String idStr=request.getParameter("id");
		int id=Integer.parseInt(idStr);	
		 Map map=commentService.getCommentDetial(id);
		 map.put("createtime",map.get("createtime").toString());
		 try {
			
			 String json =
					    JSON.toJSONString(map, SerializerFeature.DisableCircularReferenceDetect);
			  response.setContentType("text/json; charset=utf-8");  
			  response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("commentConfiguration")
	public void getAllCommentTag(HttpServletRequest request,HttpServletResponse response)
	{
	    Object star=request.getParameter("star");
	    List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
	    if(star==null){
	    	 result=commentService.getAllCommentTag();
	    }else{
	    	 result=commentService.getByCommentTagByStarLevel(Integer.parseInt(star.toString()));
	    }
		 try { 
			 String json =
					    JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect);
			  response.setContentType("text/json; charset=utf-8");  
			  response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("modifyCommenttag")
	public void modifyCommenttag(HttpServletRequest request,HttpServletResponse response){
		String starLvel=request.getParameter("starLevel");
		String modifyData=request.getParameter("modifyData");
		modifyData=modifyData.replaceAll("\"","");
		modifyData=modifyData.replace("[", "").replace("]", "");
		String modifyDatas[]=modifyData.split(",");
		Map map=new HashMap();
		int result=0;
		boolean flag=true;
        for(String str:modifyDatas)
        {
        	String modify[]=str.split("-");
        	map.put("id", Integer.parseInt(modify[0]));
        	map.put("tagcontent", modify[1]);
        	if(Integer.parseInt(map.get("id").toString())!=0)
        	{
        	result=commentService.updateCommenttag(map);
        	if(result==0)
        	{
        		flag=false;
        		break;
        	} 
        	}else{
        		map.put("starlevel", starLvel);
        		map.put("createtime", new Date());
                result=commentService.saveCommenttag(map);
            	if(result==0)
            	{
            		flag=false;
            		break;
            	} 
        	}
      
        }
        if(flag){
        	try {
  			    response.setContentType("text/json; charset=utf-8");  
				response.getWriter().write(JSON.toJSONString("修改成功".toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }else{
        	try {
  			    response.setContentType("text/json; charset=utf-8");  
				response.getWriter().write(JSON.toJSONString("修改失败".toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	@RequestMapping("deleteCommenttag")
	public void deleteCommenttag(HttpServletRequest request,HttpServletResponse response)
	{
		int id=Integer.parseInt(request.getParameter("id"));
		int result=commentService.deleteCommentTag(id);
		if(result==1){
        	try {
  			    response.setContentType("text/json; charset=utf-8");  
				response.getWriter().write(JSON.toJSONString("删除成功".toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }else{
        	try {
  			    response.setContentType("text/json; charset=utf-8");  
				response.getWriter().write(JSON.toJSONString("删除失败".toString()));
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
}	
