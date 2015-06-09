package com.qsms.account.service;

import com.qsms.account.ebean.User;
import com.qsms.util.dao.DAO;

public interface UserService extends DAO<User> {
	/**
	 * 通过Email和密码查找用户，主要用于登录
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	public User findByEP(String email, String password);

	/**
	 * 通过Email查找用户
	 * 
	 * @param email
	 * @return
	 */
	public User findByEmail(String email);

	/**
	 * 重置用户数据：<br>
	 * 《1》uploadSize=0; 用户当天已上传图片M数 <br>
	 * 《2》private int shareds=0; 用户当天已分享记录数<br>
	 * 《3》private int comments=0; 用户当天已评论记录数
	 * 
	 * @return
	 */
	public int resetData();

}
