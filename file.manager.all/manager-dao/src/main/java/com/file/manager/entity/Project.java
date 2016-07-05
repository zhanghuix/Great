package com.file.manager.entity;


public class Project extends FileAll{

	//alias
	public static final String TABLE_ALIAS = "Project";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NO = "no";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_TYPE = "type";
	public static final String ALIAS_COMPANY = "company";
	public static final String ALIAS_BEGINTIME = "begintime";
	public static final String ALIAS_ENDTIME = "endtime";
	public static final String ALIAS_SIZE = "size";
	public static final String ALIAS_ADDRESS = "address";
	public static final String ALIAS_TAKE_ON = "takeOn";
	public static final String ALIAS_PATH = "path";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_USER_NO = "userNo";

	/**
	 * 自增列
	 */
	private Integer id;
	/**
	 * 项目编号，唯一
	 */
	private String no;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 项目类型，0=普通项目，1=组件项目
	 */
	private String type;
	/**
	 * 项目所属公司
	 */
	private String company;
	/**
	 * 项目开始时间
	 */
	private java.util.Date begintime;
	/**
	 * 项目结束时间
	 */
	private java.util.Date endtime;
	/**
	 * 项目大小，人数
	 */
	private Double size;
	/**
	 * 项目开发地点
	 */
	private String address;
	/**
	 * 本人负责部分
	 */
	private String takeOn;
	/**
	 * 项目路径
	 */
	private String path;
	/**
	 * 上传时间
	 */
	private java.util.Date createtime;
	/**
	 * 上传人 
	 */
	private String userNo;
	
	private String folderNo;
	
	private String otherName;

	public String getFolderNo() {
		return folderNo;
	}

	public void setFolderNo(String folderNo) {
		this.folderNo = folderNo;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public java.lang.Integer getId() {
		return this.id;
	}
	
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	public java.lang.String getNo() {
		return this.no;
	}
	
	public void setNo(java.lang.String value) {
		this.no = value;
	}
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String value) {
		this.name = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}
	public java.lang.String getCompany() {
		return this.company;
	}
	
	public void setCompany(java.lang.String value) {
		this.company = value;
	}
	public java.util.Date getBegintime() {
		return this.begintime;
	}
	
	public void setBegintime(java.util.Date value) {
		this.begintime = value;
	}
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	public Double getSize() {
		return this.size;
	}
	
	public void setSize(Double value) {
		this.size = value;
	}
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	public java.lang.String getTakeOn() {
		return this.takeOn;
	}
	
	public void setTakeOn(java.lang.String value) {
		this.takeOn = value;
	}
	public java.lang.String getPath() {
		return this.path;
	}
	
	public void setPath(java.lang.String value) {
		this.path = value;
	}
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	public java.lang.String getUserNo() {
		return this.userNo;
	}
	
	public void setUserNo(java.lang.String value) {
		this.userNo = value;
	}
}