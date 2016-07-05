package com.file.manager.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.Video;
import com.file.manager.mybatis.plugin.PageView;

@Repository("videoDao")
public interface VideoDao {

	Video get(String no);

	List<Video> queryPage(PageView pageView,@Param("searchFields")Video video);
	
	List<Video> queryAll(Video video);

	void insert(Video video);

	void delete(String no);

	void update(Video video);
	

}
