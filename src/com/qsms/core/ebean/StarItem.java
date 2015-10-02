package com.qsms.core.ebean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qsms.account.ebean.User;

@Entity
@Table(name = "t_starItem")
public class StarItem {

	@Id
	private String id;

	/** 资源名称 **/
	private String name;

	/** 演出时间 **/
	private Date showDate;

	/** 展示图 **/
	private String showImage;

	/**
	 * <option value="1">MV</option> <br>
	 * <option value="2">音乐现场</option> <br>
	 * <option value="3">演唱会</option> <br>
	 * <option value="4">影视</option> <br>
	 * <option value="5">综艺</option> <br>
	 * <option value="5">综艺</option> <br>
	 * <option value="0">其他</option> <br>
	 **/

	/** 摘要 **/
	@Lob
	private String summary;

	@Lob
	private String content;

	/** 播放地址或代码 **/
	@Lob
	private String playUrl;

	/** 播放点击数 **/
	private int playNum = 0;

	/** 百度云下载地址 **/
	private String baiduDownUrl;

	/** 百度云下载点击数 **/
	private int baiduDownNum = 0;

	/** 创建日期 **/
	private Date createDate = new Date();

	/** 修改日期 **/
	private Date modifyDate = new Date();

	/** 排序级别 **/
	private int lv = 1;

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "s_id")
	private Star star;

	@ManyToOne(cascade = { CascadeType.REFRESH }, optional = false)
	@JoinColumn(name = "supportCategory_id")
	private SupportCategory category;

	private Boolean visible = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public String getPlayUrl() {
		return playUrl;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}

	public int getPlayNum() {
		return playNum;
	}

	public SupportCategory getCategory() {
		return category;
	}

	public void setCategory(SupportCategory category) {
		this.category = category;
	}

	public void setPlayNum(int playNum) {
		this.playNum = playNum;
	}

	public String getBaiduDownUrl() {
		return baiduDownUrl;
	}

	public void setBaiduDownUrl(String baiduDownUrl) {
		this.baiduDownUrl = baiduDownUrl;
	}

	public int getBaiduDownNum() {
		return baiduDownNum;
	}

	public void setBaiduDownNum(int baiduDownNum) {
		this.baiduDownNum = baiduDownNum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getShowImage() {
		return showImage;
	}

	public void setShowImage(String showImage) {
		this.showImage = showImage;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
		this.star = star;
	}

}
