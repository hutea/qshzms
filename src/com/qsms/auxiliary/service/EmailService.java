package com.qsms.auxiliary.service;

import com.qsms.auxiliary.ebean.Email;
import com.qsms.util.dao.DAO;

public interface EmailService extends DAO<Email> {
	/**
	 * 添加邮件记录，如果存在，则更新code和发送时间
	 * 
	 * @param uid
	 * @param code
	 */
	public void emailRecord(String uid, String code);

	/**
	 * 通过用户ID和验证码查找是否存邮件记录
	 * 
	 * @param uid
	 * @param code
	 * @return 存在返回邮件对象，否则返回null
	 */
	public Email findByUC(String uid, String code);

}
