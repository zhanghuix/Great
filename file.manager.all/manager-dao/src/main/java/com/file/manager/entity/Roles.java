package com.file.manager.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Roles implements java.io.Serializable {

	private Integer enable;//是否禁用角色　1　表示禁用　2　表示不禁用
	private String name;
	private String roleKey;
	private String description;
	private String no;
	private Timestamp createTime;
	

	private Set<Resources> resources = new HashSet<Resources>(0);

	public Roles() {
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getEnable() {
		return this.enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Resources> getResources() {
		return resources;
	}

	public void setResources(Set<Resources> resources) {
		this.resources = resources;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

}