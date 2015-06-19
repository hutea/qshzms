package com.qsms.business.qq;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	private int maxresult = 15;

	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false) String queryQicq) {
		ModelAndView mav = new ModelAndView("/customer/qq/qq_list");
		PageView<QicqInfo> pageView = new PageView<QicqInfo>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("online", "desc");
		orderby.put("top", "desc");
		orderby.put("sendTime", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if (queryQicq != null && !"".equals(queryQicq)) {
			jpql.append(" and o.qicq like?2");
			params.add("%" + queryQicq + "%");
		}
		pageView.setQueryResult(qicqInfoService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("queryQicq", queryQicq);
		return mav;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "plain/text; charset=UTF-8")
	public @ResponseBody
	String add(@ModelAttribute QicqInfo info, @RequestParam String queryQicq) {
		Map<String, String> dataMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			info.setId(Helper.generatorShortID());
			info.setDigit(info.getQicq().trim().length());
			info.setSendTime(new Date());
			qicqInfoService.save(info);
			dataMap.put("sign", "SUCCESS");
			dataMap.put("url", "/manage/qicq/list?page=1&queryQicq="
					+ queryQicq);
		} catch (Exception e) {
			dataMap.put("sign", "ERROR");
		}
		try {
			String json = mapper.writeValueAsString(dataMap);
			return json;
		} catch (JsonProcessingException e) {
			return "";
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "plain/text; charset=UTF-8")
	public @ResponseBody
	String update(@ModelAttribute QicqInfo info, @RequestParam int page,
			@RequestParam String queryQicq) {
		Map<String, String> dataMap = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
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
			dataMap.put("sign", "SUCCESS");
			dataMap.put("url", "/manage/qicq/list?page=" + page + "&queryQicq="
					+ queryQicq);
		} catch (Exception e) {
			dataMap.put("sign", "ERROR");
		}
		try {
			String json = mapper.writeValueAsString(dataMap);
			return json;
		} catch (JsonProcessingException e) {
			return "";
		}
	}

}
