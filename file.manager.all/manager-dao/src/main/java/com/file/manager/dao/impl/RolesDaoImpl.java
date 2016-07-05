package com.file.manager.dao.impl;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.file.manager.base.impl.BaseDaoImpl;
import com.file.manager.dao.RolesDao;
import com.file.manager.entity.Roles;
import com.file.manager.entity.UserRoles;


@Repository("rolesDao")
public class RolesDaoImpl extends BaseDaoImpl<Roles> implements RolesDao
{

	public List<Roles> findAll() {
		return getSqlSession().selectList("roles.findAll");
	}

	public void saveUserRole(UserRoles userRoles ) {
		getSqlSession().insert("roles.saveUserRole", userRoles);
	}

	public void deleteUserRole(String userNo) {
		getSqlSession().delete("roles.deleteUserRole", userNo);
	}
}
