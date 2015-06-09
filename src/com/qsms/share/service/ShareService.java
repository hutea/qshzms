package com.qsms.share.service;

import com.qsms.share.ebean.Share;
import com.qsms.util.dao.DAO;
import com.qsms.util.dao.PageView;

public interface ShareService extends DAO<Share> {

	/**
	 * 通过标签查找对应的Share并封装为PageView返回
	 * 
	 * @param maxresult
	 * @param page
	 * @param tagId
	 * @param type
	 *            类别<br>
	 *            0：表示查询全部
	 * @return
	 */
	public PageView<Share> queryByTag(int maxresult, int page, String tagId,
			int type);

	/**
	 * 统计某个用户的分享数
	 * 
	 * @param uid
	 * @return
	 */
	public long countByUid(String uid);

}
