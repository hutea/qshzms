package com.qsms.blog.service;

import java.util.List;

import com.qsms.blog.ebean.BlogTag;
import com.qsms.util.dao.DAO;

public interface BlogTagService extends DAO<BlogTag> {
	/**
	 * 通过标签名称获取标签，如果不存在则将此标签存储后再返回
	 * 
	 * @param tagName
	 * @return
	 */
	public BlogTag findByName(String tagName);

	/***
	 * 获取使用频率最高前num条标签
	 * 
	 * @param num
	 * @return
	 */
	public List<BlogTag> topList(int num);

}
