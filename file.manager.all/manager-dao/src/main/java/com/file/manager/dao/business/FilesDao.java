package com.file.manager.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.Files;
import com.file.manager.mybatis.plugin.PageView;

@Repository("filesDao")
public interface FilesDao {

	Files get(String no);

	List<Files> queryPage(PageView pageView,@Param("searchFields")Files files);

	List<Files> queryAll(Files files);

	void insert(Files files);

	void delete(String no);

	void update(Files files);
	

}
