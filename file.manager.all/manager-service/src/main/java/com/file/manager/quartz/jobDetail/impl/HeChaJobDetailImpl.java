package com.file.manager.quartz.jobDetail.impl;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;

import com.file.manager.quartz.HeChaConstant;
import com.file.manager.quartz.jobDetail.IHeChaJobDetai;
import com.file.manager.util.Common;

/**
 * 
 * @ClassName: HeChaJobDetailImpl
 * @Description: 任务调度器，通过jobKey和jobGroup定义一组调度器执行任务。
 * @author zhangHui
 * @date 2015年10月15日 下午9:03:59
 *
 */
public class HeChaJobDetailImpl implements IHeChaJobDetai {

	/**
	 * 
	 * getJobDetail(这里用一句话描述这个方法的作用)
	 *
	 * @Title: getJobDetail
	 * @Description: 通过key和group定义通用调度器
	 * @param jobClass
	 *            实现quartz的job接口的任务类。
	 * @param jobKey
	 *            获取调度器数据的key，同时也是通过jobGroup和此key获取唯一调度器
	 * @return JobDetail
	 * @throws Exception
	 * @author zhangHui
	 */
	@Override
	public JobDetail getJobDetail(Class<? extends Job> jobClass, String jobKey,String jobGroup)
			throws Exception {

		if (jobClass == null || Common.isEmpty(jobKey)) {
			throw new Exception(
					"com.hecha.quartz.jobDetail.impl.HeChaJobDetailImpl.getJobDetail(Class<? extends Job>, String)方法参数不能为空");
		}

		JobDetail job = JobBuilder.newJob(jobClass)
				.usingJobData(HeChaConstant.JOBKEY, jobKey)
				.withIdentity(jobKey, jobGroup).build();
		return job;
	}

}
