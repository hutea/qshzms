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

import com.qsms.core.ebean.Star;
import com.qsms.core.ebean.SupportCategory;
import com.qsms.core.service.StarService;
import com.qsms.core.service.SupportCategoryService;
import com.qsms.util.Helper;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/star")
@Controller
public class StarAction {

	@Resource
	private StarService starService;
	@Resource
	private SupportCategoryService supportCategoryService;

	private int maxresult = 10;
	private int m = 4;

	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(defaultValue = "1", required = false) int page) {
		PageView<Star> pageView = new PageView<Star>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("lv", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		pageView.setQueryResult(starService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		ModelAndView mav = new ModelAndView("core/star_list");
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", m);
		return mav;
	}

	@RequestMapping("/new")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView("core/star_new");
		mav.addObject("categorys", supportCategoryService.getScrollData()
				.getResultList());
		mav.addObject("m", m);
		return mav;
	}

	@RequestMapping("/save")
	public ModelAndView save(@ModelAttribute Star star,
			@RequestParam(required = false) Long[] scids) {
		star.setId(Helper.generatorID());
		star.setSimplePy(Helper.converPinYin(star.getName(), 2));
		star.setFullPy(Helper.converPinYin(star.getName(), 1));
		if (scids != null && scids.length > 0) {
			for (Long scid : scids) {
				star.getCategorys().add(supportCategoryService.find(scid));
			}
		}
		starService.save(star);
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}

	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam String sid) {
		Star star = starService.find(sid);
		ModelAndView mav = new ModelAndView("core/star_edit");
		StringBuffer ugs = new StringBuffer();
		for (SupportCategory category : star.getCategorys()) {
			ugs.append("#" + category.getId());
		}
		mav.addObject("star", star);
		mav.addObject("categorys", supportCategoryService.getScrollData()
				.getResultList());
		mav.addObject("ugs", ugs.toString());
		mav.addObject("m", m);
		return mav;
	}

	// 修改
	@RequestMapping("/update")
	public ModelAndView update(@ModelAttribute Star star,
			@RequestParam String sid,
			@RequestParam(required = false) Long[] scids) {
		Star entity = starService.find(sid);
		if (!star.getName().equals(entity.getName())) {// 姓名变化，则使用pinyin4j自动生成拼音
			entity.setName(star.getName());
			entity.setSimplePy(Helper.converPinYin(star.getName(), 2));
			entity.setFullPy(Helper.converPinYin(star.getName(), 1));
		} else {
			entity.setSimplePy(star.getSimplePy());
			entity.setFullPy(star.getFullPy());
		}
		entity.setNote(star.getNote());
		entity.setTagName(star.getTagName());
		entity.setImageUrl(star.getImageUrl());
		entity.setImgUrl(star.getImgUrl());
		entity.setLv(star.getLv()); 
		entity.setIod(star.getIod());
		entity.setType(star.getType());
		entity.setPv(star.getPv());
		entity.setSummary(star.getSummary());
		entity.getCategorys().clear();
		System.out.println(scids.length);
		if (scids != null && scids.length > 0) {
			for (Long scid : scids) {
				entity.getCategorys().add(supportCategoryService.find(scid));
			}
		}
		starService.update(entity);
		ModelAndView mav = new ModelAndView("redirect:list?page=1");
		return mav;
	}

}
