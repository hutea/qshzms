package com.qsms.ttk.image.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.ttk.image.ebean.ImageTask;
import com.qsms.ttk.image.ebean.TkAlbum;
import com.qsms.ttk.image.ebean.TkImage;
import com.qsms.ttk.image.service.ImageTaskService;
import com.qsms.ttk.image.service.TkAlbumService;
import com.qsms.ttk.image.service.TkImageService;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/ttk/")
@Controller
public class TkImageAction {
	@Resource
	private TkImageService tkImageService;
	private int maxresult = 10;

	@RequestMapping("/image/list")
	public ModelAndView list(
			@RequestParam(defaultValue = "1", required = false) int page) {
		PageView<TkImage> pageView = new PageView<TkImage>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		pageView.setQueryResult(tkImageService.getScrollData(pageView
				.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		ModelAndView mav = new ModelAndView("ttk/tkImage_list");
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", 9);
		return mav;
	}
}
