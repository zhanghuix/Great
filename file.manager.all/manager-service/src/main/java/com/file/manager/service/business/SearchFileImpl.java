package com.file.manager.service.business;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.lucene.IndexBean;
import com.file.manager.lucene.SearchFileAbstract;

/**
 * 
 * @ClassName:SearchFileImpl
 * @Description:搜索实现类，本类提供以文件名称或者文件内容或者文件路径为索引，
 * @author zhangHui
 * @date:2016年2月26日
 */
@Transactional
@Service
public class SearchFileImpl extends SearchFileAbstract {
	
	private final String ANALYZERFILECONTENT="contents";//以内容查询，分析器名称
	private final String ANALYZERFILENAME="fileName";//以文件名查询，分析器名称
	private final String RESULTANALYZERNAME="filePath";//返回结果，分析器名称
	

	/**
	 * 
	 * @methodName:initFSDIndex
	 * @Description:根据文件名称搜索文件
	 * @param fileName
	 *            搜索条件
	 * @author zhangHui
	 * @date 2016年2月29日 上午11:09:38
	 */
	@Override
	public IndexBean serarchPathByFileName(String fileName,int start,int end) throws Exception {

		IndexBean indexBean = new IndexBean();
		indexBean.setContent(fileName);
		indexBean.setAnalyzerName(ANALYZERFILENAME);
		indexBean.setResultAnalyzerName(RESULTANALYZERNAME);
		//索引和文件使用默认路径
		return super.serarch(indexBean,start,end);

	}

	/**
	 * 
	 * @methodName:searchPathByFileContent
	 * @Description:根据文件内容搜索文件
	 * @param content
	 * @author zhangHui
	 * @date 2016年3月4日 下午4:30:49
	 */
	@Override
	public IndexBean searchPathByFileContent(String content,int start,int end)
			throws IOException, ParseException {

		//索引和文件使用默认路径
		IndexBean indexBean = new IndexBean();
		indexBean.setContent(content);
		indexBean.setAnalyzerName(ANALYZERFILECONTENT);
		indexBean.setResultAnalyzerName(RESULTANALYZERNAME);
		return super.serarch(indexBean,start,end);
	}

}
