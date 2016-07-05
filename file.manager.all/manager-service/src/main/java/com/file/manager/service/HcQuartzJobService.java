package com.file.manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.dao.HcJobtimeDao;
import com.file.manager.dao.HcQuartzJobDao;
import com.file.manager.entity.HcJobtime;
import com.file.manager.entity.HcQuartzJob;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.quartz.JobParamBean;

/**
 * @Title:
 * @Description:
 * @Author 庞鑫
 * @Date 2015 - 2015
 * @Version V1.0
 * @Copyright © 2013 北京零彩宝技术有限公司. All rights reserved.
 */
// Spring Service Bean的标识.
@Service
@Transactional
public class HcQuartzJobService {

	private static Logger logger = LoggerFactory
			.getLogger(HcQuartzJobService.class);

	@Autowired
	private HcQuartzJobDao hcQuartzJobDao;
	@Autowired
	private HcJobtimeDao hcJobtimeDao;

	private final String STATE_ON = "Y";// 任务开启
	private final String STATE_OFF = "N";// 任务关闭

	/**
	 * 分页查询
	 * 
	 * @param searchParams
	 *            查询条件
	 * @param pageable
	 *            分页参数
	 * @return
	 */
	public Page<HcQuartzJob> searchPage(Map<String, Object> searchParams,
			Pageable pageable) {
		return hcQuartzJobDao.searchPage(searchParams, pageable);
	}

	/**
	 * 不分页查询
	 * 
	 * @param searchParas
	 *            查询条件
	 * @return
	 */
	public List<HcQuartzJob> search(Map<String, Object> searchParas) {
		return hcQuartzJobDao.search(searchParas);
	}

	public HcQuartzJob get(Long id) {
		return hcQuartzJobDao.get(id);
	}

	public void insert(HcQuartzJob hcQuartzJob) {
		hcQuartzJobDao.insert(hcQuartzJob);
	}

	public void update(HcQuartzJob hcQuartzJob) {
		hcQuartzJobDao.update(hcQuartzJob);
	}

	public void delete(Long id) {
		hcQuartzJobDao.delete(id);
	}

	public PageView query(PageView pageView,HcQuartzJob hcQuartzJob) {
		List<HcQuartzJob> list = hcQuartzJobDao.query(pageView,hcQuartzJob);
		pageView.setRecords(list);
		return pageView;
	}

	/**
	 * 
	 * @methodName:jobON
	 * @Description:开启任务，更新状态操作
	 * @param jobKey
	 * @author zhangHui
	 * @date 2015年10月30日 下午5:43:19
	 */
	public void jobON(String[] jobKey) throws Exception {

		updateState(jobKey, this.STATE_ON);
	}

	/**
	 * 
	 * @methodName:jobOFF
	 * @Description:关闭任务，更新状态操作
	 * @param jobKey
	 * @author zhangHui
	 * @date 2015年10月30日 下午5:44:04
	 */
	public void jobOFF(String[] jobKey) throws Exception {

		updateState(jobKey, this.STATE_OFF);
	}

	public void updateState(String[] jobKey, String state) throws Exception {

		if (jobKey == null || jobKey.length == 0) {
			throw new Exception(
					"com.hecha.service.HcQuartzJobService.updateState(String[],String)方法参数不能为空");
		}
		for (String key : jobKey) {
			HcQuartzJob hcQuartzJob = new HcQuartzJob();
			hcQuartzJob.setJobKey(key);
			hcQuartzJob.setState(state);

			hcQuartzJobDao.updateState(hcQuartzJob);
		}
	}

	/**
	 * 
	 * @methodName:queryJobByJobKey
	 * @Description:根据jobkey获取job信息，并封装在JobParamBean
	 * @param jobKey
	 * @return
	 * @throws Exception
	 * @author zhangHui
	 * @date 2015年10月29日 下午5:17:30
	 */
	public List<JobParamBean> queryJobByJobKey(String[] jobKey)
			throws Exception {

		if (jobKey == null || jobKey.length == 0) {
			throw new Exception(
					"com.hecha.service.HcQuartzJobService.queryJobByJobKey(String[])方法参数不能为空");
		}
		List<JobParamBean> list = new ArrayList<JobParamBean>();
		for (String key : jobKey) {
			HcQuartzJob job = hcQuartzJobDao.queryJobByJobKey(key);
			JobParamBean bean = new JobParamBean();
			bean.setJobGroup(job.getJobGroup());
			bean.setJobKey(job.getJobKey());
			bean.setJobTime(job.getJobTime());
			list.add(bean);
		}

		logger.info("queryJobByJobKey方法获取job信息数量：" + list.size());

		return list;
	}
	
	
	public List<HcJobtime> queryJobTimeAll(){
		return hcJobtimeDao.queryAll();
	}

}
