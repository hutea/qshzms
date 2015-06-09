package com.qsms.account.service;

import org.springframework.stereotype.Service;

import com.qsms.account.ebean.User;
import com.qsms.util.dao.DAOSupport;

@Service
public class UserServiceBean extends DAOSupport<User> implements UserService {

	@Override
	public User findByEP(String email, String password) {
		try {
			return (User) em
					.createQuery(
							"select o from User o where o.email=?1 and o.password=?2 and o.emailBind=?3 and o.visible=?4")
					.setParameter(1, email).setParameter(2, password)
					.setParameter(3, true).setParameter(4, true)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		try {
			return (User) em
					.createQuery("select o from User o where o.email=?1")
					.setParameter(1, email).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int resetData() {
		return em
				.createQuery(
						"update User o set o.uploadSize=?1 ,o.shareds=?2 ,o.comments=?3")
				.setParameter(1, 0).setParameter(2, 0).setParameter(3, 0)
				.executeUpdate();
	}

}
