package com.qsms.business.qq;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.account.service.UserService;
import com.qsms.share.service.ShareCommentService;
import com.qsms.share.service.TagService;
import com.qsms.util.dao.PageView;

@Controller
public class QicqInfoFrontAction {
	@Resource
	private QicqInfoService qicqInfoService;
	@Resource
	private TagService tagService;
	@Resource
	private UserService userService;
	@Resource
	private ShareCommentService shareCommentService;
	@Autowired
	private HttpServletRequest request;

	private int maxresult = 18;

	@RequestMapping("/qq")
	public ModelAndView qq() {
		return list(1);
	}

	@RequestMapping("/qq/{page}")
	public ModelAndView list(@PathVariable int page) {
		ModelAndView mav = new ModelAndView("/customer/qq/index");
		PageView<QicqInfo> pageView = new PageView<QicqInfo>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("top", "desc");
		orderby.put("sendTime", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1 and o.online=?2");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		params.add(true);
		pageView.setQueryResult(qicqInfoService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		return mav;
	}

}
