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

import com.file.manager.dao.business.ToolsDao;
import com.file.manager.entity.Tools;
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
public class ToolsService {

	private static Logger logger = LoggerFactory.getLogger(ToolsService.class);

	@Autowired
	private ToolsDao toolsDao;

	/**
	 * 分页查询
	 * 
	 * @param files
	 *            查询条件
	 * @param pageView
	 *            分页参数
	 * @return
	 */
	public PageView queryPage(Tools tools, PageView pageView) {

		List<Tools> list = toolsDao.queryPage(pageView, tools);
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
	public List<Tools> queryAll(Tools tools) {
		return toolsDao.queryAll(tools);
	}

	public Tools get(String no) {
		return toolsDao.get(no);
	}

	public void add(Tools tools) throws Exception {

		if (tools == null) {
			throw new Exception(
					"com.file.manager.service.business.ToolsService.insert(tools)参数对象不能为空");
		}
		tools.setNo(UUID.randomUUID().toString().replace("-", ""));
		tools.setCreatetime(new Date());
		toolsDao.insert(tools);
	}

	public void addList(List<String> listPath, String folderValue,
			String userName) throws Exception {
		if (listPath != null && listPath.size() > 0) {

			for (String s : listPath) {

				String name = StringUtils.substringAfter(s, "\\");// 获取文件名称

				Tools tools = new Tools();
				tools.setName(name);
				tools.setPath(s);
				tools.setTypeNo("");
				tools.setFolderNo(folderValue);
				tools.setUserNo(userName);
				this.add(tools);
			}
		}
	}

	public void modify(Tools tools) throws Exception {
		if (tools == null) {
			throw new Exception(
					"com.file.manager.service.business.ToolsService.modify(Tools)参数不能为空");
		}

		if (Common.isEmpty(tools.getNo())) {
			throw new Exception(
					"com.file.manager.service.business.ToolsService.modify(Tools)参数不能为空");
		}
	
		toolsDao.update(tools);
	}

	public void del(String[] noArray) throws Exception {

		if (noArray == null || noArray.length == 0) {
			throw new Exception(
					"com.file.manager.service.business.ToolsService.del(String)参数不能为空");
		}
		for(String no :noArray){
			toolsDao.delete(no);
		}
		
	}

	/**
	 * 
	 * @methodName:moveTools
	 * @Description:移动文件
	 * @param nos
	 * @param folderNo
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月19日 下午5:19:42
	 */
	public void moveTools(String nos, String folderNo) throws Exception {

		if (Common.isEmpty(nos) || Common.isEmpty(folderNo)) {
			throw new Exception(
					"com.file.manager.service.business.ToolsService.moveTools(String, String)参数不能为空");
		}

		String[] noArray = nos.split(",");
		for (String no : noArray) {
			Tools tools = new Tools();
			tools.setFolderNo(folderNo);
			tools.setNo(no);
			toolsDao.update(tools);
		}

	}
}
