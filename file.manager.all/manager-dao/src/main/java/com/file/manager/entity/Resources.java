package com.file.manager.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Resources implements java.io.Serializable {

	private String no;
	private String name;
	private String parentId; //父类Id
	private String parentName;
	private String resKey;//这个权限KEY是唯一的，新增时要注意，
	private String resUrl;//URL地址．例如：/videoType/query　　不需要项目名和http://xxx:8080
	private Integer level;
	private String type;//权限类型，0．表示目录　1，表示菜单．2，表示按扭．．在spring security3安全权限中，涉及精确到按扭控制
	private String description;
	private Timestamp createTime;
	public Timestamp getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	private Set<Roles> roles = new HashSet<Roles>(0);
	private Set<Resources> childs = new HashSet<Resources>(0);

	public Resources() {
	}

	
	
	public String getNo() {
		return no;
	}



	public void setNo(String no) {
		this.no = no;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getResKey() {
		return resKey;
	}

	public void setResKey(String resKey) {
		this.resKey = resKey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Set<Resources> getChilds() {
		return childs;
	}

	public void setChilds(Set<Resources> childs) {
		this.childs = childs;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}