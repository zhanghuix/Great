package com.file.manager.upload;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.file.manager.util.Common;

/**
 * 
 * @ClassName:UploadFileHanlder
 * @Description:上传入口类
 * @author zhangHui
 * @date:2015年12月11日
 */
public class UploadFileHanlder {

	private Logger log = Logger.getLogger(UploadFileHanlder.class);

	public final int THRESHOLDSIZE = 1024*1024*100;//100m
	public final int FILESIZEMAX = 2147483647;//2G
	public final int SIZEMAX = 2147483647*5;//10G
	
	// 单例，随着sring容器初始化，这个应用程序中只有一份
	private HanlderUploadStatus hanlderUploadStatus = HanlderUploadStatus
				.getInstance();

	/**
	 * 
	 * @methodName:setServletFileUpload
	 * @Description:初始化一些上传参数，设置上传状态监听，入口方法，先调用
	 * @param ful
	 *            上传状态监听类，实例化后传入
	 * @param thresholdSize
	 *            内存缓存区大小
	 * @param tempFolder
	 *            临时文件位置
	 * @param fileSizeMax
	 *            单个上传文件最大值
	 * @param sizeMax
	 *            requset最大值
	 * @return
	 * @throws Exception
	 * @author zhangHui
	 * @date 2015年11月26日 上午10:25:11
	 */
	public ServletFileUpload setServletFileUpload(FileUploadListener ful,
			String tempFolder) throws Exception {

		if (ful == null) {
			throw new Exception(
					"com.file.manager.upload.UploadFileService.setServletFileUpload(ServletFileUpload, FileUploadListener, String)参数不允许为空");
		}

		File file = new File(tempFolder);
		if (!file.exists()) {
			file.mkdir();
		}

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		// 设置内存缓冲区，超过后写入临时文件
		dfif.setSizeThreshold(THRESHOLDSIZE);
		// 设置临时文件存储位置
		dfif.setRepository(file);

		ServletFileUpload sfu = new ServletFileUpload(dfif);
		// 设置单个文件的最大上传值
		sfu.setFileSizeMax(FILESIZEMAX);
		// 设置整个request的最大值
		sfu.setSizeMax(SIZEMAX);
		sfu.setHeaderEncoding("UTF-8");
		// 设置监听
		sfu.setProgressListener(ful);

		log.info("设置---缓冲区大小：" + THRESHOLDSIZE + ";单个文件最大值：" + FILESIZEMAX
				+ ";requset最大值：" + SIZEMAX);

		return sfu;

	}

	/**
	 * 
	 * @methodName:saveFileUploadStatus
	 * @Description:保存上传状态到缓存map中，调用此方法前必须先设置本对象中的setHanlderUploadStatus和setId
	 * @param contentLength
	 * @param contentPath
	 * @param id
	 * @author zhangHui
	 * @throws Exception
	 * @date 2015年11月25日 下午4:27:55
	 */
	public void saveFileUploadStatus(int contentLength, String contentPath,
			String id) throws Exception {

		if (Common.isEmpty(id) || Common.isEmpty(contentPath)) {
			throw new Exception(
					"com.file.manager.upload.UploadFileService.saveFileUploadStatus(String, int, String, HanlderUploadStatus)参数不能为空");
		}

		FileUploadStatusBean satusBean = new FileUploadStatusBean();
		satusBean.setStatus("正在准备处理");
		satusBean.setUploadTotalSize(contentLength);
		satusBean.setProcessStartTime(System.currentTimeMillis());
		satusBean.setBaseDir(contentPath);
		hanlderUploadStatus.setUploadStatus(satusBean, id);

		log.info("saveFileUploadStatus()缓存数据：" + satusBean.toJSon() + ";id="
				+ id);
	}

	/**
	 * 
	 * @methodName:saveUploadFile
	 * @Description:执行上传文件操作，并且保存上传状态，执行此方法前必须先执行saveFileUploadStatus
	 * @param fileItemList
	 *            上传文件
	 * @param realPath
	 *            路径
	 * @param statusBean
	 *            上传状态数据
	 * @param id
	 * @return List<String> 已上传文件
	 * @throws Exception
	 * @author zhangHui
	 * @date 2015年11月26日 上午10:32:46
	 */
	public List<String> saveUploadFile(List<FileItem> fileItemList,
			String realPath, String id) throws Exception {

		FileUploadStatusBean statusBean = hanlderUploadStatus
				.getUploadStatus(id);

		realPath = realPath + File.separator;// file.separator=\

		List<String> uploadFile = new ArrayList<String>();// 存放上传成功文件，返回给客户端

		if (fileItemList != null && fileItemList.size() > 0) {

			File tempFile = new File(realPath);

			if (!tempFile.exists()) {
				tempFile.mkdir();
			}

			for (FileItem item : fileItemList) {
				if (!item.isFormField() && item.getName().length() > 0) {

					String fileName = takeOutFileName(item.getName());

					File uploadedFile = new File(realPath + fileName);

					while (uploadedFile.exists()) {

						fileName = System.currentTimeMillis() + "_" + fileName;

						uploadedFile = new File(realPath + fileName);

					}

					item.write(uploadedFile);

					uploadFile.add(realPath + fileName);

					statusBean.getUploadFileUrlList().add(fileName);
					hanlderUploadStatus.setUploadStatus(statusBean, id);

					log.info("文件：" + fileName + "已保存");
				}
			}

			statusBean.setIsOver(1);// 已全部上传完成
			hanlderUploadStatus.setUploadStatus(statusBean, id);
		}
		return uploadFile;
	}

	/**
	 * 
	 * @methodName:delUploadFile
	 * @Description:删除上传文件，并设置状态
	 * @param filePath
	 * @param id
	 * @author zhangHui
	 * @date 2015年11月26日 上午11:07:04
	 */
	public void delUploadFile(String filePath, String id) {
		FileUploadStatusBean statusBean = hanlderUploadStatus
				.getUploadStatus(id);
		for (String fileName : statusBean.getUploadFileUrlList()) {
			File file = new File(filePath + File.separator + fileName);
			file.delete();

			log.info("文件：" + fileName + "已从磁盘删除");
		}
		statusBean.getUploadFileUrlList().clear();
		statusBean.setStatus("删除已上传的文件");
		hanlderUploadStatus.setUploadStatus(statusBean, id);
	}

	/**
	 * 
	 * @methodName:uploadException
	 * @Description:上传过程中异常处理
	 * @param messg
	 * @param id
	 * @param filePath
	 * @author zhangHui
	 * @date 2015年11月27日 上午11:30:52
	 */
	public void uploadException(String messg, String filePath, String id) {
		this.delUploadFile(filePath, id);
		FileUploadStatusBean statusBean = hanlderUploadStatus
				.getUploadStatus(id);
		statusBean.setStatus(messg);
		statusBean.setIsOver(2);
		hanlderUploadStatus.setUploadStatus(statusBean, id);
	}

	/**
	 * 
	 * @methodName:takeOutFileName
	 * @Description:从文件路径中取出文件名
	 * @param filePath
	 * @return
	 * @author zhangHui
	 * @date 2015年11月25日 下午4:56:46
	 */
	private String takeOutFileName(String filePath) {
		int pos = filePath.lastIndexOf(File.separator);
		if (pos > 0) {
			return filePath.substring(pos + 1);
		} else {
			return filePath;
		}
	}
}
