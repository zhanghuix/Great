package com.file.manager.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.file.manager.base.impl.BaseDaoImpl;
import com.file.manager.dao.ResourcesDao;
import com.file.manager.entity.ResourceRoles;
import com.file.manager.entity.Resources;


@Repository("resourcesDao")
public class ResourcesDaoImpl extends BaseDaoImpl<Resources> implements ResourcesDao
{

	public List<Resources> findAll() {
		return getSqlSession().selectList("resources.findAll");
	}
	//<!-- 根据用户Id获取该用户的权限-->
	public List<Resources> getUserResources(String userNo){
		return getSqlSession().selectList("resources.getUserResources",userNo);
	}
	//<!-- 根据用户名获取该用户的权限-->
	public List<Resources> getResourcesByUserName(String loginName){
		return getSqlSession().selectList("resources.getResourcesByUserName",loginName);
	}
	//<!-- 根据用户Id获取该用户的权限-->
	public List<Resources> getRoleResources(String roleNo){
		return getSqlSession().selectList("resources.getRoleResources",roleNo);
	}
	public void saveRoleRescours(ResourceRoles resourceRoles){
		 getSqlSession().insert("resources.saveRoleRescours",resourceRoles);
	}
	public void deleteRoleRescours(String roleNo){
		getSqlSession().delete("resources.deleteRoleRescours",roleNo);
	}
}
