package com.file.manager.dao.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.file.manager.base.impl.BaseDaoImpl;
import com.file.manager.dao.UserDao;
import com.file.manager.entity.Roles;
import com.file.manager.entity.User;


@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao
{

	public int countUser(String userLoginName, String userPassword) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("loginName", userLoginName);
		map.put("pwd", userPassword);
		return (Integer)getSqlSession().selectOne("user.countUser",map);
	}

	public User querySingleUser(String loginName) {
		return (User)getSqlSession().selectOne("user.queryUserName",loginName);
	}

	public Roles findbyUserRole(String usesrNo) {
		return (Roles)getSqlSession().selectOne("roles.findbyUserRole", usesrNo);
	}
	
}
