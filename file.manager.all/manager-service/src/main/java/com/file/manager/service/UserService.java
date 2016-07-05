package com.file.manager.service;


import com.file.manager.entity.Roles;
import com.file.manager.entity.User;
import com.file.manager.mybatis.plugin.PageView;

public interface UserService{
	public PageView query(PageView pageView,User user);
	
	public void add(User user);
	
	public void delete(String id);
	
	public void modify(User user);
	
	public User getById(String id);
	
	public int countUser(String loginName,String userPassword);
	
	public User querySingleUser(String loginName);
	
	public Roles findbyUserRole(String userNo);
}
