package com.file.manager.quartz;

import java.util.List;
import java.util.Map;

import com.file.manager.quartz.job.HeChaJobAbstract;
/**
 * 
 * @ClassName:TestJobImpl
 * @Description:测试类
 * @author Administrator
 * @date:2015年10月23日
 */
public class TestJobImpl extends HeChaJobAbstract {

	@Override
	public List<Map<String, Object>> Job1() {
		System.out.println("执行了job1");
		return null;
	}

	@Override
	public List<Map<String, Object>> Job2() {
		System.out.println("执行了job2");
		return null;
	}

	

}
