package com.file.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.HcJobtime;

@Repository("hcJobtimeDao")
public interface HcJobtimeDao {

	HcJobtime get(Long id);

	List<HcJobtime> search(Map<String, Object> parameters);

	Page<HcJobtime> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

	void insert(HcJobtime hcJobtime);

	void delete(Long id);

	void update(HcJobtime hcJobtime);
	
	List<HcJobtime> queryAll();

}
