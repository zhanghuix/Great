package com.file.manager.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.file.manager.util.ApplicationPropertyPlaceholderConfigurer;

/**
 * 
 * @ClassName:DownLoadController
 * @Description:下载
 * @author zhangHui
 * @date:2015年12月30日
 */
@Controller
@RequestMapping("/web/downLoad/")
public class DownLoadController {

	/**
	 * 
	 * @methodName:downLoadFile
	 * @Description:下载文件，返回下载路径
	 * @param model
	 * @param uploadType 文件类型
	 * @author zhangHui
	 * @date 2015年12月30日 上午11:29:36
	 */
	@ResponseBody
	@RequestMapping("downFile")
	public String downLoadFile(Model model,String uploadType){
		
		
		String downLoadPath = ApplicationPropertyPlaceholderConfigurer
				.getContextProperty("DOWNLOAD_URL").toString(); 
		
		String realPath = ApplicationPropertyPlaceholderConfigurer
				.getContextProperty("UPLOAD_DIR").toString() + uploadType;
		
		realPath = StringUtils.substringAfter(realPath, "//");
		
		return downLoadPath+realPath+"/";
	}
}
