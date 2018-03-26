package com.hanson.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hanson.model.Users;

public class LoginHandlerIntercepter implements HandlerInterceptor {  
      
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object arg2, Exception arg3)  
            throws Exception {  
    }  

    @Override  
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,  
            Object arg2, ModelAndView arg3) throws Exception {  

    }  
    @Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse resp,  
            Object arg2) throws Exception { 
    	String requestURI = request.getRequestURI();  
    	//不拦截登录验证
        if(requestURI.indexOf("system")>0){  
            //说明处在编辑的页面  
            HttpSession session = request.getSession();  
            Users users = (Users) session.getAttribute("usersInfo");  
            if(users!=null){  
                //登陆成功的用户  
                return true;  
            }else{  
               //没有登陆，转向登陆界面  
               resp.sendRedirect("../index.jsp");
               return false;  
            }  
        }else{  
            return true;  
        }
           
           
    }  

}  