package com.hanson.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanson.common.util.JsonUtils;
import com.hanson.common.util.Md5Util;
import com.hanson.model.Authorities;
import com.hanson.model.Users;
import com.hanson.service.AuthoritiesService;
import com.hanson.service.UsersService;

/**
 * 管理系统基础ACTION
 * @author MildYak
 *
 */
@Controller
@RequestMapping("/main")
public class MenageController {
	
	private Log logger = LogFactory.getLog(MenageController.class);
	
	@Autowired
	private AuthoritiesService authoritiesService;
	@Autowired
	private UsersService usersService;
	
	
	
	/**
	 * 跳转到管理后台首页
	 * @param userQueryVo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/index")
	public String list(HttpSession session,HttpServletRequest request, HttpServletResponse resp, Model model) throws Exception{
		//查询用户权限
		Users users = (Users)session.getAttribute("usersInfo");
		if(users != null){
			List<Authorities> authoritiesList = authoritiesService.findAuthoritiesByUserId(users);
			model.addAttribute("authoritiesListR", authoritiesList);
			return "index";
		}else{
			return "redirect:../index.jsp";
		}
		
		
		
	}
	
	/**
	 * 登录验证
	 * @param users
	 * @param request
	 * @param resp
	 * @param model
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginCheck")
	@ResponseBody
	public String loginCheck(Users users, HttpServletRequest request, HttpServletResponse resp, Model model, HttpSession session) throws Exception{
		JSONObject jo = new JSONObject();
	  System.out.print("用户登录.............");
	  logger.debug("login start...............");
		//查询当前用户
	    String pwd=Md5Util.encoderByMd5(users.getPassword());
		users.setPassword(pwd);
		//u.setPassword(Md5Util.encoderByMd5(password));
		List<Users> usersList = usersService.findUsersLogin(users);
		if(usersList != null && usersList.size() == 1){
			//验证通过
			//将用户信息放入session
			logger.debug("login sucessful...............");
			session.setAttribute("usersInfo", usersList.get(0));
			JsonUtils.renderSuccess(jo);
		}else{
			//验证不通过
			//返回错误编码
			JsonUtils.renderException(jo);
		}
		
		return jo.toString();
	}
	
	@RequestMapping(value="/signOut")
	public String signOut(HttpSession session,HttpServletRequest request, HttpServletResponse resp, Model model) throws Exception{
		//清空session中的用户信息
		session.removeAttribute("usersInfo");
		//跳转到登录页面
		
		return "redirect:../index.jsp";
	}
	
}
