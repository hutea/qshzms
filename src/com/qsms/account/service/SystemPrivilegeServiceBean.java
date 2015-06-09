package com.qsms.account.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.qsms.account.ebean.SystemPrivilege;
import com.qsms.util.dao.DAOSupport;

@Service
public class SystemPrivilegeServiceBean extends DAOSupport<SystemPrivilege> implements
		SystemPrivilegeService {

	@Override
	public void saves(List<SystemPrivilege> sps) {
		for (SystemPrivilege sp : sps) {
			super.save(sp);
		}
	}

	@Override
	public SystemPrivilege findByURL(String url) {
		Query query = em.createQuery("select o from SystemPrivilege o where o.url=?1")
				.setParameter(1, url);
		try {
			SystemPrivilege sp = (SystemPrivilege) query.getSingleResult();
			return sp;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemPrivilege> listBylevel(int level) {
		return em.createQuery("select o from SystemPrivilege o where o.lv=?1").setParameter(1,
				level).getResultList();

	}
}
