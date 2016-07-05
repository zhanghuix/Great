package com.file.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.file.manager.entity.HcJobtime;
import com.file.manager.entity.HcQuartzJob;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.quartz.HeChaJobService;
import com.file.manager.service.HcQuartzJobService;
import com.file.manager.util.Common;

/**
 * 
 * @ClassName:QuartzJob
 * @Description:定时任务
 * @author zhangHui
 * @date:2015年10月28日
 */
@Controller
@RequestMapping("/background/quartzJob/")
public class HcQuartzJobController {

	@Autowired
	private HeChaJobService heChaJobService;
	@Autowired
	private HcQuartzJobService hcQuartzJobService;

	@RequestMapping("query")
	public String query(Model model, String pageNow) {

		PageView pageView = null;
		if (Common.isEmpty(pageNow)) {
			pageView = new PageView(1);
		} else {
			pageView = new PageView(Integer.parseInt(pageNow));
		}
		
		HcQuartzJob hcQuartzJob=new HcQuartzJob();
		
		pageView = hcQuartzJobService.query(pageView,hcQuartzJob);
		model.addAttribute("pageView", pageView);

		return "/background/quartzJob/list";
	}

	@RequestMapping("toAdd")
	public String toAdd(Model model) {
		return "/background/quartzJob/add";
	}

	@RequestMapping("toSetting")
	public String toSetting(Model model,String jobKey) {
		//加载定时任务所有时间
		List<HcJobtime> list = hcQuartzJobService.queryJobTimeAll();
		
		model.addAttribute("jobKey", jobKey);
		model.addAttribute("list", list);
		return "/background/quartzJob/setting";
	}
	@RequestMapping("addJobTime")
	public String add(Model model,String desc,String jobTime){
		
		System.out.println(desc+jobTime);
		return null;
	}
	@RequestMapping("setJobTime")
	public String setting(Model model,String jobName,String dateTime){
		
		System.out.println(jobName+dateTime);
		return null;
	}

	/**
	 * 
	 * @methodName:startJob
	 * @Description:启动定时任务
	 * @param model
	 * @param job
	 * @return
	 * @author zhangHui
	 * @date 2015年10月28日 下午8:54:43
	 */
	@ResponseBody
	@RequestMapping("start")
	public String startJob(Model model, String jobKey) {
		String res = "1000";

		try {
			if (jobKey == null) {
				throw new Exception(
						"com.hecha.controller.QuartzJob.startJob(Model, JobParamBean[])方法参数不能为空");
			}
			
			
			
			String[] jobKeys=jobKey.split(",");

			heChaJobService.start(hcQuartzJobService.queryJobByJobKey(jobKeys));
			
			hcQuartzJobService.jobON(jobKeys);
		
		} catch (Exception e) {

			res = "1001";
			e.printStackTrace();

		}

		return res;
	}

	/**
	 * 
	 * @methodName:stopJob
	 * @Description:停止定时任务
	 * @param model
	 * @param job
	 * @return
	 * @author zhangHui
	 * @date 2015年10月28日 下午8:54:57
	 */
	@ResponseBody
	@RequestMapping("shutdown")
	public String shutdownJob(Model model, String jobKey) {

		String res = "1000";

		try {
			if (jobKey == null) {
				throw new Exception(
						"com.hecha.controller.QuartzJob.shutdownJob(Model, JobParamBean[])方法参数不能为空");
			}
			
			String[] jobKeys=jobKey.split(",");

			heChaJobService.shutdown(hcQuartzJobService
					.queryJobByJobKey(jobKeys));
			
			hcQuartzJobService.jobOFF(jobKeys);

		} catch (Exception e) {

			res = "1001";
			e.printStackTrace();

		}

		return res;
	}
}
