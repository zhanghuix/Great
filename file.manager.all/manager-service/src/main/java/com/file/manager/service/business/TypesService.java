package com.file.manager.service.business;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.manager.dao.business.TypesDao;
import com.file.manager.entity.Types;
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
public class TypesService {

	// private static Logger logger =
	// LoggerFactory.getLogger(TypesService.class);

	@Autowired
	private TypesDao typesDao;

	/**
	 * 分页查询
	 * 
	 * @param type
	 *            查询条件
	 * @param pageView
	 *            分页参数
	 * @return
	 */
	public PageView queryPage(PageView pageView, Types type) {
		List<Types> list = typesDao.queryPage(pageView, type);
		pageView.setRecords(list);
		return pageView;
	}

	/**
	 * 不分页查询
	 * 
	 * @param type
	 *            查询条件
	 * @return
	 */
	public List<Types> queryAll(Types type) {
		return typesDao.queryAll(type);
	}

	public Types get(String no) throws Exception {
		if (Common.isEmpty(no)) {
			throw new Exception(
					"com.file.manager.service.business.TypesService.get(String)参数不能为空");
		}
		return typesDao.get(no);
	}

	public void add(Types types) throws Exception {
		if (types == null) {
			throw new Exception(
					"com.file.manager.service.business.TypesService.add(Types)参数对象不能为空");
		}

		// 上级为默认，下级同样为默认no=0，并且表中只能有一对默认值，即上级和下级
		if ("0".equals(types.getPartenNo())) {
			types.setNo("0");

			// 如果no=0的记录已经存在，则不做新增操作
			if (this.get(types.getNo()) != null) {
				return;
			}
		} else {
			types.setNo(UUID.randomUUID().toString().replace("-", ""));
		}

		types.setCreatetime(new Date());
		typesDao.insert(types);
	}

	public void modify(Types types) throws Exception {
		if (types == null || Common.isEmpty(types.getNo())) {
			throw new Exception(
					"com.file.manager.service.business.TypesService.modify(Types)参数对象不能为空");
		}
		typesDao.update(types);
	}

	public void del(String no) throws Exception {
		if (Common.isEmpty(no)) {
			throw new Exception(
					"com.file.manager.service.business.TypesService.del(String)参数不能为空");
		}
		typesDao.delete(no);
	}

}
