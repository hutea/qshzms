package com.qsms.share.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.qsms.auxiliary.service.SystemConfigService;
import com.qsms.share.ebean.Share;
import com.qsms.util.dao.DAOSupport;
import com.qsms.util.dao.PageView;

@Service
public class ShareServiceBean extends DAOSupport<Share> implements ShareService {

	@Resource
	private SystemConfigService systemConfigService;

	@Override
	public long countByUid(String uid) {
		return (Long) em.createQuery(
				"select count(o.id) from Share o where o.user.id=?1")
				.setParameter(1, uid).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageView<Share> queryByTag(int maxresult, int page, String tagId,
			int category) {
		int maxReported = systemConfigService.shareReportedMax();
		PageView<Share> pageView = new PageView<Share>(maxresult, page);
		Query query = em
				.createQuery(
						"select s from Share s left join s.tags t where s.visible=?1 and s.reported<=?2 and t.id=?3 and s.category<?4 ORDER BY s.id DESC")
				.setParameter(1, true).setParameter(2, maxReported)
				.setParameter(3, tagId).setParameter(4, 5);
		Query queryCount = em
				.createQuery(
						"select count(s.id) from Share s left join s.tags t  where s.visible=?1 and s.reported<=?2 and t.id=?3 and s.category<?4")
				.setParameter(1, true).setParameter(2, maxReported)
				.setParameter(3, tagId).setParameter(4, 5);
		if (category != 0) {
			query = em
					.createQuery(
							"select s from Share s left join s.tags t where s.visible=?1 and s.reported<=?2 and t.id=?3 and s.category=?4 ORDER BY s.id DESC")
					.setParameter(1, true).setParameter(2, maxReported)
					.setParameter(3, tagId).setParameter(4, category);
			queryCount = em
					.createQuery(
							"select count(s.id) from Share s left join s.tags t where s.visible=?1 and s.reported<=?2 and t.id=?3 and s.category=?4")
					.setParameter(1, true).setParameter(2, maxReported)
					.setParameter(3, tagId).setParameter(4, category);
		}
		List<Share> shares = query.setFirstResult(pageView.getFirstResult())
				.setMaxResults(maxresult).getResultList();
		pageView.setTotalrecord((Long) queryCount.getSingleResult());
		pageView.setRecords(shares);
		return pageView;
	}
}
