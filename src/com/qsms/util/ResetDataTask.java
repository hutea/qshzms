package com.qsms.util;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.qsms.account.service.UserService;

@Component
public class ResetDataTask {
	@Resource
	private UserService userService;

	private Log log = LogFactory.getLog("dataServerLog");

	public void reset() {
		log.info("执行用户数据重置 START");
		long userCount = userService.getCount();
		int result = userService.resetData();
		log.info("用户总数：" + userCount);
		log.info("用户数据重置数：" + result);
		log.info("执行用户数据重置 END");
	}
}
