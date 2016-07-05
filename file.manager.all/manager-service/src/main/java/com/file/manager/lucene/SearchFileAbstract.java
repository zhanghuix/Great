package com.file.manager.lucene;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.file.manager.util.ApplicationPropertyPlaceholderConfigurer;

/**
 * 
 * @ClassName:SearchFileAbstract
 * @Description:搜索类的默认实现
 * @author zhangHui
 * @date:2016年2月26日
 */
public abstract class SearchFileAbstract {

	private final String DEFAULTINDEXPATH = ApplicationPropertyPlaceholderConfigurer
			.getContextProperty("INDEXPATH").toString();

	/**
	 * 
	 * @methodName:query
	 * @Description:创建一次查询
	 * @param analyzerName
	 *            具体分析器名称，一个分析器名称对应一组索引
	 * @param content
	 * @param start
	 *            开始条数
	 * @param end
	 *            结束条数
	 * @author zhangHui
	 * @throws ParseException
	 * @throws IOException
	 * @date 2016年2月29日 下午2:19:57
	 */
	protected IndexBean query(IndexBean bean, int start, int end)
			throws ParseException, IOException {

		List<String> allFilePath = new ArrayList<String>(bean.getIndexPath()
				.size());
		
		//总条数，分页用
		int total =0;

		for (String path : bean.getIndexPath()) {

			Directory directory = FSDirectory.open(Paths.get(path));

			IndexReader indexReader = DirectoryReader.open(directory);

			IndexSearcher indexSearcher = new IndexSearcher(indexReader);

			Analyzer a = new StandardAnalyzer();
			Query query = new QueryParser(bean.getAnalyzerName(), a).parse(bean
					.getContent());

			// 排序
			SortField sf = new SortField(bean.getResultAnalyzerName(),
					SortField.Type.DOC);
			// 分页使用
			TopFieldCollector results = TopFieldCollector.create(new Sort(sf),
					end, false, false, false);

			// TopDocs td = indexSearcher.search(query, tfc);
			indexSearcher.search(query, results);
			
			total+=results.getTotalHits();

			// 分页
			ScoreDoc[] hits = results.topDocs(start, end).scoreDocs;

			List<String> filePath = new ArrayList<String>(hits.length);

			for (ScoreDoc sd : hits) {

				Document doc = indexSearcher.doc(sd.doc);

				filePath.add(doc.get(bean.getResultAnalyzerName()));
			}

			allFilePath.addAll(filePath);

			if (directory != null) {
				directory.close();
			}
			if (indexReader != null) {
				indexReader.close();
			}

		}

		bean.setTotalPage(total);
		bean.setFilePath(allFilePath);

		return bean;
	}

	/**
	 * 
	 * @methodName:serarch
	 * @Description:公共方法，入口方法
	 * @param indexBean
	 * @param start
	 *            开始条数
	 * @param end
	 *            结束条数
	 * @throws IOException
	 * @throws ParseException
	 * @author zhangHui
	 * @date 2016年3月4日 下午5:16:06
	 */
	protected IndexBean serarch(IndexBean indexBean, int start, int end)
			throws IOException, ParseException {

		if (indexBean == null) {
			throw new NullPointerException("indexPath参数不能为空");
		}

		List<String> indexPath = indexBean.getIndexPath();
		if (indexPath == null || indexPath.size() == 0) {

			String[] tempPath = DEFAULTINDEXPATH.split(",");

			indexBean.setIndexPath(Arrays.asList(tempPath));
		}

		IndexBean bean = this.query(indexBean, start, end);

		return bean;

	}

	/******************************************************* ++++++++++++以下为需要实现的接口方法+++++++++++++ *********************************************************/

	/**
	 * 
	 * @methodName:initFSDIndex
	 * @Description:根据文件名称搜索文件
	 * @param fileName
	 *            搜索条件
	 * @param start
	 *            开始条数
	 * @param end
	 *            结束条数
	 * @author zhangHui
	 * @date 2016年2月29日 上午11:09:38
	 */
	public abstract IndexBean serarchPathByFileName(String fileName, int start,
			int end) throws Exception;

	/**
	 * 
	 * @methodName:searchPathByFileContent
	 * @Description:根据文件内容搜索文件
	 * @param content
	 * @param start
	 *            开始条数
	 * @param end
	 *            结束条数
	 * @author zhangHui
	 * @date 2016年3月4日 下午4:30:49
	 */
	public abstract IndexBean searchPathByFileContent(String content,
			int start, int end) throws Exception;

}
