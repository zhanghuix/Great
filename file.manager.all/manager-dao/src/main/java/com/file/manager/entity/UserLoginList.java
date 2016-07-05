package com.file.manager.entity;

import java.util.Date;

/**
 * 登陆信息实体类
 * @author Dylan
 *
 */
@SuppressWarnings("serial")
public class UserLoginList implements java.io.Serializable {
	private int id;
	private String userNo;
	private String loginName;
	
	private Date time;
	private String ip;
	public UserLoginList(){}
	/*public UserLoginList(int id,String userNo,Date time,String ip,String userName,String loginName){
		this.id = id;
		this.userNo = userNo;
		this.time = time;
		this.ip = ip;
		this.userName = userName;
		this.loginName=loginName;
	}*/
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setULoginName(String loginName) {
		this.loginName = loginName;
	}
	
}
