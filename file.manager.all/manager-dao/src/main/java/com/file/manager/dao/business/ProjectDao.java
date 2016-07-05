package com.file.manager.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.Project;
import com.file.manager.mybatis.plugin.PageView;

@Repository("projectDao")
public interface ProjectDao {

	Project get(String no);

	List<Project> queryPage(PageView pageView,@Param("searchFields")Project project);
	
	List<Project> queryAll(Project project);

	void insert(Project project);

	void delete(String no);

	void update(Project project);
	

}
