package com.file.manager.dao.impl;
import org.springframework.stereotype.Repository;

import com.file.manager.base.impl.BaseDaoImpl;
import com.file.manager.dao.LogDao;
import com.file.manager.entity.Log;


@Repository("logDao")
public class LogDaoImpl extends BaseDaoImpl<Log> implements LogDao
{
}
