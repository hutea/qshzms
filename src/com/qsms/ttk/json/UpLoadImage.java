package com.qsms.ttk.json;

public class UpLoadImage {
	private int width;// 图片的宽
	private int height;// 图片的高
	private int size; // 图片大小(单位:字节)
	private String ubburl;// 图片UBB引用代码
	private String htmlurl;// 图片HTML引用代码
	private String linkurl;// 图片原图地址
	private String s_url;// 图片展示图地址
	private String t_url;// 图片缩略图地址
	private String type;//图片类型(如：jpg)

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

}
