package com.qsms.share.service;

import org.springframework.stereotype.Service;

import com.qsms.share.ebean.ReportRecord;
import com.qsms.util.dao.DAOSupport;

@Service
public class ReportRecordServiceBean extends DAOSupport<ReportRecord> implements
		ReportRecordService {
	@Override
	public long countByUid(String uid) {
		return (Long) em.createQuery(
				"select count(o.id) from ReportRecord o where o.user.id=?1")
				.setParameter(1, uid).getSingleResult();
	}
}
