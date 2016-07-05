package com.file.manager.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.Tools;
import com.file.manager.mybatis.plugin.PageView;

@Repository("toolDao")
public interface ToolsDao {

	Tools get(String no);

	List<Tools> queryPage(PageView pageView,@Param("searchFields")Tools tool);
	
	List<Tools> queryAll(Tools tool);

	void insert(Tools tool);

	void delete(String no);

	void update(Tools tool);
	

}
