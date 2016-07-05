package com.file.manager.entity;


public class Types {

	//alias
	public static final String TABLE_ALIAS = "Types";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NO = "no";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_PARTEN_NO = "partenNo";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_USER_NO = "userNo";

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 类型编号,五位数字类型吗
	 */
	private String no;
	/**
	 * 类型名称
	 */
	private String name;
	/**
	 * 父类型编号
	 */
	private String partenNo;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 * 用户表编号，外键
	 */
	private String userNo;

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
	public java.lang.String getPartenNo() {
		return this.partenNo;
	}
	
	public void setPartenNo(java.lang.String value) {
		this.partenNo = value;
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