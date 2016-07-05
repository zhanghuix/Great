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

import com.file.manager.dao.business.FilesDao;
import com.file.manager.entity.Files;
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
public class FilesService {

	private static Logger logger = LoggerFactory.getLogger(FilesService.class);

	@Autowired
	private FilesDao filesDao;

	/**
	 * 分页查询
	 * 
	 * @param files
	 *            查询条件
	 * @param pageView
	 *            分页参数
	 * @return
	 */
	public PageView queryPage(Files files, PageView pageView) {

		List<Files> list = filesDao.queryPage(pageView, files);
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
	public List<Files> queryAll(Files files) {
		return filesDao.queryAll(files);
	}

	public Files get(String no) {
		return filesDao.get(no);
	}

	public void add(Files files) throws Exception {

		if (files == null) {
			throw new Exception(
					"com.file.manager.service.business.FilesService.insert(files)参数对象不能为空");
		}
		files.setNo(UUID.randomUUID().toString().replace("-", ""));
		files.setCreatetime(new Date());
		filesDao.insert(files);
	}

	public void addList(List<String> listPath, String folderValue,
			String userName) throws Exception {
		if (listPath != null && listPath.size() > 0) {

			for (String s : listPath) {

				String name = StringUtils.substringAfter(s, "\\");// 获取文件名称

				Files files = new Files();
				files.setName(name);
				files.setPath(s);
				files.setTypeNo("");
				files.setFolderNo(folderValue);
				files.setUserNo(userName);
				this.add(files);
			}
		}
	}

	public void modify(Files files) throws Exception {
		if (files == null) {
			throw new Exception(
					"com.file.manager.service.business.FilesService.update(files)参数不能为空");
		}

		if (Common.isEmpty(files.getNo())) {
			throw new Exception(
					"com.file.manager.service.business.FilesService.update(files)参数不能为空");
		}

		filesDao.update(files);
	}

	public void del(String[] noArray) throws Exception {

		if (noArray == null || noArray.length==0) {
			throw new Exception(
					"com.file.manager.service.business.FilesService.delete(no)参数不能为空");
		}
		for(String no : noArray){
			filesDao.delete(no);
		}
		
	}
	/**
	 * 
	 * @methodName:moveFiles
	 * @Description:移动文件
	 * @param nos
	 * @param folderNo
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月19日 下午5:18:08
	 */
	public void moveFiles(String nos, String folderNo) throws Exception {

		if (Common.isEmpty(nos) || Common.isEmpty(folderNo)) {
			throw new Exception(
					"com.file.manager.service.business.FilesService.moveFiles(String, String)参数不能为空");
		}

		String[] noArray = nos.split(",");
		for (String no : noArray) {
			
			Files files = new Files();
			files.setNo(no);
			files.setFolderNo(folderNo);
			filesDao.update(files);;
		}

	}

}
