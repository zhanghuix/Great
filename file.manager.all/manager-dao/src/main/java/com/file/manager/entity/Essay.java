package com.file.manager.entity;


public class Essay extends FileAll{

	//alias
	public static final String TABLE_ALIAS = "Essay";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NO = "no";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_TYPE_NO = "typeNo";
	public static final String ALIAS_SOURCE = "source";
	public static final String ALIAS_WRITETIME = "writetime";
	public static final String ALIAS_CONTENT = "content";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_USER_NO = "userNo";
	public static final String ALIAS_DECRIPTION = "decription";

	/**
	 * 自增列
	 */
	private Integer id;
	/**
	 * 文章编号
	 */
	private String no;
	/**
	 * 文章名称
	 */
	private String name;
	/**
	 * 文章类型,外键
	 */
	private String typeNo;
	/**
	 * 文章出处
	 */
	private String source;
	/**
	 * 文章写作时间
	 */
	private java.util.Date writetime;
	/**
	 * 文章内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	/**
	 * 创建人，外键
	 */
	private String userNo;
	/**
	 * 文章简述
	 */
	private String decription;
	
	
	private String otherName;
	private String 	folderNo;
	private String tempWritetime;
	
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

	public String getFolderNo() {
		return folderNo;
	}

	public void setFolderNo(String folderNo) {
		this.folderNo = folderNo;
	}

	public String getTempWritetime() {
		return tempWritetime;
	}

	public void setTempWritetime(String tempWritetime) {
		this.tempWritetime = tempWritetime;
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
	public java.lang.String getSource() {
		return this.source;
	}
	
	public void setSource(java.lang.String value) {
		this.source = value;
	}
	public java.util.Date getWritetime() {
		return this.writetime;
	}
	
	public void setWritetime(java.util.Date value) {
		this.writetime = value;
	}
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String value) {
		this.content = value;
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
	public java.lang.String getDecription() {
		return this.decription;
	}
	
	public void setDecription(java.lang.String value) {
		this.decription = value;
	}
}