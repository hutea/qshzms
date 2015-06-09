package com.qsms.share.service;

import com.qsms.share.ebean.ShareLike;
import com.qsms.util.dao.DAO;

public interface ShareLikeService extends DAO<ShareLike> {
	/**
	 * 统计某个用户的Like总数
	 * 
	 * @param uid
	 * @return
	 */
	public long countByUid(String uid);

}
