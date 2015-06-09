package com.qsms.share.service;

import com.qsms.share.ebean.ShareComment;
import com.qsms.util.dao.DAO;
import com.qsms.util.dao.PageView;

public interface ShareCommentService extends DAO<ShareComment> {
	/**
	 * 统计指定用户对指定Share的评论总数
	 * 
	 * @param shareid
	 * @param userid
	 * @return
	 */
	public long countBySuid(String shareid, String userid);

	/**
	 * 分页列出指定Share的评论
	 * 
	 * @param sid
	 * @param page
	 * @return
	 */
	public PageView<ShareComment> listForTheShare(String sid, int page);

	/**
	 * 统计某个用户的评论总数
	 * 
	 * @param uid
	 * @return
	 */
	public long countByUid(String uid);

}
