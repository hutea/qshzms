package com.qsms.core.service;

import java.util.List;

import com.qsms.core.ebean.StarItem;
import com.qsms.util.dao.DAO;

public interface StarItemService extends DAO<StarItem> {

	/**
	 * 根据Star Id 和 分类ID 列出所有资源条目
	 * 
	 * @param starId
	 * @param categoryId
	 * @return
	 */
	public List<StarItem> list(String starId, Long categoryId);

	/**
	 * 列出最近更新，且资源介绍大于100字符的资源
	 * 
	 * @param maxresult
	 *            最大资源条目数
	 */
	public List<StarItem> listLastUpdate(int maxresult);

}
