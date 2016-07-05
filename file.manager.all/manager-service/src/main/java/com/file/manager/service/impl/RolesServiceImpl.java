package com.file.manager.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.dao.RolesDao;
import com.file.manager.entity.Roles;
import com.file.manager.entity.UserRoles;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.RolesService;

@Transactional
@Service("rolesService")
public class RolesServiceImpl implements RolesService {
	@Autowired
	private RolesDao rolesDao;

	public PageView query(PageView pageView, Roles roles) {
		List<Roles> list = rolesDao.query(pageView, roles);
		pageView.setRecords(list);
		return pageView;
	}

	public void add(Roles roles) {
		if(roles !=null){
			roles.setNo(UUID.randomUUID().toString().replace("-", ""));
			roles.setCreateTime(new Timestamp(System.currentTimeMillis()));
		}
		rolesDao.add(roles);
		
	}

	public void delete(String no) {
		rolesDao.delete(no);
		
	}

	public Roles getById(String no) {
		return rolesDao.getById(no);
	}

	public void modify(Roles roles) {
		rolesDao.modify(roles);
	}

	public List<Roles> findAll() {
		return rolesDao.findAll();
	}

	public void saveUserRole(UserRoles userRoles) {
		rolesDao.deleteUserRole(userRoles.getUserNo());
		rolesDao.saveUserRole(userRoles);
	}

}
