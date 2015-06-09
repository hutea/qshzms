package com.qsms.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qsms.blog.ebean.BlogTag;
import com.qsms.util.Helper;
import com.qsms.util.dao.DAOSupport;

@Service
public class BlogTagServiceBean extends DAOSupport<BlogTag> implements BlogTagService {
	@Override
	public BlogTag findByName(String tagName) {
		String processTagName = tagName.toLowerCase().trim();
		try {
			return (BlogTag) em.createQuery("select o from BlogTag o where o.name=?1").setParameter(1, processTagName).getSingleResult();
		} catch (Exception e) {
			BlogTag tag = new BlogTag();
			tag.setId(Helper.generatorShortID());
			tag.setName(processTagName);
			this.save(tag);
			return tag;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BlogTag> topList(int num) {
		return em.createQuery("select t from BlogTag t left join t.blogs b group by t.id ORDER BY count(b.id) DESC").setMaxResults(num).getResultList();
	}
}
