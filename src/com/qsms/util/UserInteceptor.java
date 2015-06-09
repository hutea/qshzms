package com.qsms.util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.qsms.account.ebean.User;
import com.qsms.account.service.UserService;

@Component
public class UserInteceptor extends HandlerInterceptorAdapter {
	@Resource
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		User user = WebUtil.getLoginUser(request);
		if (user == null) {// 从cookie中获取
			String uid = WebUtil.getCookieValueByName(request, "uid");
			if (uid != null && !"".equals(uid)) {
				user = userService.find(uid);
				if (user != null && user.getVisible()) {// 没有被删除
					HttpSession session = request.getSession();
					session.setMaxInactiveInterval(60 * 60); // 设置有效时间为60分钟
					session.setAttribute("loginUser", user);
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

}
