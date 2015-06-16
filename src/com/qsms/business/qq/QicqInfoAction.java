package com.qsms.business.qq;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.account.service.UserService;
import com.qsms.share.service.ShareCommentService;
import com.qsms.share.service.TagService;
import com.qsms.util.Helper;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/qicq")
@Controller
public class QicqInfoAction {
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

	private int maxresult = 12;

	@RequestMapping("/list")
	public ModelAndView qq() {
		return list(1, null);
	}

	@RequestMapping("/qq/{page}")
	public ModelAndView list(@PathVariable int page,
			@RequestParam(required = false) String qicq) {
		ModelAndView mav = new ModelAndView("/customer/qq/qq_list");
		PageView<QicqInfo> pageView = new PageView<QicqInfo>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("top", "desc");
		orderby.put("sendTime", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1 and o.online=?2");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		params.add(true);
		if (qicq != null && !"".equals(qicq)) {
			jpql.append(" and o.qicq like?3");
			params.add("%" + qicq + "%");
		}
		pageView.setQueryResult(qicqInfoService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("qicq", qicq);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "plain/text; charset=UTF-8")
	public @ResponseBody
	String add(@ModelAttribute QicqInfo info) {
		try {
			info.setId(Helper.generatorShortID());
			info.setDigit(info.getQicq().trim().length());
			info.setSendTime(new Date());
			qicqInfoService.save(info);
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "plain/text; charset=UTF-8")
	public @ResponseBody
	String update(@ModelAttribute QicqInfo info) {
		try {
			QicqInfo entity = qicqInfoService.find(info.getId());
			entity.setQicq(info.getQicq());
			entity.setDigit(info.getQicq().trim().length());
			entity.setMoney(info.getMoney());
			entity.setOnline(info.getOnline());
			entity.setTop(info.getTop());
			entity.setDetail(info.getDetail());
			entity.setLastEditTime(new Date());
			qicqInfoService.update(entity);
			return "SUCCESS";
		} catch (Exception e) {
			return "ERROR";
		}
	}

}
