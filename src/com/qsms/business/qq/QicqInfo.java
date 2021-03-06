package com.qsms.business.qq;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "bcqq_qicqinfo")
public class QicqInfo {
	@Id
	private String id;

	@Column(unique = true)
	private String qicq;

	private Double money;

	/** 置顶 **/
	private int top = 0;

	/** 号码位数 **/
	private int digit;

	@Lob
	private String detail;

	private Date sendTime;

	private Date lastEditTime;

	private Boolean online = true;

	private Boolean visible = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQicq() {
		return qicq;
	}

	public void setQicq(String qicq) {
		this.qicq = qicq;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public int getDigit() {
		return digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

}
