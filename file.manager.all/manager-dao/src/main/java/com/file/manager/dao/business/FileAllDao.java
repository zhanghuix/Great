package com.file.manager.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.FileAll;
import com.file.manager.mybatis.plugin.PageView;

@Repository("fileAllDao")
public interface FileAllDao {

	List<FileAll> queryPage(PageView pageView,@Param("searchFields")FileAll fileAll);

}
