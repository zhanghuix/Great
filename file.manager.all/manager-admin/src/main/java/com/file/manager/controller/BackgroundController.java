package com.file.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.file.manager.dao.UserDao;
import com.file.manager.entity.Resources;
import com.file.manager.entity.User;
import com.file.manager.entity.UserLoginList;
import com.file.manager.service.ResourcesService;
import com.file.manager.service.UserLoginListService;
import com.file.manager.util.Common;

/**
 * 进行管理后台框架界面的类
 * @author hecha
 * 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Controller
@RequestMapping ("/background/")
public class BackgroundController
{
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserLoginListService userLoginListService;
	@Autowired
	private ResourcesService resourcesService;
	@Autowired
	private AuthenticationManager myAuthenticationManager;
	/**
	 * @return
	 */
	@RequestMapping ("login")
	public String login(Model model,HttpServletRequest request)
	{
		//重新登录时销毁该用户的Session
		Object o = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if(null != o){
			request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
		}
		return "/background/framework/login";
	}
	
	@RequestMapping ("loginCheck")
	public String loginCheck(String loginName,String pwd,HttpServletRequest request){
		try {
			if (!request.getMethod().equals("POST")) {
				request.setAttribute("error","支持POST方法提交！");
			}
			if (Common.isEmpty(loginName) || Common.isEmpty(pwd)) {
				request.setAttribute("error","用户名或密码不能为空！");
				return "/background/framework/login";
			}
			// 验证用户账号与密码是否正确
			User users = this.userDao.querySingleUser(loginName);
			//pwd = Md5Tool.getMd5(pwd);
			if (users == null || !users.getPwd().equals(pwd)) {
				request.setAttribute("error", "用户或密码不正确！");
			    return "/background/framework/login";
			}
			Authentication authentication = myAuthenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginName,pwd));
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			HttpSession session = request.getSession(true);  
		    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);  
		    // 当验证都通过后，把用户信息放在session里
			request.getSession().setAttribute("userSession", users);
			// 记录登录信息
			UserLoginList userLoginList = new UserLoginList();
			userLoginList.setUserNo(users.getNo());
			userLoginList.setIp(Common.toIpAddr(request));
			userLoginListService.add(userLoginList);
		} catch (AuthenticationException ae) {  
			request.setAttribute("error", "登录异常，请联系管理员！");
		    return "/background/framework/login";
		}
		return "redirect:index.html";
	}
	
	/**
	 * @return
	 */
	@RequestMapping ("index")
	public String index(Model model)
	{
		return "/background/framework/main";
	}
	
	@RequestMapping ("top")
	public String top(Model model,HttpServletRequest request) {
		model.addAttribute("admin",loginInfo(request));
		return "/background/framework/top";
	}
	
	@RequestMapping ("left")
	public String left(Model model,HttpServletRequest request)
	{
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				        

			//String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = request.getUserPrincipal().getName();
			List<Resources> resources = resourcesService.getResourcesByUserName(username);
			model.addAttribute("resources", resources);
		} catch (Exception e) {
			//重新登录时销毁该用户的Session
			request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
		}
		return "/background/framework/left";
	}
	
	@RequestMapping ("tab")
	public String tab(Model model,HttpServletRequest request){
		model.addAttribute("admin",loginInfo(request));
		return "/background/framework/tab/tab";
	}
	
	@RequestMapping ("center")
	public String center(Model model) {
		return "/background/framework/center";
	}

	public Map<String,String> loginInfo(HttpServletRequest request){
		Map<String,String> admin=new HashMap<String,String>();
		User user=(User)request.getSession().getAttribute("userSession");
		admin.put("loginName", user.getLoginName());
		admin.put("type",user.getType());
		return admin;
	}
	
}
