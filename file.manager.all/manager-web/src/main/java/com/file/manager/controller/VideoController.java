package com.file.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.file.manager.entity.User;
import com.file.manager.entity.Video;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.business.VideoService;
import com.file.manager.util.Common;

@Controller
@RequestMapping("/web/video/")
public class VideoController {
	
	@Autowired
	private VideoService videoService;

	
	@RequestMapping("toVideoFolder")
	public String dispathVideoFolder(Model model){
		
		return "module/videoFolder";
	}
	
	@RequestMapping("toVideoList")
	public String dispathVideoList(Model model,
			String folderNo) {

		model.addAttribute("folderNo", folderNo);

		return "module/videoList";
	}
	
	
	@ResponseBody
	@RequestMapping("getVideo")
	public Video getVideo(String videoNo) throws Exception {

		if(Common.isEmpty(videoNo)) {
			throw new Exception(
					"com.file.manager.controller.VideoController.getVideo(String)参数不能为空");
		}

		return videoService.get(videoNo);
	}

	/**
	 * 
	 * @methodName:videoListPage
	 * @Description:视频分页查询
	 * @param folderNo 文件夹no
	 * @param pageNow
	 * @param cutover  区分1=列表或者0=缩略图
	 * @param request
	 * @throws Exception
	 * @author zhangHui
	 * @date 2015年12月31日 下午5:36:12
	 */
	@ResponseBody
	@RequestMapping("videoListPage")
	public PageView videoListPage(String folderNo, String pageNow,
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
			Video video = new Video();
			video.setFolderNo(folderNo);

			pageView = videoService.queryPage(video, pageView);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

	/**
	 * 
	 * @methodName:addVideo
	 * @Description:添加视频
	 * @param model
	 * @param request
	 * @param filePaths  上传文件的物理路径
	 * @param folderValue 上传文件所属的文件夹
	 * @author zhangHui
	 * @date 2015年12月31日 下午5:37:25
	 */
	@ResponseBody
	@RequestMapping("addVideo")
	public String addVideo(Model model, HttpServletRequest request,
			String filePaths, String folderValue) {

		String res = "1000";

		if (Common.isEmpty(filePaths)) {
			res = "3000";
		} else {

			try {

				List<String> listPath = JSONArray.parseArray(filePaths,
						String.class);
				User user = (User) request.getSession()
						.getAttribute("user");
				videoService.addList(listPath, folderValue, user.getLoginName());
			} catch (Exception e) {
				e.printStackTrace();
				res = "2000";
			}
		}
		return res;
	}

	/**
	 * 
	 * @methodName:modfiyVideo
	 * @Description:修改视频信息
	 * @param video
	 * @author zhangHui
	 * @date 2015年12月31日 下午5:38:39
	 */
	@ResponseBody
	@RequestMapping("modfiyVideo")
	public String modfiyVideo(Video video) {
		
		
		String res = "1000";
		if (video == null) {
			return "3000";
		}
		if (Common.isEmpty(video.getNo())) {
			return "3000";
		}
		
		try {
			
			videoService.modify(video);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}
		return res;
	}
	
	/**
	 * 
	 * @methodName:delVideo
	 * @Description:删除视频信息
	 * @param noArray
	 * @author zhangHui
	 * @date 2015年12月31日 下午5:39:04
	 */
	@ResponseBody
	@RequestMapping("delVideo")
	public String delVideo(String noArray){
		
		String res="1000";
		
		if(Common.isEmpty(noArray)){
			return "3000";
		}
		
		try {
			
			String[] nos = noArray.split(",");
			
			videoService.del(nos);
		} catch (Exception e) {
			e.printStackTrace();
			res ="2000";
		}
		
		return res;
	}
	
	/**
	 * 
	 * @methodName:moveVideo
	 * @Description:移动文件
	 * @param no
	 * @param folderNo
	 * @author zhangHui
	 * @date 2016年1月11日 下午2:59:08
	 */
	@ResponseBody
	@RequestMapping("moveVideo")
	public String moveVideo(String no,String folderNo) {

		String res = "1000";

		if (Common.isEmpty(no)) {
			return "3000";
		}

		try {
			videoService.moveVideo(no, folderNo);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
}
