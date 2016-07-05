package com.file.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.file.manager.entity.Tools;
import com.file.manager.entity.User;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.business.ToolsService;
import com.file.manager.util.Common;

@Controller
@RequestMapping("/web/tools/")
public class ToolsController {
	
	@Autowired
	private ToolsService toolsService;

	@RequestMapping("toToolsFolder")
	public String dispathToolsFolder(Model model){
		
		return "module/toolsFolder";
	}
	
	/**
	 * 
	 * @methodName:dispathToolsList
	 * @Description:跳转列表页面
	 * @param model
	 * @param folderNo
	 * @author zhangHui
	 * @date 2016年1月6日 上午11:18:34
	 */
	@RequestMapping("toToolsList")
	public String dispathToolsList(Model model, String folderNo) {

		model.addAttribute("folderNo", folderNo);

		return "module/toolsList";
	}
	
	/**
	 * @methodName:getTools
	 * @Description:获取单条信息
	 * @param toolsNo
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月6日 上午11:27:26
	 */
	@ResponseBody
	@RequestMapping("getTools")
	public Tools getTools(String toolsNo) throws Exception {

		if (Common.isEmpty(toolsNo)) {
			throw new Exception(
					"com.file.manager.controller.ToolsController.getTools(String)参数不能为空");
		}

		return toolsService.get(toolsNo);
	}

	/**
	 * 
	 * @methodName:toolsListPage
	 * @Description:分页查询
	 * @param folderNo
	 * @param pageNow
	 * @param request
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月6日 上午11:37:43
	 */
	@ResponseBody
	@RequestMapping("toolsListPage")
	public PageView toolsListPage(String folderNo, String pageNow, HttpServletRequest request) throws Exception {

		if (Common.isEmpty(folderNo)) {
			throw new Exception("参数不能为空");
		}

		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
			pageView.setPageSize(8);

		try {
			Tools tools = new Tools();
			tools.setFolderNo(folderNo);

			pageView = toolsService.queryPage(tools, pageView);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

	/**
	 * 
	 * @methodName:addTools
	 * @Description:新增文件信息
	 * @param model
	 * @param request
	 * @param filePaths
	 *            上传文件的物理路径
	 * @param folderValue
	 *            上传文件所属的文件夹
	 * @author zhangHui
	 * @date 2015年12月25日 下午4:38:22
	 */
	@ResponseBody
	@RequestMapping("addTools")
	public String addTools(Model model, HttpServletRequest request,
			String filePaths, String folderValue) {

		String res = "1000";

		if (Common.isEmpty(filePaths)) {
			res = "3000";
		} else {

			try {

				List<String> listPath = JSONArray.parseArray(filePaths,
						String.class);

				User user = (User) request.getSession().getAttribute("user");

				toolsService.addList(listPath, folderValue, user.getLoginName());

			} catch (Exception e) {
				e.printStackTrace();
				res = "2000";
			}
		}
		return res;
	}

	/**
	 * 
	 * @methodName:modfiyTools
	 * @Description:更新信息
	 * @param tools
	 * @author zhangHui
	 * @date 2015年12月30日 下午4:31:41
	 */
	@ResponseBody
	@RequestMapping("modfiyTools")
	public String modfiyTools(Tools tools) {

		String res = "1000";
		if (tools == null) {
			return "3000";
		}
		if (Common.isEmpty(tools.getNo())) {
			return "3000";
		}

		try {
			toolsService.modify(tools);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}
		return res;
	}

	/**
	 * 
	 * @methodName:delTools
	 * @Description:删除文件
	 * @param noArray
	 * @author zhangHui
	 * @date 2015年12月31日 下午3:18:29
	 */
	@ResponseBody
	@RequestMapping("delTools")
	public String delTools(String noArray) {

		String res = "1000";

		if (Common.isEmpty(noArray)) {
			return "3000";
		}

		try {
			
			String[] nos = noArray.split(",");
			
			toolsService.del(nos);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
	/**
	 * 
	 * @methodName:moveTools
	 * @Description:移动文件
	 * @param no
	 * @param folderNo
	 * @author zhangHui
	 * @date 2016年1月11日 下午2:57:52
	 */
	@ResponseBody
	@RequestMapping("moveTools")
	public String moveTools(String no,String folderNo) {

		String res = "1000";

		if (Common.isEmpty(no)) {
			return "3000";
		}

		try {
			toolsService.moveTools(no, folderNo);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
}
