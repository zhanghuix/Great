package com.file.manager.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.Types;
import com.file.manager.mybatis.plugin.PageView;

@Repository("typesDao")
public interface TypesDao {

	Types get(String no);

	List<Types> queryPage(PageView pageView,@Param("searchFields")Types types);
	
	List<Types> queryAll(Types types);

	void insert(Types types);

	void delete(String no);

	void update(Types types);

}
