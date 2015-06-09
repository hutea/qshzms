package com.qsms.ttk.json;

import java.util.List;

public class AlbumData {

	private String name;// 用户
	private int total;// 相册数量
	private List<Album> album;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<Album> getAlbum() {
		return album;
	}

	public void setAlbum(List<Album> album) {
		this.album = album;
	}

}
