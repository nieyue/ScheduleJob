package com.nieyue.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nieyue.bean.Acount;
import com.nieyue.bean.Finance;
import com.nieyue.bean.Role;
import com.nieyue.exception.MySessionException;
import com.nieyue.exception.TestApiException;
import com.nieyue.util.MyDESutil;

/**
 * 用户session控制拦截器
 * @author yy
 *
 */
@Configuration
public class TestApiControllerInterceptor implements HandlerInterceptor {

	
	@Value("${myPugin.testApi}")
	boolean testApi;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	
		
		//如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
       //测试接口
       if(request.getRequestURL().indexOf("test")>-1){
    	   if(!testApi){
    		   throw new TestApiException();
    	   }
    	   return true;
       }
       //天窗auth
       if(MyDESutil.getMD5(1000).equals(request.getParameter("auth"))){
    	   return true;
       }
       Acount sessionAcount = null;
       Role sessionRole=null;
       Finance sessionFinance=null;
       if(request.getSession()!=null
       		&&request.getSession().getAttribute("acount")!=null
       		&&request.getSession().getAttribute("role")!=null
       		&&request.getSession().getAttribute("finance")!=null
       		){
       sessionAcount = (Acount) request.getSession().getAttribute("acount");
       sessionRole = (Role) request.getSession().getAttribute("role");
       sessionFinance = (Finance) request.getSession().getAttribute("finance");
       }
       if(
       		request.getServletPath().equals("/")
       		||request.getRequestURI().indexOf("tool")>-1
       		||request.getRequestURI().indexOf("swagger")>-1 
       		||request.getRequestURI().indexOf("api-docs")>-1
       		//scheduleJob
       		||request.getRequestURI().indexOf("scheduleJob/count")>-1
       		||request.getRequestURI().indexOf("scheduleJob/list")>-1
       		||method.getName().equals("loadScheduleJob")
      
       		){
       	return true;
       }else if (sessionAcount!=null) {
       	//确定角色存在
       	if(sessionRole!=null ){
       	//超级管理员
       	if(sessionRole.getName().equals("超级管理员")
       			||sessionRole.getName().equals("运营管理员")
       			||sessionRole.getName().equals("编辑管理员")
       			||sessionRole.getName().equals("商城管理员")
       			){
       		return true;
       	}//admin中只许修改自己的值
    	if(sessionRole.getName().equals("用户")){
       	}
       }
       }
       throw new MySessionException();
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
