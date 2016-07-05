package com.file.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.file.manager.entity.User;
/**
 * 
 * @ClassName:LoginInterceptors
 * @Description:根据mvc.xml文件中的配置拦截请求，验证登录
 * @author zhangHui
 * @date:2015年12月22日
 */
public class LoginInterceptors extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		User user = (User) request.getSession().getAttribute("user");

		if(request.getRequestURI().indexOf("checkLogin.html")==-1){
			if (user == null) {
				response.sendRedirect("/web/login/checkLogin.html");
			}
		}

		return super.preHandle(request, response, handler);
	}

}
