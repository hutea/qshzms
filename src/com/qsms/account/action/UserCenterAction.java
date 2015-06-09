package com.qsms.account.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.account.ebean.User;
import com.qsms.account.service.UserService;
import com.qsms.auxiliary.ebean.Email;
import com.qsms.auxiliary.service.EmailService;
import com.qsms.auxiliary.service.SystemConfigService;
import com.qsms.share.ebean.ReportRecord;
import com.qsms.share.ebean.Share;
import com.qsms.share.ebean.ShareComment;
import com.qsms.share.service.ReportRecordService;
import com.qsms.share.service.ShareCommentService;
import com.qsms.share.service.ShareLikeService;
import com.qsms.share.service.ShareService;
import com.qsms.share.service.TagService;
import com.qsms.util.EmailSender;
import com.qsms.util.Helper;
import com.qsms.util.WebUtil;
import com.qsms.util.dao.PageView;

@RequestMapping(value = "/my")
@Controller
public class UserCenterAction {
	@Resource
	private ShareService shareService;
	@Resource
	private ShareCommentService shareCommentService;
	@Resource
	private ShareLikeService shareLikeService;
	@Resource
	private SystemConfigService systemConfigService;
	@Resource
	private ReportRecordService reportRecordService;
	@Resource
	private UserService userService;

	@Resource
	private TagService tagService;

	@Resource
	private EmailService emailService;

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	private int maxresult = 10;

	@RequestMapping("/share")
	public ModelAndView shareList(
			@RequestParam(required = false, defaultValue = "1") int page) {
		ModelAndView mav = new ModelAndView("view/my/user_share_list");
		PageView<Share> pageView = new PageView<Share>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.user.id=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(WebUtil.getLoginUser(request).getId());
		pageView.setQueryResult(shareService.getScrollData(pageView
				.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("mym", 2);
		mav.addObject("shareReportedMax", systemConfigService
				.shareReportedMax());
		return mav;
	}

	@RequestMapping("/share/new")
	public ModelAndView shareNew() {
		ModelAndView mav = new ModelAndView("view/my/user_share_new");
		return mav;
	}

	@RequestMapping("/comment")
	public ModelAndView commentList(
			@RequestParam(required = false, defaultValue = "1") int page) {
		ModelAndView mav = new ModelAndView("view/my/user_shareComment_list");
		PageView<ShareComment> pageView = new PageView<ShareComment>(maxresult,
				page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.user.id=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(WebUtil.getLoginUser(request).getId());
		pageView.setQueryResult(shareCommentService.getScrollData(pageView
				.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("mym", 3);
		mav.addObject("shareCommentReportedMax", systemConfigService
				.shareCommentReportedMax());
		return mav;
	}

	@RequestMapping("/report")
	public ModelAndView reportList(
			@RequestParam(required = false, defaultValue = "1") int page) {
		ModelAndView mav = new ModelAndView("view/my/user_report_list");
		PageView<ReportRecord> pageView = new PageView<ReportRecord>(maxresult,
				page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.user.id=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(WebUtil.getLoginUser(request).getId());
		pageView.setQueryResult(reportRecordService.getScrollData(pageView
				.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("shareCommentReportedMax", systemConfigService
				.shareCommentReportedMax());
		mav.addObject("shareReportedMax", systemConfigService
				.shareReportedMax());
		mav.addObject("mym", 4);
		return mav;
	}

	@RequestMapping("/center")
	public ModelAndView center() {
		ModelAndView mav = new ModelAndView("view/my/user_center");
		String uid = WebUtil.getLoginUser(request).getId();
		mav.addObject("myShares", shareService.countByUid(uid));
		mav.addObject("myComments", shareCommentService.countByUid(uid));
		mav.addObject("myReports", reportRecordService.countByUid(uid));
		mav.addObject("myLikes", shareLikeService.countByUid(uid));
		mav.addObject("myTags", tagService.listByUid(uid, 100));
		mav.addObject("mym", 1);
		return mav;
	}

	@RequestMapping("/signout")
	public @ResponseBody
	String signout(@ModelAttribute Share share) {
		User user = WebUtil.getLoginUser(request);
		if (!user.getEmailBind()) {
			return "ERROR";
		}
		WebUtil.delCookie(request, response, "uid");// 删除cookie信息
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		return "SUCCESS";
	}

	@RequestMapping("/email/send")
	public @ResponseBody
	String sendEmail(@RequestParam String email) {
		if (!Helper.validateEmail(email)) {// 邮箱错误
			return "EMAILERROR";
		}
		User user = WebUtil.getLoginUser(request);
		User entity = userService.find(user.getId());
		if (!entity.getEmailBind()) {// 没有绑定
			if (entity.getEmailTime() != null) {
				long timeDistance = System.currentTimeMillis()
						- entity.getEmailTime().getTime();
				if (timeDistance < 55 * 1000) {// 距离上次绑定时间要大于1分钟
					return 55 - Math.floor(timeDistance / 1000) + ""; // 向下取整，还有多少秒可以重发
				}
			}
			entity.setEmail(email);
			entity.setEmailTime(new Date());
			try {
				userService.update(entity);
			} catch (Exception e) {
				return "EMAILUSED";// 邮箱已经被在使用
			}
			String code = Helper.emailCode();
			StringBuffer mailContent = new StringBuffer("<h3>邮箱绑定</h3>");
			mailContent.append("<p>您可以通过以下两种方式完成邮箱绑定：</p>");
			mailContent
					.append("方法一，复制右边验证码到邮箱绑定页面完成绑定：<span style='border:1px solid #cdc;padding:2px 5px;'>"
							+ code + "</span>");
			String link = "http://www.qishimeishi.com/my/email/bind?uid="
					+ entity.getId() + "&code=" + code + "&t="
					+ System.currentTimeMillis();
			mailContent.append("<p>方法二，点击右边链接直接完成绑定：<a href='" + link + "'>"
					+ link + "</a></p>");
			mailContent
					.append("<hr><p>请在24小时内完成绑定【其实没事(www.qishimeishi.com) 友情提醒】</p>");
			emailService.emailRecord(entity.getId(), code);
			System.out.println(mailContent);
			EmailSender.send(email, "其实没事@身份认证", mailContent.toString(),
					"text/html");
			return "SUCCESS";
		} else {
			return "BINDED";
		}
	}

	@RequestMapping("/email/bind/ajax")
	public @ResponseBody
	String bindEmailAjax(@RequestParam String code) {
		String uid = WebUtil.getLoginUser(request).getId();
		Email email = emailService.findByUC(uid, code);
		if (email == null) {
			return "CODEERROR";
		}
		if (System.currentTimeMillis() - email.getSendTime().getTime() > 24 * 3600 * 1000) {
			return "CODEOVERTIME";
		}
		User user = userService.find(uid);
		if (user != null && user.getEmailBind()) {
			return "BINDED";
		}
		user.setEmailTime(new Date());
		user.setEmailBind(true);
		user.setPassword(user.getEmail().substring(0, 6));
		userService.update(user);
		updateSessionUser(user);
		return "SUCCESS";
	}

	@RequestMapping("/email/bind")
	public ModelAndView bindEmail(@RequestParam String code,
			@RequestParam String uid) {
		Email email = emailService.findByUC(uid, code);
		ModelAndView mav = new ModelAndView("view/my/user_center_email");
		if (email == null) {
			mav.addObject("msg", "CODEERROR");
			return mav;
		}
		if (System.currentTimeMillis() - email.getSendTime().getTime() > 24 * 3600 * 1000) {
			mav.addObject("msg", "CODEOVERTIME");
			return mav;
		}
		User user = userService.find(uid);
		if (user != null && user.getEmailBind()) {
			mav.addObject("msg", "BINDED");
			return mav;
		}
		user.setBindTime(new Date());
		user.setEmailBind(true);
		user.setPassword(user.getEmail().substring(0, 6));
		userService.update(user);
		mav.addObject("msg", "SUCCESS");
		updateSessionUser(user);
		return mav;
	}

	@RequestMapping("/profile/edit")
	public @ResponseBody
	String editProfile(String nickname) {
		User user = userService.find(WebUtil.getLoginUser(request).getId());
		user.setNickname(nickname);
		userService.update(user);
		updateSessionUser(user);
		return "SUCCESS";
	}

	@RequestMapping("/pwd/edit")
	public @ResponseBody
	String editPassword(String oripwd, String newpwd) {
		User user = userService.find(WebUtil.getLoginUser(request).getId());
		if (!user.getEmailBind()) {
			return "UNBIND";// 未绑定邮箱不能修改密码
		}
		if (!oripwd.equals(user.getPassword())) {
			return "ORIPWDERROR";// 原密码错误
		}
		user.setPassword(newpwd);
		userService.update(user);
		updateSessionUser(user);
		return "SUCCESS";
	}

	/**
	 * 更新session中的User
	 * 
	 * @param user
	 */
	private void updateSessionUser(User user) {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60 * 60); // 设置有效时间为60分钟
		session.setAttribute("loginUser", user);
	}

}
