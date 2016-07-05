package com.file.manager.quartz.trigger.impl;

import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import com.file.manager.quartz.HeChaConstant;
import com.file.manager.quartz.trigger.IHeChaTrigger;
/**
 * 
   * @ClassName: HeChaTriggerImpl
   * @Description: 通过key和group定义一组触发器
   * @author zhangHui
   * @date 2015年10月15日 下午9:13:54
   *
 */
public class HeChaTriggerImpl implements IHeChaTrigger {

	/**
	 * 
	   * getCronTrigger(这里用一句话描述这个方法的作用)
	   *
	   * @Title: getCronTrigger
	   * @Description: 通过key和group定义一组通用触发器，触发器类型为cron，可执行时间格式为("0/3 * * * * ?")
	   * @param datetime 触发器触发时间，格式为"0/3 * * * * ?"
	   * @param jobKey 触发器名称key
	   * @return Trigger
	   * @throws Exception  
	   * @author zhangHui
	 */
	@Override
	public Trigger getCronTrigger(String datetime, String jobKey,String jobGroup)throws Exception {
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity(jobKey, jobGroup)
				.withSchedule(CronScheduleBuilder.cronSchedule(datetime))
				.forJob(jobKey, jobGroup).build();
		return trigger;
	}

}
