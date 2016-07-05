package com.file.manager.upload;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.file.manager.util.Common;

/**
 * 
 * @ClassName:HanlderUploadStatus
 * @Description:缓存类，获取或者设置上传状态数据，单例模式，一次回话一个对象
 * @author zhangHui
 * @date:2015年11月25日
 */
public class HanlderUploadStatus {
	
	private static Logger log = Logger.getLogger(HanlderUploadStatus.class);

	private static HanlderUploadStatus hanlderUploadStatus;

	private Map<String, FileUploadStatusBean> statusMap = new HashMap<String, FileUploadStatusBean>();

	private HanlderUploadStatus() {
	}

	public static HanlderUploadStatus getInstance() {
		if (hanlderUploadStatus == null) {
			hanlderUploadStatus = new HanlderUploadStatus();
			
			log.info("实例化缓存处理类HanlderUploadStatus"+hanlderUploadStatus.hashCode());
			
			return hanlderUploadStatus;
		}
		return hanlderUploadStatus;
	}

	/**
	 * 
	 * @methodName:indexOf
	 * @Description:通过id查找缓存map中是否存在上传文件的状态信息
	 * @param id
	 * @return
	 * @throws Exception
	 * @author zhangHui
	 * @date 2015年11月25日 上午10:57:58
	 */
	public String indexOf(String id) throws Exception {

		if (Common.isEmpty(id)) {
			throw new Exception(
					"com.file.manager.upload.HanlderUploadStatus.indexOf(String)参数不能为空");
		}

		Set<String> keySet = statusMap.keySet();
		for (String key : keySet) {
			if (id.equals(key)) {
				return id;
			}
		}
		return "";
	}

	/**
	 * 
	 * @methodName:setUploadStatus
	 * @Description:设置上传文件状态信息
	 * @param bean
	 * @throws Exception
	 * @author zhangHui
	 * @date 2015年11月25日 上午11:09:49
	 */
	public void setUploadStatus(FileUploadStatusBean tempBean, String id) {
		if (tempBean != null) {

			FileUploadStatusBean bean = new FileUploadStatusBean();
			if (!Common.isEmpty(tempBean.getBaseDir())) {
				bean.setBaseDir(tempBean.getBaseDir());
			}
			bean.setCancel(tempBean.getCancel());
			bean.setCurrentUploadFileNum(tempBean.getCurrentUploadFileNum());
			bean.setProcessEndTime(tempBean.getProcessEndTime());
			bean.setProcessRunningTime(tempBean.getProcessRunningTime());
			bean.setProcessStartTime(tempBean.getProcessStartTime());
			bean.setReadTotalSize(tempBean.getReadTotalSize());
			if (!Common.isEmpty(tempBean.getStatus())) {
				bean.setStatus(tempBean.getStatus());
			}
			bean.setSuccessUploadFileCount(tempBean.getSuccessUploadFileCount());
			if (!Common.isEmpty(tempBean.getUploadAddr())) {
				bean.setUploadAddr(tempBean.getUploadAddr());
			}
			if (tempBean.getUploadFileUrlList() != null
					&& tempBean.getUploadFileUrlList().size() > 0) {
				bean.setUploadFileUrlList(tempBean.getUploadFileUrlList());
			}
			if (!Common.isEmpty(tempBean.getUploadFlag())) {
				bean.setUploadFlag(tempBean.getUploadFlag());
			}
			bean.setUploadTotalSize(tempBean.getUploadTotalSize());
			bean.setIsOver(tempBean.getIsOver());
			statusMap.put(id, bean);
		}
	}

	/**
	 * 
	 * @methodName:getUploadStatus
	 * @Description:获取上传状态信息
	 * @param id
	 * @return
	 * @author zhangHui
	 * @date 2015年11月25日 上午11:10:10
	 */
	public FileUploadStatusBean getUploadStatus(String id) {

		FileUploadStatusBean bean = statusMap.get(id);
		if (bean == null) {
			bean = new FileUploadStatusBean();
			return bean;
		}

		return statusMap.get(id);
	}
	
	/**
	 * 
	 * @methodName:remove
	 * @Description:移除缓存数据
	 * @param id
	 * @author zhangHui
	 * @date 2015年12月10日 上午10:20:45
	 */
	public void remove(String id){
		FileUploadStatusBean bean = statusMap.get(id);
		if (bean != null) {
			statusMap.remove(id);
		}
	}
}
