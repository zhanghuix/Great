package com.file.manager.entity;


public class Photo extends FileAll{

	//alias
	public static final String TABLE_ALIAS = "Photo";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_NO = "no";
	public static final String ALIAS_NAME = "name";
	public static final String ALIAS_TYPE_NO = "typeNo";
	public static final String ALIAS_SCENES = "scenes";
	public static final String ALIAS_ADDRESS = "address";
	public static final String ALIAS_PHOTOTIME = "phototime";
	public static final String ALIAS_SIZE = "size";
	public static final String ALIAS_PATH = "path";
	public static final String ALIAS_FOLDER_NO = "folderNo";
	public static final String ALIAS_CREATETIME = "createtime";
	public static final String ALIAS_USER_NO = "userNo";
	public static final String ALIAS_DECRIPTION = "decription";
	
	public static final String ALIAS_OTHERNAME = "otherName";

	/**
	 * 自增列
	 */
	private Integer id;
	/**
	 * 照片唯一编号
	 */
	private String no;
	/**
	 * 照片名称
	 */
	private String name;
	/**
	 * 类型：0=通用，1=家人，2=自己，3=爱人，4=孩子，5=朋友
            外键，类型表代码
	 */
	private String typeNo;
	/**
	 * 场景：0=通用，1=旅游，2=婚纱，3=合影，4=写真
	 */
	private String scenes;
	/**
	 * 拍照地点
	 */
	private String address;
	/**
	 * 拍照时间
	 */
	private java.util.Date phototime;
	/**
	 * 照片大小
	 */
	private Double size;
	/**
	 * 照片在磁盘中存放的路径
	 */
	private String path;
	/**
	 * 照片所放置的文件夹编号，文件夹表外键
	 */
	private String folderNo;
	/**
	 * 上传照片时间
	 */
	private java.util.Date createtime;
	/**
	 * 上传照片人员，用户表外键
	 */
	private String userNo;
	/**
	 * 照片描述
	 */
	private String decription;
	
	/**
	 * 别名
	 */
	private String otherName;
	
	/**
	 * 拍照时间--临时变量
	 */
	private String tempPhototime;

	public String getTempPhototime() {
		return tempPhototime;
	}

	public void setTempPhototime(String tempPhototime) {
		this.tempPhototime = tempPhototime;
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
	public java.util.Date getPhototime() {
		return this.phototime;
	}
	
	public void setPhototime(java.util.Date value) {
		this.phototime = value;
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