package com.qsms.blog.service;

import java.util.List;

import com.qsms.blog.ebean.Blog;
import com.qsms.util.dao.DAO;
import com.qsms.util.dao.PageView;

public interface BlogService extends DAO<Blog> {

	/**
	 * 通过标签查找对应的Share并封装为PageView返回
	 * 
	 * @param maxresult
	 * @param page
	 * @param tagId
	 * @return
	 */
	public PageView<Blog> queryByTag(int maxresult, int page, String tagId);

	/**
	 * 列出最新的num条博客
	 * 
	 * @param num
	 * @return
	 */
	public List<Blog> listNewset(int num);

	/**
	 * 得到上一条博客
	 * 
	 * @param bid
	 * @return
	 */
	public Blog lastBlog(String bid);

	/**
	 * 得到下一条博客
	 * 
	 * @param bid
	 * @return
	 */
	public Blog nextBlog(String bid);

}
