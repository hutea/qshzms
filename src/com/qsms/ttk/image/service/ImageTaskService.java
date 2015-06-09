package com.qsms.ttk.image.service;

import com.qsms.ttk.image.ebean.ImageTask;
import com.qsms.util.dao.DAO;

public interface ImageTaskService extends DAO<ImageTask> {

	public void upload();

	/**
	 * 创建一个待同步任务<br>
	 * 内部逻辑：如果是第一次，则创建。 <br>
	 * 如果是在Share或Blog修改后，调用则设置对应的记录visible=false表示未处理来引发定时器重新处理。
	 * 
	 * @param type
	 *            1:Blog/2:Share
	 * @param sobId
	 *            Blog或Share的ID
	 */
	public void creatTask(int type, String sobId);

}
