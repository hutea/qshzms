package com.qsms.share.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.share.ebean.Share;
import com.qsms.share.ebean.Tag;
import com.qsms.share.service.TagService;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/share/tag")
@Controller
public class TagAction {
	@Resource
	private TagService tagService;

	private int maxresult = 12;

	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false) String queryContent) {
		ModelAndView mav = new ModelAndView("/share/tag_list");
		PageView<Tag> pageView = new PageView<Tag>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if (queryContent != null && !"".equals(queryContent)) {
			jpql.append(" and o.name like?2");
			params.add("%" + queryContent + "%");
		}
		pageView.setQueryResult(tagService.getScrollData(pageView
				.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", 2);
		mav.addObject("queryContent", queryContent);
		return mav;
	}

	@RequestMapping("/delete")
	public @ResponseBody
	String delete(@RequestParam String tid) {
		Tag tag = tagService.find(tid);
		for (Share share : tag.getShares()) {
			share.getTags().remove(tag);
		}
		tagService.delete(tid);
		return "true";
	}

}
