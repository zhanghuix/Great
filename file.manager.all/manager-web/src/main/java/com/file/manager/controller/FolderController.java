package com.file.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.file.manager.entity.Folder;
import com.file.manager.entity.User;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.business.FolderService;
import com.file.manager.util.Common;

@Controller
@RequestMapping("/web/folder/")
public class FolderController {

	@Autowired
	private FolderService folderService;

	/**
	 * 
	 * @methodName:folderList
	 * @Description:分页查询文件夹列表
	 * @param model
	 * @param folder
	 * @param request
	 * @param pageNow
	 * @return
	 * @author zhangHui
	 * @date 2015年12月16日 下午2:50:33
	 */
	@ResponseBody
	@RequestMapping("folderList")
	public PageView folderList(Model model, Folder folder,
			HttpServletRequest request, String pageNow) {

		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView.setPageSize(6);
		
		//此判断表示为各功能模块文件夹列表过来的请求，每页取33条，每行11条，共3行
		if(!Common.isEmpty(folder.getType())){
			pageView.setPageSize(33);
		}

		try {
			pageView = folderService.queryPage(pageView, folder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageView;
	}
	
	/**
	 * 
	 * @methodName:folderListAll
	 * @Description:查询所有文件夹
	 * @param model
	 * @param folder
	 * @param request
	 * @return
	 * @author zhangHui
	 * @date 2015年12月17日 下午5:07:45
	 */
	@ResponseBody
	@RequestMapping("folderListAll")
	public List<Folder> folderListAll(Model model, Folder folder,
			HttpServletRequest request) {
		
		try {
			return folderService.queryAll(folder);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @methodName:addFolder
	 * @Description:添加文件夹
	 * @param model
	 * @param folder
	 * @param request
	 * @return
	 * @author zhangHui
	 * @date 2015年12月16日 下午2:51:01
	 */
	@ResponseBody
	@RequestMapping("addFolder")
	public String addFolder(Model model, Folder folder,
			HttpServletRequest request) {

		String res = "1000";

		if (folder == null) {
			res = "3000";
		} else {
			try {
				
				User user = (User) request.getSession().getAttribute("user");
				
				folder.setUserNo(user.getLoginName());
				
				if(Common.isEmpty(folder.getNo())){
					folderService.add(folder);
				}else{
					folderService.modify(folder);
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
	 * @methodName:getFolder
	 * @Description:获取单条文件夹信息
	 * @param model
	 * @param no
	 * @param request
	 * @author zhangHui
	 * @date 2015年12月16日 下午2:55:54
	 */
	@ResponseBody
	@RequestMapping("getFolder")
	public Folder getFolder(Model model, String no, HttpServletRequest request) {
		Folder folder = null;

		if (Common.isEmpty(no)) {
			folder = null;
		} else {
			try {

				folder = folderService.get(no);
			} catch (Exception e) {
				folder = null;
				e.printStackTrace();
			}
		}
		return folder;
	}
	/**
	 * 
	 * @methodName:delFolder
	 * @Description:删除文件夹
	 * @param model
	 * @param no
	 * @param request
	 * @author zhangHui
	 * @date 2015年12月17日 上午11:16:37
	 */
	@ResponseBody
	@RequestMapping("delFolder")
	public String delFolder(Model model, String no, HttpServletRequest request) {

		String res = "1000";

		if (Common.isEmpty(no)) {
			res = "3000";
		} else {
			try {

				folderService.del(no);
			} catch (Exception e) {
				res = "2000";
				e.printStackTrace();
			}
		}
		return res;
	}
	
	/**
	 * 
	 * @methodName:getFolderTree
	 * @Description:获取文件夹树结构
	 * @param folder
	 * @author zhangHui
	 * @date 2016年1月15日 下午2:34:11
	 */
	@ResponseBody
	@RequestMapping("getFolderTree")
	public String getFolderTree(Folder folder){
		
		try {
			
			return folderService.getFolderTree(folder);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
}
