package com.qsms.core.service;

import java.util.List;

import com.qsms.core.ebean.Star;
import com.qsms.util.dao.DAO;

public interface StarService extends DAO<Star> {

	/**
	 * 通过明星代码查找到指定明星
	 * 
	 * @param code
	 *            明星代码
	 * @return
	 */
	Star findByCode(String code);

	/**
	 * 列出最近有更新的maxNum个明星
	 * 
	 * @param maxNum
	 *            最多明星数
	 * @return
	 */
	List<Star> listLastUpdate(int maxNum);

	/**
	 * 根据PV列出指定分类下的maxNum个明星
	 * 
	 * @param type
	 *            分类值
	 * @param maxNum
	 *            最多明星数
	 * @return
	 */
	List<Star> listByTypeBasePV(int type, int maxNum);

	/**
	 * 列出首页要显示的5个明星
	 * 
	 * @return
	 */
	List<Star> listIndex();

	/**
	 * 列出所有明星
	 * 
	 * @return
	 */
	List<Star> listAll();

}
