package com.hanson.controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

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

import com.alibaba.fastjson.JSONObject;
import com.hanson.model.Images;
import com.hanson.model.ProductVO;
import com.hanson.model.ServiceProject;
import com.hanson.model.Users;
import com.hanson.model.odr.Yuyue;
import com.hanson.service.YuyueService;
import common.ToolsUtils;
@Controller
@RequestMapping("/yuyue")
public class YuyueController {
	final Logger logger = LoggerFactory.getLogger(TransactionAnalysisController.class);
	
	@Value("${yuyue.image.save.dir}")
	public String imageDir;
	@Value("${yuyue.image.view.dir}")
	public String imageViewDir;
	
	@Autowired
	private YuyueService yuyueService;
	
	/*@RequestMapping("/selectProName")
	@ResponseBody
	public String selectProjectName(String projectname)throws IOException{
		String name=new String(projectname.getBytes("ISO-8859-1"),"utf-8");
		JSONObject resultObj = new JSONObject();
		ServiceProject model=null;
		
		model=yuyueService.select_project_name(name);
		if(model!=null){
		resultObj.put("result", model.getId());
		}else{
			resultObj.put("result", "0");
		}
		return resultObj.toString();
	}
	@RequestMapping("/savebasicdata")
	public String savebasicinfo(ServiceProject serviceModel, HttpServletRequest request,HttpServletResponse response,HttpSession session)throws IllegalStateException, IOException{
		Users users = (Users)request.getSession().getAttribute("usersInfo");
		ServiceProject model=serviceModel;
		JSONObject resultObj = new JSONObject();
		int result=0;
		String detailpic = "";//轮播图
		String examplepic = "";//案例展示图
        String specialcontent="";//专业特设内容		
		//if(users.getRoleName().equals("超级管理员")){
		 List<Images> imgList = new ArrayList<Images>();
		 imgList = uploadImgList(request);
			//遍历List给不通类型的图片赋值
			if(imgList != null && imgList.size() > 0){
				for(Images img: imgList){
					if(img.getName().indexOf("serviceimg") != -1){//服务说明图
						model.setServiceinfo(img.getName());
					}
					if(img.getName().indexOf("firstImg") != -1){//主页图
						model.setFirstbanner(img.getName());
					}
					if(img.getName().indexOf("thumbImg") != -1){//缩略图
					   model.setOrderpic(img.getName());
					}
					if(StringUtils.isNotBlank(img.getName()) && img.getName().indexOf("detail") != -1){
						detailpic += img.getName()+",";
					}
					if(StringUtils.isNotBlank(img.getName()) && img.getName().indexOf("example") != -1){
						examplepic += img.getName()+",";
					}
					if(StringUtils.isNotBlank(img.getName()) && img.getName().indexOf("special") != -1){
						specialcontent += img.getName()+",";
					}
					
				}
			}
		//}
			model.setDetailpic(detailpic);
			model.setExamplepic(examplepic);
			model.setSpecialcontent(specialcontent);
			model.setSalesvolume(0);
			model.setProjecttype("1");
		if(model!=null){//插入约拍信息
		  yuyueService.saveyuepaiProject(model);
		}
		if(result!=0){
			resultObj.put("result", "1");
		}else{
		  resultObj.put("result", "0");
		}
		return  "/shop/toProduct";
	}*/

	/**
	 * 上传图片
	 * @param request
	 */
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
                       // String inputDir = imageDir+"/" + users.getId() + "/"; //项目路径
                        String inputDir = imageDir+"/"; //项目路径
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
                        //String saveFileName = "yuyueImages"+ "/" + users.getId() + "/" + fileName;
                        String saveFileName = "ypimages"+ "/" + fileName;
                       
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
