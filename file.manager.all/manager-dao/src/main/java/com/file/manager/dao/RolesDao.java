package com.file.manager.dao;


import java.util.List;

import com.file.manager.base.BaseDao;
import com.file.manager.entity.Roles;
import com.file.manager.entity.UserRoles;


public interface RolesDao extends BaseDao<Roles>{
	public List<Roles> findAll();
	public void deleteUserRole(String userNo);
	public void saveUserRole(UserRoles userRoles);
}
