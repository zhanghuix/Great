package com.file.manager.service;


import java.util.List;

import com.file.manager.entity.Resources;
import com.file.manager.mybatis.plugin.PageView;

public interface ResourcesService{
	public PageView query(PageView pageView,Resources resources);
	
	public void add(Resources resources);
	
	public void delete(String id);
	
	public void modify(Resources resources);
	
	public Resources getById(String id);
	
	public List<Resources> findAll();
	
	//<!-- 根据用户Id获取该用户的权限-->
	public List<Resources> getUserResources(String userId);
	//<!-- 根据用户Id获取该用户的权限-->
	public List<Resources> getRoleResources(String roleId);
	//<!-- 根据用户名获取该用户的权限-->
	public List<Resources> getResourcesByUserName(String loginName);
	public void saveRoleRescours(String roleId,List<String> list);
}
