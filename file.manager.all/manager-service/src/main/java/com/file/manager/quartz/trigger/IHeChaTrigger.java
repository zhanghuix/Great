package com.file.manager.quartz.trigger;

import org.quartz.Trigger;

public interface IHeChaTrigger {

	public Trigger getCronTrigger(String datetime,String jobKey,String jobGroup) throws Exception;
}
