package com.qsms.blog.service;

import org.springframework.stereotype.Service;

import com.qsms.blog.ebean.BlogType;
import com.qsms.util.dao.DAOSupport;

@Service
public class BlogTypeServiceBean extends DAOSupport<BlogType> implements BlogTypeService {

	@Override
	public BlogType findByCode(String code) {
		try {
			return (BlogType) em.createQuery("select o from BlogType o where o.code=?1").setParameter(1, code).getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
