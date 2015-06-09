package com.qsms.account.service;

import com.qsms.account.ebean.Account;
import com.qsms.util.dao.DAO;

public interface AccountService extends DAO<Account> {

	public Account findByUP(String username, String password);

	public Account findByUsername(String username);

}
