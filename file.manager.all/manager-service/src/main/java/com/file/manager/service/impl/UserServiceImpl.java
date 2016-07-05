package com.file.manager.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.dao.UserDao;
import com.file.manager.entity.Roles;
import com.file.manager.entity.User;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public PageView query(PageView pageView, User user) {
		List<User> list = userDao.query(pageView, user);
		pageView.setRecords(list);
		return pageView;
	}

	public void add(User user) {
		
		if (user != null) {
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			String password = user.getPwd();
			//user.setPwd(Md5Tool.getMd5(password));
			user.setPwd(password);
			user.setNo(UUID.randomUUID().toString().replace("-", ""));
			user.setStatus("Y");//新添加用户状态为启用
		}
		
		userDao.add(user);
		
	}

	public void delete(String no) {
		userDao.delete(no);
		
	}

	public User getById(String no) {
		return userDao.getById(no);
	}

	public void modify(User user) {
		userDao.modify(user);
	}

	public int countUser(String loginName, String pwd) {
		return userDao.countUser(loginName, pwd);
	}

	public User querySingleUser(String loginName) {
		return userDao.querySingleUser(loginName);
	}

	public Roles findbyUserRole(String userNo) {
		return userDao.findbyUserRole(userNo);
	}
}
