package com.file.manager.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.file.manager.util.Common;

/**
 * 
 * @ClassName:CreateIndexUtils
 * @Description:创建索引工具类，本类提供以文件名称和文件内容和文件路径创建索引
 * @author zhangHui
 * @date:2016年3月4日
 */
public class CreateIndexUtils {

	private static CreateIndexUtils createIndexUtils;

	private CreateIndexUtils() {
	}

	public static CreateIndexUtils getInstance() {
		if (createIndexUtils == null) {
			createIndexUtils = new CreateIndexUtils();
		}
		return createIndexUtils;
	}

	/** 与查询处使用的名称要一致 */
	private final String ANALYZERFILECONTENT = "contents";// 以内容查询，分析器名称
	private final String ANALYZERFILENAME = "fileName";// 以文件名查询，分析器名称
	private final String RESULTANALYZERNAME = "filePath";// 返回结果，分析器名称

	private final String DEFAULTINDEXPATH = "F://indexer";
	// 被索引文件路径
	private final String DEFAULTDATAPATH = "F://upload";

	private String indexPath;// 索引存放路径，如果为空则存放内存中

	private String dataPath;// 被索引文件路径

	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}

	private File getDataFile() throws Exception {

		if (Common.isEmpty(dataPath)) {
			dataPath = this.DEFAULTDATAPATH;
		}

		File file = new File(dataPath);

		return file;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	// 索引写入器
	private IndexWriter indexWriter;

	// 文件路径加载器
	private Directory directory;

	private List<Document> list = new ArrayList<Document>();

	/**
	 * @methodName:createIndex
	 * @Description:创建索引
	 * @author zhangHui
	 * @date 2016年2月26日 下午4:32:27
	 */
	public void createIndex() throws Exception {

		if (Common.isEmpty(indexPath)) {
			indexPath = this.DEFAULTINDEXPATH;
		}

		getIndexWriter(this.indexPath).addDocuments(
				this.setDocument(this.getDataFile()));

		if (indexWriter != null) {
			indexWriter.close();
		}
		if (directory != null) {
			directory.close();
		}

	}

	/**
	 * 
	 * @methodName:getIndexWriter
	 * @Description:创建索引写入器
	 * @param indexPath
	 *            索引存放磁盘位置， 如果为空，则为内存索引
	 * @author zhangHui
	 * @throws Exception
	 * @date 2016年3月4日 下午3:46:14
	 */
	private IndexWriter getIndexWriter(String indexPath) throws Exception {

		if (Common.isEmpty(indexPath)) {
			throw new Exception("indexPath不能为空");
		} else {

			//删除磁盘上的索引文件
			this.deleteFolder(indexPath);

			directory = FSDirectory.open(Paths.get(indexPath));
		}

		// 创建一个词语分析器
		Analyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig ic = new IndexWriterConfig(analyzer);

		indexWriter = new IndexWriter(directory, ic);

		return indexWriter;
	}

	/**
	 * 
	 * @methodName:setDocument
	 * @Description:构建索引,递归构建
	 * @param dataPath
	 *            被索引文件磁盘路径
	 * @throws IOException
	 * @author zhangHui
	 * @date 2016年3月4日 下午3:47:17
	 */
	private List<Document> setDocument(File dataDir) throws IOException {

		if (!dataDir.exists() || !dataDir.isDirectory()) {
			throw new IOException();
		}

		File[] files = dataDir.listFiles();

		for (File file : files) {

			if (file.isDirectory()) {
				this.setDocument(file);
			} else {

				FileReader fr = new FileReader(file);

				// 存储索引的数据字段，文本类型，有很多类型可选择
				Field fieldContents = new TextField(ANALYZERFILECONTENT, fr);
				Field fieldName = new TextField(ANALYZERFILENAME,
						file.getName(), Field.Store.YES);
				Field fieldPath = new StringField(RESULTANALYZERNAME,
						file.getCanonicalPath(), Field.Store.YES);

				// 组成索引数据结构，类似一张表的概念
				Document doc = new Document();
				// 建立索引
				doc.add(fieldContents);
				doc.add(fieldName);
				doc.add(fieldPath);

				list.add(doc);

			}

		}

		return list;
	}

	private boolean deleteFile(String sPath) {
		File file = new File(sPath);

		if (file.exists() && file.isFile()) {
			file.delete();
		}

		return true;
	}

	private boolean deleteFolder(String sPath) {
		
		File file = new File(sPath);

		if (file.exists() && file.isDirectory()) {
			for (File tempFile : file.listFiles()) {
				if (tempFile.isFile()) {
					this.deleteFile(tempFile.getAbsolutePath());
				} else {
					this.deleteFolder(tempFile.getAbsolutePath());
				}
			}
		}else{
			file.mkdir();
		}

		return true;
	}

	public static void main(String[] args) {
		try {
			CreateIndexUtils.getInstance().createIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
