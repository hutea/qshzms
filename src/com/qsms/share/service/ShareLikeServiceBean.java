package com.qsms.share.service;

import org.springframework.stereotype.Service;

import com.qsms.share.ebean.ShareLike;
import com.qsms.util.dao.DAOSupport;

@Service
public class ShareLikeServiceBean extends DAOSupport<ShareLike> implements
		ShareLikeService {
	@Override
	public long countByUid(String uid) {
		return (Long) em.createQuery(
				"select count(o.id) from ShareLike o where o.user.id=?1")
				.setParameter(1, uid).getSingleResult();
	}
}
