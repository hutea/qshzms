package com.qsms.core.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.core.ebean.SupportCategory;
import com.qsms.core.service.SupportCategoryService;
import com.qsms.util.Helper;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/star/category")
@Controller
public class SupportCategoryAction {

	@Resource
	private SupportCategoryService supportCategoryService;
	private int maxresult = 10;
	private int m = 4;

	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(defaultValue = "1", required = false) int page) {
		PageView<SupportCategory> pageView = new PageView<SupportCategory>(
				maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("lv", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		pageView.setQueryResult(supportCategoryService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		ModelAndView mav = new ModelAndView("core/supportCategory_list");
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", m);
		return mav;
	}

	// 添加
	@RequestMapping("/save")
	public ModelAndView save(@ModelAttribute SupportCategory category) {
		category.setId(Helper.generatorID());
		supportCategoryService.save(category);
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}

	// 修改
	@RequestMapping("/update")
	public ModelAndView update(@ModelAttribute SupportCategory SupportCategory,
			@RequestParam String sid) {
		SupportCategory entity = supportCategoryService.find(sid);
		entity.setLv(SupportCategory.getLv());
		entity.setName(SupportCategory.getName());
		supportCategoryService.update(entity);
		ModelAndView mav = new ModelAndView("redirect:list?page=1");
		return mav;
	}

}
