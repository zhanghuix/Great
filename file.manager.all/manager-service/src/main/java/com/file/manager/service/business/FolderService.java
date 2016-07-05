package com.file.manager.service.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.file.manager.dao.business.FolderDao;
import com.file.manager.entity.Folder;
import com.file.manager.enums.ModelType;
import com.file.manager.mybatis.plugin.PageView;
import com.file.manager.util.ApplicationPropertyPlaceholderConfigurer;
import com.file.manager.util.Common;

/**
 * @Title:
 * @Description:
 * @Author 张辉
 * @Date 2015 - 2015
 * @Version V1.0
 */
// Spring Service Bean的标识.
@Service
@Transactional
public class FolderService {

	private static Logger logger = LoggerFactory.getLogger(FolderService.class);

	

	@Autowired
	private FolderDao folderDao;

	/**
	 * 分页查询
	 * 
	 * @param searchParams
	 *            查询条件
	 * @param pageable
	 *            分页参数
	 * @return
	 * @throws Exception
	 */
	public PageView queryPage(PageView pageView, Folder folder)
			throws Exception {

		if (folder == null) {
			throw new Exception("参数不能为空");
		}

		logger.info(folder.toString());

		List<Folder> list = folderDao.queryPage(pageView, folder);
		pageView.setRecords(list);
		return pageView;
	}

	/**
	 * 
	 * @methodName:queryAll
	 * @Description:查询所有
	 * @param folder
	 * @throws Exception
	 * @author zhangHui
	 * @date 2015年12月17日 下午5:05:47
	 */
	public List<Folder> queryAll(Folder folder) throws Exception {

		logger.info(folder.toString());

		List<Folder> list = folderDao.queryAll(folder);

		return list;
	}

	/**
	 * 
	 * @methodName:add
	 * @Description:添加文件夹
	 * @param folder
	 * @throws Exception
	 * @author zhangHui
	 * @date 2015年12月16日 下午2:15:00
	 */
	public void add(Folder folder) throws Exception {

		if (folder == null) {
			throw new Exception("添加文件夹，对象不能为空");
		}
		if (Common.isEmpty(folder.getName())) {
			throw new Exception("添加文件夹，名称不能为空");
		}
		if (Common.isEmpty(folder.getType())) {
			throw new Exception("添加文件夹，类型不能为空");
		}

		// 默认文件夹只允许添加一次
		/*if (ModelType.TYPE.getTypeDefaylt().equals(folder.getType())) {
			folder.setNo(ModelType.TYPE.getTypeDefaylt());

			// 如果no=0的记录已经存在，则不做新增操作
			if (this.get(folder.getNo()) != null) {
				return;
			}
		} else {
			
		}*/
		
		folder.setNo(UUID.randomUUID().toString().replace("-", ""));

		folder.setCreatetime(new Timestamp(System.currentTimeMillis()));
		folderDao.insert(folder);
	}

	/**
	 * 
	 * @methodName:getFolderTree
	 * @Description:查询文件夹类型为树形结构
	 * @param folder
	 * @author zhangHui
	 * @throws Exception
	 * @date 2016年1月18日 下午2:26:13
	 */
	public String getFolderTree(Folder folder) throws Exception {

		List<Folder> list = folderDao.queryTrue(folder);
		// 按照true格式排序{ id:11, pId:1, name:"叶子节点1",
		// icon:"plugin/jquery/css/zTreeStyle/img/diy/2.png"},
		if (list != null && list.size() > 0) {

			List<LinkedHashMap<String, Object>> nodesList = new ArrayList<LinkedHashMap<String, Object>>();

			// 根节点
			LinkedHashMap<String, Object> rootMap = new LinkedHashMap<String, Object>();
			rootMap.put("id", folder.getType());
			rootMap.put("pId", "-1");
			rootMap.put("name", ModelType.TYPE.getTypeName(folder.getType()));
			rootMap.put("open", true);

			String iconOpen = ApplicationPropertyPlaceholderConfigurer
					.getContextProperty("rootIconOpen").toString();
			String iconClose = ApplicationPropertyPlaceholderConfigurer
					.getContextProperty("rootIconClose").toString();
			String icon = ApplicationPropertyPlaceholderConfigurer
					.getContextProperty("icon").toString();

			rootMap.put("iconOpen", iconOpen);
			rootMap.put("iconClose", iconClose);

			for (Folder tempfolder : list) {

				LinkedHashMap<String, Object> nodeMap = new LinkedHashMap<String, Object>();

				nodeMap.put("id", tempfolder.getNo());
				nodeMap.put("pId", folder.getType());
				nodeMap.put("name", tempfolder.getName());
				nodeMap.put("icon", icon);
				nodesList.add(nodeMap);
			}

			nodesList.add(0, rootMap);// 根节点放在第一行

			String folderTrue = JSONObject.toJSONString(nodesList);

			return folderTrue;
		}

		return "";
	}

	public void modify(Folder folder) {
		folderDao.update(folder);
	}

	public void del(String no) {
		folderDao.delete(no);
	}

	public Folder get(String no) {
		return folderDao.get(no);
	}

	

}
