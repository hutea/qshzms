package com.qsms.share.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qsms.account.ebean.User;
import com.qsms.account.service.UserService;
import com.qsms.share.ebean.ReportRecord;
import com.qsms.share.ebean.Share;
import com.qsms.share.ebean.ShareComment;
import com.qsms.share.service.ReportRecordService;
import com.qsms.share.service.ShareCommentService;
import com.qsms.share.service.ShareService;
import com.qsms.util.WebUtil;

@Controller
public class ReportRecordFrontAction {
	@Resource
	private ReportRecordService reprotRecordService;
	@Resource
	private ShareService shareService;
	@Resource
	private ShareCommentService shareCommentService;
	@Resource
	private UserService userService;
	@Autowired
	private HttpServletRequest request;

	/**
	 * 
	 * @param sid
	 *            Share ID 或者 ShareComment ID
	 * @param type
	 *            1表示Share ID 2表示 ShareComment ID
	 * @return
	 */
	@RequestMapping(value = "/share/report", method = RequestMethod.POST)
	public @ResponseBody
	String add(@RequestParam String sid, @RequestParam int type) {
		User sessionUser = WebUtil.getLoginUser(request);
		if (sessionUser == null) {
			return "NOUSER";// 用户未登录
		}
		User user = userService.find(sessionUser.getId());
		ReportRecord rrDB = reprotRecordService.find(sid + "-" + user.getId());
		if (rrDB != null) {
			return "REREPORT"; // 当前用户已经举报过
		}
		if (type == 1) {// Share举报
			Share share = shareService.find(sid);
			if (share == null) {
				return "NONE";// sid没有对应的Share
			}
			if (share.getSiteauth()) {// 官方认证不可举报
				return "SITEAUTH";
			}
			ReportRecord rr = new ReportRecord();
			rr.setId(sid + "-" + user.getId());
			rr.setShare(share); // 
			rr.setUser(user);
			reprotRecordService.save(rr);
			share.setReported(share.getReported() + 1);
			shareService.update(share);
			return "TRUE";
		} else if (type == 2) {// ShareComment举报
			ShareComment shareComment = shareCommentService.find(sid);
			if (shareComment == null) {
				return "NONE"; // sid没有对应的ShareComment
			}
			if (shareComment.getSiteauth()) {// 官方认证不可举报
				return "SITEAUTH";
			}
			ReportRecord sl = new ReportRecord();
			sl.setId(sid + "-" + user.getId());
			sl.setShareComment(shareComment); // 设置ShareComment ID
			sl.setUser(user);
			reprotRecordService.save(sl);
			shareComment.setReported(shareComment.getReported() + 1);
			shareCommentService.update(shareComment);
			return "TRUE";
		} else {
			return "ERRORTYPE"; // type类型错误
		}

	}
}
