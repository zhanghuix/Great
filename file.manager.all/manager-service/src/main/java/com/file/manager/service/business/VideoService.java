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

import com.file.manager.dao.business.VideoDao;
import com.file.manager.entity.Video;
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
public class VideoService {

	private static Logger logger = LoggerFactory.getLogger(VideoService.class);

	@Autowired
	private VideoDao videoDao;

	/**
	 * 分页查询
	 * 
	 * @param video
	 *            查询条件
	 * @param pageView
	 *            分页参数
	 * @return
	 */
	public PageView queryPage(Video video, PageView pageView) {

		List<Video> list = videoDao.queryPage(pageView, video);
		pageView.setRecords(list);
		return pageView;
	}

	/**
	 * 不分页查询
	 * 
	 * @param video
	 *            查询条件
	 * @return
	 */
	public List<Video> queryAll(Video video) {
		return videoDao.queryAll(video);
	}

	public Video get(String no) {
		return videoDao.get(no);
	}

	public void add(Video video) throws Exception {
		if (video == null) {
			throw new Exception(
					"com.file.manager.service.business.VideoService.insert(Video)参数对象不能为空");
		}
		video.setNo(UUID.randomUUID().toString().replace("-", ""));
		video.setCreatetime(new Date());
		videoDao.insert(video);
	}

	public void addList(List<String> listPath, String folderValue,
			String userName) throws Exception {
		if (listPath != null && listPath.size() > 0) {

			for (String s : listPath) {

				String name = StringUtils.substringAfter(s, "\\");// 获取文件名称

				Video video = new Video();
				video.setName(name);
				video.setPath(s);
				video.setTypeNo("");
				video.setFolderNo(folderValue);
				video.setUserNo(userName);
				this.add(video);
			}
		}
	}

	public void modify(Video video) throws Exception {
		if (video == null) {
			throw new Exception(
					"com.file.manager.service.business.VideoService.update(Video)参数不能为空");
		}

		if (Common.isEmpty(video.getNo())) {
			throw new Exception(
					"com.file.manager.service.business.VideoService.update(Video)参数不能为空");
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (!Common.isEmpty(video.getTempVideotime())) {
			video.setVideotime(sdf.parse(video.getTempVideotime()));
		}

		videoDao.update(video);
	}

	public void del(String[] noArray) throws Exception {

		if (noArray == null || noArray.length == 0) {
			throw new Exception(
					"com.file.manager.service.business.VideoService.delete(no)参数不能为空");
		}

		for (String no : noArray) {
			videoDao.delete(no);
		}

	}

	/**
	 * 
	 * @methodName:moveVideo
	 * @Description:移动文件
	 * @param nos
	 * @param folderNo
	 * @throws Exception
	 * @author zhangHui
	 * @date 2016年1月19日 下午5:20:18
	 */
	public void moveVideo(String nos, String folderNo) throws Exception {

		if (Common.isEmpty(nos) || Common.isEmpty(folderNo)) {
			throw new Exception(
					"com.file.manager.service.business.VideoService.moveVideo(String, String)参数不能为空");
		}

		String[] noArray = nos.split(",");
		for (String no : noArray) {
			Video video = new Video();
			video.setFolderNo(folderNo);
			video.setNo(no);
			videoDao.update(video);
		}

	}

}
