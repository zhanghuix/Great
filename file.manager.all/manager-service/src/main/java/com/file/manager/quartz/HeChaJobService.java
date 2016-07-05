package com.file.manager.quartz;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import com.file.manager.quartz.job.SupperJob;
import com.file.manager.quartz.jobDetail.impl.HeChaJobDetailImpl;
import com.file.manager.quartz.trigger.impl.HeChaTriggerImpl;
/**
 * 
   * @ClassName: HeChaJob
   * @Description: 调用quartz定时任务入口类
   * @author zhangHui
   * @date 2015年10月15日 下午9:17:50
   *
 */
@Service
public class HeChaJobService {

	/**
	   * @Title: start
	   * @Description: 启动任务，此方法中设置调度器和触发器使用同一个key和group。
	   * @param beans 多个任务
	   * @throws SchedulerException
	   * @throws Exception  
	   * @author zhangHui
	 */
	public void start(List<JobParamBean> beans) throws SchedulerException,
			Exception {

		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();

		if (beans == null || beans.size() == 0) {
			throw new Exception("启动任务时参数异常");
		}
		for (JobParamBean bean : beans) {
			scheduler.scheduleJob(this.getJobDetail(bean.getJobKey(),bean.getJobGroup()),
					this.getTrgger(bean.getJobKey(), bean.getJobTime(),bean.getJobGroup()));
		}
		scheduler.start();

	}

	/**
	 * 
	   * @Title: shutdown
	   * @Description: 停止多个任务，通过停止一个触发器实现停止任务，
	   * @param beans 多个任务
	   * @throws SchedulerException
	   * @throws Exception 
	   * @return void 
	   * @author zhangHui
	 */
	public void shutdown(List<JobParamBean> beans) throws SchedulerException,
			Exception {

		SchedulerFactory sf = new StdSchedulerFactory();

		Scheduler scheduler = sf.getScheduler();

		if (beans == null || beans.size() == 0) {
			throw new Exception("停止任务时参数异常");
		}

		for (JobParamBean bean : beans) {
			TriggerKey tk = new TriggerKey(bean.getJobKey(), bean.getJobGroup());

			if (scheduler.checkExists(tk)) {
				scheduler.unscheduleJob(tk);
			}
		}
	}

	
	public Trigger getTrgger(String jobKey, String datetime,String jobGoup) throws Exception {
		return new HeChaTriggerImpl().getCronTrigger(datetime, jobKey,jobGoup);
	}

	public JobDetail getJobDetail(String jobKey,String jobGroup) throws Exception {
		return new HeChaJobDetailImpl().getJobDetail(SupperJob.class, jobKey,jobGroup);
	}

	public static void main(String[] args) {
		HeChaJobService j = new HeChaJobService();

		JobParamBean bean = new JobParamBean();
		bean.setJobTime("0/3 * * * * ?");
		bean.setJobGroup("jobgroup");
		bean.setJobKey("job1");
		
		JobParamBean bean1 = new JobParamBean();
		bean1.setJobTime("0/3 * * * * ?");
		bean1.setJobGroup("jobgroup");
		bean1.setJobKey("job2");

		List<JobParamBean> list = new ArrayList<JobParamBean>();
		list.add(bean);
		list.add(bean1);
		
		List<JobParamBean> list2 = new ArrayList<JobParamBean>();
		list2.add(bean1);
		try {
			j.start(list);
			j.shutdown(list2);

			//j.start(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
