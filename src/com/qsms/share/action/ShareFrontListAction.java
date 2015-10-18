package com.qsms.share.action;

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

import com.qsms.account.ebean.User;
import com.qsms.auxiliary.service.SystemConfigService;
import com.qsms.share.ebean.Share;
import com.qsms.share.ebean.Tag;
import com.qsms.share.service.ShareCommentService;
import com.qsms.share.service.ShareService;
import com.qsms.share.service.TagService;
import com.qsms.util.WebUtil;
import com.qsms.util.dao.PageView;

@Controller
public class ShareFrontListAction {
	@Resource
	private ShareService shareService;
	@Resource
	private ShareCommentService shareCommentService;
	@Resource
	private SystemConfigService systemConfigService;
	@Resource
	private TagService tagService;

	@Autowired
	private HttpServletRequest request;

	private int maxresult = 12;

	@RequestMapping("/share")
	public ModelAndView list() {
		return list(1);
	}

	@RequestMapping("/share/page/{page}")
	public ModelAndView list(@PathVariable int page) {
		ModelAndView mav = new ModelAndView("view/share/share_list");
		PageView<Share> pageView = new PageView<Share>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer(
				"o.visible=?1 and o.reported<=?2 and o.category<?3");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		params.add(systemConfigService.shareReportedMax());
		params.add(5);
		pageView.setQueryResult(shareService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		User user = WebUtil.getLoginUser(request);
		for (Share share : pageView.getRecords()) {
			if (user != null) {
				long comt = shareCommentService.countBySuid(share.getId(),
						user.getId());
				share.viewload(user, comt);
			} else {
				share.viewload(user, 0);
			}
			StringBuffer tagss = new StringBuffer();
			for (Tag tag : share.getTags()) {
				tagss.append(tag.getName() + "#");
			}
		}
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		return mav;
	}

	@RequestMapping("/share/tag/{tagID}")
	public ModelAndView listByTag(@PathVariable String tagID) {
		return listByTag(tagID, 1);
	}

	@RequestMapping("/share/tag/{tagID}/page/{page}")
	public ModelAndView listByTag(@PathVariable String tagID,
			@PathVariable int page) {
		ModelAndView mav = new ModelAndView("view/share/share_list_tag");
		PageView<Share> pageView = shareService.queryByTag(maxresult, page,
				tagID, 0);
		User user = WebUtil.getLoginUser(request);
		for (Share share : pageView.getRecords()) {
			if (user != null) {
				long comt = shareCommentService.countBySuid(share.getId(),
						user.getId());
				share.viewload(user, comt);
			} else {
				share.viewload(user, 0);
			}
		}
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("tagID", tagID);
		return mav;
	}

	@RequestMapping("/share/{type}")
	public ModelAndView listByType(@PathVariable String type) {
		ModelAndView mav = listByType(type, 1);
		return mav;
	}

	@RequestMapping("/share/{type}/page/{page}")
	public ModelAndView listByType(@PathVariable String type,
			@PathVariable int page) {
		ModelAndView mav = new ModelAndView("view/share/share_list");
		PageView<Share> pageView = new PageView<Share>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1 and o.reported<=?2");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		params.add(systemConfigService.shareReportedMax());
		if ("file".equals(type)) {
			jpql.append(" and o.category=?" + (params.size() + 1));
			params.add(1);
			mav.setViewName("view/share/share_list_file");
			mav.addObject("m", 1);// 菜单“class=active”使用
		} else if ("article".equals(type)) {
			jpql.append(" and o.category=?" + (params.size() + 1));
			params.add(2);
			mav.setViewName("view/share/share_list_article");
			mav.addObject("m", 2);// 菜单“class=active”使用
		} else if ("music".equals(type)) {
			jpql.append(" and o.category=?" + (params.size() + 1));
			params.add(3);
			mav.setViewName("view/share/share_list_music");
			mav.addObject("m", 3);// 菜单“class=active”使用
		} else if ("video".equals(type)) {
			jpql.append(" and o.category=?" + (params.size() + 1));
			params.add(4);
			mav.setViewName("view/share/share_list_video");
			mav.addObject("m", 4);// 菜单“class=active”使用
		} else if ("image".equals(type)) {
			jpql.append(" and o.category=?" + (params.size() + 1));
			params.add(5);
			mav.setViewName("view/share/share_list_image");
			mav.addObject("m", 5);// 菜单“class=active”使用
		} else {
			mav.setViewName("view/share/share_list");
			mav.addObject("m", 0);// 菜单“class=active”使用
		}

		pageView.setQueryResult(shareService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		User user = WebUtil.getLoginUser(request);
		for (Share share : pageView.getRecords()) {
			if (user != null) {
				long comt = shareCommentService.countBySuid(share.getId(),
						user.getId());
				share.viewload(user, comt);
			} else {
				share.viewload(user, 0);
			}
		}
		mav.addObject("pageView", pageView);
		return mav;
	}

	@RequestMapping("/share/{type}/tag/{tagID}")
	public ModelAndView listByTypeTag(@PathVariable String type,
			@PathVariable String tagID) {
		ModelAndView mav = listByTypeTag(type, tagID, 1);
		return mav;
	}

	@RequestMapping("/share/{type}/tag/{tagID}/page/{page}")
	public ModelAndView listByTypeTag(@PathVariable String type,
			@PathVariable String tagID, @PathVariable int page) {
		ModelAndView mav = new ModelAndView("");
		int typeInt = 0;
		if ("file".equals(type)) {
			mav.setViewName("view/share/share_list_file_tag");
			mav.addObject("m", 1);// 菜单“class=active”使用
			typeInt = 1;
		} else if ("article".equals(type)) {
			mav.setViewName("view/share/share_list_article_tag");
			mav.addObject("m", 2);// 菜单“class=active”使用
			typeInt = 2;
		} else if ("music".equals(type)) {
			mav.setViewName("view/share/share_list_music_tag");
			mav.addObject("m", 3);// 菜单“class=active”使用
			typeInt = 3;
		} else if ("video".equals(type)) {
			mav.setViewName("view/share/share_list_video_tag");
			mav.addObject("m", 4);// 菜单“class=active”使用
			typeInt = 4;
		} else if ("image".equals(type)) {
			mav.setViewName("view/share/share_list_image_tag");
			mav.addObject("m", 5);// 菜单“class=active”使用
			typeInt = 5;
		}
		PageView<Share> pageView = shareService.queryByTag(maxresult, page,
				tagID, typeInt);
		User user = WebUtil.getLoginUser(request);
		for (Share share : pageView.getRecords()) {
			if (user != null) {
				long comt = shareCommentService.countBySuid(share.getId(),
						user.getId());
				share.viewload(user, comt);
			} else {
				share.viewload(user, 0);
			}
		}
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("tagID", tagID);
		return mav;
	}

	/**
	 * 滚动图片加载数据
	 * 
	 * @param page
	 * @param tagID
	 * @return
	 */
	@RequestMapping("/share/image/scroll/{page}")
	public ModelAndView imageScroll(@PathVariable int page) {
		ModelAndView mav = new ModelAndView(
				"view/share/share_list_image_scroll");
		PageView<Share> pageView = new PageView<Share>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer(
				"o.visible=?1 and o.reported<=?2 and o.category=?3");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		params.add(systemConfigService.shareReportedMax());
		params.add(5);
		pageView.setQueryResult(shareService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		mav.addObject("list", pageView.getRecords());
		mav.addObject("page", page);
		return mav;
	}

	/**
	 * 滚动图片(带标签)加载数据
	 * 
	 * @param page
	 * @param tagID
	 * @return
	 */
	@RequestMapping("/share/image/scroll/tag/{tagID}/{page}")
	public ModelAndView imageTagScroll(@PathVariable int page,
			@PathVariable String tagID) {
		ModelAndView mav = new ModelAndView(
				"view/share/share_list_image_scroll");
		PageView<Share> pageView = shareService.queryByTag(maxresult, page,
				tagID, 5);
		mav.addObject("list", pageView.getRecords());
		mav.addObject("page", page);
		return mav;
	}

}
