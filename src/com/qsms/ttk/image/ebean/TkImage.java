package com.qsms.ttk.image.ebean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qsms.blog.ebean.Blog;
import com.qsms.share.ebean.Share;

@Entity
@Table(name = "t_tkimage")
public class TkImage {
	@Id
	private String id;

	/** 存储在本地的源地址 或者网络图片地址 **/
	private String oriurl;

	/** 备份后的存储地址：/upload/20150602/uuid.img **/
	private String backurl;

	/** 图片的宽 **/
	private int width;

	/** 图片的高 **/
	private int height;

	/** 图片大小(单位:字节) **/
	private int size;

	/** 图片UBB引用代码 **/
	private String ubburl;

	/** 图片HTML引用代码 **/
	private String htmlurl;

	/** 图片原图地址 **/
	private String linkurl;

	/** 图片展示图地址 **/
	private String s_url;

	/** 图片缩略图地址 **/
	private String t_url;

	/** 查找图片url **/
	private String find_url;

	/** 图片类型，如jpg **/
	private String type;

	/** 上传成功时间 **/
	private Date uploadTime;

	private Boolean visible = true;

	/** 是否备份到云盘 **/
	private Boolean backup = false;

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = false)
	@JoinColumn(name = "imagetask_id")
	private ImageTask imageTask;

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = true)
	@JoinColumn(name = "share_id")
	private Share share;

	@ManyToOne(cascade = { CascadeType.REFRESH, }, optional = true)
	@JoinColumn(name = "blog_id")
	private Blog blog;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getUbburl() {
		return ubburl;
	}

	public void setUbburl(String ubburl) {
		this.ubburl = ubburl;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getHtmlurl() {
		return htmlurl;
	}

	public void setHtmlurl(String htmlurl) {
		this.htmlurl = htmlurl;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String getS_url() {
		return s_url;
	}

	public void setS_url(String sUrl) {
		s_url = sUrl;
	}

	public String getT_url() {
		return t_url;
	}

	public void setT_url(String tUrl) {
		t_url = tUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFind_url() {
		return find_url;
	}

	public void setFind_url(String findUrl) {
		find_url = findUrl;
	}

	public String getOriurl() {
		return oriurl;
	}

	public void setOriurl(String oriurl) {
		this.oriurl = oriurl;
	}

	public ImageTask getImageTask() {
		return imageTask;
	}

	public void setImageTask(ImageTask imageTask) {
		this.imageTask = imageTask;
	}

	public Boolean getBackup() {
		return backup;
	}

	public void setBackup(Boolean backup) {
		this.backup = backup;
	}

	public String getBackurl() {
		return backurl;
	}

	public void setBackurl(String backurl) {
		this.backurl = backurl;
	}

}
