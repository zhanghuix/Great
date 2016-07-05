package com.file.manager.service;

import com.file.manager.entity.UserLoginList;
import com.file.manager.mybatis.plugin.PageView;

public interface UserLoginListService {
	public PageView query(PageView pageView,UserLoginList userLoginList);
	
	public void add(UserLoginList userLoginList);

}
