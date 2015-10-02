package com.qsms.core.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qsms.core.ebean.Star;
import com.qsms.util.dao.DAOSupport;

@Service
public class StarServiceBean extends DAOSupport<Star> implements StarService {

	@Override
	public Star findByCode(String code) {
		try {
			return (Star) em
					.createQuery("select o from Star o where o.code=?1")
					.setParameter(1, code).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Star> listLastUpdate(int maxNum) {
		return em
				.createQuery(
						"select o from Star o where o.visible=?1 order by modifyDate desc")
				.setParameter(1, true).setMaxResults(maxNum).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Star> listByTypeBasePV(int type, int maxNum) {
		return em
				.createQuery(
						"select o from Star o where o.visible=?1 and o.type=?2 order by pv desc")
				.setParameter(1, true).setParameter(2, type)
				.setMaxResults(maxNum).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Star> listIndex() {
		return em
				.createQuery(
						"select o from Star o where o.visible=?1 order by iod desc")
				.setParameter(1, true).setMaxResults(5).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Star> listAll() {
		return em.createQuery("select o from Star o where o.visible=?1")
				.setParameter(1, true).getResultList();
	}

}
