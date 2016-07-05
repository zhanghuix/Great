package com.file.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.file.manager.entity.Types;
import com.file.manager.entity.User;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.business.TypesService;
import com.file.manager.util.Common;

/**
 * 
 * @ClassName:TypesController
 * @Description:模块中的类型
 * @author zhangHui
 * @date:2015年12月25日
 */
@Controller
@RequestMapping("/web/types/")
public class TypesController {

	@Autowired
	private TypesService typesService;

	/**
	 * 
	 * @methodName:typesList
	 * @Description:分页查询类型列表
	 * @param model
	 * @param types
	 * @param request
	 * @param pageNow
	 * @author zhangHui
	 * @date 2015年12月16日 下午2:50:33
	 */
	@ResponseBody
	@RequestMapping("typesList")
	public PageView typesList(Model model, Types types,
			HttpServletRequest request, String pageNow) {

		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView.setPageSize(6);

		try {
			pageView = typesService.queryPage(pageView, types);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageView;
	}

	/**
	 * 
	 * @methodName:typesListAll
	 * @Description:查询所有类型
	 * @param model
	 * @param types
	 * @param request
	 * @author zhangHui
	 * @date 2015年12月17日 下午5:07:45
	 */
	@ResponseBody
	@RequestMapping("typesListAll")
	public List<Types> typesListAll(Model model, Types types,
			HttpServletRequest request) {

		try {
			return typesService.queryAll(types);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @methodName:addTypes
	 * @Description:添加类型
	 * @param model
	 * @param types
	 * @param request
	 * @author zhangHui
	 * @date 2015年12月16日 下午2:51:01
	 */
	@ResponseBody
	@RequestMapping("addTypes")
	public String addTypes(Model model, Types types, HttpServletRequest request) {

		String res = "1000";

		if (types == null || Common.isEmpty(types.getName())) {
			res = "3000";
		} else {
			try {
				User user = (User) request.getSession().getAttribute("user");
				types.setUserNo(user.getLoginName());

				if (Common.isEmpty(types.getNo())) {
					typesService.add(types);
				} else {
					typesService.modify(types);
				}

			} catch (Exception e) {
				res = "2000";
				e.printStackTrace();
			}
		}
		return res;
	}

	/**
	 * 
	 * @methodName:gettypes
	 * @Description:获取单条类型信息
	 * @param model
	 * @param no
	 * @param request
	 * @author zhangHui
	 * @date 2015年12月16日 下午2:55:54
	 */
	@ResponseBody
	@RequestMapping("getTypes")
	public Types getTypes(Model model, String no, HttpServletRequest request) {
		Types types = null;

		if (Common.isEmpty(no)) {
			types = null;
		} else {
			try {

				types = typesService.get(no);
			} catch (Exception e) {
				types = null;
				e.printStackTrace();
			}
		}
		return types;
	}

	/**
	 * 
	 * @methodName:delTypes
	 * @Description:删除一条类型数据
	 * @param model
	 * @param no
	 * @param request
	 * @author zhangHui
	 * @date 2015年12月17日 上午11:16:37
	 */
	@ResponseBody
	@RequestMapping("delTypes")
	public String delTypes(Model model, String no, HttpServletRequest request) {

		String res = "1000";

		if (Common.isEmpty(no)) {
			res = "3000";
		} else {
			try {

				typesService.del(no);
			} catch (Exception e) {
				res = "2000";
				e.printStackTrace();
			}
		}
		return res;
	}

}
