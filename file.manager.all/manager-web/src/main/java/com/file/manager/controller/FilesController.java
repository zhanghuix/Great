package com.file.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.file.manager.entity.Files;
import com.file.manager.entity.User;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.business.FilesService;
import com.file.manager.util.Common;

@Controller
@RequestMapping("/web/files/")
public class FilesController {
	
	@Autowired
	private FilesService filesService;

	
	@RequestMapping("toFilesFolder")
	public String dispathFilesFolder(Model model){
		
		return "module/filesFolder";
	}
	
	/**
	 * 
	 * @methodName:dispathFilesList
	 * @Description:跳转文件列表
	 * @param model
	 * @param request
	 * @param folderNo 文件夹no
	 * @author zhangHui
	 * @date 2016年1月4日 下午2:36:18
	 */
	@RequestMapping("toFilesList")
	public String dispathFilesList(Model model, HttpServletRequest request,
			String folderNo) {

		model.addAttribute("folderNo", folderNo);

		return "module/filesList";
	}

	/**
	 * 
	 * @methodName:getFiles
	 * @Description:获取单挑文件信息
	 * @param filesNo
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月4日 下午2:36:56
	 */
	@ResponseBody
	@RequestMapping("getFiles")
	public Files getFiles(String filesNo) throws Exception {

		if (Common.isEmpty(filesNo)) {
			throw new Exception(
					"com.file.manager.controller.FilesController.getFiles(String)参数不能为空");
		}

		return filesService.get(filesNo);
	}

	/**
	 * @methodName:filesListPage
	 * @Description:文件列表分页
	 * @param folderNo 文件夹号
	 * @param pageNow
	 * @param request
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月4日 下午2:37:30
	 */
	@ResponseBody
	@RequestMapping("filesListPage")
	public PageView filesListPage(String folderNo, String pageNow, HttpServletRequest request) throws Exception {

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
			Files files = new Files();
			files.setFolderNo(folderNo);

			pageView = filesService.queryPage(files, pageView);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

	/**
	 * 
	 * @methodName:addfiles
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
	@RequestMapping("addFiles")
	public String addFiles(Model model, HttpServletRequest request,
			String filePaths, String folderValue) {

		String res = "1000";

		if (Common.isEmpty(filePaths)) {
			res = "3000";
		} else {

			try {

				List<String> listPath = JSONArray.parseArray(filePaths,
						String.class);

				User user = (User) request.getSession().getAttribute("user");

				filesService.addList(listPath, folderValue, user.getLoginName());

			} catch (Exception e) {
				e.printStackTrace();
				res = "2000";
			}
		}
		return res;
	}

	/**
	 * 
	 * @methodName:modfiyfiles
	 * @Description:更新文件信息
	 * @param files
	 * @author zhangHui
	 * @date 2015年12月30日 下午4:31:41
	 */
	@ResponseBody
	@RequestMapping("modfiyFiles")
	public String modfiyFiles(Files files) {

		String res = "1000";
		if (files == null) {
			return "3000";
		}
		if (Common.isEmpty(files.getNo())) {
			return "3000";
		}

		try {
			filesService.modify(files);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}
		return res;
	}

	/**
	 * 
	 * @methodName:delfiles
	 * @Description:删除文件
	 * @param noArray
	 * @author zhangHui
	 * @date 2015年12月31日 下午3:18:29
	 */
	@ResponseBody
	@RequestMapping("delFiles")
	public String delFiles(String noArray) {

		String res = "1000";

		if (Common.isEmpty(noArray)) {
			return "3000";
		}

		try {
			
			String[] nos = noArray.split(",");
			
			filesService.del(nos);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
	/**
	 * 
	 * @methodName:moveFiles
	 * @Description:移动文件
	 * @param no
	 * @param folderNo
	 * @author zhangHui
	 * @date 2016年1月11日 下午2:55:41
	 */
	@ResponseBody
	@RequestMapping("moveFiles")
	public String moveFiles(String no,String folderNo) {

		String res = "1000";

		if (Common.isEmpty(no)) {
			return "3000";
		}

		try {
			filesService.moveFiles(no, folderNo);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
}
