package com.file.manager.quartz.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.file.manager.quartz.HeChaConstant;

/**
 * 
 * @ClassName: SupperJob
 * @Description: 自定义任务超类，所有任务在此类中统一调用
 * @author zhangHui
 * @date 2015年10月15日 下午8:56:51
 *
 */
public class SupperJob implements Job {

	private IHeChaJob heChaJob;

	Logger log = Logger.getLogger(SupperJob.class);

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

		// 取得任务调度器，通过判断调度器名称执行不同任务，通过定义jobKey可执行更多任务

		String key = context.getJobDetail().getJobDataMap()
				.getString(HeChaConstant.JOBKEY);

		if (key.equals(HeChaConstant.JOB_CARD1)) {
			log.info(key + "开始执行");
			System.out.println("dddd");

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();

			heChaJob.Job1();
		}
		if (key.equals(HeChaConstant.JOB_CARD2)) {
			log.info(key + "开始执行");

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job2();
		}
		if (key.equals(HeChaConstant.JOB_CARD3)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job3();
		}
		if (key.equals(HeChaConstant.JOB_CARD4)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job4();
		}
		if (key.equals(HeChaConstant.JOB_CARD5)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job5();
		}
		if (key.equals(HeChaConstant.JOB_CARD6)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job6();
		}
		if (key.equals(HeChaConstant.JOB_CARD7)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job7();
		}
		if (key.equals(HeChaConstant.JOB_CARD8)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job8();
		}
		if (key.equals(HeChaConstant.JOB_CARD9)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job9();
		}
		if (key.equals(HeChaConstant.JOB_CARD10)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job10();
		}
		if (key.equals(HeChaConstant.JOB_CARD11)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job11();
		}
		if (key.equals(HeChaConstant.JOB_CARD12)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job12();
		}
		if (key.equals(HeChaConstant.JOB_CARD13)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job13();
		}
		if (key.equals(HeChaConstant.JOB_CARD14)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job14();
		}
		if (key.equals(HeChaConstant.JOB_CARD15)) {

			heChaJob = HeChaJobFactory.getInstance().getHeChaJobImpl();
			heChaJob.Job15();
		}

	}

}
