package com.qsms.blog.ebean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_blog")
public class Blog {
	@Id
	private String id;

	/** 标题 **/
	private String title;

	/** 内容 **/
	@Lob
	private String content;

	/** 文本 **/
	@Lob
	private String text;

	/** 摘要 **/
	@Lob
	private String summary;

	/** 静态页地址 **/
	private String html;

	/** 创建日期 **/
	private Date createDate = new Date();

	/** 浏览量 **/
	private int pv;

	/** Like数 **/
	private int lk;

	/** 是否被推荐 **/
	private Boolean recommend = false;

	/** 排序级别 **/
	private int lv = 1;

	private Boolean visible = true;

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
	@JoinColumn(name = "type_id")
	private BlogType blogType;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "t_blog_tag", joinColumns = @JoinColumn(name = "blog_id"), inverseJoinColumns = @JoinColumn(name = "blogTag_id"))
	private List<BlogTag> tags = new ArrayList<BlogTag>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getPv() {
		return pv;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}

	public int getLk() {
		return lk;
	}

	public void setLk(int lk) {
		this.lk = lk;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public BlogType getBlogType() {
		return blogType;
	}

	public void setBlogType(BlogType blogType) {
		this.blogType = blogType;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	public List<BlogTag> getTags() {
		return tags;
	}

	public void setTags(List<BlogTag> tags) {
		this.tags = tags;
	}

}
