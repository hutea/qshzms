package com.qsms.share.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsms.account.ebean.User;
import com.qsms.account.service.UserService;
import com.qsms.auxiliary.service.SystemConfigService;
import com.qsms.share.ebean.Share;
import com.qsms.share.ebean.ShareComment;
import com.qsms.share.service.ShareCommentService;
import com.qsms.share.service.ShareService;
import com.qsms.util.Helper;
import com.qsms.util.WebUtil;
import com.qsms.util.dao.PageView;

@Controller
public class ShareCommentFrontAction {
	@Resource
	private ShareCommentService shareCommentService;
	@Resource
	private ShareService shareService;
	@Resource
	private UserService userService;
	@Resource
	private SystemConfigService systemConfigService;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/share/comment/list", method = RequestMethod.POST)
	public ModelAndView list(String sid, int page) {
		PageView<ShareComment> pageView = shareCommentService.listForTheShare(sid, page);
		ModelAndView mav = new ModelAndView("view/share/shareComment_more");
		mav.addObject("pageView", pageView);
		mav.addObject("sid", sid);
		mav.addObject("reportedMax", systemConfigService.shareCommentReportedMax());
		return mav;

	}

	@RequestMapping(value = "/share/comment/save", method = RequestMethod.POST, produces = "plain/text; charset=UTF-8")
	public @ResponseBody
	String add(@RequestParam String sid, @RequestParam String content) {
		try {
			User dbUser = userService.find(WebUtil.getLoginUser(request).getId());
			if (dbUser == null) {
				return "NOUSER";// 用户未登录
			}
			Share share = shareService.find(sid);
			if (share == null) {
				return "NOSHARE";// sid没有对应的Share
			}
			if (dbUser.getComments() > dbUser.getMaxComment()) {// 今日评论数超大上限
				return "TCMORE";// 今日评论数超过上限
			}
			boolean load = false;
			long comts = shareCommentService.countBySuid(sid, dbUser.getId());
			if (!share.getUser().getId().equals(dbUser.getId())) {// Share不属于当前评论人
				load = comts > 0 ? false : true; // 判断评论后是否加载Share内容.
			}
			ShareComment sc = new ShareComment();
			sc.setId(Helper.generatorID());
			sc.setShare(share);
			sc.setContent(content);
			sc.setUser(dbUser);
			shareCommentService.save(sc);
			share.setComt(share.getComt() + 1);// 设置评论总数:自评或其他人评的
			if (share.getUser().getId() != dbUser.getId()) {// 不是自己的Share
				if (comts == 0) {// 用户首次评论
					share.setComtOther(share.getComtOther() + 1);
					share.resetStar();
				}
			}
			shareService.update(share);
			dbUser.setComments(dbUser.getComments() + 1);// 设置用户当天评论数
			userService.update(dbUser);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			dataMap.put("comts", share.getComt());
			dataMap.put("content", share.getContent());
			dataMap.put("load", load);// 是否加载内容
			String json = mapper.writeValueAsString(dataMap);
			return json;
		} catch (Exception e) {
			return e.toString();
		}

	}
}
