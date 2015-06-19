package com.qsms.share.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.account.ebean.User;
import com.qsms.account.service.UserService;
import com.qsms.share.ebean.Share;
import com.qsms.share.ebean.Tag;
import com.qsms.share.service.ShareCommentService;
import com.qsms.share.service.ShareService;
import com.qsms.share.service.TagService;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/share")
@Controller
public class ShareAction {
	@Resource
	private ShareService shareService;
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
	public ModelAndView list(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false) String queryContent) {
		ModelAndView mav = new ModelAndView("/share/share_list");
		PageView<Share> pageView = new PageView<Share>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if (queryContent != null && !"".equals(queryContent)) {
			jpql.append(" and (o.title like?2 or o.content like?3 or o.id like?4 )");
			params.add("%" + queryContent + "%");
			params.add("%" + queryContent + "%");
			params.add("%" + queryContent + "%");
		}
		pageView.setQueryResult(shareService.getScrollData(pageView
				.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		for (Share share : pageView.getRecords()) {
			User user = share.getUser();
			if (user.getNickname() == null || "".equals(user.getNickname())) {
				String userid = user.getId();
				user.setNickname(userid.substring(userid.length() - 8));
			}
		}
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("queryContent", queryContent);
		mav.addObject("m", 2);
		return mav;
	}

	@RequestMapping("/edit/{sid}")
	public ModelAndView editUI(@PathVariable String sid) {
		ModelAndView mav = new ModelAndView("/share/share_edit");
		Share share = shareService.find(sid);
		mav.addObject("share", share);
		mav.addObject("m", 2);
		StringBuffer tag_str = new StringBuffer();
		for (Tag tag : share.getTags()) {
			tag_str.append(tag.getName() + "#");
		}
		int i = tag_str.lastIndexOf("#");
		if (i != -1) {
			mav.addObject("tag_str", tag_str.substring(0, i));
		}
		return mav;
	}

	@RequestMapping("/update")
	public ModelAndView edit(@ModelAttribute Share share,
			@RequestParam(required = false) String tag_str) {
		Share entity = shareService.find(share.getId());
		entity.setCategory(share.getCategory());
		entity.setComt(share.getComt());
		entity.setContent(share.getContent());
		entity.setLk(share.getLk());
		entity.setSiteauth(share.getSiteauth());
		if (entity.getSiteauth()) {// 站点认证清空举报数
			entity.setReported(0);
		}
		entity.setStar(share.getStar());
		entity.setSumary(share.getSumary());
		entity.setTitle(share.getTitle());
		entity.setUrl(share.getUrl());
		entity.setVideo(share.getVideo());
		entity.setView(share.getView());

		/** 开始处理标签 **/
		entity.getTags().clear(); // 清空
		if (tag_str != null && tag_str.length() >= 0) {
			String[] tagNames = tag_str.trim().toLowerCase().split("#");
			for (String tagName : tagNames) {
				if (tagName != null && !"".equals(tagName.replaceAll(" ", ""))) {// 空标签不存储
					Tag tag = tagService.findByName(tagName);
					entity.getTags().add(tag);
				}
			}
		}
		shareService.update(entity);
		ModelAndView mav = new ModelAndView("redirect:show/" + share.getId());
		return mav;
	}

	@RequestMapping("/show/{sid}")
	public ModelAndView show(@PathVariable String sid) {
		Share share = shareService.find(sid);
		ModelAndView mav = new ModelAndView("/share/share_show");
		mav.addObject("m", 2);
		mav.addObject("share", share);
		StringBuffer tag_str = new StringBuffer();
		for (Tag tag : share.getTags()) {
			tag_str.append(tag.getName() + "#");
		}
		int i = tag_str.lastIndexOf("#");
		if (i != -1) {
			mav.addObject("tag_str", tag_str.substring(0, i));
		}
		return mav;
	}

	@RequestMapping("/delete")
	public @ResponseBody
	String delete(@RequestParam String sid) {
		Share entity = shareService.find(sid);
		entity.setVisible(false);
		shareService.update(entity);
		return "true";
	}
}
