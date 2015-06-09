package com.qsms.share.ebean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qsms.account.ebean.User;

/**
 * 点赞记录
 * 
 * @author Administrator
 * 
 */

@Entity
@Table(name = "t_sharelike")
public class ShareLike {
	@Id
	private String id; // sid+"-"+uid

	private Date lkTime = new Date();// 点赞时间

	private Boolean visible = true;

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
	@JoinColumn(name = "share_id")
	private Share share;
	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getLkTime() {
		return lkTime;
	}

	public void setLkTime(Date lkTime) {
		this.lkTime = lkTime;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
