package com.hanson.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Page;
import com.hanson.model.Company;
import com.hanson.model.Images;
import com.hanson.model.Product;
import com.hanson.model.ProductVO;
import com.hanson.model.Productinfo;
import com.hanson.model.ServiceProject;
import com.hanson.model.Specvalue;
import com.hanson.model.Users;
import com.hanson.service.CompanyService;
import com.hanson.service.ProductService;
import com.hanson.service.ProductinfoService;
import com.hanson.service.ServiceprojectCompanyService;
import com.hanson.service.SpecvalueService;
import com.hanson.service.YuyueService;

import common.CommonServiceImpl;
import common.Result;
import common.ToolsUtils;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {
	final Logger logger = LoggerFactory.getLogger(ShoppingController.class);
	@Value("${yuyue.image.save.dir}")
	public String yuyueimageDir;
	@Value("${yuyue.image.view.dir}")
	public String yuyueimageViewDir;
	
	@Value("${manage.image.save.dir}")
	public String imageDir;
	@Value("${manage.image.view.dir}")
	public String imageViewDir;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductinfoService productinfoService;
	@Autowired
	private SpecvalueService specvalueService;
	@Autowired
	private YuyueService yuyueService;
	@Autowired
	private ServiceprojectCompanyService serviceprojectCompanyService;
	@Autowired
	private CompanyService companyService; 
	
	/**
	 * 构建模糊查询条件及验证
	 */
	private Map<String, Object> filterParamMap(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String status = request.getParameter("status");
		String producttype = request.getParameter("producttype");
		String kdate = request.getParameter("kdate");
		String jdate = request.getParameter("jdate");
		
		if(!StringUtils.isEmpty(id)){
			paramMap.put("id", id);
		}
		if(!StringUtils.isEmpty(name)){
			paramMap.put("name", name);
		}
		if(!StringUtils.isEmpty(status)){
			paramMap.put("status", status);
		}
		if(!StringUtils.isEmpty(producttype)){
			paramMap.put("producttype", producttype);
		}
		if(!StringUtils.isEmpty(kdate)){
			paramMap.put("kdate", kdate);
		}
		if(!StringUtils.isEmpty(jdate)){
			paramMap.put("jdate", jdate);
		}
		
		Users users = (Users)request.getSession().getAttribute("usersInfo");
		if(users != null){
			paramMap.put("qyid", users.getQyid());
		}
		
		return paramMap;
	}
	private Map<String, Object> filterParamMap1(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		/*String name = request.getParameter("name");
		String status = request.getParameter("status");
		String producttype = request.getParameter("producttype");
		String kdate = request.getParameter("kdate");
		String jdate = request.getParameter("jdate");
		
		if(!StringUtils.isEmpty(id)){
			paramMap.put("id", id);
		}
		if(!StringUtils.isEmpty(name)){
			paramMap.put("name", name);
		}
		if(!StringUtils.isEmpty(status)){
			paramMap.put("status", status);
		}
		if(!StringUtils.isEmpty(producttype)){
			paramMap.put("producttype", producttype);
		}
		if(!StringUtils.isEmpty(kdate)){
			paramMap.put("kdate", kdate);
		}
		if(!StringUtils.isEmpty(jdate)){
			paramMap.put("jdate", jdate);
		}*/
		
		Users users = (Users)request.getSession().getAttribute("usersInfo");
		/*if(users != null){
			paramMap.put("qyid", users.getQyid());
		}*/
		
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
			map.put("name", bean.get("name"));
			map.put("producttype", bean.get("producttype"));
			map.put("yxl", bean.get("yxl"));
			map.put("salecount", bean.get("salecount"));
			map.put("status", bean.get("status"));
			map.put("standardprice", bean.get("standardprice"));
			map.put("createtime", bean.get("createtime") == null?"":bean.get("createtime").toString());
			
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	
	private JSONArray parseYuepaiprojectList(List<Map<String,Object>> yupailist){
		JSONArray jsonArg = new JSONArray();
	//	JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: yupailist) {
			JSONObject map = new JSONObject();
			map.put("id", bean.get("id"));
			map.put("area", bean.get("area"));
			map.put("companyname", bean.get("companyname"));
			map.put("qyid", bean.get("qyid"));
			map.put("presentmoney", bean.get("presentmoney"));
			map.put("addproductmoney", bean.get("addproductmoney"));
			map.put("originalprice", bean.get("originalprice"));
			jsonArg.add(map);
		}
	
		return jsonArg;
	}
	private JSONObject parseYuepaiList(Page<Map<String, Object>> page){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: page.getResult()) {
			JSONObject map = new JSONObject();
			map.put("id", bean.get("id"));
			map.put("projectname", bean.get("projectname"));
			map.put("englishname", bean.get("englishname"));
			map.put("shootingtype", bean.get("shootingtype"));
		    String totalamount=	bean.get("totalamount").toString();
			map.put("totalamount",Double.valueOf(totalamount)/100);
			map.put("updownframestatus", bean.get("updownframestatus"));
			map.put("serviceinfo", bean.get("serviceinfo"));
			map.put("mainfunction", bean.get("mainfunction"));
			map.put("specialcontent", bean.get("specialcontent"));
			map.put("addservice", bean.get("addservice"));
			map.put("salesvolume", bean.get("salesvolume"));
			map.put("createdate", bean.get("createdate") == null?"":bean.get("createdate").toString());
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	@RequestMapping("/yuepaiList")
	@ResponseBody
	public String yuepaiList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		request.getParameterNames();
		Page<Map<String, Object>> pagepaper = new Page<Map<String, Object>>();
		pagepaper.setPageNo(Integer.valueOf(page));
		pagepaper.setPageSize(Integer.valueOf(rows));
		/*if(!pagepaper.isOrderBySetted()){
			pagepaper.setOrderBy("id");
			pagepaper.setOrder(Page.DESC);
		}*/
		try{
		  pagepaper=yuyueService.FindyupaiProducts(pagepaper, filterParamMap1(request));
		  JSONObject jsonData = parseYuepaiList(pagepaper);
		 JsonUtils.renderSuccess(jsonData);
		 return jsonData.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/productList")
	@ResponseBody
	public String orderList(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		request.getParameterNames();
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("id");
			pagepeper.setOrder(Page.DESC);
		}
		try {
			pagepeper = productService.findProductPage(pagepeper, filterParamMap(request));
			JSONObject jsonData = parseBeanJsonList(pagepeper);
			JsonUtils.renderSuccess(jsonData);
			return jsonData.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping("/deleteyuepaiProduct")
	@ResponseBody
	public String deleteyuepaiProduct(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		response.setContentType("text/plain; charset=utf-8");
	    String sid=request.getParameter("id");
		Long id = Long.valueOf(sid);
		String resString = "";//返回消息
		try {
			
			yuyueService.DeleteServiceProject(id);
			serviceprojectCompanyService.deleteProjectCompany(id);
			resString="删除成功";
		} catch (Exception e) {
			resString="删除出错";
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print(resString);
		out.flush();
		return null;
	}
	/**
	 * 删除产品
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/deleteProduct")
	public String deleteProduct(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		response.setContentType("text/plain; charset=utf-8");
		Long id = Long.valueOf(request.getParameter("id"));
		Product product = new Product();
		product.setId(id);
		
		String resString = "";//返回消息
		try {
			productService.deleteProduct(product);
			resString="删除成功";
		} catch (Exception e) {
			resString="删除出错";
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print(resString);
		out.flush();
		return null;
	}
	
	/**
	 * 产品发布
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/saveProduct")
	public String saveProduct(ProductVO productVO, HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IllegalStateException, IOException {
		Users users = (Users)request.getSession().getAttribute("usersInfo");
		//users.setQyid(1L);
		//产品附加信息
		Productinfo productinfo = productVO.getProductinfo();
		List<Specvalue> specvalueList = productVO.getSpecvalueList();
		
		List<Images> imgList = new ArrayList<Images>();
		//上传图片信息并返回生成图片List
		imgList = uploadImgList(request);
		int i = 1;//详情图指针标识
		int j = 0; //规格属性原图和缩略图指针标识
		int m = 0; //规格属性原图和缩略图指针标识
		
		String scrollimg = "";
		//遍历List给不通类型的图片赋值
		if(imgList != null && imgList.size() > 0){
			for(Images img: imgList){
				//主图
				if(img.getName().indexOf("mainimg") != -1){
					productVO.setMainimg(img.getName());
				}
				
				//详情图
				if(img.getName().indexOf("detialImg") != -1){
					if(i == 1){
						productinfo.setImg1(img.getName());
					}
					if(i == 2){
						productinfo.setImg2(img.getName());
					}
					if(i == 3){
						productinfo.setImg3(img.getName());
					}
					if(i == 4){
						productinfo.setImg4(img.getName());
					}
					if(i == 5){
						productinfo.setImg5(img.getName());
					}
					i++;
				}
				
				//轮播图
				if(StringUtils.isNotBlank(img.getName()) && img.getName().indexOf("scrollimg") != -1){
					scrollimg += img.getName()+",";
				}
				
				//规格属性原图
				if(img.getName().indexOf("original") != -1){
					specvalueList.get(j).setOriginal(img.getName());
					j++;
				}
				
				//规格属性缩略图
				if(img.getName().indexOf("thumbnail") != -1){
					specvalueList.get(m).setThumbnail(img.getName());
					m++;
				}
			}
		}
		
		
		//插入产品表
		if(productVO != null){
			productVO.setQyid(users.getQyid().intValue());
			productVO.setCatalogid("1");
			productVO.setStatus(1);
			productVO.setProducttype("20");
			productVO.setScrollimg(scrollimg);
			productService.saveProduct(productVO);
		}
		
		
		//插入产品附件表
		if(productinfo != null){
			productinfo.setProductid(productVO.getID());
			productinfoService.saveProductinfo(productinfo);
		}
		
		
		//插入产品规格属性表
		try {
			if(specvalueList != null && specvalueList.size() > 0){
				for(Specvalue specvalue: specvalueList){
					specvalue.setProductid(productVO.getID().longValue());
					specvalueService.save(specvalue);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 产品发布
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/editProduct")
	public String editProduct(ProductVO productVO, HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IllegalStateException, IOException {
		
		//Users users = (Users)request.getSession().getAttribute("usersInfo");
		//users.setQyid(1L);
		try {
			
			
			//产品附加信息
			Productinfo productinfo = productVO.getProductinfo();
			List<Specvalue> specvalueList = productVO.getSpecvalueList();
			List<Images> imgList = new ArrayList<Images>();
			
			//上传图片信息并返回生成图片List
			imgList = uploadImgList(request);
			
			//遍历图片List将不同类型的图片放到各自的list
			List<String> mainimgList = new ArrayList<String>();		//主图
			List<String> detialImgList = new ArrayList<String>();	//详情图
			List<String> scrollimgList = new ArrayList<String>();	//轮播图
			List<String> originalList = new ArrayList<String>();	//规格原图
			List<String> thumbnailList = new ArrayList<String>();	//规格缩略图
			
			if(imgList != null && imgList.size() > 0){
				for(Images img: imgList){
					//主图
					if(img.getName().indexOf("mainimg") != -1){
						mainimgList.add(img.getName());
					}
					
					//详情图
					if(img.getName().indexOf("detialImg") != -1){
						detialImgList.add(img.getName());
					}
					
					//轮播图
					if(img.getName().indexOf("scrollimg") != -1){
						scrollimgList.add(img.getName());
					}
					
					//规格属性原图
					if(img.getName().indexOf("original") != -1){
						originalList.add(img.getName());
					}
					
					//规格属性缩略图
					if(img.getName().indexOf("thumbnail") != -1){
						thumbnailList.add(img.getName());
					}
				}
			}
			
			//将个种类图片List中的数据赋值到各自对象中
			
			//主图
			if(mainimgList != null && mainimgList.size() > 0){
				productVO.setMainimg(mainimgList.get(0));
			}
			//轮播图
			List<String> oldImgs = new ArrayList<String>();
			String lunboImage = request.getParameter("lunboImage");
			String[] lbImgs = lunboImage.split(",");
			oldImgs = Arrays.asList(lbImgs);
			if(scrollimgList != null && scrollimgList.size() > 0){
				for(String img: scrollimgList){
					for(int i = 0; i < oldImgs.size(); i++){
						if("null".equals(oldImgs.get(i))){
							oldImgs.set(i, img);
							break;
						}
					}
				}
			}
			String[] arr = (String[])oldImgs.toArray(new String[oldImgs.size()]);
			String str1=StringUtils.join(arr, ",");
			productVO.setScrollimg(str1);
			
			//详情图
			List<String> oldDetailList = new ArrayList<String>();
			String detailImage = request.getParameter("detailImg");
			String[] detailImageArr = detailImage.split(",");
			oldDetailList = Arrays.asList(detailImageArr);
			if(detialImgList != null && detialImgList.size() > 0){
				for(String img: detialImgList){
					for(int i = 0; i < oldDetailList.size(); i++){
						if("null".equals(oldDetailList.get(i))){
							oldDetailList.set(i, img);
							break;
						}
					}
				}
			}
			if(oldDetailList != null && oldDetailList.size() >0){
				//设置现有详情图为空
				productinfo.setImg1("");
				productinfo.setImg2("");
				productinfo.setImg3("");
				productinfo.setImg4("");
				productinfo.setImg5("");
				
				for(int i = 0; i < oldDetailList.size(); i++){
					if(i == 0){
						productinfo.setImg1(oldDetailList.get(i));
					}
					if(i == 1){
						productinfo.setImg2(oldDetailList.get(i));
					}
					if(i == 2){
						productinfo.setImg3(oldDetailList.get(i));
					}
					if(i == 3){
						productinfo.setImg4(oldDetailList.get(i));
					}
					if(i == 4){
						productinfo.setImg5(oldDetailList.get(i));
					}
				}
			}
			
			//规格属性原图
			if(originalList != null && originalList.size() > 0){
				for(String original: originalList){
					for(int i=0; i < specvalueList.size(); i++){
						if(StringUtils.isBlank(specvalueList.get(i).getOriginal())){
							Specvalue specvalue = specvalueList.get(i);
							specvalue.setOriginal(original);
							specvalueList.set(i, specvalue);
							break;
						}
					}
				}
			}
			//规格属性缩略图
			if(thumbnailList != null && thumbnailList.size() > 0){
				for(String thumbnail: thumbnailList){
					for(int i=0; i < specvalueList.size(); i++){
						if(StringUtils.isBlank(specvalueList.get(i).getThumbnail())){
							Specvalue specvalue = specvalueList.get(i);
							specvalue.setThumbnail(thumbnail);
							specvalueList.set(i, specvalue);
							break;
						}
					}
				}
			}
			
			//更新产品表
			if(productVO != null){
				productService.updateProduct(productVO);
			}
			
			//跟新产品附加表
			if(productinfo != null){
				productinfoService.updateProductinfo(productinfo);
			}
			
			//更新产品规格属性信息
			List<Specvalue> oldSpeList = new ArrayList<Specvalue>();
			List<Specvalue> updateSpeList = new ArrayList<Specvalue>();
			oldSpeList = specvalueService.findUniqueByPid(productVO.getID().longValue());
			
			if(specvalueList != null && specvalueList.size() > 0){
				//第一次双循环修改并删除数据
				for(Specvalue oldSpe: oldSpeList){
					boolean flag = false;
					String oldSize = oldSpe.getSize();
					String oldColor = oldSpe.getColor();
					for(Specvalue newSpe: specvalueList){
						String newSize = newSpe.getSize();
						String newColor = newSpe.getColor();
						if(oldSize.equals(newSize) && oldColor.equals(newColor)){
							//上架
							if(productVO.getStatus() == 1){
								newSpe.setIsvalid(0);
							}
							//下架
							if(productVO.getStatus() == 2){
								newSpe.setIsvalid(1);
							}
							specvalueService.updateSpecvalue(newSpe);
							flag = false;
							break;
						}else{
							flag = true;
						}
					}
					if(flag){
						//删除旧的产品规格属性
						specvalueService.deleteSpecvalue(oldSpe);
					}
				}
				
				//第二次双循环新增数据
				for(Specvalue newSpe: specvalueList){
					boolean flag = false;
					String newSize = newSpe.getSize();
					String newColor = newSpe.getColor();
					for(Specvalue oldSpe: oldSpeList){
						String oldSize = oldSpe.getSize();
						String oldColor = oldSpe.getColor();
						if(oldSize.equals(newSize) && oldColor.equals(newColor)){
							flag = false;
							break;
						}else{
							flag = true;
						}
					}
					if(flag){
						//新增新的产品规格属性
						newSpe.setProductid(productVO.getID().longValue());
						//上架
						if(productVO.getStatus() == 1){
							newSpe.setIsvalid(0);
						}
						//下架
						if(productVO.getStatus() == 2){
							newSpe.setIsvalid(1);
						}
						
						specvalueService.save(newSpe);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 上传图片
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
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
                        Date datetime=new Date();
                        int year=datetime.getYear();
                        int month=datetime.getMonth();
                        String diret=year+""+month;
                        
                        String inputDir = imageDir+"/" +diret+"/"+ users.getId() + "/"; //项目路径
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
                        //manageImages 八月相馆和小浪雨上传相框照片，需要数据库存入Images
                        String saveFileName = "images/manageImages"+ "/" +diret+"/"+ users.getId() + "/" + fileName;
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
	@RequestMapping("/toyuepaiEdit")
	public String toyuepaiEdit(ServiceProject serviceProject,HttpServletRequest request,Model model){
	String yupaiid=request.getParameter("ID");
		//Long id=serviceProject.getId();
		serviceProject=yuyueService.FindServiceProject(Long.valueOf(yupaiid));
		
		
		
	   List<String> detailimgList = new ArrayList<String>();
	   if(StringUtils.isNotBlank(serviceProject.getDetailpic())){
		  String[] detailpicImages = serviceProject.getDetailpic().split(",");
		  if(detailpicImages != null && detailpicImages.length > 0){
			detailimgList = Arrays.asList(detailpicImages);
		  }
		}
		List<String> examplepicList=new ArrayList<String>();
		if(StringUtils.isNotBlank(serviceProject.getExamplepic())){
			String[] examplepics=serviceProject.getExamplepic().split(",");
			if(examplepics!=null && examplepics.length>0){
				examplepicList=Arrays.asList(examplepics);
			}
		}
		List<String> specialcontentList=new ArrayList<String>();
		if(StringUtils.isNotEmpty(serviceProject.getSpecialcontent())){
			String[] specialContent=serviceProject.getSpecialcontent().split(",");
			if(specialContent!=null && specialContent.length>0){
				specialcontentList=Arrays.asList(specialContent);
			}
		}
		model.addAttribute("serviceProject", serviceProject);
		model.addAttribute("detailimgList",detailimgList);
		model.addAttribute("examplepicList",examplepicList);
		model.addAttribute("specialcontentList",specialcontentList);
		return "shopping/yuepaiedit";
	}
	@RequestMapping("/toyuepaiPriceEdit")
	public String toyuepaiPriceEdit(HttpServletRequest request,Model model){
		String yupaiid=request.getParameter("ID");
		/*String area=request.getParameter("area");
		String qyid=request.getParameter("qyid");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectid", yupaiid);
		map.put("area",area);
		map.put("qyid", qyid);
	    List<Map<String,Object>> projectList=serviceprojectCompanyService.FindCompanyyuepaiList(map);
	    JSONArray jsonData = parseYuepaiprojectList(projectList);*/
		 //JsonUtils.renderSuccess(jsonData);
		// return jsonData.toString();
		 
		model.addAttribute("yupaiid", yupaiid);
		return "shopping/yuepaiprice";
	}
	
	@RequestMapping(value="/selectyuepaiPrice",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String selectyuepaiPrice(HttpServletRequest request,Model model){
		String yupaiid=request.getParameter("projectid");
		String area=request.getParameter("area");
		String qyid=request.getParameter("qyid");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectid", yupaiid);
		map.put("area",area);
		map.put("qyid", qyid);
	    List<Map<String,Object>> projectList=serviceprojectCompanyService.FindCompanyyuepaiList(map);
	    JSONArray jsonData = parseYuepaiprojectList(projectList);
        return jsonData.toString();
	}
	
	@RequestMapping(value="/modifyyuepaiPrice",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String modifyyuepaiPrice(HttpServletRequest request){
		String id=request.getParameter("id");
		String price=request.getParameter("price");
		String addPrice=request.getParameter("addPrice");
		String originalPrice=request.getParameter("originalprice");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("originalprice", originalPrice);
		map.put("addproductmoney", addPrice);
		map.put("presentmoney", price);
		map.put("id",id);
		Integer result=serviceprojectCompanyService.modifyprice(map);
		return result.toString();
	}
	@RequestMapping("/SynchronProductFee")
	@ResponseBody
	public String SynchronProductFee(HttpServletRequest request,HttpSession session){
		JSONObject jsonData=new JSONObject();
		Users user = (Users) session.getAttribute("usersInfo");
		try{
		if(user!=null){
		  long qyid=user.getQyid();
		  Company company= companyService.findUniqueById(qyid);
		  long parentid=company.getHeadoffice();
		  if(parentid!=company.getId()&&parentid!=0d){
			  productService.findUniqueByQyId(parentid);
		  }
		}
		}catch(Exception e){
			e.printStackTrace();
			JsonUtils.renderException(jsonData);
		}
		return jsonData.toString();
	}
	@RequestMapping("/SettingProductFee")
	@ResponseBody
	public String SettingProductFee(HttpServletRequest request){
		JSONObject jsonData=new JSONObject();
		String productid=request.getParameter("id");
		 List<Specvalue> specList=new ArrayList<Specvalue>();
		try{
	      specList=specvalueService.findUniqueByPid(Long.valueOf(productid));
	       jsonData = parseBeanJsonList(specList);
		   //JsonUtils.renderSuccess(jsonData);
		   return jsonData.toString();
		}catch(Exception ex){
			ex.printStackTrace();
			JsonUtils.renderException(jsonData);
		 }
		return jsonData.toString();
	}
	@RequestMapping("/ModifyProductFee")
	@ResponseBody
	public String ModifyProductFee(HttpServletRequest request){
		JSONObject jsonData=new JSONObject();
		String[] productid_Value=request.getParameter("spectids").split(",");
		Map<String, Object> map1=null;
		try{
			for(String x : productid_Value) { 
			  map1=new HashMap<String, Object>();
	           String[] strs= x.split("_");
	           map1.put("id", strs[0]);
	           map1.put("storeproductcost", Double.valueOf(strs[1])*100);
	           specvalueService.updateproductBatch(map1);
	         } 
		   JsonUtils.renderSuccess(jsonData);
		   return jsonData.toString();
		}catch(Exception ex){
			ex.printStackTrace();
			JsonUtils.renderException(jsonData);
		 }
		return jsonData.toString();
	}
	
	private JSONObject parseBeanJsonList(List<Specvalue> specList){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Specvalue bean: specList) {
			JSONObject map = new JSONObject();
			map.put("id", bean.getId());
			map.put("size", bean.getSize());
			map.put("color", bean.getColor());
			map.put("storeproductcost", bean.getStoreproductcost());
			map.put("plastic", bean.getPlastic());
			map.put("specinfo", bean.getSpecinfo());
			map.put("plasticprice", bean.getPlasticprice());
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		return jsonData;
	}
	/**
	 * 跳转到相框编辑页
	 * @return
	 */
	@RequestMapping("/toXkfbEdit")
	public String toXkfbEdit(ProductVO productVO, Model model) {
		//根据ID查询产品
		ProductVO productVOTmp = new ProductVO();
		Productinfo productinfo = new Productinfo();
		List<Specvalue> specvalueList = new ArrayList<Specvalue>();
		
		//查询产品信息
		productVOTmp = productService.findProductById(productVO);
		//查询产品附加信息
		productinfo.setProductid(productVO.getID());
		productinfo = productinfoService.findProductinfoByPId(productinfo);
		
		//查询产品规格信息
		specvalueList = specvalueService.findUniqueByPid(productVOTmp.getID().longValue());
		
		//轮播图List
		List<String> scrollimgList = new ArrayList<String>();
		if(StringUtils.isNotBlank(productVOTmp.getScrollimg())){
			String[] scrollImages = productVOTmp.getScrollimg().split(",");
			if(scrollImages != null && scrollImages.length > 0){
				scrollimgList = Arrays.asList(scrollImages);
			}
		}
		
		
		model.addAttribute("product", productVOTmp);
		model.addAttribute("productinfo", productinfo);
		model.addAttribute("specvalueList", specvalueList);
		model.addAttribute("scrollimgList", scrollimgList);
		model.addAttribute("imageViewDir", imageViewDir);
		
		
		
		return "shopping/xkfb_eidt";
	}
	
	//产品详情
	@RequestMapping("/toXkfbView")
	public String toXkfbView(ProductVO productVO, Model model) {
		//根据ID查询产品
				ProductVO productVOTmp = new ProductVO();
				Productinfo productinfo = new Productinfo();
				List<Specvalue> specvalueList = new ArrayList<Specvalue>();
				
				//查询产品信息
				productVOTmp = productService.findProductById(productVO);
				//查询产品附加信息
				productinfo.setProductid(productVO.getID());
				productinfo = productinfoService.findProductinfoByPId(productinfo);
				
				//查询产品规格信息
				specvalueList = specvalueService.findUniqueByPid(productVOTmp.getID().longValue());
				
				//轮播图List
				List<String> scrollimgList = new ArrayList<String>();
				if(StringUtils.isNotBlank(productVOTmp.getScrollimg())){
					String[] scrollImages = productVOTmp.getScrollimg().split(",");
					if(scrollImages != null && scrollImages.length > 0){
						scrollimgList = Arrays.asList(scrollImages);
					}
				}
				
				
				model.addAttribute("product", productVOTmp);
				model.addAttribute("productinfo", productinfo);
				model.addAttribute("specvalueList", specvalueList);
				model.addAttribute("scrollimgList", scrollimgList);
				model.addAttribute("imageViewDir", imageViewDir);
				
				
				
				return "shopping/xkfb_view";
	}
	
	public String download(){
		
		return null;
	}

	//跳转到商品列表页面
	@RequestMapping("/toProduct")
	public String order() {
		return "shopping/productList";
	}
	@RequestMapping("/toYuepai")
	public String yuepaiList(){
       return "shopping/yuepaiList";		
	}
	@RequestMapping("/toNavigation")
	public String toNavigation() {
		return "shopping/fbxz";
	}
	
	@RequestMapping("/toAddProduct")
	public String toAddProduct() {
		return "shopping/xkfb";
	}

	@RequestMapping("/toAblumFrame")
	public String toAblumFrame() {
		return "shopping/xcfb";
	}
    @RequestMapping("/yuepaiFrame")
    public String yuepaiFrame(){
    	return "shopping/addyuepai";
    }
	/**
	 * 导出产品数据excel
	 * @param data
	 * @return
	 */
	@RequestMapping(value="/productExcel")
	@ResponseBody
	public Result<?> productExcel(HttpServletRequest request,HttpServletResponse response){
		
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("id",request.getParameter("id"));//产品编号
		data.put("producttype",request.getParameter("producttype"));//产品类型
		data.put("status",request.getParameter("status"));//上下架状态
		data.put("name",request.getParameter("name"));//商品名
		data.put("kdate",request.getParameter("kdate"));//开始时间
		data.put("jdate",request.getParameter("jdate"));//截止时间
		Users users = (Users)request.getSession().getAttribute("usersInfo");
		if(users != null){
			data.put("qyid", users.getQyid());
		}
		//写数据到Excel  
		byte[] byt = productService.productExcel(data);
		//下载excel
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String fileName= time+"-productExcel-"+System.currentTimeMillis();
		CommonServiceImpl.downloadExcel(request, response, fileName, byt);
				
		return Result.newSuccessResult();
		
	}
	
	
	
	public static void main(String[] args){
		/*String str = "scrollimg001";
		System.out.println(str.indexOf("scrollimg"));*/
		
/*		String s = "杭州20170222ax";
		Pattern p = Pattern.compile("([0-9].{5,5})");
		Matcher m = p.matcher(s);
		m.find();
		System.out.println(m.group(1));*/
	}

}
