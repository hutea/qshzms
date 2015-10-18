package com.qsms.account.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsms.account.ebean.User;
import com.qsms.account.service.UserService;
import com.qsms.auxiliary.service.SystemConfigService;
import com.qsms.core.ebean.Star;
import com.qsms.core.ebean.StarItem;
import com.qsms.core.ebean.SupportCategory;
import com.qsms.core.service.StarItemService;
import com.qsms.core.service.StarService;
import com.qsms.core.service.SupportCategoryService;
import com.qsms.ttk.image.service.ImageTaskService;
import com.qsms.util.Helper;
import com.qsms.util.WebUtil;
import com.qsms.util.dao.PageView;

@Controller
public class UserCenterStarItemAction {
	@Resource
	private StarItemService starItemService;
	@Resource
	private StarService starService;
	@Resource
	private ImageTaskService imageTaskService;
	@Resource
	private UserService userService;
	@Resource
	private SystemConfigService systemConfigService;
	@Resource
	private SupportCategoryService supportCategoryService;

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	private int maxresult = 10;

	@RequestMapping("/my/star/item")
	public ModelAndView starList(
			@RequestParam(required = false, defaultValue = "1") int page) {
		ModelAndView mav = new ModelAndView("view/my/user_staritem_list");
		PageView<StarItem> pageView = new PageView<StarItem>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.user.id=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(WebUtil.getLoginUser(request).getId());
		pageView.setQueryResult(starItemService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("mym", 5);
		return mav;
	}

	@RequestMapping("/my/star/item/view/{siid}/{tip}")
	public ModelAndView view(@PathVariable String siid,
			@PathVariable Integer tip) {
		ModelAndView mav = new ModelAndView("view/my/user_staritem_show");
		mav.addObject("starItem", starItemService.find(siid));
		mav.addObject("tip", tip);
		if (tip == 1) {
			mav.addObject("message", "保存成功");
		} else if (tip == 2) {
			mav.addObject("message", "修改成功");
		} else if (tip == 3) {
			mav.addObject("message", "修改时间过期");
		}
		return mav;
	}

	@RequestMapping("/my/star/item/new")
	public ModelAndView starNew() {
		ModelAndView mav = new ModelAndView("view/my/user_staritem_new");
		return mav;
	}

	@RequestMapping(value = "/my/star/item/save", method = RequestMethod.POST)
	public ModelAndView save(
			@ModelAttribute StarItem starItem,
			@RequestParam(value = "image", required = false) MultipartFile image,
			String sid, Long scid, String sdate) {
		String validateResult = validate(starItem);
		if (!"success".equals(validateResult)) {
			ModelAndView mav = new ModelAndView("view/my/user_staritem_new");
			return mav;
		}
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		User dbUser = userService.find(loginUser.getId());
		if (dbUser == null || !dbUser.getVisible() || dbUser.getLv() < 5) {
			ModelAndView mav = new ModelAndView("view/my/user_staritem_new");
			return mav;
		}
		starItem.setId(Helper.generatorID());
		starItem.setUser(dbUser);
		Star star = starService.find(sid);
		starItem.setStar(star);
		starItem.setModifyDate(new Date());
		starItem.setCategory(supportCategoryService.find(scid));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			starItem.setShowDate(sdf.parse(sdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (image != null && image.getSize() > 0) {
			String oriname = image.getOriginalFilename();
			String suffix = oriname.substring(oriname.lastIndexOf("."));
			String filePath = "resource/upload/" + Helper.genDateString() + "/"
					+ Helper.generatorID() + suffix;
			File tempFile = new File(request.getSession().getServletContext()
					.getRealPath(filePath));
			starItem.setShowImage("/" + filePath);
			try {
				FileUtils.copyInputStreamToFile(image.getInputStream(),
						tempFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		starItemService.save(starItem);
		/** 创建任务 */
		imageTaskService.creatTask(3, starItem.getId());
		/** 更新Star */
		star.setModifyDate(new Date());
		star.setResnum(star.getResnum() + 1);
		starService.update(star);
		ModelAndView mav = new ModelAndView("redirect:/my/star/item/view/"
				+ starItem.getId() + "/1");
		return mav;
	}

	@RequestMapping("/my/star/item/edit/{siid}")
	public ModelAndView starEdit(@PathVariable String siid) {
		ModelAndView mav = new ModelAndView("view/my/user_staritem_edit");
		mav.addObject("starItem", starItemService.find(siid));
		return mav;
	}

	@RequestMapping("/my/star/item/update")
	public ModelAndView starUpdate(
			@ModelAttribute StarItem starItem,
			@RequestParam String sid,
			@RequestParam Long scid,
			@RequestParam String siid,
			@RequestParam(value = "image", required = false) MultipartFile image,
			String sdate) {
		StarItem entity = starItemService.find(siid);
		long millis = System.currentTimeMillis()
				- entity.getModifyDate().getTime();
		if (millis > 8 * 3600 * 1000) {
			ModelAndView mav = new ModelAndView("redirect:/my/star/item/view/"
					+ siid + "/3");
			return mav;
		}
		entity.setName(starItem.getName());
		entity.setStar(starService.find(sid));
		entity.setCategory(supportCategoryService.find(scid));
		entity.setBaiduDownUrl(starItem.getBaiduDownUrl());
		entity.setPlayUrl(starItem.getPlayUrl());
		entity.setSummary(starItem.getSummary());
		entity.setModifyDate(new Date());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			entity.setShowDate(sdf.parse(sdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (image != null && image.getSize() > 0) {
			String oriname = image.getOriginalFilename();
			String suffix = oriname.substring(oriname.lastIndexOf("."));
			String filePath = "resource/upload/" + Helper.genDateString() + "/"
					+ Helper.generatorID() + suffix;
			File tempFile = new File(request.getSession().getServletContext()
					.getRealPath(filePath));
			entity.setShowImage("/" + filePath);
			try {
				FileUtils.copyInputStreamToFile(image.getInputStream(),
						tempFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		starItemService.update(entity);
		ModelAndView mav = new ModelAndView("redirect:/my/star/item/view/"
				+ siid + "/2");
		return mav;
	}

	@RequestMapping(value = "/my/star/query/{keyword}")
	public @ResponseBody
	String starQuery(@PathVariable String keyword) {
		return starQueryJson(keyword);
	}

	@RequestMapping(value = "/my/star/query/ajax/{keyword}.json")
	public @ResponseBody
	String starQueryJson(@PathVariable String keyword) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("id", "desc");
			StringBuffer jpql = new StringBuffer(
					"o.visible=?1 and (o.name like?2 or o.simplePy like?3 or o.fullPy like?4)");

			List<Object> params = new ArrayList<Object>();
			params.add(true);
			params.add("%" + keyword + "%");
			params.add("%" + keyword + "%");
			params.add("%" + keyword + "%");
			List<Star> stars = starService.getScrollData(0, 10,
					jpql.toString(), params.toArray(), orderby).getResultList();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (Star star : stars) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", star.getId());
				map.put("label", star.getName());
				map.put("icon", star.getImgUrl());
				map.put("resnum", star.getResnum());
				list.add(map);
			}
			String json = mapper.writeValueAsString(list);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}

	@RequestMapping(value = "/my/star/support/category", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String starSupportCategory(@RequestParam String sid) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Star star = starService.find(sid);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (SupportCategory sc : star.getCategorys()) {
				Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
				dataMap.put("id", sc.getId());
				dataMap.put("name", sc.getName());
				list.add(dataMap);
			}
			String json = mapper.writeValueAsString(list);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}

	/**
	 * 校验Share数据：校验成功返回success，否则返回对应的提示信息
	 * 
	 * @param share
	 * @return
	 */
	private String validate(StarItem share) {
		// if (share.getCategory() == 1) {// 下载
		// if (share.getTitle() == null
		// || "".equals(share.getTitle().replace(" ", ""))) {
		// return "资源名称未填写";
		// }
		// if (share.getUrl() == null
		// || share.getUrl().replace(" ", "").length() < 3) {
		// return "下载地址填写错误";
		// }
		// } else if (share.getCategory() == 2) {// 文章
		// if (share.getTitle() == null
		// || "".equals(share.getTitle().replace(" ", ""))) {
		// return "文章标题未填写";
		// }
		// if (WebUtil.HtmltoText(share.getContent()).length() < 100) {
		// return "文章内容过少";
		// }
		//
		// } else if (share.getCategory() == 3) {// 音乐
		// if (share.getTitle() == null
		// || "".equals(share.getTitle().replace(" ", ""))) {
		// return "音乐名称未填写";
		// }
		// if (WebUtil.HtmltoText(share.getContent()).length() < 30) {
		// return "写点有意义的分享理由";
		// }
		// } else if (share.getCategory() == 4) {// 视频
		// if (share.getTitle() == null
		// || "".equals(share.getTitle().replace(" ", ""))) {
		// return "视频名称未填写";
		// }
		// if (WebUtil.HtmltoText(share.getContent()).length() < 30) {
		// return "写点有意义的分享理由";
		// }
		// } else if (share.getCategory() == 5) {// 图片
		// if ("/resource/image/preview.png".equals(share.getUrl())) {
		// return "未上传任何图片";
		// }
		// }
		return "success";
	}

}
