package com.qsms.account.service;

import java.util.List;

import com.qsms.account.ebean.SystemPrivilege;
import com.qsms.util.dao.DAO;

public interface SystemPrivilegeService extends DAO<SystemPrivilege> {

	public void saves(List<SystemPrivilege> sps);

	public SystemPrivilege findByURL(String url);

	public List<SystemPrivilege> listBylevel(int level);

}
