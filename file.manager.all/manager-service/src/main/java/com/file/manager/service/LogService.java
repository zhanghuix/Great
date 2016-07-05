package com.file.manager.service;


import java.util.List;

import com.file.manager.entity.Log;
import com.file.manager.mybatis.plugin.PageView;

public interface LogService{
	public PageView query(PageView pageView,Log log);
	
	public void add(Log log);
	
	public void delete(String id);
	
	public void modify(Log log);
	
	public Log getById(String id);
	
	public List<Log> findAll(Log log);
	
}
