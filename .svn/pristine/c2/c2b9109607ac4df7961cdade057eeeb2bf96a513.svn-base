package com.hanson.controller;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Page;
import com.hanson.model.Announce;
import com.hanson.model.Images;
import com.hanson.model.Users;
import com.hanson.model.YyPagemodel;
import com.hanson.service.AnnounceService;
import com.hanson.util.StringUtil;

import common.ToolsUtils;

@Controller
@RequestMapping("/actionCenter")
public class AnnounceController {
    final Logger logger = LoggerFactory.getLogger(AnnounceController.class);
	@Value("${store.activity.save.dir}")
	public String storeimageDir;
	@Value("${store.activity.view.dir}")
	public String storeimageViewDir;
	
	@Value("${store.banner.save.dir}")
	public String publishBannerimageDir;
	@Value("${store.banner.view.dir}")
	public String publishBannerViewDir;

	@Autowired
	private AnnounceService announceService;
	
	@RequestMapping("/activilist")
	public String activitilist(){
		return "actionCenter/actionCenter";
	}
	@RequestMapping("/robotinput")
	public String robotinput(){
		return "actionCenter/robotinput";
	}
	@RequestMapping("/releaseActivity")
	public String addActivity(){
		return "actionCenter/releaseActivity";
	}
	@RequestMapping("/newBanner")
	public String newBanner(){
		return "actionCenter/newBanner";
	}
	@RequestMapping("/newActivity")
	public String newActivity(){
		return "actionCenter/newActivity";
	}
	
	@RequestMapping("/publishBanner")
	public String publishBanner()
	{
		return "actionCenter/bannerPublish";
	}

	private static boolean sendGETRequest(String path, Map<String, String> params, String ecoding) throws Exception{  
	    StringBuilder url = new StringBuilder(path);  
	    url.append("?");  
	    for(Map.Entry<String, String> entry : params.entrySet()){  
	        url.append(entry.getKey()).append("=");  
	        url.append(URLEncoder.encode(entry.getValue(), ecoding));  
	        url.append("&");  
	    }  
	    url.deleteCharAt(url.length() - 1);  
	    HttpURLConnection conn = (HttpURLConnection)new URL(url.toString()).openConnection();  
	    conn.setConnectTimeout(5000);  
	    conn.setRequestMethod("GET");  
	    if(conn.getResponseCode() == 200){  
	        return true;  
	    }  
	    return false;  
	}
	@RequestMapping("/findAnnounce")
	@ResponseBody
	public String findAnnounce(HttpServletRequest request) throws UnsupportedEncodingException{
		JSONObject jo = new JSONObject();
		List<Announce> announeList=new ArrayList<Announce>();
		String  title=new String(request.getParameter("name").getBytes("ISO-8859-1"),"utf8");
		announeList=announceService.findAnnounce(title.trim());
		jo.put("announeList", announeList);
		return jo.toString();
	}

	@RequestMapping(value="bannerlist",produces = "application/json; charset=utf-8") 
	@ResponseBody
	public String bannerlist(HttpServletRequest request){
	  List<Announce>	bannerList=announceService.bannerList();
	  JSONArray jarr = new JSONArray();
	  for(Announce bean:bannerList){
		  JSONObject jo = new JSONObject();
		  jo.put("id", bean.getId().toString());
		  jo.put("title", bean.getTitle());
		  jo.put("bannerurl", bean.getBannerurl());
		  jo.put("profile", bean.getProfile());
		  jarr.add(jo);
	  }
	  JSONObject json = new JSONObject();
	  json.put("rows", jarr);
	  return json.toString();
	}
	@RequestMapping(value="/jsonlist")
	@ResponseBody
	public String jsonlist(HttpServletRequest request) throws Exception{
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("sort");
			pagepeper.setOrder(Page.ASC);
		}
		 try{
			 pagepeper=announceService.searchAnnounce(pagepeper,filterParamMap(request));
			 JSONObject jsonData = parseBeanJsonList(pagepeper);
			 JsonUtils.renderSuccess(jsonData);
			 return jsonData.toString();
		  }catch(Exception ex){
			  ex.printStackTrace();
		  }
		 return null;
	}
	@RequestMapping("/saveactivity")
	public String saveactivity(Announce announce,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Integer result=0;
		Users users = (Users)request.getSession().getAttribute("usersInfo");
		if(announce.getRecommend()==null){
			announce.setRecommend(0);
		}
		String pic="";
		List<Images> imgList = new ArrayList<Images>();
		try{
		   imgList = uploadImgList(request);
		   if(imgList != null && imgList.size() > 0){
			   for(Images img: imgList){
				   if(img.getName().indexOf("picture") != -1){//服务说明图
					   pic=  img.getName();
					}
			   }	 
		   }
		   announce.setMsgdetailurl(pic);
		   announce.setCreatetime(new Date());
		   result= announceService.insertAnnounce(announce);
		   Map<String, String> params=new HashMap<>();
		   params.put("type", "1");
		   params.put("msgid", announce.getId().toString());
		   params.put("announcetype", announce.getAnnouncetype().toString());
		   sendGETRequest("http://xgapic.homy.cc/AutoPrintController/messageInform",params,"UTF-8"); 
		}catch(Exception ex){
		 ex.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/saveBanner")
	public String saveBanner(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Users users = (Users)request.getSession().getAttribute("usersInfo");
		Map<String, String> paramMap = new HashMap<String, String>();
		String[] introduceValues = request.getParameterValues("introduce");
		String[] idValues=request.getParameterValues("inputid");
		String scrollimg = "";
		Announce model=null;
		List<Images> imgList = new ArrayList<Images>();
		try{
			imgList = uploadImgList(request);
			  if(imgList != null && imgList.size() > 0){
				  for(Images img: imgList){
					  if(img.getName().indexOf("bannerurl") != -1){
						  scrollimg+= img.getName()+",";
						}
				  }	 
			  }
			  String[] str= scrollimg.split(",");
			 
			  for(int i=0;i<idValues.length;i++){
				  if(!StringUtil.isNullStr(str[i])){
				  model=announceService.Querybyid(Integer.valueOf(idValues[i]));
				  model.setBannerurl(str[i]);
				  model.setProfile(introduceValues[i]);
				  announceService.updatebean(model);
				  }
			  }
			  
		}catch(Exception ex){
		  ex.printStackTrace();
		}
		
		return null;
	}
	public List<Images> uploadImgList(HttpServletRequest request) throws IllegalStateException, IOException{
		//获取当前登陆用户的信息
		Users users = (Users)request.getSession().getAttribute("usersInfo");
		List<Images> imgList = new ArrayList<>();
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    String filedName = file.getName();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        //重命名上传后的文件名  
                        String originalFilename = ToolsUtils.getNo(3) + ".jpg";
                        String fileName = filedName + originalFilename;  
                        //定义上传路径  
                        String inputDir = storeimageDir+"/" +users.getId() + "/"; //项目路径
                        File fImageDir = new File(inputDir);
    					if (!fImageDir.exists()) {
    						fImageDir.mkdirs();
    					} else {
    						try {
    							fImageDir.createNewFile();
    						} catch (IOException e) {
    							e.printStackTrace();
    						}
    					}
                        String path =inputDir + fileName;  
                        File localFile = new File(path);  
                        file.transferTo(localFile);  
                        String saveFileName = "manageImages/msgInform"+"/"+ users.getId() + "/" + fileName;
                        Images images = new Images();
                        images.setFieldName(filedName);
                        images.setName(saveFileName);
                        imgList.add(images);
                    }  
                }  
            }  
        }  
		return imgList;
	}
	
	@RequestMapping("/setRecommend")
	@ResponseBody
	public String setRecommend(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		try{
		Announce bean=announceService.Querybyid(Integer.valueOf(id));
		if(bean!=null){
		  Integer	res=bean.getRecommend();
		  if(res==1){
			  bean.setRecommend(0);
		  }else{
			  bean.setRecommend(1);
		  }
		  announceService.RecommendSet(bean);
		  if(bean.getRecommend()==0){
		   Map<String, String> params=new HashMap<>();
		   params.put("type", "2");
		   params.put("msgid", bean.getId().toString());
		   params.put("announcetype", bean.getAnnouncetype().toString());
		   sendGETRequest("http://xgapic.homy.cc/AutoPrintController/messageInform",params,"UTF-8"); 
		  }
		  JsonUtils.renderSuccess(jo);
		}
		}catch(Exception ex){
			ex.printStackTrace();
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	@RequestMapping("/deleteone")
	@ResponseBody
	public String deleteone(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		try{
			announceService.deletebean(Integer.valueOf(id));
			JsonUtils.renderSuccess(jo);
		}catch(Exception ex){
			ex.printStackTrace();
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	private JSONObject parseBeanJsonList(Page<Map<String, Object>> page){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: page.getResult()) {
			JSONObject map = new JSONObject();
			map.put("id", bean.get("id"));
			map.put("title", bean.get("title"));
			String announceType=bean.get("announcetype").toString().trim();
			if(announceType.equals("1")){
				map.put("announcetype","活动通知");
			}else{
				map.put("announcetype","新功能发布");
			}
			String date1=bean.get("createtime").toString();
			map.put("createtime",date1 );
			map.put("createUser", bean.get("createUser"));
			String recommend=bean.get("recommend").toString().trim();
			if(recommend.equals("1")){
				map.put("setview", "<a name='setview' href='javascript:void(0);' onclick = 'cancelClick("+bean.get("id")+")'>取消推荐</a>");
			}else{
				map.put("setview", "<a name='setview' href='javascript:void(0);' onclick = 'cancelClick("+bean.get("id")+")'>设置推荐</a>");
			}
			
			map.put("selectview", "<a name='selectview' href='javascript:void(0);' onclick = 'dClick("+bean.get("id")+")'>删除</a>");
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	
	private Map<String, Object> filterParamMap(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String title=request.getParameter("title");
		String announceType=request.getParameter("announceType");
		String createUser=request.getParameter("createUser");
		if(!StringUtils.isEmpty(title)){
			paramMap.put("title", title);
		}
		if(!StringUtils.isEmpty(createUser)){
			paramMap.put("createUser", createUser);
		}
		if(!StringUtils.isEmpty(announceType)){
			paramMap.put("announceType", announceType);
		}
		//获取日期
		String s=request.getParameter("WdatePickerStart");
		String s1=request.getParameter("WdatePickerEnd");
		//判断开始时间，结束时间
		
		if(!StringUtils.isEmpty(s)){
			s=s+" 00:00:00";
			paramMap.put("WdatePickerStart", s);
		}
		if(!StringUtils.isEmpty(s1)){
			s1=s1+" 23:59:59";
			paramMap.put("WdatePickerEnd", s1);
		}
		return paramMap;		
	}
	
	
	
	
	@RequestMapping(value="getAllbanners",produces = "application/json; charset=utf-8") 
	@ResponseBody
	public String getAllbanners(HttpServletRequest request){
	  List<YyPagemodel>	bannerList=announceService.getAllYyPageModel();
	  JSONArray jarr = new JSONArray();
	  for(YyPagemodel bean:bannerList){
		  JSONObject jo = new JSONObject();
		  jo.put("id", bean.getId().toString());
		  jo.put("title", bean.getTitle());
		  jo.put("bannerurl", bean.getPictureurl());
	   // jo.put("profile", bean.getProfile());
		  jarr.add(jo);
	  }
	  JSONObject json = new JSONObject();
	  json.put("rows", jarr);
	  return json.toString();
	}
	
	
	
	@RequestMapping("/savePublishBanners")
	public String savePublishBanners(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		Users users = (Users)request.getSession().getAttribute("usersInfo");
		Map<String, String> paramMap = new HashMap<String, String>();
		String[] introduceValues = request.getParameterValues("introduce");
		String[] idValues=request.getParameterValues("inputid");
		String scrollimg = "";
		YyPagemodel model=null;
		List<Images> imgList = new ArrayList<Images>();
		try{
			imgList = uploadpublishBannerImgList(request);
			  if(imgList != null && imgList.size() > 0){
				  for(Images img: imgList){
					  if(img.getName().indexOf("bannerurl") != -1){
						  scrollimg+= img.getName()+",";
						}
				  }	 
			  }
			  String[] str= scrollimg.split(",");
			 
			  for(int i=0;i<idValues.length;i++){
				  if(!StringUtil.isNullStr(str[i])){
				  model=announceService.QueryYyPagemodelByid(Integer.valueOf(idValues[i]));
				  model.setPictureurl(str[i]);
				  model.setTitle(introduceValues[i]);
				  model.setCreatetime(new Date());
			   // model.setBannerurl(str[i]);
			//	  model.setProfile(introduceValues[i]);
				  announceService.updateYyPagemodel(model);
				  }
			  }
			  
		}catch(Exception ex){
		  ex.printStackTrace();
		}
		
		return null;
	}

	public List<Images> uploadpublishBannerImgList(HttpServletRequest request) throws IllegalStateException, IOException{
		//获取当前登陆用户的信息
		Users users = (Users)request.getSession().getAttribute("usersInfo");
		List<Images> imgList = new ArrayList<>();
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();  
                    String filedName = file.getName();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                    if(myFileName.trim() !=""){  
                        //重命名上传后的文件名  
                        String originalFilename = ToolsUtils.getNo(3) + ".jpg";
                        String fileName = filedName + originalFilename;  
                        //定义上传路径  
                        System.out.println(publishBannerimageDir);
                        System.out.println(users.getId());
                        String inputDir = publishBannerimageDir+"/" +users.getId() + "/"; //项目路径
                        File fImageDir = new File(inputDir);
    					if (!fImageDir.exists()) {
    						fImageDir.mkdirs();
    					} else {
    						try {
    							fImageDir.createNewFile();
    						} catch (IOException e) {
    							e.printStackTrace();
    						}
    					}
                        String path =inputDir + fileName;  
                        File localFile = new File(path);  
                        file.transferTo(localFile);  
                        String saveFileName = "images/bannerimages"+"/"+ users.getId() + "/" + fileName;
                        Images images = new Images();
                        images.setFieldName(filedName);
                        images.setName(saveFileName);
                        imgList.add(images);
                    }  
                }  
            }  
        }  
		return imgList;
	}
	
}
