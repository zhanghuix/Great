package com.file.manager.entity;


public class FileAll {


	/**
	 * 自增列
	 */
	private Integer id;
	/**
	 * 文档编号
	 */
	private String no;
	/**
	 * 文档名称
	 */
	private String name;
	/**
	 * 文档类型：0=通用，1=技术类，2=阅读类，外键，类型表编码
	 */
	private String typeNo;
	/**
	 * 文档大小 KB
	 */
	private Double size;
	/**
	 * 文档所属文件夹
	 */
	private String folderNo;
	/**
	 * 上传时间
	 */
	private java.util.Date createtime;
	/**
	 * 上传人，用户表外键
	 */
	private String userNo;
	/**
	 * 描述
	 */
	private String description;
	
	private String otherName;
	
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
	public java.lang.String getTypeNo() {
		return this.typeNo;
	}
	
	public void setTypeNo(java.lang.String value) {
		this.typeNo = value;
	}
	public Double getSize() {
		return this.size;
	}
	
	public void setSize(Double value) {
		this.size = value;
	}
	public java.lang.String getFolderNo() {
		return this.folderNo;
	}
	
	public void setFolderNo(java.lang.String value) {
		this.folderNo = value;
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
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
}