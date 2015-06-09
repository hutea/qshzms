package com.qsms.share.service;

import com.qsms.share.ebean.ReportRecord;
import com.qsms.util.dao.DAO;

public interface ReportRecordService extends DAO<ReportRecord> {
	/**
	 * 统计某个用户的举报总数
	 * 
	 * @param uid
	 * @return
	 */
	public long countByUid(String uid);
}
