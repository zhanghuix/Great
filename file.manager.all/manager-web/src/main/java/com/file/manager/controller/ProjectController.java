package com.file.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.file.manager.entity.Project;
import com.file.manager.entity.User;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.business.ProjectService;
import com.file.manager.util.Common;

@Controller
@RequestMapping("/web/project/")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;

	@RequestMapping("toProjectFolder")
	public String dispathProjectFolder(Model model){
		
		return "module/projectFolder";
	}
	
	/**
	 * 
	 * @methodName:dispathProjectList
	 * @Description:跳转列表页面
	 * @param model
	 * @param folderNo
	 * @author zhangHui
	 * @date 2016年1月6日 上午11:18:34
	 */
	@RequestMapping("toProjectList")
	public String dispathProjectList(Model model, String folderNo) {

		model.addAttribute("folderNo", folderNo);

		return "module/projectList";
	}
	
	/**
	 * @methodName:getProject
	 * @Description:获取单条信息
	 * @param projectNo
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月6日 上午11:27:26
	 */
	@ResponseBody
	@RequestMapping("getProject")
	public Project getProject(String projectNo) throws Exception {

		if (Common.isEmpty(projectNo)) {
			throw new Exception(
					"com.file.manager.controller.ProjectController.getProject(String)参数不能为空");
		}

		return projectService.get(projectNo);
	}

	/**
	 * 
	 * @methodName:projectListPage
	 * @Description:分页查询
	 * @param folderNo
	 * @param pageNow
	 * @param request
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月6日 上午11:37:43
	 */
	@ResponseBody
	@RequestMapping("projectListPage")
	public PageView projectListPage(String folderNo, String pageNow, HttpServletRequest request) throws Exception {

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
			Project project = new Project();
			project.setFolderNo(folderNo);

			pageView = projectService.queryPage(project, pageView);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

	/**
	 * 
	 * @methodName:addProject
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
	@RequestMapping("addProject")
	public String addProject(Model model, HttpServletRequest request,
			String filePaths, String folderValue) {

		String res = "1000";

		if (Common.isEmpty(filePaths)) {
			res = "3000";
		} else {

			try {

				List<String> listPath = JSONArray.parseArray(filePaths,
						String.class);

				User user = (User) request.getSession().getAttribute("user");

				projectService.addList(listPath, folderValue, user.getLoginName());

			} catch (Exception e) {
				e.printStackTrace();
				res = "2000";
			}
		}
		return res;
	}

	/**
	 * 
	 * @methodName:modfiyProject
	 * @Description:更新信息
	 * @param project
	 * @author zhangHui
	 * @date 2015年12月30日 下午4:31:41
	 */
	@ResponseBody
	@RequestMapping("modfiyProject")
	public String modfiyProject(Project project) {

		String res = "1000";
		if (project == null) {
			return "3000";
		}
		if (Common.isEmpty(project.getNo())) {
			return "3000";
		}

		try {
			projectService.modify(project);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}
		return res;
	}

	/**
	 * 
	 * @methodName:delProject
	 * @Description:删除文件
	 * @param noArray
	 * @author zhangHui
	 * @date 2015年12月31日 下午3:18:29
	 */
	@ResponseBody
	@RequestMapping("delProject")
	public String delProject(String noArray) {

		String res = "1000";

		if (Common.isEmpty(noArray)) {
			return "3000";
		}

		try {
			
			String[] nos = noArray.split(",");
			
			projectService.del(nos);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
	/**
	 * 
	 * @methodName:moveProject
	 * @Description:移动文件
	 * @param no
	 * @param folderNo
	 * @return
	 * @author zhangHui
	 * @date 2016年1月11日 下午2:56:57
	 */
	@ResponseBody
	@RequestMapping("moveProject")
	public String moveProject(String no,String folderNo) {

		String res = "1000";

		if (Common.isEmpty(no)) {
			return "3000";
		}

		try {
			projectService.moveProject(no, folderNo);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
}
