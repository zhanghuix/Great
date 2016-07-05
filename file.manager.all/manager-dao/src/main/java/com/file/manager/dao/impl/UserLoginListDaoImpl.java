package com.file.manager.dao.impl;

import org.springframework.stereotype.Repository;

import com.file.manager.base.impl.BaseDaoImpl;
import com.file.manager.dao.UserLoginListDao;
import com.file.manager.entity.UserLoginList;

@Repository("userLoginListDao")
public class UserLoginListDaoImpl extends BaseDaoImpl<UserLoginList> implements UserLoginListDao{

}
