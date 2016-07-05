package com.file.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.dao.UserLoginListDao;
import com.file.manager.entity.UserLoginList;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.UserLoginListService;

@Transactional
@Service("userLoginListService")
public class UserLoginListServiceImpl implements UserLoginListService{
	@Autowired
	private UserLoginListDao userLoginListDao;
	
	public PageView query(PageView pageView, UserLoginList userLoginList) {
		List<UserLoginList> list = userLoginListDao.query(pageView, userLoginList);
		pageView.setRecords(list);
		return pageView;
	}

	public void add(UserLoginList userLoginList) {
		userLoginListDao.add(userLoginList);
	}

}
