package com.qsms.blog.service;

import com.qsms.blog.ebean.BlogType;
import com.qsms.util.dao.DAO;

public interface BlogTypeService extends DAO<BlogType> {

	public BlogType findByCode(String code);

}
