package com.qsms.ttk.image.ebean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_imagetask")
public class ImageTask {
	@Id
	private String id;

	/** 记录来源：1=博客；2=share **/
	private int type;

	/** 记录来源ID **/
	private String sobId;

	private boolean visible = false; // true表示已处理

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSobId() {
		return sobId;
	}

	public void setSobId(String sobId) {
		this.sobId = sobId;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
