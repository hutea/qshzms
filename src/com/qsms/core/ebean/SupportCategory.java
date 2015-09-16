package com.qsms.core.ebean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 支持的分类
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "t_supportcatgory")
public class SupportCategory {
	@Id
	private String id;

	/** 名称 */
	private String name;

	/** 排序 */
	private int lv;

	private Boolean visible = true;

	@ManyToMany(mappedBy = "categorys", cascade = CascadeType.REFRESH)
	private Set<Star> stars = new HashSet<Star>();

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

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public Set<Star> getStars() {
		return stars;
	}

	public void setStars(Set<Star> stars) {
		this.stars = stars;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

}
