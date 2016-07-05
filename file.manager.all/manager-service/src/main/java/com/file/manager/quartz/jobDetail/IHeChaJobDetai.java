package com.file.manager.quartz.jobDetail;

import org.quartz.Job;
import org.quartz.JobDetail;


public interface IHeChaJobDetai {


	public JobDetail getJobDetail(Class<? extends Job> jobClass,String jobKey,String jobGroup)throws Exception;
}
