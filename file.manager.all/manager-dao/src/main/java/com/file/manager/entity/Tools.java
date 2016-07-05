package com.file.manager.entity;


public class Tools extends FileAll{

	//alias
	public static final String TABLE_ALIAS = "Tool";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NO = "no";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_TYPE_NO = "typeNo";
	public static final String ALIAS_SIZE = "size";
	public static final String ALIAS_PATH = "path";
	public static final String ALIAS_FOLDER_NO = "folderNo";
	public static final String ALIAS_VERSION = "version";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_USER_NO = "userNo";

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 工具编号，唯一
	 */
	private String no;
	/**
	 * 工具名称
	 */
	private String name;
	/**
	 * 工具类型，外键
	 */
	private String typeNo;
	/**
	 * 工具大小
	 */
	private Double size;
	/**
	 * 工具路径
	 */
	private String path;
	/**
	 * 工具所属文件夹
	 */
	private String folderNo;
	/**
	 * 工具版本
	 */
	private String version;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 上传时间
	 */
	private java.util.Date createtime;
	/**
	 * 上传人，外键
	 */
	private String userNo;
	
	
	private String otherName;

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
	public java.lang.String getTypeNo() {
		return this.typeNo;
	}
	
	public void setTypeNo(java.lang.String value) {
		this.typeNo = value;
	}
	public java.lang.Double getSize() {
		return this.size;
	}
	
	public void setSize(java.lang.Double value) {
		this.size = value;
	}
	public java.lang.String getPath() {
		return this.path;
	}
	
	public void setPath(java.lang.String value) {
		this.path = value;
	}
	public java.lang.String getFolderNo() {
		return this.folderNo;
	}
	
	public void setFolderNo(java.lang.String value) {
		this.folderNo = value;
	}
	public java.lang.String getVersion() {
		return this.version;
	}
	
	public void setVersion(java.lang.String value) {
		this.version = value;
	}
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
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