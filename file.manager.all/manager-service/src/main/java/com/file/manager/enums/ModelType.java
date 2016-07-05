package com.file.manager.enums;

/**
 * 
 * @ClassName:ModelType
 * @Description:模块type，在folder表中以type标示
 * @author zhangHui
 * @date:2016年1月28日
 */
public enum ModelType {

	TYPE();
	/** 文件夹根类型 */
//	private final String TYPEDEFAYLT = "0";// 通用
	private final String TYPEPHOTO = "1";// 照片
	private final String TYPEVIDEO = "2";// 视频
	private final String TYPEFILE = "3";// 文件
	private final String TYPEARTICLE = "4";// 文章
	private final String TYPETOOLS = "5";// 工具
	private final String TYPEPROJECT = "6";// 项目

	/*public String getTypeDefaylt() {
		return this.TYPEDEFAYLT;
	}*/

	public String getTypePhoto() {
		return this.TYPEPHOTO;
	}

	public String getTypeFile() {
		return this.TYPEFILE;
	}

	public String getTypeArticle() {
		return this.TYPEARTICLE;
	}

	public String getTypeTools() {
		return this.TYPETOOLS;
	}

	public String getTypeProject() {
		return this.TYPEPROJECT;
	}

	public String getTypeVideo() {
		return this.TYPEVIDEO;
	}

	String[] name = { "默认", "照片", "视频", "文件", "文章", "工具", "项目" };

	public String getTypeName(String i) {
		try {
			return name[Integer.parseInt(i)];
		} catch (Exception e) {
			e.printStackTrace();
			return i;
		}
	}

}
