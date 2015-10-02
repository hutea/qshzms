package com.qsms.core.ebean;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "t_star")
public class Star {

	@Id
	private String id;

	/** 代码 */
	@Column(unique = true)
	private String code;

	/** 名称 */
	private String name;

	/** 简短说明 */
	private String note;

	/** 分类：1=华语、2=欧美、3=日韩、（1-10明星资源）（11-20计算机资源预留） */
	private int type = 1;

	/** 首页排序使用：数字越大越靠前 */
	private int iod = 1;

	/** 名称简拼 */
	private String simplePy;

	/** 名称全拼 */
	private String fullPy;

	/** 图片地址 */
	private String imageUrl;

	/** 小图片地址 */
	private String imgUrl;

	/** 摘要 */
	@Lob
	private String summary;

	/** 创建日期 */
	private Date createDate = new Date();

	/** 最后修改日期，有资源发布时该日期进行更新 */
	private Date modifyDate = new Date();

	/** 浏览量 */
	private int pv = 0;

	/** 排序级别 */
	private int lv = 1;

	/** 拥有的资源数 */
	private int resnum = 0;

	private String tagName;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "t_star_category", joinColumns = @JoinColumn(name = "sc_id"), inverseJoinColumns = @JoinColumn(name = "star_id"))
	@OrderBy(value = "lv asc")
	private Set<SupportCategory> categorys = new LinkedHashSet<SupportCategory>();

	private Boolean visible = true;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

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

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIod() {
		return iod;
	}

	public void setIod(int iod) {
		this.iod = iod;
	}

}
