package com.file.manager.service.business;

import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.upload.FileUploadListener;
import com.file.manager.upload.UploadFileHanlder;

/**
 * 
 * @ClassName:UploadFileService
 * @Description:上传文件业务类
 * @author zhangHui
 * @date:2015年11月27日
 */
@Transactional
@Service("uploadFileService")
public class UploadFileService {
	
	private UploadFileHanlder uploadFileHanlder;
	
	public UploadFileService(){
		uploadFileHanlder = new UploadFileHanlder();
	}
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

		return uploadFileHanlder.setServletFileUpload(ful, tempFolder);

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

		uploadFileHanlder.saveFileUploadStatus(contentLength, contentPath, id);
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

		return uploadFileHanlder.saveUploadFile(fileItemList, realPath, id);
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
		uploadFileHanlder.delUploadFile(filePath, id);
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
		uploadFileHanlder.uploadException(messg, filePath, id);
	}
}
