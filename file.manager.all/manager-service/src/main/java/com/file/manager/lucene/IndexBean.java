package com.file.manager.lucene;

import java.util.List;

public class IndexBean {

	//索引路径
	private List<String> indexPath;
	//被索引文件路径
	private List<String> dataPath;
	//按文件中内容搜索
	private String content;
	//搜索结果，文件路径
	private List<String> filePath;
	//分析器名称，一个分析器批评一组索引，针对查询
	private String analyzerName;
	//分析器名称，针对获取查询结果
	private String resultAnalyzerName;
	//返回总条数
	private int totalPage;
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getResultAnalyzerName() {
		return resultAnalyzerName;
	}
	public void setResultAnalyzerName(String resultAnalyzerName) {
		this.resultAnalyzerName = resultAnalyzerName;
	}
	private int fileTotal;
	
	public int getFileTotal() {
		return fileTotal;
	}
	public void setFileTotal(int fileTotal) {
		this.fileTotal = fileTotal;
	}
	public String getAnalyzerName() {
		return analyzerName;
	}
	public void setAnalyzerName(String analyzerName) {
		this.analyzerName = analyzerName;
	}
	public List<String> getIndexPath() {
		return indexPath;
	}
	public void setIndexPath(List<String> indexPath) {
		this.indexPath = indexPath;
	}
	public List<String> getDataPath() {
		return dataPath;
	}
	public void setDataPath(List<String> dataPath) {
		this.dataPath = dataPath;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getFilePath() {
		return filePath;
	}
	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}
}
