package com.qsms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qsms.account.ebean.User;

/**
 * 个人中心拦截器
 * 
 * @author www.hydom.cn [heou]
 * 
 */
@Component
public class CenterInteceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		User user = WebUtil.getLoginUser(request);
		if (user == null) {
			return false;
		}
		return super.preHandle(request, response, handler);
	}

}
