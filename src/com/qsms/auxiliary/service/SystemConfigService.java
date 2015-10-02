package com.qsms.auxiliary.service;

import com.qsms.auxiliary.ebean.SystemConfig;
import com.qsms.util.dao.DAO;

public interface SystemConfigService extends DAO<SystemConfig> {

	/**
	 * 得到博客图片存储相册id
	 * 
	 * @return
	 */
	public String blogAid();

	/**
	 * 得到share图片存储相册id
	 * 
	 * @return
	 */
	public String shareAid();

	/**
	 * 得到明星资源条目(StarItem)存储相册id
	 * 
	 * @return
	 */
	public String starAid();

	/**
	 * Share被举报的最大值 如 <br>
	 * 如果配置异常，则返回“2”
	 * 
	 * @return
	 */
	public int shareReportedMax();

	/**
	 * Share评论被举报的最大值 <br>
	 * 如果配置异常，则返回“1”
	 * 
	 * @return
	 */
	public int shareCommentReportedMax();

	/**
	 * 系统默认博客分类代码，用于博客“导航”<br>
	 * 如果配置异常，返回“java”
	 * 
	 * @return
	 */
	public String defalutBlogTypeCode();

	/**
	 * 系统默认博客标签名称，用于博客“导航”<br>
	 * 如果配置异常，返回“java”
	 * 
	 * @return
	 */
	public String defalutBlogTagName();

	/**
	 * 判断一个url地址是否是外链站的地址，如果是则不需要上传
	 * 
	 * @param url
	 * @return
	 */
	boolean isExternalSite(String url);

}
