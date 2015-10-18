package com.qsms.core.action;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.account.ebean.User;
import com.qsms.auxiliary.ebean.Email;
import com.qsms.core.ebean.Star;
import com.qsms.core.ebean.StarItem;
import com.qsms.core.ebean.SupportCategory;
import com.qsms.core.service.StarItemService;
import com.qsms.core.service.StarService;
import com.qsms.core.service.SupportCategoryService;
import com.qsms.util.Helper;
import com.qsms.util.WebUtil;
import com.qsms.util.dao.PageView;

@RequestMapping("/star/item")
@Controller
public class StarItemFrontAction {

	@Resource
	private StarService starService;
	@Resource
	private SupportCategoryService supportCategoryService;
	@Resource
	private StarItemService starItemService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	@RequestMapping
	public ModelAndView list(
			@RequestParam(defaultValue = "1", required = false) int page) {
		ModelAndView mav = new ModelAndView("view/core/starItem_list");
		PageView<StarItem> pageView = new PageView<StarItem>(15, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("modifyDate", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		pageView.setQueryResult(starItemService.getScrollData(
				pageView.getFirstResult(), pageView.getMaxresult(),
				jpql.toString(), params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("stars", starService.listAll());
		return mav;
	}

	@RequestMapping(value = "/{op}/click", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody
	String clickOperation(@PathVariable String op, @RequestParam String siid) {
		StarItem entity = starItemService.find(siid);
		if ("download".equals(op)) {
			String cookieID = WebUtil.getCookieValueByName(request, siid
					+ "-BD");
			if (cookieID == null) {
				entity.setBaiduDownNum(entity.getBaiduDownNum() + 1);
				WebUtil.addCookie(response, siid + "-BD",
						System.currentTimeMillis() + "-qsms", 8 * 3600);//
				starItemService.update(entity);
			}
			return genATag(entity.getBaiduDownUrl(), 2);
		}
		if ("paly".equals(op)) {
			String cookieID = WebUtil.getCookieValueByName(request, siid
					+ "-PD");
			if (cookieID == null) {
				entity.setPlayNum(entity.getPlayNum() + 1);
				WebUtil.addCookie(response, siid + "-PD",
						System.currentTimeMillis() + "-qsms", 8 * 3600);//
				starItemService.update(entity);
			}
			if (entity.getPlayUrl().startsWith("http")) {
				return genATag(entity.getPlayUrl(), 1);
			} else {
				return entity.getPlayUrl();
			}
		}
		return "ERROR";
	}

	/**
	 * 将给定url转换为a标签
	 * 
	 * @param urlStr
	 * @param model
	 *            model=1进行全串式转换;其他为智能转换
	 * @return
	 */
	private String genATag(String urlStr, int model) {
		if (model == 1) {
			String url = "<a href=" + urlStr + " target='_blank' >" + urlStr
					+ "</a>";
			return url;
		} else {
			String url = urlStr.replaceAll("(?is)(?<!')(http://[/\\.\\w]+)",
					"<a href='$1' target='_blank'>$1</a>");
			return url;
		}
	}

}
