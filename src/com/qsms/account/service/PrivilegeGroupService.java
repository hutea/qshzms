package com.qsms.account.service;

import com.qsms.account.ebean.PrivilegeGroup;
import com.qsms.util.dao.DAO;

public interface PrivilegeGroupService extends DAO<PrivilegeGroup> {

	public PrivilegeGroup findByName(String groupName);

}
