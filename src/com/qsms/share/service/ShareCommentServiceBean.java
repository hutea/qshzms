package com.qsms.share.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qsms.account.ebean.User;
import com.qsms.share.ebean.ShareComment;
import com.qsms.util.dao.DAOSupport;
import com.qsms.util.dao.PageView;

@Service
public class ShareCommentServiceBean extends DAOSupport<ShareComment> implements
		ShareCommentService {

	@Override
	public long countBySuid(String shareid, String userid) {
		return (Long) em
				.createQuery(
						"select count(o.id) from ShareComment o where o.share.id=?1 and o.user.id=?2")
				.setParameter(1, shareid).setParameter(2, userid)
				.getSingleResult();
	}

	@Override
	public long countByUid(String uid) {
		return (Long) em.createQuery(
				"select count(o.id) from ShareComment o where o.user.id=?1")
				.setParameter(1, uid).getSingleResult();
	}

	public PageView<ShareComment> listForTheShare(String sid, int page) {
		int maxresult = 10;
		PageView<ShareComment> pageView = new PageView<ShareComment>(maxresult,
				page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1 and o.share.id=?2");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		params.add(sid);
		pageView.setQueryResult(this.getScrollData(pageView.getFirstResult(),
				maxresult, jpql.toString(), params.toArray(), orderby));
		for (ShareComment sc : pageView.getRecords()) {
			User user = sc.getUser();
			if (user.getNickname() == null || "".equals(user.getNickname())) {// 如果用户未设置昵称，则取ID后八位作为昵称
				user.setNickname(user.getId().substring(
						user.getId().length() - 8));
			}
		}
		return pageView;
	}

}
