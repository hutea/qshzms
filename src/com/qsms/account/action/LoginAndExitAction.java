package com.qsms.account.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.account.ebean.Account;
import com.qsms.account.service.AccountService;

@RequestMapping("/account")
@Controller
public class LoginAndExitAction {
	@Resource
	private AccountService accountService;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/sign")
	public ModelAndView signin() {
		ModelAndView mav = new ModelAndView("signin");
		return mav;
	}

	@RequestMapping("/signin")
	public ModelAndView signin(@RequestParam String username,
			@RequestParam String password) {
		ModelAndView mav = new ModelAndView("signin");
		Account account = accountService.findByUP(username, password);
		if (account == null) {
			request.setAttribute("error", "用户名或密码错误");
			return mav;
		}
		if (account.getServerEndDate().getTime() < System.currentTimeMillis()) {
			request.setAttribute("error", "服务到期");
			return mav;
		}
		HttpSession session = request.getSession();
		accountService.update(account);
		session.setAttribute("loginAccount", account);
		mav = new ModelAndView("redirect:/manage/blog/list");
		if ("caoyuan".equals(account.getUsername())) {
			mav = new ModelAndView("redirect:/manage/qicq/list");
		}
		return mav;
	}

	@RequestMapping("/signout")
	public ModelAndView signout() {
		ModelAndView mav = new ModelAndView("signin");
		HttpSession session = request.getSession();
		session.removeAttribute("loginAccount");
		return mav;
	}

}
