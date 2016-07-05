package com.file.manager.entity;

import java.sql.Timestamp;


@SuppressWarnings("serial")
public class UserRoles implements java.io.Serializable {

	private String roleNo;
	private String userNo;
	private Timestamp createTime;
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(String roleNo) {
		this.roleNo = roleNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

}