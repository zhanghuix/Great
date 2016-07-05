package com.file.manager.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.Photo;
import com.file.manager.mybatis.plugin.PageView;

@Repository("photoDao")
public interface PhotoDao {

	Photo get(String no);

	List<Photo> queryPage(PageView pageView,@Param("searchFields")Photo photo);
	
	List<Photo> queryAll(Photo photo);

	void insert(Photo photo);

	void delete(String no);

	void update(Photo photo);
	

}
