package com.file.manager.service.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.dao.business.EssayDao;
import com.file.manager.dao.business.FileAllDao;
import com.file.manager.dao.business.FilesDao;
import com.file.manager.dao.business.PhotoDao;
import com.file.manager.dao.business.ProjectDao;
import com.file.manager.dao.business.ToolsDao;
import com.file.manager.dao.business.VideoDao;
import com.file.manager.entity.FileAll;
import com.file.manager.enums.ModelType;
import com.file.manager.mybatis.plugin.PageView;

/**
 * @Title:
 * @Description:
 * @Date 2015 - 2015
 * @Version V1.0
 */
// Spring Service Bean的标识.
@Service
@Transactional
public class FileAllService {

	private static Logger logger = LoggerFactory
			.getLogger(FileAllService.class);

	@Autowired
	private FileAllDao fileAllDao;
	@Autowired
	private EssayDao essayDao;
	@Autowired
	private PhotoDao photoDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private ToolsDao toolsDao;
	@Autowired
	private VideoDao videoDao;
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
	public PageView queryPage(FileAll fileAll, PageView pageView) {

		List<FileAll> list = fileAllDao.queryPage(pageView, fileAll);
		pageView.setRecords(list);
		return pageView;
	}

	/**
	 * 
	 * @methodName:delAllFileByNo
	 * @Description:删除文件，根据不同模块type执行不同表删除。
	 * @param noArray
	 * @author zhangHui
	 * @throws Exception
	 * @date 2016年1月28日 下午2:03:08
	 */
	public void delAllFileByNo(String[] noArray) throws Exception {
		
		if (noArray == null || noArray.length == 0) {
			throw new Exception("参数不能为空");
		}

		for (String noType : noArray) {

			String[] temp = noType.split("-");

			String no = temp[0];
			String type = temp[1];

			if (ModelType.TYPE.getTypePhoto().equals(type)) {

				photoDao.delete(no);

			} else if (ModelType.TYPE.getTypeVideo().equals(type)) {
				videoDao.delete(no);

			} else if (ModelType.TYPE.getTypeFile().equals(type)) {

				filesDao.delete(no);

			} else if (ModelType.TYPE.getTypeArticle().equals(type)) {

				essayDao.delete(no);

			} else if (ModelType.TYPE.getTypeTools().equals(type)) {

				toolsDao.delete(no);

			} else if (ModelType.TYPE.getTypeProject().equals(type)) {

				projectDao.delete(no);
			}
		}
	}

}
