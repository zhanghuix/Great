package com.file.manager.entity;


public class Video extends FileAll{

	//alias
	public static final String TABLE_ALIAS = "Video";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NO = "no";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_TYPE_NO = "typeNo";
	public static final String ALIAS_SCENES = "scenes";
	public static final String ALIAS_ADDRESS = "address";
	public static final String ALIAS_VIDEOTIME = "videotime";
	public static final String ALIAS_SIZE = "size";
	public static final String ALIAS_PATH = "path";
	public static final String ALIAS_FOLDER_NO = "folderNo";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_USER_NO = "userNo";
	public static final String ALIAS_DECRIPTION = "decription";

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 视频编号，唯一
	 */
	private String no;
	/**
	 * 视频名称
	 */
	private String name;
	/**
	 * 视频类型，外键
	 */
	private String typeNo;
	/**
	 * 视频场景：0=通用，1=结婚，2=个人，3=资料
	 */
	private String scenes;
	/**
	 * 拍摄地点
	 */
	private String address;
	/**
	 * 拍摄时间
	 */
	private java.util.Date videotime;
	/**
	 * 视频大小
	 */
	private Double size;
	/**
	 * 视频路径
	 */
	private String path;
	/**
	 * 视频所属文件夹，外键
	 */
	private String folderNo;
	/**
	 * 上传时间
	 */
	private java.util.Date createtime;
	/**
	 * 上传人，外键，用户表
	 */
	private String userNo;
	/**
	 * 描述
	 */
	private String decription;
	
	/**
	 * 别名
	 */
	private String otherName;
	
	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	private String tempVideotime;

	public String getTempVideotime() {
		return tempVideotime;
	}

	public void setTempVideotime(String tempVideotime) {
		this.tempVideotime = tempVideotime;
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
	public java.lang.String getScenes() {
		return this.scenes;
	}
	
	public void setScenes(java.lang.String value) {
		this.scenes = value;
	}
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	public java.util.Date getVideotime() {
		return this.videotime;
	}
	
	public void setVideotime(java.util.Date value) {
		this.videotime = value;
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