package com.qsms.blog.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.auxiliary.service.SystemConfigService;
import com.qsms.blog.ebean.Blog;
import com.qsms.blog.ebean.BlogTag;
import com.qsms.blog.service.BlogService;
import com.qsms.blog.service.BlogTagService;
import com.qsms.blog.service.BlogTypeService;
import com.qsms.util.Helper;
import com.qsms.util.WebUtil;
import com.qsms.util.dao.PageView;

@Controller
public class BlogFrontAction {
	@Resource
	private BlogService blogService;
	@Resource
	private BlogTypeService blogTypeService;
	@Resource
	private BlogTagService blogTagService;
	@Resource
	private SystemConfigService systemConfigService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	private int maxresult = 5;

	@RequestMapping("/blog")
	public ModelAndView list() {
		return list(1);
	}

	@RequestMapping("/blog/page/{page}")
	public ModelAndView list(@PathVariable int page) {
		PageView<Blog> pageView = new PageView<Blog>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("lv", "desc");
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		pageView.setQueryResult(blogService.getScrollData(pageView.getFirstResult(), maxresult,
				jpql.toString(), params.toArray(), orderby));
		ModelAndView mav = new ModelAndView("view/blog/blog_list");
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", 1);
		mav
				.addObject("type", blogTypeService.findByCode(systemConfigService
						.defalutBlogTypeCode()));
		mav.addObject("tag", blogTagService.findByName(systemConfigService.defalutBlogTagName()));
		return mav;
	}

	@RequestMapping("/blog/better")
	public ModelAndView listRecommend() {
		return listRecommend(1);
	}

	@RequestMapping("/blog/better/page/{page}")
	public ModelAndView listRecommend(@PathVariable int page) {
		PageView<Blog> pageView = new PageView<Blog>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("lv", "desc");
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1 and o.recommend=?2");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		params.add(true);
		pageView.setQueryResult(blogService.getScrollData(pageView.getFirstResult(), maxresult,
				jpql.toString(), params.toArray(), orderby));
		ModelAndView mav = new ModelAndView("view/blog/blog_list_recommend");
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", 2);
		mav
				.addObject("type", blogTypeService.findByCode(systemConfigService
						.defalutBlogTypeCode()));
		mav.addObject("tag", blogTagService.findByName(systemConfigService.defalutBlogTagName()));
		return mav;
	}

	@RequestMapping("/blog/{code}")
	public ModelAndView listByType(@PathVariable String code) {
		return listByType(code, 1);
	}

	@RequestMapping("/blog/{code}/page/{page}")
	public ModelAndView listByType(@PathVariable String code, @PathVariable int page) {
		PageView<Blog> pageView = new PageView<Blog>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("lv", "desc");
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1 and o.blogType.code=?2");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		params.add(code);
		pageView.setQueryResult(blogService.getScrollData(pageView.getFirstResult(), maxresult,
				jpql.toString(), params.toArray(), orderby));
		ModelAndView mav = new ModelAndView("view/blog/blog_list_type");
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", 3);
		mav.addObject("type", blogTypeService.findByCode(code));
		mav.addObject("tag", blogTagService.findByName(systemConfigService.defalutBlogTagName()));
		return mav;
	}

	@RequestMapping("/blog/tag/{tagID}")
	public ModelAndView listByTag(@PathVariable String tagID) {
		return listByTag(tagID, 1);
	}

	@RequestMapping("/blog/tag/{tagID}/page/{page}")
	public ModelAndView listByTag(@PathVariable String tagID, @PathVariable int page) {
		PageView<Blog> pageView = blogService.queryByTag(maxresult, page, tagID);
		ModelAndView mav = new ModelAndView("view/blog/blog_list_tag");
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", 4);
		BlogTag tag = blogTagService.find(tagID);
		mav
				.addObject("type", blogTypeService.findByCode(systemConfigService
						.defalutBlogTypeCode()));
		mav.addObject("tag", tag);
		return mav;
	}

	@RequestMapping("/blog/view/{bid}")
	public ModelAndView view(@PathVariable String bid) {
		Blog blog = blogService.find(bid);
		String cookieBID = WebUtil.getCookieValueByName(request, bid);
		if (cookieBID == null) {
			blog.setPv(blog.getPv()+Helper.randomRange(1, 2));
			blogService.update(blog);
			WebUtil.addCookie(response, bid, System.currentTimeMillis() + "-qsms", 2 * 3600);//
		}
		ModelAndView mav = new ModelAndView("view/blog/blog_view");
		mav.addObject("blog", blog);
		mav.addObject("lastBlog", blogService.lastBlog(bid));
		mav.addObject("nextBlog", blogService.nextBlog(bid));
		return mav;
	}

	@RequestMapping("/blog/load/ftag")
	public ModelAndView loadFooterTag(@RequestParam(required = false, defaultValue = "20") int num) {
		ModelAndView mav = new ModelAndView("view/blog/blog_load_footerTag");
		mav.addObject("tags", blogTagService.topList(num));
		return mav;
	}

	@RequestMapping("/blog/load/atag")
	public ModelAndView loadAsideTag(@RequestParam(required = false, defaultValue = "15") int num) {
		ModelAndView mav = new ModelAndView("view/blog/blog_load_asideTag");
		mav.addObject("tags", blogTagService.topList(num));
		return mav;

	}

	@RequestMapping("/blog/load/nblog")
	public ModelAndView newestBlg(@RequestParam(required = false, defaultValue = "2") int num) {
		ModelAndView mav = new ModelAndView("view/blog/blog_load_newestBlog");
		mav.addObject("blogs", blogService.listNewset(num));
		return mav;
	}

	@RequestMapping("/blog/tags/all")
	public ModelAndView tagsAll() {
		ModelAndView mav = new ModelAndView("view/blog/blog_tags");
		mav.addObject("tags", blogTagService.topList(120));
		return mav;
	}
}
