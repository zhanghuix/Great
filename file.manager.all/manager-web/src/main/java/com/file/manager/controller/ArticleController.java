package com.file.manager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.file.manager.entity.Essay;
import com.file.manager.entity.User;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.business.EssayService;
import com.file.manager.util.Common;

@Controller
@RequestMapping("/web/article/")
public class ArticleController {

	@Autowired
	private EssayService essayService;
	/**
	 * 
	 * @methodName:dispathArticleFolder
	 * @Description:跳转文章文件夹列表
	 * @param model
	 * @author zhangHui
	 * @date 2016年1月6日 上午11:18:52
	 */
	@RequestMapping("toArticleFolder")
	public String dispathArticleFolder(Model model) {

		return "module/articleFolder";
	}

	/**
	 * 
	 * @methodName:dispathArticleList
	 * @Description:跳转文章列表页面
	 * @param model
	 * @param folderNo
	 * @author zhangHui
	 * @date 2016年1月6日 上午11:18:34
	 */
	@RequestMapping("toArticleList")
	public String dispathArticleList(Model model, String folderNo) {

		model.addAttribute("folderNo", folderNo);

		return "module/articleList";
	}
	
	/**
	 * @methodName:getArticle
	 * @Description:获取单挑信息
	 * @param articleNo
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月6日 上午11:27:26
	 */
	@ResponseBody
	@RequestMapping("getArticle")
	public Essay getArticle(String articleNo) throws Exception {

		if (Common.isEmpty(articleNo)) {
			throw new Exception(
					"com.file.manager.controller.ArticleController.getArticle(String)参数不能为空");
		}

		return essayService.get(articleNo);
	}

	/**
	 * 
	 * @methodName:articleNoListPage
	 * @Description:分页查询
	 * @param folderNo
	 * @param pageNow
	 * @param request
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月6日 上午11:37:43
	 */
	@ResponseBody
	@RequestMapping("articleListPage")
	public PageView articleListPage(String folderNo, String pageNow, HttpServletRequest request) throws Exception {

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
			Essay essay = new Essay();
			essay.setFolderNo(folderNo);

			pageView = essayService.queryPage(essay, pageView);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pageView;
	}

	/**
	 * 
	 * @methodName:addArticle
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
	@RequestMapping("addArticle")
	public String addArticle(Model model, HttpServletRequest request,
			String filePaths, String folderValue) {

		String res = "1000";

		if (Common.isEmpty(filePaths)) {
			res = "3000";
		} else {

			try {

				List<String> listPath = JSONArray.parseArray(filePaths,
						String.class);

				User user = (User) request.getSession().getAttribute("user");

				essayService.addList(listPath, folderValue, user.getLoginName());

			} catch (Exception e) {
				e.printStackTrace();
				res = "2000";
			}
		}
		return res;
	}

	/**
	 * 
	 * @methodName:modfiyArticle
	 * @Description:更新信息
	 * @param essay
	 * @author zhangHui
	 * @date 2015年12月30日 下午4:31:41
	 */
	@ResponseBody
	@RequestMapping("modfiyArticle")
	public String modfiyArticle(Essay essay) {

		String res = "1000";
		if (essay == null) {
			return "3000";
		}
		if (Common.isEmpty(essay.getNo())) {
			return "3000";
		}

		try {
			essayService.modify(essay);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}
		return res;
	}

	/**
	 * 
	 * @methodName:delArticle
	 * @Description:删除文件
	 * @param noArray
	 * @author zhangHui
	 * @date 2015年12月31日 下午3:18:29
	 */
	@ResponseBody
	@RequestMapping("delArticle")
	public String delArticle(String noArray) {

		String res = "1000";

		if (Common.isEmpty(noArray)) {
			return "3000";
		}

		try {
			
			String[] nos = noArray.split(",");
			
			essayService.del(nos);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
	
	/**
	 * 
	 * @methodName:moveArticle
	 * @Description:移动文件
	 * @param no
	 * @param folderNo
	 * @author zhangHui
	 * @date 2016年1月11日 下午2:54:57
	 */
	@ResponseBody
	@RequestMapping("moveArticle")
	public String moveArticle(String no,String folderNo) {

		String res = "1000";

		if (Common.isEmpty(no)) {
			return "3000";
		}

		try {
			essayService.moveEssay(no, folderNo);
		} catch (Exception e) {
			e.printStackTrace();
			res = "2000";
		}

		return res;
	}
}
