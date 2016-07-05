package com.file.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.file.manager.entity.User;

@Controller
@RequestMapping("/web/login/")
public class LoginController {

	/**
	 * 
	 * @methodName:checkLogin
	 * @Description:登录
	 * @param mode
	 * @param request
	 * @param user
	 * @author zhangHui
	 * @date 2015年12月22日 下午2:38:12
	 */
	@RequestMapping("checkLogin")
	public String checkLogin(Model mode, HttpServletRequest request, User user) {

		if ("123".equals(user.getPwd())) {
			request.getSession().setAttribute("user", user);
			return "/framework/main";
		} else {
			return "/framework/login";
		}

	}
	
	
	@RequestMapping("toTop")
	public String dispathTopPage(Model model) {

		return "/framework/top";
	}
	
	@RequestMapping("toBottom")
	public String dispathBottomPage(Model model) {

		return "/framework/bottom";
	}
	
	@RequestMapping("toFenyePage")
	public String dispathFenyePage(Model model) {

		return "/common/webfenye";
	}
	
	
	

	@RequestMapping("toHome")
	public String dispathMainFolder(Model model) {

		return "/module/home";
	}

	@RequestMapping("toPhotoFolder")
	public String dispathPhoto(Model model) {

		return "/module/photoFolder";
	}

	@RequestMapping("toVideoFolder")
	public String dispathVideo(Model model) {

		return "/module/photoFolder";
	}

	@RequestMapping("toFileFolder")
	public String dispathFile(Model model) {

		return "/module/photoFolder";
	}

	@RequestMapping("toArticleFolder")
	public String dispathArticle(Model model) {

		return "/module/photoFolder";
	}

	@RequestMapping("toToolsFolder")
	public String dispathTools(Model model) {

		return "/module/photoFolder";
	}

	@RequestMapping("toProjectFolder")
	public String dispathProject(Model model) {

		return "/module/photoFolder";
	}
}
