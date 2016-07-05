package com.file.manager.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.file.manager.service.business.UploadFileService;
import com.file.manager.upload.FileUploadListener;
import com.file.manager.upload.FileUploadStatusBean;
import com.file.manager.upload.HanlderUploadStatus;
import com.file.manager.util.ApplicationPropertyPlaceholderConfigurer;

@Controller
@RequestMapping("/fileManager/upload/")
public class UploadFileController {

	private Logger log = Logger.getLogger(UploadFileController.class);

	// 单例，随着sring容器初始化，这个应用程序中只有一份
	private HanlderUploadStatus hanlderUploadStatus = HanlderUploadStatus
			.getInstance();

	@Autowired
	private UploadFileService uploadFileService;

	/**
	 * 
	 * @methodName:upload
	 * @Description:上传文件
	 * @param model
	 * @param request
	 * @param response
	 * @author zhangHui
	 * @date 2015年11月26日 下午1:58:44
	 */
	@ResponseBody
	@RequestMapping("uploadFile")
	public String upload(Model model, HttpServletRequest request,
			String uploadType) {
		
		List<String> uploadFile = new ArrayList<String>();

		//用sessionid作为存放上传文件信息的key
		String id = request.getSession().getId();

		
		String realPath = ApplicationPropertyPlaceholderConfigurer
				.getContextProperty("UPLOAD_DIR").toString() + uploadType;// request.getRealPath(UPLOAD_DIR);
		String tempFolder =realPath + "/temp"; // request.getRealPath(TEMP_FOLDER);

		int contentLength = request.getContentLength();
		String contentPath = request.getContextPath() + tempFolder;

		// 通过区分是否为form表单的multipart/form-data类型提交来完成上传与取消和状态回传的功能
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		try {
			if (isMultipart) {
				// multipart/form-data提交类型，为正常文件上传
				
				FileUploadListener ful = new FileUploadListener(id);
				ServletFileUpload sfu = uploadFileService.setServletFileUpload(
						ful, tempFolder);

				// 首次缓存上传状态初始化
				uploadFileService.saveFileUploadStatus(contentLength,
						contentPath, id);

				// 执行后将开启监听，并且获取上传数据。
				List<FileItem> fileItemList = sfu.parseRequest(request);

				// 必须在sfu.parseRequest()方法后执行，因为此方法才能出发监听
				// 上传中途取消则删除已上传文件,监听会在上传完成之前不断读取客户端发送过来的请求，状态中的取消会在用户点击取消时设置。
				if (hanlderUploadStatus.getUploadStatus(id).getCancel()) {
					uploadFileService.delUploadFile(realPath, id);
				} else {

					// 执行上传持久化到磁盘
					uploadFile = uploadFileService.saveUploadFile(fileItemList,
							realPath,
							id);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			uploadFileService.uploadException("上传出现异常，请重新上传", realPath, id);
		}

		JSONArray json = new JSONArray();
		json.addAll(uploadFile);

		return json.toString();
	}

	/**
	 * 
	 * @methodName:cancelUploadFile
	 * @Description:取消上传，上传中途取消，其余无效
	 * @param model
	 * @param request
	 * @author zhangHui
	 * @date 2015年11月26日 下午1:58:59
	 */
	@ResponseBody
	@RequestMapping("cancleUpload")
	public String cancelUploadFile(Model model, HttpServletRequest request) {
		String id = request.getSession().getId();

		FileUploadStatusBean statusBean = hanlderUploadStatus
				.getUploadStatus(id);
		statusBean.setCancel(true);
		hanlderUploadStatus.setUploadStatus(statusBean, id);

		log.info("执行上传取消功能：" + statusBean.toJSon());

		return statusBean.toJSon();
	}

	/**
	 * 
	 * @methodName:modfiyUploadStatus
	 * @Description:更新上传状态，控制进度条，回传页面数据
	 * @param model
	 * @param request
	 * @author zhangHui
	 * @date 2015年11月26日 下午3:58:20
	 */
	@ResponseBody
	@RequestMapping("updateStatus")
	public String modfiyUploadStatus(Model model, HttpServletRequest request) {

		String id = request.getSession().getId();

		FileUploadStatusBean statusBean = hanlderUploadStatus
				.getUploadStatus(id);
		log.info("当前上传状态：" + statusBean.toJSon());
		model.addAttribute("statusBean", statusBean.toJSon());

		Object jsons = JSON.toJSON(statusBean);

		if (statusBean.getIsOver() == 1) {
			// 上传完成后，移除缓存中当前上传的数据，因为是单例模式，从启动应用程序只有一份
			hanlderUploadStatus.remove(id);
		}

		return jsons.toString();
	}

}
