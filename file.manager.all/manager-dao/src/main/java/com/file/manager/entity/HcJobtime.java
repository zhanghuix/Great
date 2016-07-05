package com.file.manager.entity;


public class HcJobtime {

	//alias
	public static final String TABLE_ALIAS = "HcJobtime";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_JOBNO = "jobNo";
	public static final String ALIAS_JOBTIME = "jobTime";
	public static final String ALIAS_DESC = "desc";
	public static final String ALIAS_CREATEUSER = "createUser";
	public static final String ALIAS_CREATETIME = "createTime";

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 主键编号
	 */
	private String jobNo;
	/**
	 * 任务执行时间，符合quartz时间格式
	 */
	private String jobTime;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.String getJobNo() {
		return this.jobNo;
	}
	
	public void setJobNo(java.lang.String value) {
		this.jobNo = value;
	}
	public java.lang.String getJobTime() {
		return this.jobTime;
	}
	
	public void setJobTime(java.lang.String value) {
		this.jobTime = value;
	}
	public java.lang.String getDesc() {
		return this.desc;
	}
	
	public void setDesc(java.lang.String value) {
		this.desc = value;
	}
	public java.lang.String getCreateUser() {
		return this.createUser;
	}
	
	public void setCreateUser(java.lang.String value) {
		this.createUser = value;
	}
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
}