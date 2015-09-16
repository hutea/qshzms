package com.qsms.core.ebean;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_star")
public class Star {

	@Id
	private String id;

	/** 名称 **/
	private String name;

	/** 名称简拼 **/
	private String simplePy;

	/** 名称全拼 **/
	private String fullPy;

	/** 图片地址 **/
	private String imageUrl;

	/** 摘要 **/
	@Lob
	private String summary;

	/** 创建日期 **/
	private Date createDate = new Date();

	/** 最后修改日期 **/
	private Date modifyDate;

	/** 浏览量 **/
	private int pv = 0;

	/** 排序级别 **/
	private int lv = 1;

	/** 拥有的资源数 **/
	private int resnum = 0;

	private String tagid;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "t_star_category", joinColumns = @JoinColumn(name = "sc_id"), inverseJoinColumns = @JoinColumn(name = "star_id"))
	private Set<SupportCategory> categorys = new LinkedHashSet<SupportCategory>();

	private Boolean visible = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Set<SupportCategory> getCategorys() {
		return categorys;
	}

	public void setCategorys(Set<SupportCategory> categorys) {
		this.categorys = categorys;
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

	public String getSimplePy() {
		return simplePy;
	}

	public void setSimplePy(String simplePy) {
		this.simplePy = simplePy;
	}

	public String getFullPy() {
		return fullPy;
	}

	public void setFullPy(String fullPy) {
		this.fullPy = fullPy;
	}

	public int getResnum() {
		return resnum;
	}

	public void setResnum(int resnum) {
		this.resnum = resnum;
	}

	public String getTagid() {
		return tagid;
	}

	public void setTagid(String tagid) {
		this.tagid = tagid;
	}

}
