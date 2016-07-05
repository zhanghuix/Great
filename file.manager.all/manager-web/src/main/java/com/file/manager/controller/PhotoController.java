package com.file.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.file.manager.entity.Photo;
import com.file.manager.entity.User;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.business.PhotoService;
import com.file.manager.util.Common;

@Controller
@RequestMapping("/web/photo/")
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	/**
	 * 
	 * @methodName:dispathPhotoFolder
	 * @Description:跳转并初始化照片文件夹页面
	 * @param model
	 * @author zhangHui
	 * @date 2015年12月15日 上午11:21:37
	 */
	@RequestMapping("toPhotoFolder")
	public String dispathPhotoFolder(Model model) {

		return "module/photoFolder";
	}

	/**
	 * 
	 * @methodName:dispathPhotoList
	 * @Description:跳转并初始化照片列表页面
	 * @param model
	 * @param folderNo
	 *            文件夹NO
	 * @author zhangHui
	 * @date 2015年12月15日 上午11:22:16
	 */
	@RequestMapping("toPhotoList")
	public String dispathPhotoList(Model model, HttpServletRequest request,
			String folderNo) {

		model.addAttribute("folderNo", folderNo);

		return "module/photoList";
	}

	/**
	 * 
	 * @methodName:getPhoto
	 * @Description:根据编号no获取单条信息
	 * @param photoNo
	 * @throws Exception
	 * @author zhangHui
	 * @date 2015年12月30日 下午4:51:09
	 */
	@ResponseBody
	@RequestMapping("getPhoto")
	public Photo getPhoto(String photoNo) throws Exception {

		if (Common.isEmpty(photoNo)) {
			throw new Exception(
					"com.file.manager.controller.PhotoController.getPhoto(String)参数不能为空");
		}

		return photoService.get(photoNo);
	}

	/**
	 * 
	 * @methodName:photoListPage
	 * @Description:获取照片列表，分页
	 * @param folderNo
	 *            文件夹no
	 * @param pageNow
	 * @param cutover
	 *            //区分1=列表或者0=缩略图
	 * @param request
	 * @author zhangHui
	 * @throws Exception
	 * @date 2015年12月29日 上午10:51:35
	 */
	@ResponseBody
	@RequestMapping("photoListPage")
	public PageView photoListPage(String folderNo, String pageNow,
			String cutover, HttpServletRequest request) throws Exception {

		if (Common.isEmpty(folderNo)) {
			throw new Exception("参数不能为空");
		}

		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		// 如果为列表显示，则8条为一页
		if ("1".equals(cutover)) {
			pageView.setPageSize(8);
		} else {
			pageView.setPageSize(14);
		}

		try {
			Photo photo = new Photo();
			photo.setFolderNo(folderNo);

			pageView = photoService.queryPage(photo, pageView);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

	/**
	 * 
	 * @methodName:addPhoto
	 * @Description:新增照片信息
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
	@RequestMapping("addPhoto")
	public String addPhoto(Model model, HttpServletRequest request,
			String filePaths, String folderValue) {

		String res = "1000";

		if (Common.isEmpty(filePaths)) {
			res = "3000";
		} else {

			try {

				List<String> listPath = JSONArray.parseArray(filePaths,
						String.class);

				User user = (User) request.getSession().getAttribute("user");

				photoService.addList(listPath, folderValue, user.getLoginName());

			} catch (Exception e) {
				e.printStackTrace();
				res = "2000";
			}
		}
		return res;
	}

	/**
	 * 
	 * @methodName:modfiyPhoto
	 * @Description:更新照片信息
	 * @param photo
	 * @author zhangHui
	 * @date 2015年12月30日 下午4:31:41
	 */
	@ResponseBody
	@RequestMapping("modfiyPhoto")
	public String modfiyPhoto(Photo photo) {

		String res = "1000";
		if (photo == null) {
			return "3000";
		}
		if (Common.isEmpty(photo.getNo())) {
			return "3000";
		}

		try {
			photoService.modify(photo);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}
		return res;
	}

	/**
	 * 
	 * @methodName:delPhoto
	 * @Description:删除照片
	 * @param noArray
	 * @author zhangHui
	 * @date 2015年12月31日 下午3:18:29
	 */
	@ResponseBody
	@RequestMapping("delPhoto")
	public String delPhoto(String noArray) {

		String res = "1000";

		if (Common.isEmpty(noArray)) {
			return "3000";
		}

		try {
			
			String[] nos = noArray.split(",");
			
			photoService.del(nos);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
	
	
	@ResponseBody
	@RequestMapping("movePhoto")
	public String movePhoto(String no,String folderNo) {

		String res = "1000";

		if (Common.isEmpty(no)) {
			return "3000";
		}

		try {
			photoService.movePhoto(no, folderNo);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
	

}
