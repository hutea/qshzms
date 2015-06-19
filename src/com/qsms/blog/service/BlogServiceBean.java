package com.qsms.blog.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.qsms.blog.ebean.Blog;
import com.qsms.util.dao.DAOSupport;
import com.qsms.util.dao.PageView;

@Service
public class BlogServiceBean extends DAOSupport<Blog> implements BlogService {

	@SuppressWarnings("unchecked")
	@Override
	public PageView<Blog> queryByTag(int maxresult, int page, String tagId) {
		PageView<Blog> pageView = new PageView<Blog>(maxresult, page);
		Query query = em.createQuery("select b from Blog b left join b.tags t where b.visible=?1 and t.id=?2 ORDER BY b.lv desc, b.id DESC").setParameter(1, true).setParameter(2,
				tagId);
		Query queryCount = em.createQuery("select count(b.id) from Blog b left join b.tags t where b.visible=?1 and t.id=?2").setParameter(1, true).setParameter(2, tagId);
		List<Blog> blogs = query.setFirstResult(pageView.getFirstResult()).setMaxResults(maxresult).getResultList();
		pageView.setTotalrecord((Long) queryCount.getSingleResult());
		pageView.setRecords(blogs);
		return pageView;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> listNewset(int num) {
		return em.createQuery("select b from Blog b where b.visible=?1 order by id desc").setParameter(1, true).setMaxResults(num).getResultList();
	}

	@Override
	public Blog lastBlog(String bid) {
		try {
			return (Blog) em.createQuery("select b from Blog b where b.visible=?1 and b.id<?2 order by id desc").setParameter(1, true).setParameter(2, bid).setMaxResults(1)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Blog nextBlog(String bid) {
		try {
			return (Blog) em.createQuery("select b from Blog b where b.visible=?1 and b.id>?2 order by id asc").setParameter(1, true).setParameter(2, bid).setMaxResults(1)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
