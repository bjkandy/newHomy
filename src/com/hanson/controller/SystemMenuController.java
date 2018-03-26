package com.hanson.controller;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Page;
import com.hanson.model.Authorities;
import com.hanson.model.Company;
import com.hanson.model.Parameter;
import com.hanson.model.Roles;
import com.hanson.model.RolesAuthorities;
import com.hanson.model.Users;
import com.hanson.service.AuthoritiesService;
import com.hanson.service.CompanyService;
import com.hanson.service.ParameterService;
import com.hanson.service.RolesService;
import com.hanson.service.UsersRolesService;
import com.hanson.service.UsersService;
import com.hanson.util.Constant;
import com.hanson.util.MD5Util;

@Controller
@RequestMapping("/system")
public class SystemMenuController {

	@Autowired
	private UsersService usersService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private AuthoritiesService authoritiesService;
	@Autowired
	private ParameterService parameterService;
	@Autowired
	private UsersRolesService usersRolesService;
	@Autowired
	private CompanyService companyService;
	
	
	
	
	/**
	 * 用户管理初始列表
	 * @param users
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/usersList")
	@ResponseBody
	public String list(Users users,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		request.getParameterNames();
		Page<Map<String, Object>> pagepeper = new Page<Map<String, Object>>();
		pagepeper.setPageNo(Integer.valueOf(page));
		pagepeper.setPageSize(Integer.valueOf(rows));
		if(!pagepeper.isOrderBySetted()){
			pagepeper.setOrderBy("id");
			pagepeper.setOrder(Page.ASC);
		}
		try {
			pagepeper = usersService.findUsersPage(pagepeper, filterParamMap(request));
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
		String login_name = request.getParameter("login_name");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		if(!StringUtils.isEmpty(login_name)){
			paramMap.put("login_name", login_name);
		}
		if(!StringUtils.isEmpty(name)){
			paramMap.put("name", name);
		}
		if(!StringUtils.isEmpty(email)){
			paramMap.put("email", email);
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
			map.put("qyid", bean.get("qyid"));
			map.put("login_name", bean.get("login_name"));
			map.put("name", bean.get("name"));
			map.put("email", bean.get("email"));
			map.put("roleName", bean.get("roleName"));
			jsonArg.add(map);
		}
		jsonData.put("rows", jsonArg);
		jsonData.put("total", page.getTotalCount());	
		return jsonData;
	}
	
	/**
	 * 保存用户
	 * @param users
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveUsers")
	public String saveUsers(Users users, HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject jo = new JSONObject();
		try {
			users.setPassword(MD5Util.string2MD5(users.getPassword()));
			usersService.saveUsers(users);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		PrintWriter out = response.getWriter();
		out.write(jo.toString());
		return null;
	}
	
	/**
	 * 条件查询
	 * @param users
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findUsersByCondition")
	public String findUsersByCondition(Users users,HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		//条件查询
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		try {
			List<Users> list = usersService.findUsers(users);
			jo.put("users", list);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			e.printStackTrace();
			JsonUtils.renderException(jo);
		}
		
		PrintWriter out = response.getWriter();
		out.write(jo.toString());
		return null;
	}
	
	@RequestMapping(value="/updateUsers")
	public String updateUsers(Users users, HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject jo = new JSONObject();
		
		try {
			//更新用户表
			usersService.updateUsers(users);
			//更新企业信息表中的用户名
			Company company = new Company();
			company.setId(users.getQyid());
			company.setCode(users.getLogin_name());
			company.setCompanyname(users.getName());
			company.setEmail(users.getEmail());
			companyService.save(company);
			
			//删除用户角色表记录
			usersRolesService.deleteByUserId(users);
			//插入当前用户角色
			usersRolesService.saveUsersRoles(users);
			
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		PrintWriter out = response.getWriter();
		out.write(jo.toString());
		return null;
	}
	
	@RequestMapping(value="/updatePassword")
	public String updatePassword(Users users, HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setContentType("text/plain; charset=utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject jo = new JSONObject();
		
		try {
			users.setPassword(MD5Util.string2MD5(users.getPassword()));
			usersService.updatePassword(users);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		PrintWriter out = response.getWriter();
		out.write(jo.toString());
		return null;
	}
	
	/**
	 * 角色列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/rolesList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String rolesList(Roles roles, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//条件查询
		List<Roles> list = rolesService.findRoles(roles);
		JSONArray jsonyy =JSONArray.fromObject(list);
		return jsonyy.toString();
	}
	
	/**
	 * 新增角色
	 * @param roles
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveRoles", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveRoles(Roles roles, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			rolesService.saveRoles(roles);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	/**
	 * 根据ID查询角色
	 * @param roles
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findRolesById", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findRolesById(Roles roles, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			Roles rolesR = rolesService.findRolesById(roles);
			jo.put("roles", rolesR);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	/**
	 * 修改角色
	 * @param roles
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateRoles", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateRoles(Roles roles, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			rolesService.updateRoles(roles);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	/**
	 * 根据ID删除角色
	 * @param roles
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/delteRolesById", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String delteRolesById(Roles roles, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			rolesService.deleteRolesById(roles);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	
	@RequestMapping(value="/authoritiesList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String authoritiesList(Authorities authorities, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			List<Authorities> authoritiesList = authoritiesService.findAuthoritiesList(authorities);
			
			jo.put("authoritiesList", authoritiesList);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	
	/**
	 * 获取用户当前权限项
	 * @param roles
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getAuthorities", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthorities(Roles roles, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			//查询某一角色拥有的权限
			List<Authorities> authoritiesList = rolesService.findAuthorities(roles);
			jo.put("authoritiesList", authoritiesList);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
		}
		return jo.toString();
	}
	
	/**
	 * 保存角色权限设置
	 * @param roles
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveRolesAuthorities", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveRolesAuthorities(String authorityIds, Roles roles, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			//删除原有角色的权限
			rolesService.deleteRolesAuthorities(roles);
			
			//新增当前角色的权限
			String[] sourceStrArray = authorityIds.split(",");
			List<RolesAuthorities> rolesAuthoritiesList = new ArrayList<RolesAuthorities>();
			if(sourceStrArray != null && sourceStrArray.length > 0){
				for(int i = 0; i < sourceStrArray.length; i++){
					if(StringUtils.isNotBlank(sourceStrArray[i])){
						RolesAuthorities rolesAuthorities = new RolesAuthorities();
						rolesAuthorities.setRole_id(roles.getId());
						rolesAuthorities.setAuthority_id(Long.parseLong(sourceStrArray[i]));
						rolesAuthoritiesList.add(rolesAuthorities);
					}
					
				}
			}
			rolesService.saveRolesAuthorities(rolesAuthoritiesList);
			
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	/**
	 * 新增权限项
	 * @param authorityIds
	 * @param roles
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveAuthorities", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveAuthorities(Authorities authorities, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			//新增当前角色的权限
			//默认值
			authoritiesService.saveAuthorities(authorities);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	/**
	 * 编辑权限
	 * @param authorities
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/updateAuthorities", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateAuthorities(Authorities authorities, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			//编辑权限
			authoritiesService.updateAuthorities(authorities);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	/**
	 * 删除权限
	 * @param authorities
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAuthorities", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteAuthorities(Authorities authorities, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			//编辑权限
			authoritiesService.deleteAuthorities(authorities);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	/**
	 * 根据ID查询权限项
	 * @param authorities
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/findAuthoritiesById", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String findAuthoritiesById(Authorities authorities, HttpServletRequest request, HttpServletResponse response) throws Exception{
		JSONObject jo = new JSONObject();
		try {
			//编辑权限
			Authorities authoritiesR = authoritiesService.findAuthoritiesById(authorities);
			jo.put("authorities", authoritiesR);
			JsonUtils.renderSuccess(jo);
		} catch (Exception e) {
			JsonUtils.renderException(jo);
			e.printStackTrace();
		}
		return jo.toString();
	}
	
	/**
	 * 用户管理页面跳转
	 * @return
	 */
	@RequestMapping("/users")
	public String yuyue(){
		return "system/users";
	}
	
	/**
	 * 角色管理页面跳转
	 * @return
	 */
	@RequestMapping("/roles")
	public String roles(Authorities authorities, HttpServletRequest req, HttpServletResponse resp, Model model){
		//查询权限列表
		List<Authorities> authoritiesList = authoritiesService.findAuthoritiesList(authorities);
		model.addAttribute("authoritiesList", authoritiesList);
		
		return "system/roles";
	}
	
	/**
	 * 权限项管理跳转
	 * @return
	 */
	@RequestMapping("/item")
	public String item(Authorities authorities, HttpServletRequest req, HttpServletResponse resp, Model model){
		//获取权限列表
		List<Authorities> authoritiesList = authoritiesService.findAuthoritiesList(authorities);
		//获取根目录（权限）
		List<Authorities> authoritiesRootList = authoritiesService.findAuthoritiesRoot(authorities);
		//获取权限类型
		Parameter parameter = new Parameter();
		parameter.setPcid(Constant.RESOURCE_TYPE);
		List<Parameter> parameterList = parameterService.findParameters(parameter);
		
		model.addAttribute("authoritiesList", authoritiesList);
		model.addAttribute("authoritiesRootList", authoritiesRootList);
		model.addAttribute("parameterList", parameterList);
		
		
		return "system/item";
	} 
	
	@RequestMapping("comment")
	public String comment()
	{
		return "comment/comment";
	}
	
	@RequestMapping("commentConfiguration")
	public String commentConfiguration()
	{
		return "comment/commentConfiguration";
	}
	
}
