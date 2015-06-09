package com.qsms.blog.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.blog.ebean.BlogType;
import com.qsms.blog.service.BlogTypeService;
import com.qsms.util.Helper;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/blog/type")
@Controller
public class BlogTypeAction {
	@Resource
	private BlogTypeService blogTypeService;
	private int maxresult = 10;

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(defaultValue = "1", required = false) int page) {
		PageView<BlogType> pageView = new PageView<BlogType>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("lv", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		pageView.setQueryResult(blogTypeService.getScrollData(pageView.getFirstResult(), maxresult, jpql.toString(), params.toArray(), orderby));
		ModelAndView mav = new ModelAndView("blog/blogtype_list");
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", 1);
		return mav;
	}

	// 添加
	@RequestMapping("/save")
	public ModelAndView add(@ModelAttribute BlogType blogType) {
		blogType.setId(Helper.generatorID());
		blogTypeService.save(blogType);
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}

	// 修改导向
	@RequestMapping("/edit")
	public ModelAndView editUI(@RequestParam String btid) {
		BlogType blog = blogTypeService.find(btid);
		ModelAndView mav = new ModelAndView("man/blog/blog_edit");
		// mav.addObject("types", types);
		mav.addObject("blog", blog);
		return mav;
	}

	// 修改
	@RequestMapping("/update")
	public ModelAndView edit(@ModelAttribute BlogType blogType, @RequestParam String btid) {
		BlogType entity = blogTypeService.find(btid);
		entity.setName(blogType.getName());
		entity.setCode(blogType.getCode());
		blogTypeService.update(entity);
		ModelAndView mav = new ModelAndView("redirect:list?page=1&tid=0");
		return mav;
	}

	@RequestMapping("/delete")
	public @ResponseBody
	int del(@RequestParam int bid) {
		int res = 1;
		try {
			blogTypeService.delete(bid);
		} catch (Exception e) {
			res = 0;
		}
		return res;
	}

	// 批量删除
	@RequestMapping("/delete/more")
	public ModelAndView delAll(@RequestParam String[] btids) {
		for (String btid : btids) {
			blogTypeService.delete(btid);
		}
		ModelAndView mav = new ModelAndView("redirect:list?page=1&tid=0");
		return mav;
	}
}
