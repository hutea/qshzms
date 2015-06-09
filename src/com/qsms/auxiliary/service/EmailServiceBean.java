package com.qsms.auxiliary.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.qsms.auxiliary.ebean.Email;
import com.qsms.util.Helper;
import com.qsms.util.dao.DAOSupport;

@Service
public class EmailServiceBean extends DAOSupport<Email> implements EmailService {

	@Override
	public Email findByUC(String uid, String code) {
		try {
			return (Email) em.createQuery("select o from Email o where uid=?1 and code=?2")
					.setParameter(1, uid).setParameter(2, code).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void emailRecord(String uid, String code) {
		Email email = null;
		try {
			email = (Email) em.createQuery("select o from Email o where uid=?1 and code=?2")
					.setParameter(1, uid).setParameter(2, code).getSingleResult();
		} catch (Exception e) {
			email = null;
		}
		if (email == null) {// 创建
			email = new Email();
			email.setId(Helper.generatorID());
			email.setUid(uid);
			email.setSendTime(new Date());
			email.setCode(code);
			this.save(email);
		} else {// 更新为false
			email.setCode(code);
			email.setSendTime(new Date());
			this.update(email);
		}
	}
}
