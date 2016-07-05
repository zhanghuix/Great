package com.file.manager.dao;


import com.file.manager.base.BaseDao;
import com.file.manager.entity.Roles;
import com.file.manager.entity.User;


public interface UserDao extends BaseDao<User>{
	public int countUser(String userName,String userPassword);
	
	public User querySingleUser(String userName);
	
	public Roles findbyUserRole(String userNo);
	
}
