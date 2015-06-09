package com.qsms.ttk.json;

import java.util.List;

public class Album {

	private String albumid;// 相册ID：在创建相册返回
	private String aid;// 相册ID：列表返回
	private String albumname;// 相册名称
	private String num;// 相册照片数量
	private String type;//
	private String code;// 查询状200为查询成功
	private String info;// 错误信息
	private List<Pic> pic;

	public String getAlbumid() {
		return albumid;
	}

	public void setAlbumid(String albumid) {
		this.albumid = albumid;
	}

	public String getAlbumname() {
		return albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Pic> getPic() {
		return pic;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setPic(List<Pic> pic) {
		this.pic = pic;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
