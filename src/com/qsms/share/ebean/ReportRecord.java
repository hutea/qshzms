package com.qsms.share.ebean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.qsms.account.ebean.User;

/**
 * 被举报记录
 * 
 * @author Administrator
 * 
 */

@Entity
@Table(name = "t_reportrecord")
public class ReportRecord {
	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date reportTime = new Date();// 举报时间

	private Boolean visible = true;

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = true)
	@JoinColumn(name = "share_id")
	private Share share;

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = true)
	@JoinColumn(name = "comment_id")
	private ShareComment shareComment;

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public ShareComment getShareComment() {
		return shareComment;
	}

	public void setShareComment(ShareComment shareComment) {
		this.shareComment = shareComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
