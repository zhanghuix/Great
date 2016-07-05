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

import com.file.manager.dao.business.PhotoDao;
import com.file.manager.entity.Photo;
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
public class PhotoService {

	private static Logger logger = LoggerFactory.getLogger(PhotoService.class);

	@Autowired
	private PhotoDao photoDao;

	/**
	 * 分页查询
	 * 
	 * @param photo
	 *            查询条件
	 * @param pageView
	 *            分页参数
	 * @return
	 */
	public PageView queryPage(Photo photo, PageView pageView) throws Exception {

		List<Photo> list = photoDao.queryPage(pageView, photo);

		pageView.setRecords(list);

		return pageView;
	}

	/**
	 * 不分页查询
	 * 
	 * @param photo
	 *            查询条件
	 * @return
	 */
	public List<Photo> queryAll(Photo photo) {
		return photoDao.queryAll(photo);
	}

	public Photo get(String no) {
		return photoDao.get(no);
	}

	public void add(Photo photo) throws Exception {
		if (photo == null) {
			throw new Exception(
					"com.file.manager.service.business.PhotoService.insert(Photo)参数对象不能为空");
		}
		photo.setNo(UUID.randomUUID().toString().replace("-", ""));
		photo.setCreatetime(new Date());
		photoDao.insert(photo);
	}

	public void addList(List<String> listPath, String folderValue,
			String userName) throws Exception {
		if (listPath != null && listPath.size() > 0) {

			for (String s : listPath) {

				String name = StringUtils.substringAfter(s, "\\");// 获取文件名称

				Photo photo = new Photo();
				photo.setName(name);
				photo.setPath(s);
				photo.setTypeNo("");
				photo.setFolderNo(folderValue);
				photo.setUserNo(userName);
				this.add(photo);
			}
		}
	}

	public void modify(Photo photo) throws Exception {
		if (photo == null) {
			throw new Exception(
					"com.file.manager.service.business.PhotoService.update(Photo)参数不能为空");
		}

		if (Common.isEmpty(photo.getNo())) {
			throw new Exception(
					"com.file.manager.service.business.PhotoService.update(Photo)参数不能为空");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (!Common.isEmpty(photo.getTempPhototime())) {
			photo.setPhototime(sdf.parse(photo.getTempPhototime()));
		}

		photoDao.update(photo);
	}

	public void del(String[] noArray) throws Exception {
		
		if (noArray == null || noArray.length == 0) {
			throw new Exception(
					"com.file.manager.service.business.PhotoService.delete(no)参数不能为空");
		}

		for (String no : noArray) {
			photoDao.delete(no);
		}

	}

	/**
	 * 
	 * @methodName:movePhoto
	 * @Description:移动文件
	 * @param nos
	 *            逗号隔开的文件编号
	 * @param folderNo
	 *            文件夹编号
	 * @author zhangHui
	 * @throws Exception
	 * @date 2016年1月19日 下午5:12:20
	 */
	public void movePhoto(String nos, String folderNo) throws Exception {

		if (Common.isEmpty(nos) || Common.isEmpty(folderNo)) {
			throw new Exception(
					"com.file.manager.service.business.PhotoService.movePhoto(String, String)参数不能为空");
		}

		String[] noArray = nos.split(",");
		for (String no : noArray) {

			Photo photo = new Photo();
			photo.setFolderNo(folderNo);
			photo.setNo(no);

			photoDao.update(photo);
		}

	}

}
