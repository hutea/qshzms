package com.qsms.util;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class FileCleanerTask {

	private Log log = LogFactory.getLog("dataServerLog");

	public void clear() {
		String path = this.getClass().getClassLoader().getResource("")
				.getPath();
		File dir = new File(path);
		File appRoot = dir.getParentFile().getParentFile();
		File uploadDirs = new File(appRoot, "resource/upload");
		double uploadSize = Helper.getDirSize(uploadDirs);
		System.out.println("要清理的总目录大小为：" + uploadSize);
		if (uploadSize < 400) {// 小于400M不清理
			return;
		}
		System.out.println("开始执行清理：" + uploadDirs.getAbsolutePath());
		for (File uploadDir : uploadDirs.listFiles()) {
			log.info("uploadDir:" + uploadDir.getAbsolutePath());
			if (uploadDir.isDirectory()) {
				log.info("目录:" + uploadDir.getAbsolutePath() + "清理开始");
				for (File file : uploadDir.listFiles()) {
					if (file.isFile()) {
						Boolean result = file.delete();
						log.info("删除结果：" + result);
					}
				}
				double uploadSizeAfterClean = Helper.getDirSize(uploadDirs);
				if (uploadSizeAfterClean < 400) {// 小于400M则终止
					break;
				}
			}
		}
	}
}
