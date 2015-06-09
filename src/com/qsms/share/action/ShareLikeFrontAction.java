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
import com.qsms.share.ebean.Share;
import com.qsms.share.ebean.ShareLike;
import com.qsms.share.service.ShareLikeService;
import com.qsms.share.service.ShareService;
import com.qsms.util.WebUtil;

@Controller
public class ShareLikeFrontAction {
	@Resource
	private ShareLikeService shareLikeService;
	@Resource
	private ShareService shareService;
	@Resource
	private UserService userService;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/share/like/save", method = RequestMethod.POST)
	public @ResponseBody
	String add(@RequestParam String sid) {
		User user = WebUtil.getLoginUser(request);
		if (user == null) {
			return "NOUSER";// 用户未登录
		}
		ShareLike slDB = shareLikeService.find(sid + "-" + user.getId());
		if (slDB != null) {
			return "RELIKE"; // 用户已Like过
		}
		Share share = shareService.find(sid);
		if (share == null) {
			return "NOSHARE";// sid没有对应的Share
		}
		ShareLike sl = new ShareLike();
		sl.setId(sid + "-" + user.getId());
		sl.setShare(shareService.find(sid));
		sl.setUser(userService.find(user.getId()));
		shareLikeService.save(sl);
		share.setLk(share.getLk() + 1);
		share.resetStar();
		shareService.update(share);
		return share.getLk() + "";
	}
}
