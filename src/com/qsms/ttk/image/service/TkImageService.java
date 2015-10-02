package com.qsms.ttk.image.service;

import com.qsms.ttk.image.ebean.TkImage;
import com.qsms.util.dao.DAO;
import com.qsms.util.dao.PageView;

public interface TkImageService extends DAO<TkImage> {

	/**
	 * 根据标签id字串查找出符合条件的所有图片
	 * 
	 * @param maxresult
	 * @param page
	 * @param tagName
	 *           标签名称字串
	 * @return
	 */
	PageView<TkImage> queryByTag(int maxresult, int page, String [] tagName);

}
