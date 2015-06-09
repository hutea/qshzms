package com.qsms.share.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.qsms.share.ebean.Tag;
import com.qsms.util.Helper;
import com.qsms.util.dao.DAOSupport;

@Service
public class TagServiceBean extends DAOSupport<Tag> implements TagService {

	@Override
	public Tag findByName(String tagName) {
		String processTagName = tagName.toLowerCase().trim();
		try {
			return (Tag) em.createQuery("select o from Tag o where o.name=?1")
					.setParameter(1, processTagName).getSingleResult();
		} catch (Exception e) {
			Tag tag = new Tag();
			tag.setId(Helper.generatorShortID());
			tag.setName(processTagName);
			this.save(tag);
			return tag;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> topList(int num) {
		return em
				.createQuery(
						"select t from Tag t left join t.shares s where t.visible=?1 group by s  ORDER BY count(s.id) DESC")
				.setParameter(1, true).setMaxResults(num).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> listByUid(String uid, int num) {
		return em
				.createQuery(
						"select t from Tag t left join t.shares s where t.visible=?1 and s.user.id=?2 group by s ORDER BY count(s.id) DESC")
				.setParameter(1, true).setParameter(2, uid).setMaxResults(num)
				.getResultList();
	}
}
