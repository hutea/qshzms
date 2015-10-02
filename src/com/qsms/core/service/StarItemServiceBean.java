package com.qsms.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qsms.core.ebean.StarItem;
import com.qsms.util.dao.DAOSupport;

@Service
public class StarItemServiceBean extends DAOSupport<StarItem> implements
		StarItemService {

	@SuppressWarnings("unchecked")
	@Override
	public List<StarItem> list(String starId, Long categoryId) {
		return em
				.createQuery(
						"select o from StarItem o where o.star.id=?1 and o.category.id=?2 and o.visible=?3 order by showDate desc")
				.setParameter(1, starId).setParameter(2, categoryId)
				.setParameter(3, true).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StarItem> listLastUpdate(int maxresult) {
		return em
				.createQuery(
						"select o as len from StarItem o where  o.visible=?1 and length(o.summary)>=?2 order by createDate desc")
				.setParameter(1, true).setParameter(2, 50)
				.setMaxResults(maxresult).getResultList();
	}
}
