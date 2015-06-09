package com.qsms.share.ebean;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.qsms.account.ebean.User;

@Entity
@Table(name = "t_share")
public class Share {
	@Id
	private String id;

	/** 1=下载；2=文章；3=音乐；4=视频；5=图片 **/
	private int category;

	/** 标题 **/
	private String title;

	/** 内容摘要 **/
	@Lob
	private String sumary;

	/** 内容 **/
	@Lob
	private String content;

	/** 直接可见 **/
	private Boolean view = true;// 直接可见

	/** url地址：下载链接、音视频播放地址、图片地址、文章引用地址 **/
	private String url;

	/** 播放代码：通常是根据视频url地址生成 **/
	private String video;

	/** 赞数 **/
	private int lk = 0;

	/** 评论总数 **/
	private int comt = 0;

	/** 别人的评论数(每个人的N次评论，只进行+1操作)，主要用于评定星级 **/
	private int comtOther = 0;

	/** 被举报数 **/
	private int reported = 0;

	/** 站点认证，使得用户不能举报此信息 **/
	private Boolean siteauth = false;

	/** 推荐星级 **/
	private int star = 0;// 

	/** 创建日期 **/
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	private Boolean visible = true;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "t_share_tag", joinColumns = @JoinColumn(name = "share_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tags = new ArrayList<Tag>();

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	private Boolean loadMore = false; // 是否显示“加载更多”链接

	@Transient
	private Boolean comment = false; // 是否评论过：不存数据库

	/**
	 * 根据评论数和赞数重设星级
	 */
	public void resetStar() {
		// 设置星级 :10次赞换一星；5次评论换一星
		int lkStar = this.getLk() / 10;// 赞能获得的星
		int cmtStar = this.getComtOther() / 5;// 评论能获得的星
		int star = lkStar + cmtStar;// 能获得的星
		if (star > this.getStar()) {
			this.setStar(star);
			if (this.getStar() > 5) {
				this.setStar(5);// 最多只能5星
			}
		}
	}

	/**
	 * 根据视频地址生成播放代码
	 */
	public void genVideo() {
		if (this.category != 4 || this.url == null || "".equals(this.url)) {// 类型非视频
			return;
		}
		if (url.contains("youku.com")) {// url为来源为youku
			if (url.startsWith("http://player.youku.com")) {
				// <embed
				// src="http://player.youku.com/player.php/sid/XOTU3MzA3NjAw/v.swf"
				// allowFullScreen="true" quality="high"
				// width="480" height="400" align="middle"
				// allowScriptAccess="always"
				// type="application/x-shockwave-flash"></embed>
				String vcode = "<embed src=\""
						+ this.url
						+ "\" allowFullScreen=\"true\" quality=\"high\" width=\"480\" height=\"400\" align=\"middle\" allowScriptAccess=\"always\" type=\"application/x-shockwave-flash\"></embed>";
				this.video = vcode;
			} else {
				this.video = this.url;
			}
		}
	}

	/**
	 * 处理页面显示效果：<br>
	 * 1.对于设置了“评论可见”的内容，进行判断是否可显示
	 * 
	 * @param loginUser
	 * @param count
	 *            :当前用户对当前share的评论总数
	 */
	public void viewload(User loginUser, long count) {
		if (!this.getView()) {// 设置了评论可见
			if (loginUser != null) {
				if (this.getUser().getId().equals(loginUser.getId())) {// 用户自己发布的，直接可见
					this.setComment(true);
				} else {
					this.setComment(count > 0 ? true : false);// 设置用户是否评论过
				}
			} else {
				this.setComment(false);
			}
		}
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public int getReported() {
		return reported;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public void setReported(int reported) {
		this.reported = reported;
	}

	public int getStar() {
		return star;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Boolean getSiteauth() {
		return siteauth;
	}

	public void setSiteauth(Boolean siteauth) {
		this.siteauth = siteauth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getComment() {
		return comment;
	}

	public void setComment(Boolean comment) {
		this.comment = comment;
	}

	public int getLk() {
		return lk;
	}

	public void setLk(int lk) {
		this.lk = lk;
	}

	public int getComt() {
		return comt;
	}

	public void setComt(int comt) {
		this.comt = comt;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Boolean getView() {
		return view;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setView(Boolean view) {
		this.view = view;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSumary() {
		return sumary;
	}

	public void setSumary(String sumary) {
		this.sumary = sumary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getComtOther() {
		return comtOther;
	}

	public void setComtOther(int comtOther) {
		this.comtOther = comtOther;
	}

	public Boolean getLoadMore() {
		return loadMore;
	}

	public void setLoadMore(Boolean loadMore) {
		this.loadMore = loadMore;
	}

}
