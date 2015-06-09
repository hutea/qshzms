package com.qsms.share.ebean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qsms.account.ebean.User;

/**
 * 评论
 * 
 * @author Administrator
 * 
 */

@Entity
@Table(name = "t_sharecomment")
public class ShareComment {
	@Id
	private String id;

	/** 评论时间 **/
	private Date cmtTime = new Date();

	/** 评论内容 **/
	@Lob
	private String content;

	/** 被举报数 **/
	private int reported = 0;

	/** 站点认证，使得用户不能举报 **/
	private Boolean siteauth = false;

	private Boolean visible = true;

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
	@JoinColumn(name = "share_id")
	private Share share;

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	public ShareComment() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getSiteauth() {
		return siteauth;
	}

	public void setSiteauth(Boolean siteauth) {
		this.siteauth = siteauth;
	}

	public int getReported() {
		return reported;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setReported(int reported) {
		this.reported = reported;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public Date getCmtTime() {
		return cmtTime;
	}

	public void setCmtTime(Date cmtTime) {
		this.cmtTime = cmtTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

}
