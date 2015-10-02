package com.qsms.core.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.core.ebean.Star;
import com.qsms.core.ebean.StarItem;
import com.qsms.core.ebean.SupportCategory;
import com.qsms.core.service.StarItemService;
import com.qsms.core.service.StarService;
import com.qsms.core.service.SupportCategoryService;
import com.qsms.share.ebean.Tag;
import com.qsms.ttk.image.ebean.TkImage;
import com.qsms.ttk.image.service.TkImageService;
import com.qsms.util.WebUtil;
import com.qsms.util.dao.PageView;

@RequestMapping("/star")
@Controller
public class StarFrontAction {

	@Resource
	private StarService starService;
	@Resource
	private SupportCategoryService supportCategoryService;
	@Resource
	private StarItemService starItemService;
	@Resource
	private TkImageService tkImageService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	private int m = 4;

	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("view/core/star_index");
		mav.addObject("indexStarList", starService.listIndex());// 首页推荐
		mav.addObject("lastStarList", starService.listLastUpdate(10));// 最近更新明星资源
		mav.addObject("hyStarList", starService.listByTypeBasePV(1, 4));
		mav.addObject("omStarList", starService.listByTypeBasePV(2, 4));
		mav.addObject("rhStarList", starService.listByTypeBasePV(3, 4));
		mav.addObject("starItemList", starItemService.listLastUpdate(6));
		return mav;
	}

	@RequestMapping("/list/{way}")
	public ModelAndView list(@PathVariable String way,
			@RequestParam(defaultValue = "1", required = false) int page) {
		int maxresult = 2;
		ModelAndView mav = new ModelAndView("view/core/star_list");
		PageView<Star> pageView = new PageView<Star>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		if ("pv".equals(way)) {
			orderby.put("pv", "desc");
			mav.addObject("title", "总访问量");
		}
		if ("total".equals(way)) {
			orderby.put("resnum", "desc");
			mav.addObject("title", "资源总数");
		}
		if ("recent".equals(way)) {
			orderby.put("modifyDate", "desc");
			mav.addObject("title", "最近更新");
		}
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		pageView.setQueryResult(starService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", m);
		return mav;
	}

	@RequestMapping("/view/{code}")
	public ModelAndView view(@PathVariable String code) {
		Star star = starService.findByCode(code);
		ModelAndView mav = new ModelAndView("view/core/star_view");
		Map<String, List<StarItem>> dataMap = new HashMap<String, List<StarItem>>();
		for (SupportCategory category : star.getCategorys()) {
			dataMap.put(category.getId() + "",
					starItemService.list(star.getId(), category.getId()));
		}
		String cookieID = WebUtil.getCookieValueByName(request, star.getId()
				+ "-star");
		if (cookieID == null) {
			star.setPv(star.getPv() + 1);
			WebUtil.addCookie(response, star.getId() + "-star",
					System.currentTimeMillis() + "-qsms", 8 * 3600);//
			starService.update(star);
		}
		List<Star> stars = new ArrayList<Star>();
		for (Star s : starService.listLastUpdate(3)) {
			if (!s.getId().equals(star.getId())) {
				stars.add(s);
			}
		}
		mav.addObject("star", star);
		mav.addObject("dataMap", dataMap);
		mav.addObject("stars", stars);// 用于推荐
		return mav;
	}

	@RequestMapping("/load/image")
	public ModelAndView loadImage(
			@RequestParam(defaultValue = "1", required = false) int page,
			String starId) {
		ModelAndView mav = new ModelAndView("view/core/star_image");
		Star star = starService.find(starId);
		if (star.getTagName() != null && star.getTagName().length() > 1) {
			String[] tagNames = star.getTagName().split("#");
			if (tagNames.length > 0) {
				PageView<TkImage> pageView = tkImageService.queryByTag(50,
						page, tagNames);
				for (TkImage tk : pageView.getRecords()) {
					StringBuffer tagDes = new StringBuffer();
					for (Tag tag : tk.getShare().getTags()) {
						tagDes.append(tag.getName() + " ");
					}
					tk.setTagDes(tagDes.toString());
				}
				mav.addObject("pageView", pageView);
				return mav;
			}
		}
		mav.addObject("pageView", new PageView<TkImage>(10, page));
		return mav;
	}

	@RequestMapping("/contact")
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("view/core/star_contact");
		return mav;
	}
}
