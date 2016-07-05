package com.file.manager.service;


import java.util.List;

import com.file.manager.entity.Roles;
import com.file.manager.entity.UserRoles;
import com.file.manager.mybatis.plugin.PageView;

public interface RolesService{
	public PageView query(PageView pageView,Roles roles);
	
	public void add(Roles roles);
	
	public void delete(String id);
	
	public void modify(Roles roles);
	
	public Roles getById(String id);
	
	public List<Roles> findAll();
	
	public void saveUserRole(UserRoles userRoles);
}
