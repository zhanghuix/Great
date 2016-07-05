package com.file.manager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.file.manager.entity.HcQuartzJob;
import com.file.manager.mybatis.plugin.PageView;

@Repository("hcQuartzJobDao")
public interface HcQuartzJobDao {

	HcQuartzJob get(Long id);

	List<HcQuartzJob> search(Map<String, Object> parameters);

	Page<HcQuartzJob> searchPage(@Param("searchFields") Map<String, Object> searchParams, Pageable pageRequest);

	void insert(HcQuartzJob hcQuartzJob);

	void delete(Long id);

	void update(HcQuartzJob hcQuartzJob);
	
	
	List<HcQuartzJob> query(PageView pageView,HcQuartzJob hcQuartzJob);
	
	HcQuartzJob queryJobByJobKey(String jobKey);
	
	void updateState(HcQuartzJob hcQuartzJob);
}
