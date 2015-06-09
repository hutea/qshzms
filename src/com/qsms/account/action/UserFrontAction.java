package com.qsms.account.action;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.account.ebean.User;
import com.qsms.account.service.UserService;
import com.qsms.auxiliary.ebean.Email;
import com.qsms.auxiliary.service.EmailService;
import com.qsms.util.EmailSender;
import com.qsms.util.Helper;
import com.qsms.util.WebUtil;

@RequestMapping("/user")
@Controller
public class UserFrontAction {
	@Resource
	private UserService userService;
	@Resource
	private EmailService emailService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	/**
	 * 一键注册
	 */
	@RequestMapping("/signup")
	public @ResponseBody
	String signup(@RequestParam String app) {
		HttpSession session = request.getSession();
		User sesUser = (User) session.getAttribute("loginUser");
		if (sesUser != null) {// 用户已登录
			return sesUser.getId();
		}
		User user = new User();
		user.setId(app.replaceAll(" ", "") + "-" + Helper.generatorID());
		user.setCreateDate(new Date());
		userService.save(user);
		session.setMaxInactiveInterval(60 * 60); // 设置有效时间为60分钟
		session.setAttribute("loginUser", user);
		WebUtil.addCookie(response, "uid", user.getId(), Integer.MAX_VALUE);
		return user.getId();
	}

	@RequestMapping("/signin")
	public @ResponseBody
	String signin(@RequestParam String email, @RequestParam String password) {
		User user = userService.findByEP(email, password);
		if (user != null && user.getVisible()) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60 * 60); // 设置有效时间为60分钟
			session.setAttribute("loginUser", user);
			WebUtil.addCookie(response, "uid", user.getId(), Integer.MAX_VALUE);
			return "SUCCESS";
		} else {
			return "ERROR";
		}
	}

	@RequestMapping("/pwd/find")
	public ModelAndView findPwd() {
		ModelAndView mav = new ModelAndView("view/common/pwd_find");
		return mav;
	}

	/** 密码找回邮件 **/
	@RequestMapping("/pwd/email/send")
	public @ResponseBody
	String pwdSendEmail(@RequestParam String email) {
		User entity = userService.findByEmail(email);
		if (entity == null) {// 错误的邮箱
			return "EMAILERROR";
		}
		if (entity.getEmailTime() != null) {
			long timeDistance = System.currentTimeMillis() - entity.getEmailTime().getTime();
			if (timeDistance < 55 * 1000) {// 距离上次绑定时间要大于1分钟
				return 55 - Math.floor(timeDistance / 1000) + ""; // 向下取整，还有多少秒可以重发
			}
		}
		String code = Helper.emailCode();
		entity.setEmailTime(new Date());
		userService.update(entity);
		StringBuffer mailContent = new StringBuffer("<h3>密 码 找 回</h3>");
		mailContent.append("系统为您分配了随机密 码：<span style='border:1px solid #cdc;padding:2px 5px;'>"
				+ code + "</span>");
		String link = "http://www.qishimeishi.com/user/pwd/reset?uid=" + entity.getId() + "&code="
				+ code + "&t=" + System.currentTimeMillis();
		mailContent.append("<p>点击右边链接完成密码找回：<a href='" + link + "'>" + link + "</a></p>");
		mailContent.append("<hr><p>请在24小时内完成密码找回【其实没事(www.qishimeishi.com) 友情提醒】</p>");
		emailService.emailRecord(entity.getId(), code);
		System.out.println(mailContent);
		EmailSender.send(email, "其实没事@身份认证", mailContent.toString(), "text/html");
		return "SUCCESS";
	}

	/** 密码找回确定 **/
	@RequestMapping("/pwd/reset")
	public ModelAndView pwdReset(@RequestParam String uid, @RequestParam String code) {
		Email email = emailService.findByUC(uid, code);
		ModelAndView mav = new ModelAndView("view/common/pwd_find_success");
		if (email == null) {
			mav.addObject("msg", "CODEERROR");
			return mav;
		}
		if (System.currentTimeMillis() - email.getSendTime().getTime() > 24 * 3600 * 1000) {
			mav.addObject("msg", "CODEOVERTIME");
			return mav;
		}
		User user = userService.find(uid);
		if (user == null) {
			mav.addObject("msg", "UIDERROR");
			return mav;
		}
		user.setEmailBind(true);
		user.setPassword(code);
		userService.update(user);
		mav.addObject("msg", "SUCCESS");
		mav.addObject("pwd", code);
		return mav;
	}
}
