package com.qsms.blog.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.blog.ebean.Blog;
import com.qsms.blog.ebean.BlogTag;
import com.qsms.blog.ebean.BlogType;
import com.qsms.blog.service.BlogService;
import com.qsms.blog.service.BlogTagService;
import com.qsms.blog.service.BlogTypeService;
import com.qsms.ttk.image.service.ImageTaskService;
import com.qsms.util.Helper;
import com.qsms.util.WebUtil;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/blog")
@Controller
public class BlogAction {
	@Resource
	private BlogService blogService;
	@Resource
	private BlogTypeService blogTypeService;
	@Resource
	private BlogTagService blogTagService;
	@Resource
	private ImageTaskService imageTaskService;
	private int maxresult = 5;

	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false) String queryContent) {
		PageView<Blog> pageView = new PageView<Blog>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		if (queryContent != null && !"".equals(queryContent)) {
			jpql.append(" and (o.title like?2 or o.content like?3 )");
			params.add("%" + queryContent + "%");
			params.add("%" + queryContent + "%");
		}
		pageView.setQueryResult(blogService.getScrollData(pageView
				.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		ModelAndView mav = new ModelAndView("blog/blog_list");
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("queryContent", queryContent);
		mav.addObject("m", 1);
		return mav;
	}

	// 添加导向
	@RequestMapping("/new")
	public ModelAndView addUI() {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		List<BlogType> types = blogTypeService.getScrollData(jpql.toString(),
				params.toArray(), orderby).getResultList();
		ModelAndView mav = new ModelAndView("/blog/blog_new");
		mav.addObject("types", types);
		mav.addObject("m", 1);
		return mav;
	}

	// 添加
	@RequestMapping("/save")
	public ModelAndView add(@ModelAttribute Blog blog,
			@RequestParam String tid,
			@RequestParam(required = false) String tag_str) {
		blog.setBlogType(blogTypeService.find(tid));
		String summary = WebUtil.HtmltoText(blog.getContent());
		blog.setId(Helper.generatorID());
		blog.setCreateDate(new Date());
		blog.setLv(1);
		blog.setLk(Helper.randomRange(5, 30));
		blog.setPv(Helper.randomRange(10, 100));
		blog.setText(summary);
		if (summary.length() <= 250) {
			blog.setSummary(summary);
		} else {
			blog.setSummary(summary.substring(0, 250));
		}

		/** 开始处理标签 **/
		if (tag_str != null && tag_str.length() >= 0) {
			String[] tagNames = tag_str.trim().toLowerCase().split("#");
			for (String tagName : tagNames) {
				if (tagName != null && !"".equals(tagName.replaceAll(" ", ""))) {// 空标签不存储
					BlogTag tag = blogTagService.findByName(tagName);
					blog.getTags().add(tag);
				}
			}
		}
		blogService.save(blog);
		imageTaskService.creatTask(1, blog.getId());// 建立图片任务
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}

	// 修改导向
	@RequestMapping("/edit/{bid}")
	public ModelAndView editUI(@PathVariable String bid) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		List<BlogType> types = blogTypeService.getScrollData(jpql.toString(),
				params.toArray(), orderby).getResultList();
		Blog blog = blogService.find(bid);
		ModelAndView mav = new ModelAndView("blog/blog_edit");
		mav.addObject("blog", blog);
		mav.addObject("types", types);
		StringBuffer tag_str = new StringBuffer();
		for (BlogTag tag : blog.getTags()) {
			tag_str.append(tag.getName() + "#");
		}
		int i = tag_str.lastIndexOf("#");
		if (i != -1) {
			mav.addObject("tag_str", tag_str.substring(0, i));
		}
		return mav;
	}

	// 修改
	@RequestMapping("/update")
	public ModelAndView edit(@ModelAttribute Blog blog,
			@RequestParam String tid,
			@RequestParam(required = false) String tag_str) {
		Blog entity = blogService.find(blog.getId());
		entity.setTitle(blog.getTitle());
		entity.setBlogType(blogTypeService.find(tid));
		entity.setLv(blog.getLv());
		entity.setLk(blog.getLk());
		entity.setPv(blog.getPv());
		entity.setRecommend(blog.getRecommend());
		entity.setContent(blog.getContent());
		String summary = WebUtil.HtmltoText(blog.getContent());
		entity.setText(summary);
		if (summary.length() <= 250) {
			blog.setSummary(summary);
		} else {
			entity.setSummary(summary.substring(0, 250));
		}

		/** 开始处理标签 **/
		entity.getTags().clear(); // 清空
		if (tag_str != null && tag_str.length() >= 0) {
			String[] tagNames = tag_str.trim().toLowerCase().split("#");
			for (String tagName : tagNames) {
				if (tagName != null && !"".equals(tagName.replaceAll(" ", ""))) {// 空标签不存储
					BlogTag tag = blogTagService.findByName(tagName);
					entity.getTags().add(tag);
				}
			}
		}
		blogService.update(entity);
		imageTaskService.creatTask(1, blog.getId());// 建立图片任务
		ModelAndView mav = new ModelAndView("redirect:list");
		// 重新生成静态页面
		return mav;
	}

	// 加减级别
	@RequestMapping("/lv")
	public @ResponseBody
	int level(@RequestParam String bid, @RequestParam String sign) {
		Blog entity = blogService.find(bid);
		if ("true".equals(sign)) {
			entity.setLv(entity.getLv() + 1);
		} else if (entity.getLv() >= 1) {
			entity.setLv(entity.getLv() - 1);
		}
		blogService.update(entity);
		return entity.getLv();
	}

	// 删除某一条
	@RequestMapping("/delete")
	public @ResponseBody
	String del(@RequestParam String bid) {
		try {
			blogService.delete(bid);
			return "success";
		} catch (Exception e) {
			return "ERROR";
		}
	}

	// 批量删除
	@RequestMapping("/delete/more")
	public ModelAndView delAll(@RequestParam int[] bids) {
		for (int bid : bids) {
			blogService.delete(bid);
		}
		ModelAndView mav = new ModelAndView("redirect:list?page=1&tid=0");
		return mav;
	}
}
