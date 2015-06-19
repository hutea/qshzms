package com.qsms.account.ebean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_user")
public class User {
	@Id
	private String id;
	@Column(unique = true)
	private String email;
	private Boolean emailBind = false;
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	@Temporal(TemporalType.TIMESTAMP)
	private Date emailTime;// 发送邮件时间：主要指发送绑定邮件时间或者找回密码时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date bindTime;// 完成绑定邮件时间
	private String nickname; // 用户昵称
	private int maxUploadSize = 50 * 1024;// 允许用户当天传图最大kb数，默认是50M=1024*20
	private int maxShare = 30;// 允许用户当天最大分享数
	private int maxComment = 20; // 允许用户当天最大评论数
	private int uploadSize=0;// 用户当天已上传图片M数
	private int shareds=0;// 用户当天已分享记录数
	private int comments=0;// 用户当天已评论记录数
	private int lv = 1; // 用户级别：1=普通用户；2=邮箱用户；3=高级用户  4=vip用户 5=核心用户(发布直接成为站点认证，信息不能删除)
	private Boolean visible = true;

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getMaxShare() {
		return maxShare;
	}

	public void setMaxShare(int maxShare) {
		this.maxShare = maxShare;
	}

	public int getMaxComment() {
		return maxComment;
	}

	public void setMaxComment(int maxComment) {
		this.maxComment = maxComment;
	}

	public int getMaxUploadSize() {
		return maxUploadSize;
	}

	public void setMaxUploadSize(int maxUploadSize) {
		this.maxUploadSize = maxUploadSize;
	}

	public int getUploadSize() {
		return uploadSize;
	}

	public void setUploadSize(int uploadSize) {
		this.uploadSize = uploadSize;
	}

	public int getShareds() {
		return shareds;
	}

	public void setShareds(int shareds) {
		this.shareds = shareds;
	}

	public int getComments() {
		return comments;
	}

	public void setComments(int comments) {
		this.comments = comments;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public Boolean getEmailBind() {
		return emailBind;
	}

	public void setEmailBind(Boolean emailBind) {
		this.emailBind = emailBind;
	}

	public Date getEmailTime() {
		return emailTime;
	}

	public void setEmailTime(Date emailTime) {
		this.emailTime = emailTime;
	}

	public Date getBindTime() {
		return bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}

}
