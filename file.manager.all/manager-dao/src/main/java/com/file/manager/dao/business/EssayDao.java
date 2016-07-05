package com.file.manager.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.Essay;
import com.file.manager.mybatis.plugin.PageView;

@Repository("essayDao")
public interface EssayDao {

	Essay get(String no);

	List<Essay> queryPage(PageView pageView,@Param("searchFields")Essay essay);

	List<Essay> queryAll(Essay essay);;

	void insert(Essay essay);

	void delete(String no);

	void update(Essay essay);
}
