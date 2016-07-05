package com.file.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName:FileSearchController
 * @Description:全文搜索类，使用lucene
 * @author zhangHui
 * @date:2016年2月26日
 */
@Controller
@RequestMapping("/web/search/")
public class FileSearchController {

	/**
	 * 
	 * @methodName:searchFile
	 * @Description:全文搜索，跳转页面
	 * @param file
	 * @author zhangHui
	 * @date 2016年2月26日 下午4:19:44
	 */
	@RequestMapping("searchFile")
	public String searchFile(String file,Model model){
	
		
		
		model.addAttribute("fileAll", "");
		return "/search/search";
	}
}
