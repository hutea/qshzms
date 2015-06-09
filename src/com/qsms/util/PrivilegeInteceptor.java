package com.qsms.util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qsms.account.ebean.Account;
import com.qsms.account.ebean.PrivilegeGroup;
import com.qsms.account.ebean.SystemPrivilege;
import com.qsms.account.service.SystemPrivilegeService;

/**
 * 权限细粒度拦截器
 * 
 * @author www.hydom.cn [heou]
 * 
 */
@Component
public class PrivilegeInteceptor extends HandlerInterceptorAdapter {
	@Resource
	private SystemPrivilegeService systemPrivilegeService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Account account = WebUtil.getloginAccount(request);
		if (account != null && "admin".equals(account.getUsername())) {
			return true;
		}
		StringBuffer requestUrl = request.getRequestURL();
		if (request.getQueryString() != null
				&& request.getQueryString().length() > 0) {
			requestUrl.append("#" + request.getQueryString());
		}
		String url = requestUrl.substring(requestUrl.indexOf("manage"));
		System.out.println(url);
		SystemPrivilege sp = systemPrivilegeService.findByURL(url);
		System.out.println(sp + "-->sp");
		if (sp != null) { // request url is required
			if (account != null && account.getGroups() != null) {
				System.out.println(account + "-->account");
				for (PrivilegeGroup group : account.getGroups()) {
					if (group.getPrivileges().contains(sp)) {
						return true;
					}
				}
				return false;
			} else {
				return false;
			}
		} else {
			return true;
		}

	}

}
