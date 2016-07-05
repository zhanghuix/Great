/**
 * 通过监听Web上传文件过程中的进度显示
 */

package com.file.manager.upload;

import java.util.Date;

import org.apache.commons.fileupload.ProgressListener;
import org.apache.log4j.Logger;

public class FileUploadListener implements ProgressListener {
	
	private Logger log = Logger.getLogger(FileUploadListener.class);

	private String id;
	

	public int currentFileNum = 0;
	public long totalFileSize = 0;

	//传入上传文件状态类，和缓存上传状态的mapKey,
	public FileUploadListener(String id)
			throws Exception {
		if (null == id) {
			throw new Exception("实例化FileUploadListener类必须传入参数");
		}
		this.id = id;
	}

	@Override
	public void update(long pBytesRead, long pContentLength, int pItems) {
		
		log.info("监控启动：pBytesRead="+pBytesRead+";pContentLength="+pContentLength+";pItems="+pItems);
		
		HanlderUploadStatus hanlderUploadStatus = HanlderUploadStatus
				.getInstance();
		
		FileUploadStatusBean statusBean =hanlderUploadStatus.getUploadStatus(id);

		statusBean.setUploadTotalSize(pContentLength);
		statusBean.setUploadFlag("http");

		// 读取完成
		if (pContentLength == pBytesRead) {
			statusBean.setStatus("完成对" + (pItems + 1)/2 + "个文件的读取:读取了 " + pBytesRead/1024
					+ " KB.");
			statusBean.setReadTotalSize(pBytesRead);
			statusBean.setSuccessUploadFileCount((pItems + 1)/2);
			statusBean.setProcessEndTime(System.currentTimeMillis());
			statusBean.setProcessRunningTime(statusBean.getProcessEndTime());
			
			
			// 读取中
		} else {
			statusBean.setStatus("当前正在处理第" + (pItems + 1)/2
					+ "个文件的客户端上传:已经读取了 " + pBytesRead/1024 + " / " + pContentLength/1024
					+ " KB.");
			statusBean.setReadTotalSize(pBytesRead);
			statusBean.setCurrentUploadFileNum((pItems + 1)/2);
			statusBean.setProcessRunningTime(System.currentTimeMillis());
		}
		this.currentFileNum = (pItems + 1)/2;
		this.totalFileSize = pContentLength;
		hanlderUploadStatus.setUploadStatus(statusBean,id);
		
		log.info(""+statusBean.getStatus()+new Date());
	}

}
