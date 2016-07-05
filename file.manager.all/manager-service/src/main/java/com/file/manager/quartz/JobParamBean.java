package com.file.manager.quartz;

import java.io.Serializable;

/**
 * 
   * @ClassName: JobParamBean
   * @Description: 定时任务时间和调度器，触发器参数bean定义
   * @author zhangHui
   * @date 2015年10月15日 下午9:48:12
   *
 */
public class JobParamBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String jobTime;
	private String jobKey;
	private String jobGroup;
	
	private String state;
	
	private String jobName;
	
	private String desc;
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobTime() {
		return jobTime;
	}
	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}
	public String getJobKey() {
		return jobKey;
	}
	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
}
