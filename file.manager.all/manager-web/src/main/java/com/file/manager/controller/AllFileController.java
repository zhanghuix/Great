package com.file.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.file.manager.entity.FileAll;
import com.file.manager.lucene.IndexBean;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.business.FileAllService;
import com.file.manager.service.business.SearchFileImpl;
import com.file.manager.util.Common;

/**
 * 
 * @ClassName:AllFileController
 * @Description:操作全部文件，以及公共操作
 * @author zhangHui
 * @date:2016年1月8日
 */
@Controller
@RequestMapping("/web/allFile/")
public class AllFileController {

	@Autowired
	private FileAllService fileAllService;
	@Autowired
	private SearchFileImpl searchFileImpl;

	@RequestMapping("toAllFileList")
	public String dispathAllFileList(Model model) {

		return "module/allFileList";
	}

	/**
	 * 
	 * @methodName:queryAllList
	 * @Description:查询所有文件
	 * @param pageNow
	 * @param request
	 * @author zhangHui
	 * @date 2016年1月20日 下午4:08:35
	 */
	@ResponseBody
	@RequestMapping("allListPage")
	public PageView queryAllList(String pageNow, FileAll fileAll) {

		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		pageView.setPageSize(10);

		try {

			pageView = fileAllService.queryPage(fileAll, pageView);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

	/**
	 * 
	 * @methodName:delfileAll
	 * @Description:删除文件，通过type控制删除某张表数据
	 * @param noArray
	 * @author zhangHui
	 * @date 2016年1月28日 下午5:49:31
	 */
	@ResponseBody
	@RequestMapping("delfileAll")
	public String delfileAll(String noArray) {

		String res = "1000";

		if (Common.isEmpty(noArray)) {
			return "3000";
		}

		try {

			String[] nos = noArray.split(",");

			fileAllService.delAllFileByNo(nos);

		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}

	/**
	 * 
	 * @methodName:searchFileByName
	 * @Description:通过文件名称模糊搜索，全文检索
	 * @param content
	 * @param model
	 * @author zhangHui
	 * @date 2016年3月7日 下午3:30:57
	 */
	@RequestMapping("search")
	public String searchFileByName(String content, Model model, String pageNow) {

		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1,20);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow),20);
		}
		

		try {
			IndexBean indexBean = searchFileImpl.serarchPathByFileName(content,
					pageView.getStartPage(),
					pageView.getStartPage() + pageView.getPageSize());
			
			pageView.setRecords(indexBean.getFilePath());
			pageView.setRowCount(indexBean.getTotalPage());
			model.addAttribute("pageView", pageView);
			model.addAttribute("content", content);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
		}

		return "search/search";
	}

}
