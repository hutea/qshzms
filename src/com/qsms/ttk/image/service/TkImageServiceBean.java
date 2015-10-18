package com.qsms.ttk.image.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.qsms.auxiliary.service.SystemConfigService;
import com.qsms.ttk.image.ebean.TkImage;
import com.qsms.util.dao.DAOSupport;
import com.qsms.util.dao.PageView;

@Service
public class TkImageServiceBean extends DAOSupport<TkImage> implements
		TkImageService {
	@Resource
	private SystemConfigService systemConfigService;

	@SuppressWarnings("unchecked")
	@Override
	public PageView<TkImage> queryByTag(int maxresult, int page,
			String[] tagNames) {
		int maxReported = systemConfigService.shareReportedMax();
		PageView<TkImage> pageView = new PageView<TkImage>(maxresult, page);
		StringBuffer querySTR = new StringBuffer("and (");
		for (int i = 0; i < tagNames.length; i++) {
			if (i == 0) {
				querySTR.append(" t.name like?" + (i + 4));
			} else {
				querySTR.append(" or t.name like?" + (i + 4));
			}
		}
		querySTR.append(" )");

		Query query = em
				.createQuery(
						"select  distinct s from TkImage s  join s.share.tags t  where s.share.visible=?1 and s.share.reported<=?2 and s.share.category=?3 "
								+ querySTR).setParameter(1, true)
				.setParameter(2, maxReported).setParameter(3, 5);
		for (int i = 0; i < tagNames.length; i++) {
			query.setParameter(i + 4, "%" + tagNames[i] + "%");
		}
		Query queryCount = em
				.createQuery(
						"select count( distinct s.id) from TkImage s  join s.share.tags t where s.share.visible=?1 and s.share.reported<=?2 and s.share.category=?3 "
								+ querySTR).setParameter(1, true)
				.setParameter(2, maxReported).setParameter(3, 5);
		for (int i = 0; i < tagNames.length; i++) {
			queryCount.setParameter(i + 4, "%" + tagNames[i] + "%");
		}
		List<TkImage> shares = query.setFirstResult(pageView.getFirstResult())
				.setMaxResults(maxresult).getResultList();
		pageView.setTotalrecord((Long) queryCount.getSingleResult());
		pageView.setRecords(shares);
		return pageView;
	}
}
