package com.file.manager.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.Folder;
import com.file.manager.mybatis.plugin.PageView;

@Repository("folderDao")
public interface FolderDao {
	
	Folder get(String no);

	List<Folder> queryPage(PageView pageView,@Param("searchFields")Folder folder);
	List<Folder> queryAll(@Param("searchFields")Folder folder);

	void insert(Folder folder);

	void delete(String no);

	void update(Folder folder);
	
	List<Folder> queryTrue(@Param("searchFields")Folder folder);

}
