package com.qsms.auxiliary.service;

import org.springframework.stereotype.Service;

import com.qsms.auxiliary.ebean.SystemConfig;
import com.qsms.util.dao.DAOSupport;

@Service
public class SystemConfigServiceBean extends DAOSupport<SystemConfig> implements
		SystemConfigService {

	@Override
	public boolean isExternalSite(String url) {
		String esite = "tietuku.com";
		try {
			esite = this.find("externalSite").getValue();
		} catch (Exception e) {
			esite = "tietuku.com";
		}
		String[] sites = esite.split("#");
		for (String site : sites) {
			if (url.contains(site)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int shareReportedMax() {
		try {
			return Integer.parseInt(this.find("shareReportedMax").getValue());
		} catch (Exception e) {
			return 2;
		}
	}

	@Override
	public int shareCommentReportedMax() {
		try {
			return Integer.parseInt(this.find("shareCommentReportedMax").getValue());
		} catch (Exception e) {
			return 1;
		}
	}

	@Override
	public String blogAid() {
		try {
			return this.find("blogAid").getValue();
		} catch (Exception e) {
			return "1047390"; // 返回默认相册ID

		}
	}

	@Override
	public String shareAid() {
		try {
			return this.find("shareAid").getValue();
		} catch (Exception e) {
			return "1047390"; // 返回默认相册ID
		}
	}

	@Override
	public String defalutBlogTypeCode() {
		try {
			return this.find("defaultBlogTypeCode").getValue();
		} catch (Exception e) {
			return "java";
		}
	}

	@Override
	public String defalutBlogTagName() {
		try {
			return this.find("defaultBlogTagName").getValue();
		} catch (Exception e) {
			return "java";
		}
	}

}
