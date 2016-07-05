package com.file.manager.entity;


public class HcQuartzJob {

	//alias
	public static final String TABLE_ALIAS = "HcQuartzJob";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JOBNAME = "jobName";
	public static final String ALIAS_JOBGROUP = "jobGroup";
	public static final String ALIAS_JOBKEY = "jobKey";
	public static final String ALIAS_STATE = "state";
	public static final String ALIAS_DESC = "desc";
	public static final String ALIAS_JOBTIME = "jobTime";

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 任务名称
	 */
	private String jobName;
	/**
	 * 在程序中任务所属组
	 */
	private String jobGroup;
	/**
	 * 在程序中任务所对应的key，唯一
	 */
	private String jobKey;
	/**
	 * 任务状态，1=启，0=停
	 */
	private String state;
	/**
	 * 任务描述
	 */
	private String desc;
	/**
	 * 任务执行时间，外键
	 */
	private String jobTime;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.String getJobName() {
		return this.jobName;
	}
	
	public void setJobName(java.lang.String value) {
		this.jobName = value;
	}
	public java.lang.String getJobGroup() {
		return this.jobGroup;
	}
	
	public void setJobGroup(java.lang.String value) {
		this.jobGroup = value;
	}
	public java.lang.String getJobKey() {
		return this.jobKey;
	}
	
	public void setJobKey(java.lang.String value) {
		this.jobKey = value;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String value) {
		this.state = value;
	}
	public java.lang.String getDesc() {
		return this.desc;
	}
	
	public void setDesc(java.lang.String value) {
		this.desc = value;
	}
	public java.lang.String getJobTime() {
		return this.jobTime;
	}
	
	public void setJobTime(java.lang.String value) {
		this.jobTime = value;
	}
}