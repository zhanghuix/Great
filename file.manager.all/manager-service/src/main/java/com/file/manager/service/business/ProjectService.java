package com.file.manager.service.business;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.dao.business.ProjectDao;
import com.file.manager.entity.Project;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.util.Common;

/**
* @Title: 
* @Description: 
* @Date 2015 - 2015
* @Version V1.0
*/
// Spring Service Bean的标识.
@Service
@Transactional
public class ProjectService {

	private static Logger logger = LoggerFactory
			.getLogger(ProjectService.class);

	@Autowired
	private ProjectDao projectDao;

	/**
	 * 分页查询
	 * 
	 * @param files
	 *            查询条件
	 * @param pageView
	 *            分页参数
	 * @return
	 */
	public PageView queryPage(Project project, PageView pageView) {

		List<Project> list = projectDao.queryPage(pageView, project);
		pageView.setRecords(list);
		return pageView;
	}

	/**
	 * 不分页查询
	 * 
	 * @param searchParas
	 *            查询条件
	 * @return
	 */
	public List<Project> queryAll(Project project) {
		return projectDao.queryAll(project);
	}

	public Project get(String no) {
		return projectDao.get(no);
	}

	public void add(Project project) throws Exception {

		if (project == null) {
			throw new Exception(
					"com.file.manager.service.business.ProjectService.insert(project)参数对象不能为空");
		}
		project.setNo(UUID.randomUUID().toString().replace("-", ""));
		project.setCreatetime(new Date());
		projectDao.insert(project);
	}

	public void addList(List<String> listPath, String folderValue,
			String userName) throws Exception {
		if (listPath != null && listPath.size() > 0) {

			for (String s : listPath) {

				String name = StringUtils.substringAfter(s, "\\");// 获取文件名称

				Project project = new Project();
				project.setName(name);
				project.setPath(s);
				project.setType("1");//默认为普通类型
				project.setFolderNo(folderValue);
				project.setUserNo(userName);
				this.add(project);
			}
		}
	}

	public void modify(Project project) throws Exception {
		if (project == null) {
			throw new Exception(
					"com.file.manager.service.business.ProjectService.modify(Project)参数不能为空");
		}

		if (Common.isEmpty(project.getNo())) {
			throw new Exception(
					"com.file.manager.service.business.ProjectService.modify(Project)参数不能为空");
		}
	
		projectDao.update(project);
	}

	public void del(String[] noArray) throws Exception {

		if (noArray == null || noArray.length == 0) {
			throw new Exception(
					"com.file.manager.service.business.ProjectService.del(String)参数不能为空");
		}
		for(String no : noArray){
			projectDao.delete(no);
		}
		
	}
	
	/**
	 * 
	 * @methodName:moveProject
	 * @Description:移动文件
	 * @param nos
	 * @param folderNo
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月19日 下午5:18:52
	 */
	public void moveProject(String nos, String folderNo) throws Exception {

		if (Common.isEmpty(nos) || Common.isEmpty(folderNo)) {
			throw new Exception(
					"com.file.manager.service.business.ProjectService.moveProject(String, String)参数不能为空");
		}

		String[] noArray = nos.split(",");
		for (String no : noArray) {
			Project project = new Project();
			project.setFolderNo(folderNo);
			project.setNo(no);
			projectDao.update(project);
		}

	}
}
