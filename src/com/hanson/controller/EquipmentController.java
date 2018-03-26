package com.hanson.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanson.common.util.Page;
import com.hanson.model.Equipment;
import com.hanson.service.EquipmentService;
import com.hanson.util.DateTimeUtil;

@RequestMapping("equipment")
@Controller
public class EquipmentController{
	
	@Autowired
	private EquipmentService equipmentService;

	@RequestMapping(value = "pagingQuery",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String pagingQuery(Equipment equipment,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("id");
			pagepeper.setOrder(Page.ASC);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(equipment.getId()!=null && equipment.getId()!=0 ){
			map.put("id", equipment.getId());
		}
		if(equipment.getQyid()!=null && equipment.getQyid()!=0){
			map.put("qyid", equipment.getQyid());
		}
		if(equipment.getEquipmentname()!=null && equipment.getEquipmentname()!=""){
			map.put("equipmentname", equipment.getEquipmentname());
		}
		if(equipment.getProvincename()!=null && !equipment.getProvincename().equals("--请选择省份--")){
			map.put("provincename", equipment.getProvincename());
		}
		if(equipment.getCityname()!=null && !equipment.getCityname().equals("--请选择市/区--")){
			map.put("cityname", equipment.getCityname());
		}
		if(equipment.getDistrictname()!=null && !equipment.getDistrictname().equals("--请选择区/县--")){
			map.put("districtname", equipment.getDistrictname());
		}
		if(equipment.getCreatetimeStart()!=null){
			map.put("createtimeStart", equipment.getCreatetimeStart());
		}
		if(equipment.getCreatetimeEnd()!=null){
			map.put("createtimeEnd", equipment.getCreatetimeEnd());
		}
		
		pagepeper = equipmentService.pagingQuery(pagepeper, map);
		
		JSONObject resultObje = parseBeanJsonList(pagepeper);
		
		System.out.println(resultObje.toString());
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		return resultObje.toString();
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
			map.put("qyid", bean.get("qyid"));
			map.put("equipmentname", bean.get("equipmentname"));
			map.put("provincename", bean.get("provincename"));
			map.put("cityname", bean.get("cityname"));
			map.put("districtname", bean.get("districtname"));
			map.put("address", bean.get("address"));
			
			map.put("qrcode", bean.get("qrcode"));
			//map.put("equipmentspec", bean.get("equipmentspec"));
			map.put("openstoc", bean.get("openstoc"));
			map.put("createtime",DateTimeUtil.toDateTime(bean.get("createtime").toString()));
			map.put("stocknum", bean.get("stocknum"));
			map.put("bdelete", bean.get("bdelete"));
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		jsonData.put("pagesize", page.getTotalPages());
		jsonData.put("prepage", page.getPrePage());
		jsonData.put("nextpage", page.getNextPage());
		jsonData.put("pageno", page.getPageNo());
		return jsonData;
	}
	
	@RequestMapping("/out")
	public String out(){
		return "autoPrint/autoPrint";
	}
	
	@RequestMapping("/add")
	public String add(Integer id,Model model,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response){
		if(id!=null && id != 0){
			Equipment equipment = equipmentService.get(id);
			modelMap.addAttribute("equipment", equipment);
		}
		return "autoPrint/autoEdit";
	}
	
	/**
	 * 保存设备
	 * @param equipment
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("insert")
	@ResponseBody
	public String insert(Equipment equipment,HttpServletRequest request,HttpServletResponse response){
		System.out.println(equipment);
		JSONObject size5Obj = new JSONObject();
		JSONObject size6Obj = new JSONObject();
		JSONObject size7Obj = new JSONObject();
		JSONObject size8Obj = new JSONObject();
		JSONObject rsize5Obj = new JSONObject();
		JSONObject rsize6Obj = new JSONObject();
		JSONObject rsize7Obj = new JSONObject();
		JSONObject rsize8Obj = new JSONObject();
		JSONArray specArray = new JSONArray();
		if (equipment.getSize5().length == 4) {
			size5Obj.put("specname", "光面");
			size5Obj.put("isvalid", 0);
			size5Obj.put("specsize", equipment.getSize5()[0]);
			size5Obj.put("price", equipment.getSize5()[1]);
			size5Obj.put("socknum", equipment.getSize5()[3]);
			specArray.add(size5Obj);
		}
		if (equipment.getSize6().length == 4) {
			size6Obj.put("specname", "光面");
			size6Obj.put("isvalid", 0);
			size6Obj.put("specsize", equipment.getSize6()[0]);
			size6Obj.put("price", equipment.getSize6()[1]);
			size6Obj.put("socknum", equipment.getSize6()[3]);
			specArray.add(size6Obj);
		}
		if (equipment.getSize7().length == 4) {
			size7Obj.put("specname", "光面");
			size7Obj.put("isvalid", 0);
			size7Obj.put("specsize", equipment.getSize7()[0]);
			size7Obj.put("price", equipment.getSize7()[1]);
			size7Obj.put("socknum", equipment.getSize7()[3]);
			specArray.add(size7Obj);
		}
		if (equipment.getSize8().length == 4) {
			size8Obj.put("specname", "光面");
			size8Obj.put("isvalid", 1);
			size8Obj.put("specsize", equipment.getSize8()[0]);
			size8Obj.put("price", equipment.getSize8()[1]);
			size8Obj.put("socknum", equipment.getSize8()[3]);
			specArray.add(size8Obj);
		}
		
		if (equipment.getRsize5().length == 4) {
			rsize5Obj.put("specname", "绒面");
			rsize5Obj.put("isvalid", 0);
			rsize5Obj.put("specsize", equipment.getRsize5()[0]);
			rsize5Obj.put("price", equipment.getRsize5()[1]);
			rsize5Obj.put("socknum", equipment.getRsize5()[3]);
			specArray.add(rsize5Obj);
		}
		if (equipment.getRsize6().length == 4) {
			rsize6Obj.put("specname", "绒面");
			rsize6Obj.put("isvalid", 0);
			rsize6Obj.put("specsize", equipment.getRsize6()[0]);
			rsize6Obj.put("price", equipment.getRsize6()[1]);
			rsize6Obj.put("socknum", equipment.getRsize6()[3]);
			specArray.add(rsize6Obj);
		}
		if (equipment.getRsize7().length == 4) {
			rsize7Obj.put("specname", "绒面");
			rsize7Obj.put("isvalid", 0);
			rsize7Obj.put("specsize", equipment.getRsize7()[0]);
			rsize7Obj.put("price", equipment.getRsize7()[1]);
			rsize7Obj.put("socknum", equipment.getRsize7()[3]);
			specArray.add(rsize7Obj);
		}
		if (equipment.getRsize8().length == 4) {
			rsize8Obj.put("specname", "绒面");
			rsize8Obj.put("isvalid", 0);
			rsize8Obj.put("specsize", equipment.getRsize8()[0]);
			rsize8Obj.put("price", equipment.getRsize8()[1]);
			rsize8Obj.put("socknum", equipment.getRsize8()[3]);
			specArray.add(rsize8Obj);
		}
		
		JSONObject result = new JSONObject();
		try {
			equipment.setEquipmentspec(specArray.toString());
			equipment.setStocknum(Integer.valueOf(equipment.getSize8()[3]));
			if(equipmentService.insertEquipment(equipment) >= 1){
				result.put("success", true);
				result.put("msg", "新增设备成功");
				
				sendGet("http://hmwc-test.homy.cc/wxapi/qrcode/createPrinterQrcode","qyprintid="+ equipment.getQyid() + "_" + equipment.getId());
			} else {
				result.put("success", false);
				result.put("msg", "新增设备失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查看二维码
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("createQrcode")
	@ResponseBody
	public String createQrcode(Integer id,HttpServletRequest request,HttpServletResponse response){
		JSONObject jo = new JSONObject();
		Equipment equipment = equipmentService.get(id);
		jo.put("msg","查看二维码");
		jo.put("url", equipment.getQrcode());
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
}
