package com.file.manager.entity;

import java.sql.Timestamp;
import java.util.Date;


public class Folder {

	//alias
	public static final String TABLE_ALIAS = "DbFolder";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NO = "no";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_PATH = "path";
	public static final String ALIAS_USER_NO = "userNo";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_DESCRIPTION = "description";
	public static final String ALIAS_TYPE = "type";

	/**
	 * 自增列
	 */
	private Integer id;
	/**
	 * 文件夹编号
	 */
	private String no;
	/**
	 * 文件夹名称
	 */
	private String name;
	/**
	 * 文件夹路径
	 */
	private String path;
	/**
	 * 创建人，外键，用户表
	 */
	private String userNo;
	/**
	 * 创建时间
	 */
	private Date createtime;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 1=照片，2=视频，3=文件，4=文章，5=工具，6=项目
	 */
	private String type;

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
	public java.lang.String getPath() {
		return this.path;
	}
	
	public void setPath(java.lang.String value) {
		this.path = value;
	}
	public java.lang.String getUserNo() {
		return this.userNo;
	}
	
	public void setUserNo(java.lang.String value) {
		this.userNo = value;
	}
	public Date getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(Date value) {
		this.createtime = value;
	}
	public java.lang.String getDescription() {
		return this.description;
	}
	
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String value) {
		this.type = value;
	}

	@Override
	public String toString() {
		return this.id+this.no+this.name+this.type;
	}
	
	
	
}