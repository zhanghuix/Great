package com.file.manager.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.dao.ResourcesDao;
import com.file.manager.entity.ResourceRoles;
import com.file.manager.entity.Resources;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.service.ResourcesService;
import com.file.manager.util.Common;

@Transactional
@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {
	@Autowired
	private ResourcesDao resourcesDao;

	public PageView query(PageView pageView, Resources resources) {
		List<Resources> list = resourcesDao.query(pageView, resources);
		pageView.setRecords(list);
		return pageView;
	}

	public void add(Resources resources) {
		if(resources !=null){
			resources.setNo(UUID.randomUUID().toString().replace("-", ""));
			resources.setCreateTime(new Timestamp(System.currentTimeMillis()));
		}
		
		resourcesDao.add(resources);
		
	}

	public void delete(String no) {
		resourcesDao.delete(no);
		
	}

	public Resources getById(String no) {
		return resourcesDao.getById(no);
	}

	public void modify(Resources resources) {
		resourcesDao.modify(resources);
	}

	public List<Resources> findAll() {
		return resourcesDao.findAll();
	}

	public List<Resources> getUserResources(String userNo) {
		
		return resourcesDao.getUserResources(userNo);
	}
	//<!-- 根据用户Id获取该用户的权限-->
	public List<Resources> getRoleResources(String roleNo){
		return resourcesDao.getRoleResources(roleNo);
	}
	public void saveRoleRescours(String roleNo,List<String> list) {
			resourcesDao.deleteRoleRescours(roleNo);
			for (String rNo : list) {
				if(!Common.isEmpty(rNo)){
					ResourceRoles resourceRoles = new ResourceRoles(); 
					resourceRoles.setRescNo(rNo);
					resourceRoles.setRoleNo(roleNo);
					resourcesDao.saveRoleRescours(resourceRoles);
				}
			}
	}

	public List<Resources> getResourcesByUserName(String loginName) {
		return resourcesDao.getResourcesByUserName(loginName);
	}

}
