package com.qsms.share.service;

import java.util.List;

import com.qsms.share.ebean.Tag;
import com.qsms.util.dao.DAO;

public interface TagService extends DAO<Tag> {

	/**
	 * 通过标签名称获取标签，如果不存在则将此标签存储后再返回
	 * 
	 * @param tagName
	 * @return
	 */
	public Tag findByName(String tagName);

	/**
	 * 列出使用频率最高的num条标签
	 * 
	 * @param num
	 * @return
	 */
	public List<Tag> topList(int num);

	/**
	 * 列出指定用户用过的标签(以使用频率排序)
	 * 
	 * @param uid
	 *            :用户id
	 * @param num
	 *            ：列出的数目
	 * @return
	 */
	public List<Tag> listByUid(String uid, int num);

}
