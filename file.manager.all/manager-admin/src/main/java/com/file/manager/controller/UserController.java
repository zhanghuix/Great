package com.file.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.file.manager.entity.Roles;
import com.file.manager.entity.User;
import com.file.manager.entity.UserRoles;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.RolesService;
import com.file.manager.service.UserService;
import com.file.manager.util.Common;

/**
 * 
 * @author hecha 2013-11-19
 * @Email: mmm333zzz520@163.com
 * @version 1.0v
 */
@Controller
@RequestMapping("/background/user/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RolesService rolesService;

	/**
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping("query")
	public String query(Model model, User user, String pageNow) {
		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView = userService.query(pageView, user);
		model.addAttribute("pageView", pageView);
		return "/background/user/list";
	}

	/**
	 * 保存数据
	 * 
	 * @param model
	 * @param videoType
	 * @return
	 */
	@RequestMapping("add")
	public String add(Model model, User user, HttpServletRequest request) {

		User admin = (User) request.getSession().getAttribute("userSession");
		
		userService.add(user);

		return "redirect:query.html";
	}

	/**
	 * 跑到新增界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI() {
		return "/background/user/add";
	}

	/**
	 * 删除
	 * 
	 * @param model
	 * @param videoTypeId
	 * @return
	 */
	@RequestMapping("deleteById")
	public String deleteById(Model model, String userNo) {
		userService.delete(userNo);
		return "redirect:query.html";
	}

	/**
	 * 修改界面
	 * 
	 * @param model
	 * @param videoTypeIds
	 * @return
	 */
	@RequestMapping("getById")
	public String getById(Model model, String userNo, int type) {
		User user = userService.getById(userNo);
		model.addAttribute("user", user);
		List<Roles> roles = rolesService.findAll();
		model.addAttribute("roles", roles);
		if (type == 1) {
			return "/background/user/edit";
		} else {
			return "/background/user/show";
		}
	}

	/**
	 * 更新类型
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	public String update(Model model, User user, UserRoles userRoles) {
		userService.modify(user);
		if (userRoles.getRoleNo() != null)
			rolesService.saveUserRole(userRoles);
		return "redirect:query.html";
	}

	/**
	 * 删除所选的
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("deleteAll")
	public String deleteAll(Model model, String[] check) {
		for (String string : check) {
			userService.delete(string);
		}
		return "redirect:query.html";
	}

	/**
	 * 给用户分配角色界面
	 * 
	 * @return
	 */
	@RequestMapping("userRole")
	public String userRole(Model model, String userNo) {
		User user = userService.getById(userNo);
		model.addAttribute("user", user);
		List<Roles> roles = rolesService.findAll();
		model.addAttribute("roles", roles);
		return "/background/user/userRole";
	}

	/**
	 * 保存用户分配角色
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("allocation")
	public String allocation(Model model, UserRoles userRoles) {
		String errorCode = "1000";
		try {
			rolesService.saveUserRole(userRoles);
		} catch (Exception e) {
			e.printStackTrace();
			errorCode = "1001";
		}
		return errorCode;
	}

}