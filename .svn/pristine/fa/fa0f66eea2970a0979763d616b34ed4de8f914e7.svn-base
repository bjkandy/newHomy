package com.hanson.controller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Page;
import com.hanson.common.util.SysConstant;
import com.hanson.model.City;
import com.hanson.model.Company;
import com.hanson.model.Product;
import com.hanson.model.ProductVO;
import com.hanson.model.Productinfo;
import com.hanson.model.ServiceProject;
import com.hanson.model.ServiceprojectCompany;
import com.hanson.model.Specvalue;
import com.hanson.model.StoreProperty;
import com.hanson.model.StoreRule;
import com.hanson.service.CompanyService;
import com.hanson.service.ProductService;
import com.hanson.service.ProductinfoService;
import com.hanson.service.ProvincialService;
import com.hanson.service.ServiceprojectCompanyService;
import com.hanson.service.SpecvalueService;
import com.hanson.service.StorePropertyService;
import com.hanson.service.StoreRuleService;
import com.hanson.service.YuyueService;
import com.hanson.util.Constant;
import com.hanson.util.StringUtil;

@Controller
@RequestMapping("/company")
public class CompanyController {
	@Value("${company.code.view.dir}")
	public String companycodeurl;
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SpecvalueService specvalueService;
	@Autowired
	private YuyueService yuyueService;
	@Autowired
	private ProvincialService provincialService;
	@Autowired
	private ServiceprojectCompanyService serviceprojectCompanyService;
	@Autowired
	private StoreRuleService storeRuleService;
	@Autowired
	private StorePropertyService storePropertyService;
    @Autowired
    private ProductinfoService productinfoService;
	/**
	 * 跳转到列表页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request, Model model) throws Exception{
		//可被汇美指派的生产中心
		List<Company> hymnCentersList = new ArrayList<Company>();
		Company company = new Company();
		hymnCentersList = companyService.findHymnCenters(new Company());
		model.addAttribute("hymnCentersList", hymnCentersList);
		
		return "company/company";
	}
	
	/**
	 * 用户名检查
	 * @return
	 */
	@RequestMapping(value="/loginVerify")
	public void loginVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		if(StringUtils.isEmpty(id) || !id.equals(name)){
			int count = companyService.isLoginname(name);
			if(count > 0){
				response.getWriter().print(false);
			}else{
				response.getWriter().print(true);
			}
		}else{
			
		}
	}
	
	/**
	 * 查找企業
	 * @return
	 */
	@RequestMapping(value="/findCompany")
	@ResponseBody
	public String findCompany(HttpServletRequest request) throws Exception {
		String q = request.getParameter("q");
		System.out.println(q);
//		String page = request.getParameter("page");
		List<Company> list = companyService.findListLikeName(q);
		JSONArray jarr = new JSONArray();
		for(Company company : list){
			JSONObject jo = new JSONObject();
			jo.put("id", company.getId());
			jo.put("text", company.getCompanyname());
			jarr.add(jo);
		}
		JSONObject json = new JSONObject();
		json.put("rows", jarr);
		
		return json.toString();
	}
	/**
	 * 创建二维码
	 * @return
	 */
	@RequestMapping(value="/createQrcode")
	@ResponseBody
	public String createQrcode(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		String message = "企业 : id为" + id + "的二维码创建成功";
		JSONObject jo = new JSONObject();
		String result = sendGet(companycodeurl,"qyid="+id);
		
		JSONObject resultJson = null;
		if(request != null){
			resultJson = JSONObject.fromObject(result);
			resultJson.getString("qrcodeUrl");
		}
		jo.put("msg",message);
		jo.put("url", resultJson.getString("qrcodeUrl"));
		System.out.println(jo.toString());
		return jo.toString();
	}
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
	/**
	 * 修改消息语
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/updateMsgType")
	@ResponseBody
	public String updateMsgType(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		String message = "";
		try {
			Company company = companyService.findUniqueById(Long.valueOf(id));
			if(company.getMsgtype() == 1){
				company.setMsgtype(2);
				message = "企业信息 : id为" + id + "的数据修改为上级自己公司名称成功";
			}else{
				company.setMsgtype(1);
				message = "企业信息 : id为" + id + "的数据修改为显示自己公司名称成功";
			}
			companyService.save(company);
			jo.put("msg", message);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			message = "企业信息 : id为" + id + "的数据的欢迎语修改失败";
			jo.put("msg", message);
			JsonUtils.renderException(jo);
		}
		
		return jo.toString();
	}
	
	/**
	 * 清除所属企业关系
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/clearParentid")
	@ResponseBody
	public String clearParentid(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		try {
			companyService.clearParentid(Long.valueOf(id));
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		
		return jo.toString();
	}
	@RequestMapping("/creatcodeUrl")
	@ResponseBody
	public String creatcodeUrl(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id").trim();
		String tuiguangurl = "";
		try{
		Company company=companyService.findUniqueById(Long.valueOf(id));
		String FXR_URL_DOMAIN = SysConstant.FXR_URL_DOMAIN;	//request.getScheme() + "://" + 		
		tuiguangurl = FXR_URL_DOMAIN +  "/" + request.getContextPath()+"fxr"+id+".do";
		company.setTuiguangurl(tuiguangurl);
	    companyService.createprometerurl(company);
	    JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	//开通相框复制产品的功能
	@RequestMapping("/addphotoalbumfunction")
	@ResponseBody
	public String addphotoalbumfunction(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id").trim();
		Company company=companyService.findUniqueById(Long.valueOf(id));
		Integer photoalbumenable=company.getPhotoalbumenable();
		if(photoalbumenable==0){
			JsonUtils.renderoutphot(jo);
			return jo.toString();
		}
		Integer qyid=company.getId().intValue();
		/*Map<String, Object> map1=new HashMap<String, Object>();
		 map1.put("qyid", company.getId());
         map1.put("producttype", "40");//40相册
         map1.put("status", "1");*/
         /*List<ProductVO> existProducts= productService.FindStoreProductbywhere(map1);
         if(existProducts.size()>0){
        	 JsonUtils.renderexist(jo);
        	 return jo.toString();
         }*/
		try{
			List<ProductVO> pmodelList = productService.FindalbumProduct();
			String productName="";
			ProductVO oldProduct=null;
			Map<String, Object> map2=new HashMap<String, Object>();
			
			if(pmodelList != null && pmodelList.size() > 0){
				for(ProductVO pd: pmodelList){
					productName=pd.getName();
					map2.put("qyid", qyid);
					map2.put("name", productName);
					oldProduct=productService.SelectProductByName(map2);
					List<Specvalue> pdSList =specvalueService.findUniqueByPid(pd.getID().longValue()); 
					if(oldProduct==null){
						//查询产品附加信息
						Productinfo productinfoT= new Productinfo();
						productinfoT.setProductid(pd.getID());
						productinfoT = productinfoService.findProductinfoByPId(productinfoT);//获得产品详情信息
						//productinfoService.
						pd.setQyid(qyid);
						pd.setID(null);
						pd.setSellcount(0);
						productService.saveProduct(pd);
						productinfoT.setProductid(pd.getID());
						productinfoService.saveProductinfo(productinfoT);
						//保存产品规格
						Integer pdID = pd.getID();
						for(Specvalue spec : pdSList) {
							spec.setProductid(pdID.longValue());
							spec.setSalecount(0);
							specvalueService.save(spec);
						}
					}
					
				}
			}
			JsonUtils.renderSuccess(jo);
		}catch(Exception ex){
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	//开通相框复制产品的功能
	@RequestMapping("/addphotoframefunction")
	@ResponseBody
	public String addphotoframefunction(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id").trim();//获取当前公司id
		Company company=companyService.findUniqueById(Long.valueOf(id));
		Integer photoframeenable=company.getPhotoframeenable();
		if(photoframeenable==0){//判断当前公司是否开通了相框的功能
			JsonUtils.renderoutphot(jo);
			return jo.toString();
		}
		Integer qyid=company.getId().intValue();//获得到当前公司id
		try {
		List<ProductVO> pmodelList = productService.findPhotoFrame();//查找ptd_product表里103的相框产品
		String productName="";
		ProductVO oldProduct=null;
		Map<String, Object> map1=new HashMap<String, Object>();
		if(pmodelList != null && pmodelList.size() > 0){//查找ptd_product表里103的相框产品
				for(ProductVO pd: pmodelList){
					productName=pd.getName();
					//查找当前公司是否有此产品
					map1.put("qyid", qyid);
					map1.put("name", productName);
					oldProduct=productService.SelectProductByName(map1);
					//根据产品id去查找产品规格表的信息,如不同尺寸的信息,获取上级产品的信息 上级的产品规格103
					List<Specvalue> pdSList =specvalueService.findUniqueByPid(pd.getID().longValue());
					if(oldProduct==null)
					{//新增产品
						//查询产品附加信息
						Productinfo productinfoT= new Productinfo();
						productinfoT.setProductid(pd.getID());
						productinfoT = productinfoService.findProductinfoByPId(productinfoT);//获得产品详情信息
						//保存产品
						pd.setQyid(qyid);
						pd.setID(null);
						pd.setSellcount(0);
						productService.saveProduct(pd);
						//保存产品附加属性
						productinfoT.setProductid(pd.getID());
						productinfoService.saveProductinfo(productinfoT);
						//保存产品规格
						Integer pdID = pd.getID();
						for(Specvalue spec : pdSList) {
							spec.setProductid(pdID.longValue());
							spec.setSalecount(0);
							specvalueService.save(spec);
						}
						
					}
					/*else{//同步旧产品的信息，但不修改价钱和上下架状态  更新当前产品 oldProduct不为空
						oldProduct.setMainimg(pd.getMainimg());
						oldProduct.setScrollimg(pd.getScrollimg());
						productService.updateProduct(oldProduct);//只同步旧产品的图片
						
						Productinfo newproductInfo=new Productinfo();
						Productinfo oldproductInfo=new Productinfo();
						oldproductInfo.setProductid(oldProduct.getID());
						oldproductInfo = productinfoService.findProductinfoByPId(oldproductInfo);//获得非103
						
						newproductInfo.setProductid(pd.getID());
						newproductInfo = productinfoService.findProductinfoByPId(newproductInfo);//获得103的信息
						if(newproductInfo.getBackimg()!=null){
							oldproductInfo.setBackimg(newproductInfo.getBackimg());
						}
						if(newproductInfo.getImg1()!=null){
							oldproductInfo.setImg1(newproductInfo.getImg1());
						}
						if(newproductInfo.getImg2()!=null){
							oldproductInfo.setImg2(newproductInfo.getImg2());
						}
						if(newproductInfo.getImg3()!=null){
							oldproductInfo.setImg3(newproductInfo.getImg3());
						}
						if(newproductInfo.getImg4()!=null){
							oldproductInfo.setImg4(newproductInfo.getImg4());
						}
						if(newproductInfo.getImg5()!=null){
							oldproductInfo.setImg5(newproductInfo.getImg5());
						}
						if(newproductInfo.getTemplateImage()!=null){
							oldproductInfo.setTemplateImage(newproductInfo.getTemplateImage());
						}
						oldproductInfo.setType(newproductInfo.getType());
						if(newproductInfo.getSeriesDescribe()!=null){
						oldproductInfo.setSeriesDescribe(newproductInfo.getSeriesDescribe());
						}
						productinfoService.updateProductinfo(oldproductInfo);//只同步旧产品的图片
						//获取旧的pdt_specvalue表的信息

						List<Specvalue> oldproductSList =specvalueService.findUniqueByPid(Long.valueOf(oldProduct.getID().toString()));
						for(Specvalue oldspec : oldproductSList) {
							//oldspec.setOriginal(original)
							oldspec.setWidth(0);
							oldspec.setHeight(0);
							oldspec.setThumbnail("");
							oldspec.setOriginal("");
							specvalueService.save(oldspec);
						}
						
					}*/
					
				}
			}
		 JsonUtils.renderSuccess(jo);
		}catch(Exception e){
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	@RequestMapping("/addphotofunction")
	@ResponseBody
	public String addphotofunction(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id").trim();
		Company company=companyService.findUniqueById(Long.valueOf(id));
		Integer photoenable=company.getPhotoenable();
		if(photoenable==0){
			JsonUtils.renderoutphot(jo);
			return jo.toString();
		}
		String modelid = SysConstant.DEFAULT_PHOTOFINISHING_ID; // 获得默认冲印商品id
		String catalogid = SysConstant.DEFAULT_PHOTOFINISHING_CATALOG; // 获得默认冲印商品id
		long qyid=company.getId();
		try {
		 Map<String, Object> map1=new HashMap<String, Object>();
			map1.put("qyid", company.getId());
	        map1.put("producttype", "10");//10照片
	        map1.put("status", "1");
	        List<ProductVO> existProducts= productService.FindStoreProductbywhere(map1);
	        if(existProducts.size()>0){
	        	JsonUtils.renderexist(jo);
	        	return jo.toString();
	         }
		 if(!StringUtils.isEmpty(modelid)){
			Long pid = new Long(modelid);//1
			//----------------------------照片冲印<start>------------------------------------------------------
			Product pmodel =productService.findUniqueById(pid);// productMapper.findUniqueById(pid);    // 获得默认冲印产品entity
			Product product = new Product();
			product.setQyid(qyid);
			product.setName(pmodel.getName());
			product.setCatalogid(catalogid);
			product.setStatus(pmodel.getStatus());
			product.setScore(pmodel.getScore());
			product.setSort(pmodel.getSort());
			product.setBdelete(pmodel.getBdelete());
			product.setSpecnameids(pmodel.getSpecnameids());
			product.setIsrecommend("0");
			product.setCreatetime(new Date());
			productService.save(product);
			//productMapper.save(product);
		    //增加对应的规格 
			Long productid = product.getId();
			List<Specvalue> list = specvalueService.findUniqueByPid(pid);//specvalueMapper.findUniqueByPid(pid);// 获得默认冲印产品的规格及价格
			for(Specvalue spec : list) {
				Specvalue svalue = new Specvalue();
				svalue.setProductid(productid);
				svalue.setIsvalid(spec.getIsvalid());
				svalue.setPrice(spec.getPrice());
				svalue.setOldprice(spec.getOldprice());
				svalue.setInventory(spec.getInventory());
				svalue.setIsdefault(spec.getIsdefault());
				svalue.setPlasticprice(spec.getPlasticprice());
				
				svalue.setPlastic(spec.getPlastic());
				svalue.setBdelete(spec.getBdelete());
				if(spec.getProductioncost()!=null){
				  svalue.setProductioncost(spec.getProductioncost());
				}else{
					svalue.setProductioncost(0d);
				}
				svalue.setSalecount(12500);
				svalue.setSize(spec.getSize());
				svalue.setSpecinfo(spec.getSpecinfo());
				specvalueService.save(svalue);
			}
			 JsonUtils.renderSuccess(jo);
		}
		
		}catch(Exception e){
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	@RequestMapping("/addYuepaifunction")
	@ResponseBody
	public String addYuepaifunction(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id").trim();
		Company company=companyService.findUniqueById(Long.valueOf(id));
		Integer outphoto= company.getOutphotoenable();
		if(outphoto==0){
			JsonUtils.renderoutphot(jo);
			return jo.toString();
		}
        
		String cityid=company.getCity().trim();
		City cityModel=provincialService.GetcityModel(Integer.valueOf(cityid));
		List<ServiceProject> serviceProjectList=null;
		
		List<String> entryAboutPat=null;//查询出已经录入约拍的产品标签(具有唯一性)。
		//List<ServiceprojectCompany> beanList=serviceprojectCompanyService.findListByQyid(Integer.valueOf(id));
		try {
			serviceProjectList=yuyueService.getYuyue();
			entryAboutPat=yuyueService.getProductTagByQyid(Integer.valueOf(cityid));
			StoreRule storeRulebean=storeRuleService.GetBean(Integer.valueOf(id));
			if(storeRulebean==null){
				storeRulebean=new StoreRule();
				storeRulebean.setQyid(Integer.valueOf(id));
				storeRulebean.setTimelot(30);
				storeRulebean.setAmstarttime("09:00");
				storeRulebean.setAmendtime("12:00");
				storeRulebean.setPmstarttime("14:00");
				storeRulebean.setPmendtime("18:00");
				storeRulebean.setEvstarttime("19:00");
				storeRulebean.setEvendtime("21:00");
				storeRulebean.setArea(cityModel.getCityname());
				storeRulebean.setAppointcount(1);
				storeRuleService.InsertStorerule(storeRulebean);
			}
			ServiceprojectCompany bean=null;
			JSONObject map = new JSONObject();
			for(ServiceProject model : serviceProjectList){	
				if(entryAboutPat.contains(model.getProductTag()))//如果表中该企业已经存在总店的该商品，则更新。
				{                                                //else否则将对yy_service_project表进行插入操作。
					 //修改约拍产品。
					model.setQyid(Integer.valueOf(id));//设置需要更新记录的企业id。
					yuyueService.updateYuePai(model);
				}else{                                          
					model.setQyid(Integer.valueOf(id));//设置需要更新记录的企业id。
                    yuyueService.saveyuepaiProject(model);
					map.put("projectid", model.getId());
					map.put("qyid", id);
					bean=serviceprojectCompanyService.GetOneProject(map);
					if(bean==null){	
						bean=new ServiceprojectCompany();
						bean.setProjectid(model.getId()); 
						bean.setQyid(Integer.valueOf(id));
						bean.setAddproductmoney(Double.valueOf("200"));
						bean.setMonthsalecount(0);
						bean.setTotalsale(0);
						bean.setOriginalprice(model.getTotalamount());
						bean.setPresentmoney(model.getTotalamount());
						bean.setAppointmentcount(1);
						bean.setShootingtype(model.getShootingtype());
						bean.setUpdownframestatus(model.getUpdownframestatus());
						bean.setArea(cityModel.getCityname());
						serviceprojectCompanyService.SaveprojectCompany(bean);
					}
				}
	         }
			 JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	@RequestMapping(value="/make")
	@ResponseBody
	public String make(HttpServletRequest request) throws Exception {
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		Company company = companyService.findUniqueById(Long.valueOf(id));
		if(company.getCompanytype().equals("10")||company.getCompanytype().equals("50")){
			Product product = productService.findUniqueByQyId(Long.valueOf(id));
			if(product != null){
//				String catalogid= product.getCatalogid();
				
				request.setAttribute("productid", product.getId());
				jo.put("productid", product.getId());
				List<Specvalue> specvalues = specvalueService.findUniqueByPid(product.getId());
				request.setAttribute("specvalues", specvalues);
				jo.put("specvalues", specvalues);
			}
		}else{
			request.setAttribute("specvalues","");
		} 
		JsonUtils.renderSuccess(jo);
		return jo.toString();
	}
	
	@RequestMapping(value="/deletecompany")
	@ResponseBody
	public String deletecompany(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		try {
			companyService.deleteCompany(Long.valueOf(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	@RequestMapping(value="/setstoreproduct")
	@ResponseBody
    public String setstoreproduct(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		try {
			Company company = companyService.findUniqueById(Long.valueOf(id));
			StoreProperty storeProperty=null;
			if(company!=null){
				storeProperty=storePropertyService.Findbycompanyid(company.getId());
			}
			if(company.getHeadoffice()!=company.getId()){
				Company parentCompany=companyService.findUniqueById(company.getHeadoffice());
				company.setCompanyname(parentCompany.getCompanyname());
			}
			if(storeProperty!=null){
				company.setPhotoproduct(storeProperty.getPhotoproduct());
				company.setPhotoalbumproduct(storeProperty.getPhotoalbumproduct());
				company.setPhotoframeproduct(storeProperty.getPhotoframeproduct());
				company.setStandardsum(storeProperty.getStandardsum());
				company.setMemberrate(storeProperty.getMerchantrate());
			}
			
			jo.put("company", company);
			
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	@RequestMapping(value="/findUniqueById")
	@ResponseBody
	public String findUniqueById(HttpServletRequest request) throws Exception {	
		
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		try {
			Company company = companyService.findUniqueById(Long.valueOf(id));
			jo.put("company", company);
			
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	/**
	 * 企业信息查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/jsonlist")
	@ResponseBody
	public String jsonlist(HttpServletRequest request) throws Exception{
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("ucompany.id");
			pagepeper.setOrder(Page.ASC);
		}
		try {
			pagepeper = companyService.search(pagepeper, filterParamMap(request));
			JSONObject jsonData = parseBeanJsonList(pagepeper);
			JsonUtils.renderSuccess(jsonData);
			return jsonData.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@RequestMapping(value="/setCompanyAuth")
	@ResponseBody
	public String setCompanyAuth(HttpServletRequest request)throws Exception{
		JSONObject jo=new JSONObject();
	    String number=request.getParameter("standardsum");
		try{
			JsonUtils.renderSuccess(jo);
		}catch(Exception e){
			e.printStackTrace();
			JsonUtils.renderException(jo);
		}
		return jo.toString();
		
	}
	@RequestMapping(value="/savestoremoney")
	@ResponseBody
	public void savestoremoney(HttpServletRequest request)throws Exception{
		JSONObject jo=new JSONObject();
		try{
	      String id1=request.getParameter("id1");
	      String chargemoney=request.getParameter("chargemoney");
	      StoreProperty bean=storePropertyService.Findbycompanyid(Long.valueOf(id1));
	      bean.setChargemoney(Double.valueOf(chargemoney)*100);
	      bean.setTotalmoney(bean.getTotalmoney()+Double.valueOf(chargemoney)*100);
	      bean.setLeftmoney(bean.getLeftmoney()+Double.valueOf(chargemoney)*100);
	      storePropertyService.updatechargeMoney(bean);
	      JsonUtils.renderSuccess(jo);
	 	}catch(Exception e){
			e.printStackTrace();
			JsonUtils.renderException(jo);
		}
	}
	
	@RequestMapping(value="/storeAuthority")
	@ResponseBody
	public void storeAuthority(Company company,HttpServletRequest request)throws Exception{
		Object closeEvaluateAuth=request.getParameter("closeEvaluateAuth");
		JSONObject jo=new JSONObject();
		try{
			if(company.getMemberenable()==null){
				company.setMemberenable(0);
			}
			companyService.setstoreauthority(company);
			StoreProperty storeProperty=new StoreProperty();
			storeProperty.setCompanyid(company.getId());
			storeProperty.setStandardsum(company.getStandardsum());
			storeProperty.setPhotoproduct(company.getPhotoproduct());
			storeProperty.setPhotoframeproduct(company.getPhotoframeproduct());
			storeProperty.setPhotoalbumproduct(company.getPhotoalbumproduct());
			storeProperty.setMerchantrate(company.getMemberrate());
			//新增关闭隐藏评价的权限。
			if(closeEvaluateAuth!=null){
				storeProperty.setCloseEvaluateAuth(1);	//1表示有权限。
			}else{
				storeProperty.setCloseEvaluateAuth(0); //0表示没用权限。
			}
			storePropertyService.setauthority(storeProperty);
			if(company.getIsHeadStore()==0){
				if(company.getHeadoffice()!=null){
				companyService.updateMainStore(company);
				}
			}
			companyService.updatememberrate(company);
			JsonUtils.renderSuccess(jo);
		}catch(Exception e){
			e.printStackTrace();
			JsonUtils.renderException(jo);
		}
	}
	@RequestMapping(value="/save")
	@ResponseBody
	public String save(Company company, HttpServletRequest request) throws Exception {	
		JSONObject jo = new JSONObject();
		try {
			Boolean isAdd = company.getId()==null;
			if("40".equals(company.getCompanytype())){
				company.setDistributid(company.getParentid());
			}
			//当前创建的商户类型为门店或者生产中心
			if("10".equals(company.getCompanytype()) || "50".equals(company.getCompanytype())){
				//商户类型为门店或者生产中心，所属上级ID即为Distributid
				company.setDistributid(company.getParentid());
			}
			//当前创建的商户类型为推广中心
			/*if("20".equals(company.getCompanytype())){
				Company compayTem = companyService.findUniqueById(company.getParentid());
				company.setMerchantid(company.getParentid());
				company.setDistributid(compayTem.getDistributid());
			}*/
			/*if("30".equals(company.getCompanytype())){
				Company compayTem = companyService.findUniqueById(company.getParentid());
				company.setPrometerid(company.getParentid());
				company.setMerchantid(compayTem.getMerchantid());
				company.setDistributid(compayTem.getDistributid());
			}*/
			String code=company.getCode();
			
			if(isAdd){
				company.setCreatetime(new Date());
				Company oldmodel=companyService.FindCompanybyCode(code);
				if(oldmodel!=null){
					JsonUtils.storeexist(jo);
					return jo.toString();
				}
				if(company.getId()==null){
				companyService.InsertNewCompany(company);
				}else{
					companyService.updatecompany(company);
				}
				//companyService.saveNew(company);         // 保存公司信息
			}else{
				if(company.getId()==null){
					companyService.InsertNewCompany(company);
				}else{
					companyService.updatecompany(company);
				}
				
				//companyService.save(company); 
			}
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	/**
     * 保存商品规格
     * @return
     */
	@RequestMapping(value="savespecstmp")
	@ResponseBody
    public String savespecstmp(HttpServletRequest request) throws Exception  {
		String[] specid = request.getParameterValues("specid");
		String[] oldprices = request.getParameterValues("oldprices");
		
		JSONObject jo = new JSONObject();
    	try {
    		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    		for (int i = 0; i < specid.length; i++) {
    			Map<String,Object> map = new HashMap<String,Object>();
    			map.put("id", specid[i]);
    			if(StringUtils.isEmpty(oldprices[i])){
    				map.put("productioncost", 0);
    			}
    			map.put("productioncost", oldprices[i]);
    			
    			list.add(map);
    		}
    		specvalueService.updateBatch(list);
    		JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.renderException(jo);
		}
    	return jo.toString();
    }
	
	/**
	 * 构建模糊查询条件及验证
	 */
	private Map<String, Object> filterParamMap(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String companyname = request.getParameter("companyname");
		if(!StringUtils.isEmpty(companyname)){
			paramMap.put("companyname", companyname);
		}
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)){
			paramMap.put("id", id);
		}
		String code = request.getParameter("code");
		if(!StringUtils.isEmpty(code)){
			paramMap.put("code", code);
		}
		String companytype = request.getParameter("companytype");
		if(!StringUtils.isEmpty(companytype)){
			paramMap.put("companytype", companytype);
		}
		
		String province = request.getParameter("province");
		if(!StringUtils.isEmpty(province)){
			paramMap.put("province", province);
		}
		String city = request.getParameter("city");
		if(!StringUtils.isEmpty(city)){
			paramMap.put("city", city);
		}
		String area = request.getParameter("area");
		if(!StringUtils.isEmpty(area)){
			paramMap.put("area", area);
		}
		String mobile = request.getParameter("mobile");
		if(!StringUtils.isEmpty(mobile)){
			paramMap.put("mobile", mobile);
		}
		String phone = request.getParameter("phone");
		if(!StringUtils.isEmpty(phone)){
			paramMap.put("phone", phone);
		}
		String email = request.getParameter("email");
		if(!StringUtils.isEmpty(email)){
			paramMap.put("email", email);
		}
		String address = request.getParameter("address");
		if(!StringUtils.isEmpty(address)){
			paramMap.put("address", address);
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
	
	/**
	 * 构建返回参数
	 */
	private JSONObject parseBeanJsonList(Page<Map<String, Object>> page){
		JSONArray jsonArg = new JSONArray();
		JSONObject jsonData = new JSONObject();
		for(Map<String, Object> bean: page.getResult()) {
			JSONObject map = new JSONObject();
			map.put("checkbox", "<input type='checkbox' >");
			map.put("id", bean.get("id"));
			map.put("companyname", bean.get("companyname"));
			map.put("license", bean.get("license"));
			map.put("licenseimg", bean.get("licenseimg"));
			map.put("address", bean.get("address"));
			map.put("email", bean.get("email"));
			map.put("website", bean.get("website"));
			map.put("createtime", bean.get("createtime").toString());
			map.put("mobile", bean.get("mobile"));
			map.put("phone", bean.get("phone"));
			map.put("sdesc", bean.get("sdesc"));
			map.put("code", bean.get("code"));
			map.put("preferential", bean.get("preferential"));
			map.put("provincePostage", bean.get("provincePostage"));
			map.put("cityPostage", bean.get("cityPostage"));
			map.put("nationPostage", bean.get("nationPostage"));
			map.put("parentid", bean.get("parentid"));
			map.put("tuiguangurl", bean.get("tuiguangurl"));
			map.put("provincename", bean.get("provincename"));
			map.put("cityname", bean.get("cityname"));
			map.put("areaname", bean.get("districtname"));
			if(bean.get("companytype")!=null&&!bean.get("companytype").equals("")){
				switch(Integer.valueOf(bean.get("companytype").toString())){
//				switch("2"){
				 case 10 :map.put("companytype", "区域制作中心");break;
				 case 20 :map.put("companytype", "推广中心");break;
				 case 30 :map.put("companytype", "推广员");break;
				 case 40 :map.put("companytype", "经销商");break;
				 case 50:map.put("companytype", "门店");break;
				 default:map.put("companytype", "");break;
				}
				if(!bean.get("companytype").equals("30")){
					map.put("tuiguangurl",bean.get("tuiguangurl"));
				} else{
					map.put("tuiguangurl","");
				}
			} else{
				map.put("tuiguangurl", "");
			}
			/*map.put("detail", "<a name='companydetail' href='javascript:void(0);' onclick = 'aClick("+bean.get("id")+")'>详情</a>");
			map.put("delete", "<a name='companydelete' href='javascript:void(0);' onclick = 'dClick("+bean.get("id")+")'>删除</a>");
			*/
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	
	@RequestMapping(value = "exportExcel")//TODO 列表页导出
	public String exportExcel(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("octets/stream"); 
		response.setContentType("applicationnd.ms-excel");  
		try {
			String bName = "企业信息表.xls";
			String s = new String(bName.getBytes("UTF-8"),"ISO8859-1");
			response.setHeader("Content-Disposition", "attach; filename="+s);
			
			String page = request.getParameter("page");
			String rows = request.getParameter("rows");
			
			Map<String, Object> paramMap = filterParamMap(request);
			Long count = companyService.countTotles(paramMap);
			Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
			pagepeper.setPageNo(1);
			pagepeper.setPageSize(count.intValue());
			if(!pagepeper.isOrderBySetted()){
				pagepeper.setOrderBy("ucompany.id");
				pagepeper.setOrder(Page.ASC);
			}
			pagepeper = companyService.search(pagepeper, paramMap);
			HSSFWorkbook exportExcel = exportExcel(pagepeper.getResult());
			OutputStream out = response.getOutputStream();
			try {
				exportExcel.write(out);
				System.out.println("导出成功。。。。。。");
			} catch (Exception e) { 
				e.printStackTrace();
			}finally{
				out.close();
			}
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}
	
	public HSSFWorkbook exportExcel(List<Map<String, Object>> list) {
		//声明一个工作簿  
        HSSFWorkbook workbook = new HSSFWorkbook();  
        //生成一个表格  
        HSSFSheet sheet = workbook.createSheet("企业信息表");  
        //设置表格默认列宽度为15个字符  
        sheet.setDefaultColumnWidth(16);  
        sheet.setColumnWidth(6, 20*350);
        //生成一个样式，用来设置标题样式  
        HSSFCellStyle style = workbook.createCellStyle();  
        //设置这些样式     
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        
        //生成一个字体  
        HSSFFont font = workbook.createFont();  
        font.setColor(HSSFColor.BLACK.index);  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        font.setFontHeightInPoints((short) 30);
        
        //把字体应用到当前的样式  
        style.setFont(font);  
        HSSFFont fontt = workbook.createFont();  
        fontt.setColor(HSSFColor.BLACK.index);  
        fontt.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        
        HSSFFont headerFont1 = (HSSFFont) workbook.createFont();  
        headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//      cell4.setCellStyle(stylejiacu);加粗样式
        HSSFCellStyle stylejiacu = workbook.createCellStyle();  
        stylejiacu.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        stylejiacu.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        stylejiacu.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        stylejiacu.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        stylejiacu.setFont(headerFont1);
        stylejiacu.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        stylejiacu.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        
        // 生成并设置另一个样式,用于设置内容样式  
        HSSFCellStyle style2 = workbook.createCellStyle();  
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        // 生成另一个字体  
        HSSFFont font2 = workbook.createFont();  
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);   
        // 把字体应用到当前的样式  
        style2.setFont(font2); 
        
        //测试样式
        //创建样式
        HSSFCellStyle teststyle = workbook.createCellStyle(); 
        
        teststyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        teststyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        teststyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        teststyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框  
        teststyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        teststyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        //合并列
        CellRangeAddress region1 = new CellRangeAddress(0,0, (short) 0, (short) 24); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列 
        sheet.addMergedRegion(region1);
        //创建行
        HSSFRow row = sheet.createRow(0);
        //创建列
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        
        HSSFCell cellbt1 = row.createCell(1);
        cellbt1.setCellStyle(style);
        HSSFCell cellbt2 = row.createCell(2);
        cellbt2.setCellStyle(style);
        HSSFCell cellbt3 = row.createCell(3);
        cellbt3.setCellStyle(style);
        HSSFCell cellbt4 = row.createCell(4);
        cellbt4.setCellStyle(style);
        HSSFCell cellbt5 = row.createCell(12);
        cellbt5.setCellStyle(style);
        cell.setCellStyle(style);
        cell.setCellValue("企业信息表"); 
        row.setHeightInPoints(50);
        
        HSSFCellStyle styleHH = workbook.createCellStyle();
        styleHH.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        styleHH.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        styleHH.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        styleHH.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框   
        styleHH.setWrapText(true);
        //第二行
        HSSFCellStyle teststyle2 = workbook.createCellStyle(); 
        teststyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框  
        teststyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框  
        teststyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框  
        teststyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框   
        teststyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        HSSFRow row2 = sheet.createRow(1);
        
        HSSFCell cell0 = row2.createCell(0);
        cell0.setCellStyle(teststyle); 
        cell0.setCellStyle(stylejiacu);
        cell0.setCellValue("ID"); 
        
        HSSFCell cell1 = row2.createCell(1);
        cell1.setCellStyle(teststyle); 
        cell1.setCellStyle(stylejiacu);
        cell1.setCellValue("公司名称"); 
        
        HSSFCell cell2 = row2.createCell(2);
        cell2.setCellStyle(teststyle); 
        cell2.setCellStyle(stylejiacu);
        cell2.setCellValue("登录名"); 
        
        HSSFCell cell3= row2.createCell(3);
        cell3.setCellStyle(teststyle); 
        cell3.setCellStyle(stylejiacu);
        cell3.setCellValue("省份"); 
        
        HSSFCell cell4 = row2.createCell(4);
        cell4.setCellStyle(teststyle); 
        cell4.setCellStyle(stylejiacu);
        cell4.setCellValue("市/区"); 
        
        HSSFCell cell5 = row2.createCell(5);
        cell5.setCellStyle(teststyle); 
        cell5.setCellStyle(stylejiacu);
        cell5.setCellValue("区/县"); 
        
        HSSFCell cell6 = row2.createCell(6);
        cell6.setCellStyle(teststyle); 
        cell6.setCellStyle(stylejiacu);
        cell6.setCellValue("公司地址"); 
        
        HSSFCell cell7 = row2.createCell(7);
        cell7.setCellStyle(teststyle); 
        cell7.setCellStyle(stylejiacu);
        cell7.setCellValue("公司邮箱"); 
        
        HSSFCell cell8 = row2.createCell(8);
        cell8.setCellStyle(teststyle); 
        cell8.setCellStyle(stylejiacu);
        cell8.setCellValue("银行账号"); 
        
        HSSFCell cell9= row2.createCell(9);
        cell9.setCellStyle(teststyle); 
        cell9.setCellStyle(stylejiacu);
        cell9.setCellValue("注册时间"); 
        
        HSSFCell cell10 = row2.createCell(10);
        cell10.setCellStyle(teststyle); 
        cell10.setCellStyle(stylejiacu);
        cell10.setCellValue("手机号"); 
        
        HSSFCell cell11 = row2.createCell(11);
        cell11.setCellStyle(teststyle); 
        cell11.setCellStyle(stylejiacu);
        cell11.setCellValue("联系电话"); 
        
        HSSFCell cell12 = row2.createCell(12);
        cell12.setCellStyle(teststyle); 
        cell12.setCellStyle(stylejiacu);
        cell12.setCellValue("省内费用"); 
        
        HSSFCell cell13 = row2.createCell(13);
        cell13.setCellStyle(teststyle); 
        cell13.setCellStyle(stylejiacu);
        cell13.setCellValue("市内费用"); 
        
        HSSFCell cell14 = row2.createCell(14);
        cell14.setCellStyle(teststyle); 
        cell14.setCellStyle(stylejiacu);
        cell14.setCellValue("全国费用"); 
        
        HSSFCell cell15= row2.createCell(15);
        cell15.setCellStyle(teststyle); 
        cell15.setCellStyle(stylejiacu);
        cell15.setCellValue("包邮费用"); 
        
        HSSFCell cell16 = row2.createCell(16);
        cell16.setCellStyle(teststyle); 
        cell16.setCellStyle(stylejiacu);
        cell16.setCellValue("所属公司"); 
        
        HSSFCell cell17 = row2.createCell(17);
        cell17.setCellStyle(teststyle); 
        cell17.setCellStyle(stylejiacu);
        cell17.setCellValue("商户类型"); 
        
        HSSFCell cell18 = row2.createCell(18);
        cell18.setCellStyle(teststyle); 
        cell18.setCellStyle(stylejiacu);
        cell18.setCellValue("推广URL"); 
        
        HSSFCell cell19 = row2.createCell(19);
        cell19.setCellStyle(teststyle); 
        cell19.setCellStyle(stylejiacu);
        cell19.setCellValue("描述"); 
        
        HSSFCell cell120 = row2.createCell(20);
        cell120.setCellStyle(teststyle); 
        cell120.setCellStyle(stylejiacu);
        cell120.setCellValue("公司网址"); 
        
        HSSFCell cell121 = row2.createCell(21);
        cell121.setCellStyle(teststyle); 
        cell121.setCellStyle(stylejiacu);
        cell121.setCellValue("是否授权生产"); 
        
        HSSFCell cell122 = row2.createCell(22);
        cell122.setCellStyle(teststyle); 
        cell122.setCellStyle(stylejiacu);
        cell122.setCellValue("商户权限");
        
        HSSFCell cell123 = row2.createCell(23);
        cell123.setCellStyle(teststyle); 
        cell123.setCellStyle(stylejiacu);
        cell123.setCellValue("返佣比例");
        
        for (int i = 0; i < list.size(); i++) {
        	Map<String, Object> listin = list.get(i);
        	
        	HSSFRow rowInfo = sheet.createRow(2+i);
        	
        	HSSFCell cell_data0 = rowInfo.createCell(0);
            cell_data0.setCellStyle(teststyle); 
            cell_data0.setCellValue(listin.get("id").toString()); 
            
            HSSFCell cell_data1 = rowInfo.createCell(1);
            cell_data1.setCellStyle(teststyle); 
            cell_data1.setCellValue(listin.get("companyname").toString()); 
            
            HSSFCell cell_data2 = rowInfo.createCell(2);
            cell_data2.setCellStyle(teststyle); 
            cell_data2.setCellValue(listin.get("code").toString()); 
            
            HSSFCell cell_data3= rowInfo.createCell(3);
            cell_data3.setCellStyle(teststyle); 
            cell_data3.setCellValue(listin.get("provincename")==null?"":listin.get("provincename").toString()); 
            
            HSSFCell cell_data4 = rowInfo.createCell(4);
            cell_data4.setCellStyle(teststyle); 
            cell_data4.setCellValue(listin.get("cityname")==null?"":listin.get("cityname").toString()); 
            
            HSSFCell cell_data5 = rowInfo.createCell(5);
            cell_data5.setCellStyle(teststyle); 
            cell_data5.setCellValue(listin.get("districtname")==null?"":listin.get("districtname").toString()); 
            
            HSSFCell cell_data6 = rowInfo.createCell(6);
            cell_data6.setCellStyle(teststyle); 
            cell_data6.setCellValue(listin.get("address")==null?"":listin.get("address").toString()); 
            
            HSSFCell cell_data7 = rowInfo.createCell(7);
            cell_data7.setCellStyle(teststyle); 
            cell_data7.setCellValue(listin.get("email")==null?"":listin.get("email").toString()); 
            
            HSSFCell cell_data8 = rowInfo.createCell(8);
            cell_data8.setCellStyle(teststyle); 
            cell_data8.setCellValue(listin.get("website")==null?"":listin.get("website").toString()); 
            
            HSSFCell cell_data9= rowInfo.createCell(9);
            cell_data9.setCellStyle(teststyle); 
            cell_data9.setCellValue(listin.get("createtime").toString().substring(0, 10)); 
            
            HSSFCell cell_data10 = rowInfo.createCell(10);
            cell_data10.setCellStyle(teststyle); 
            cell_data10.setCellValue(listin.get("mobile")==null?"":listin.get("mobile").toString()); 
            
            HSSFCell cell_data11 = rowInfo.createCell(11);
            cell_data11.setCellStyle(teststyle); 
            cell_data11.setCellValue(listin.get("phone")==null?"":listin.get("phone").toString()); 
            
            HSSFCell cell_data12 = rowInfo.createCell(12);
            cell_data12.setCellStyle(teststyle); 
            cell_data12.setCellValue(listin.get("provincePostage")==null?0.00: (double)listin.get("provincePostage")); 
            
            HSSFCell cell_data13 = rowInfo.createCell(13);
            cell_data13.setCellStyle(teststyle); 
            cell_data13.setCellValue(listin.get("cityPostage")==null?0.00:(double)listin.get("cityPostage")); 
            
            HSSFCell cell_data14 = rowInfo.createCell(14);
            cell_data14.setCellStyle(teststyle); 
            cell_data14.setCellValue(listin.get("nationPostage")==null?0.00:(double)listin.get("nationPostage")); 
            
            HSSFCell cell_data15= rowInfo.createCell(15);
            cell_data15.setCellStyle(teststyle); 
            cell_data15.setCellValue(listin.get("preferential")==null?0.00:(double)listin.get("preferential")); 
            
            HSSFCell cell_data16 = rowInfo.createCell(16);
            cell_data16.setCellStyle(teststyle); 
            //所属公司
            if(listin.get("merchantid") != null && Integer.valueOf(listin.get("merchantid").toString()) != 0){
            	cell_data16.setCellValue(listin.get("merchantid").toString()); 
            }else if(listin.get("prometerid") != null && Integer.valueOf(listin.get("prometerid").toString()) != 0){
            	cell_data16.setCellValue(listin.get("prometerid").toString()); 
            }else if(listin.get("distributid") != null && Integer.valueOf(listin.get("distributid").toString()) != 0){
            	cell_data16.setCellValue(listin.get("distributid").toString()); 
            }else{
            	cell_data16.setCellValue(""); 
            }
            
            HSSFCell cell_data17 = rowInfo.createCell(17);
            cell_data17.setCellStyle(teststyle); 
            //商户类型:10为区域制作中心;20为推广中心;30为推广员;40为经销商;50为门店
            if(Integer.valueOf(listin.get("companytype").toString()) == 10){
            	cell_data17.setCellValue("区域制作中心"); 
            }else if(Integer.valueOf(listin.get("companytype").toString()) == 20){
            	cell_data17.setCellValue("推广中心"); 
            }else if(Integer.valueOf(listin.get("companytype").toString()) == 30){
            	cell_data17.setCellValue("推广员"); 
            }else if(Integer.valueOf(listin.get("companytype").toString()) == 40){
            	cell_data17.setCellValue("经销商"); 
            }else if(Integer.valueOf(listin.get("companytype").toString()) == 50){
            	cell_data17.setCellValue("门店"); 
            }else{
            	cell_data17.setCellValue(""); 
            }
            
            HSSFCell cell_data18 = rowInfo.createCell(18);
            cell_data18.setCellStyle(teststyle); 
            cell_data18.setCellValue(listin.get("tuiguangurl")==null?"":listin.get("tuiguangurl").toString()); 
            
            HSSFCell cell_data19 = rowInfo.createCell(19);
            cell_data19.setCellStyle(teststyle); 
            cell_data19.setCellValue(listin.get("sdesc")==null?"":listin.get("sdesc").toString()); 
            
            //公司网址
            HSSFCell cell_data20 = rowInfo.createCell(20);
            cell_data20.setCellStyle(teststyle); 
            cell_data20.setCellValue(listin.get("website")==null?"":listin.get("website").toString());
            
            //是否授权生产
            HSSFCell cell_data21 = rowInfo.createCell(21);
            cell_data21.setCellStyle(teststyle); 
            if(listin.get("website") != null && "0".equals(listin.get("website").toString())){
            	cell_data21.setCellValue("不可以");
            }else if(listin.get("website") != null && "0".equals(listin.get("website").toString())){
            	cell_data21.setCellValue("可以");
            }else{
            	cell_data21.setCellValue("");
            }
            
            //商户权限
            String strAble = "";
            String strRate = "";
            HSSFCell cell_data22 = rowInfo.createCell(22);
            cell_data22.setCellStyle(teststyle); 
            DecimalFormat    df   = new DecimalFormat("######0.00");   
            if(listin.get("photoenable") != null){
            	if("1".equals(listin.get("photoenable").toString())){
            		strAble += "微传;";
            		strRate += "微传:"+df.format(Double.parseDouble(listin.get("photorate").toString())*100) + "%";
            	}else{
            		strAble += "";
            	}
            }
            if(listin.get("photoframeenable") != null){
            	if("1".equals(listin.get("photoframeenable").toString())){
            		strAble += "相框;";
            		strRate += "相框:"+df.format(Double.parseDouble(listin.get("photoframerate").toString())*100) + "%;";
            	}else{
            		strAble += "";
            	}
            }
            if(listin.get("photoalbumenable") != null){
            	if("1".equals(listin.get("photoalbumenable").toString())){
            		strAble += "相册;";
            		strRate += "相册:"+df.format(Double.parseDouble(listin.get("photoalbumrate").toString())*100) + "%;";
            	}else{
            		strAble += "";
            	}
            }
            cell_data22.setCellValue(strAble);
            
            //返佣比例
            HSSFCell cell_data23 = rowInfo.createCell(23);
            cell_data23.setCellStyle(teststyle); 
            cell_data23.setCellValue(strRate);
            
		}
        
        return workbook;
	}
	
	@RequestMapping(value="/findheadStore")
	@ResponseBody
	public String findheadStore(String name) throws Exception{
		JSONObject jo = new JSONObject();
		List<Company> companyList = new ArrayList<Company>();
		if(!StringUtil.isNullStr(name)){
			companyList=companyService.findheadStore(name);
		}
        jo.put("companyList", companyList);
		return jo.toString();
	}
	
	@RequestMapping(value="/findParent")
	@ResponseBody
	public String findParent(String id,String companyid) throws Exception {
		//返回对象定义
		JSONObject jo = new JSONObject();
		Company model=null;
		if(!StringUtil.isNullStr(companyid)){
		  model=companyService.findUniqueById(Long.valueOf(companyid));
		}
		List<Company> companyList = new ArrayList<Company>();
		if("40".equals(id)){
			//companyList = companyService.findDistributParent(Constant.HUIMEI_ID);
			if(model!=null)
			companyList = companyService.getdistributParent(model);
		}else if("10".equals(id)){
			//查询区域制作中心和门店所属上级-经销商
			if(model!=null){
			  companyList = companyService.getdistributParent(model);
			}else{
				companyList = companyService.findMerchantParent(Constant.HUIMEI_ID);
			}
		}else if("20".equals(id)){
			//companyList = companyService.findPrometerParent(Constant.HUIMEI_ID);
			if(model!=null)
			companyList=companyService.getStoreParent(model);
		}else if("30".equals(id)){
			//companyList = companyService.findPrometerCenter(Constant.HUIMEI_ID);
			if(model!=null)
			companyList = companyService.getprometerCenter(model);
		}else if("50".equals(id)){
			if(model!=null){
			  companyList = companyService.getdistributParent(model);
			}else{
			  companyList = companyService.findDistributParent(Constant.HUIMEI_ID);
			}
		}else{
			companyList = companyService.findDistributParent(Constant.HUIMEI_ID);
		}
		
		jo.put("companyList", companyList);
		
		return jo.toString();
	}
}
