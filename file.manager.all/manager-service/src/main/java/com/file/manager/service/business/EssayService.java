package com.file.manager.service.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.dao.business.EssayDao;
import com.file.manager.entity.Essay;
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
public class EssayService {

	private static Logger logger = LoggerFactory
			.getLogger(EssayService.class);

	@Autowired
	private EssayDao essayDao;

	/**
	 * 分页查询
	 * 
	 * @param files
	 *            查询条件
	 * @param pageView
	 *            分页参数
	 * @return
	 */
	public PageView queryPage(Essay essay, PageView pageView) {

		List<Essay> list = essayDao.queryPage(pageView, essay);
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
	public List<Essay> queryAll(Essay essay) {
		return essayDao.queryAll(essay);
	}

	public Essay get(String no) {
		return essayDao.get(no);
	}

	public void add(Essay essay) throws Exception {

		if (essay == null) {
			throw new Exception(
					"com.file.manager.service.business.EssayService.add(Essay)参数对象不能为空");
		}
		essay.setNo(UUID.randomUUID().toString().replace("-", ""));
		essay.setCreatetime(new Date());
		essayDao.insert(essay);
	}

	/**
	 * 
	 * @methodName:addList
	 * @Description:添加
	 * @param listPath
	 * @param folderValue
	 * @param userName
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月28日 下午6:21:51
	 */
	public void addList(List<String> listPath, String folderValue,
			String userName) throws Exception {
		if (listPath != null && listPath.size() > 0) {

			for (String s : listPath) {

				String name = StringUtils.substringAfter(s, "\\");// 获取文件名称

				Essay essay = new Essay();
				essay.setName(name);
				essay.setPath(s);
				essay.setTypeNo("");
				essay.setFolderNo(folderValue);
				essay.setUserNo(userName);
				this.add(essay);
			}
		}
	}

	/**
	 * 
	 * @methodName:modify
	 * @Description:修改
	 * @param essay
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月28日 下午6:21:36
	 */
	public void modify(Essay essay) throws Exception {
		if (essay == null) {
			throw new Exception(
					"com.file.manager.service.business.EssayService.modify(Essay)参数不能为空");
		}

		if (Common.isEmpty(essay.getNo())) {
			throw new Exception(
					"com.file.manager.service.business.EssayService.modify(Essay)参数不能为空");
		}
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if (!Common.isEmpty(essay.getTempWritetime())) {
			essay.setWritetime(sdf.parse(essay.getTempWritetime()));
		}

		essayDao.update(essay);
	}

	/**
	 * 
	 * @methodName:del
	 * @Description:删除
	 * @param noArray
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月28日 下午6:21:23
	 */
	public void del(String[] noArray) throws Exception {

		if (null==noArray||noArray.length==0) {
			throw new Exception(
					"com.file.manager.service.business.EssayService.del(String)参数不能为空");
		}
		
		for (String no : noArray) {
			essayDao.delete(no);
		}
		
	}
	
	/**
	 * 
	 * @methodName:moveEssay
	 * @Description:移动文件
	 * @param nos
	 * @param folderNo
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月19日 下午5:20:55
	 */
	public void moveEssay(String nos, String folderNo) throws Exception {

		if (Common.isEmpty(nos) || Common.isEmpty(folderNo)) {
			throw new Exception(
					"com.file.manager.service.business.EssayService.moveEssay(String, String)参数不能为空");
		}

		String[] noArray = nos.split(",");
		for (String no : noArray) {
			
			Essay essay = new Essay();
			essay.setFolderNo(folderNo);
			essay.setNo(no);
			essayDao.update(essay);
		}

	}

}
