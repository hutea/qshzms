package com.qsms.account.action;

import java.io.File;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsms.account.ebean.Account;
import com.qsms.account.ebean.User;
import com.qsms.account.service.UserService;
import com.qsms.auxiliary.ebean.Email;
import com.qsms.auxiliary.service.EmailService;
import com.qsms.auxiliary.service.SystemConfigService;
import com.qsms.core.ebean.Star;
import com.qsms.core.ebean.StarResource;
import com.qsms.core.service.StarResourceService;
import com.qsms.core.service.StarService;
import com.qsms.share.ebean.ReportRecord;
import com.qsms.share.ebean.Share;
import com.qsms.share.ebean.ShareComment;
import com.qsms.share.ebean.Tag;
import com.qsms.share.service.ReportRecordService;
import com.qsms.share.service.ShareCommentService;
import com.qsms.share.service.ShareLikeService;
import com.qsms.share.service.ShareService;
import com.qsms.share.service.TagService;
import com.qsms.ttk.image.service.ImageTaskService;
import com.qsms.util.EmailSender;
import com.qsms.util.Helper;
import com.qsms.util.WebUtil;
import com.qsms.util.dao.PageView;

@Controller
public class UserCenterStarAction {
	@Resource
	private StarResourceService starResourceService;
	@Resource
	private StarService starService;
	@Resource
	private ImageTaskService imageTaskService;
	@Resource
	private UserService userService;
	@Resource
	private SystemConfigService systemConfigService;

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	private int maxresult = 10;

	@RequestMapping("/my/star")
	public ModelAndView starList(
			@RequestParam(required = false, defaultValue = "1") int page) {
		ModelAndView mav = new ModelAndView("view/my/user_star_list");
		PageView<StarResource> pageView = new PageView<StarResource>(maxresult,
				page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.user.id=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(WebUtil.getLoginUser(request).getId());
		pageView.setQueryResult(starResourceService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("mym", 5);
		return mav;
	}

	@RequestMapping("/my/star/new")
	public ModelAndView starNew() {
		ModelAndView mav = new ModelAndView("view/my/user_star_new");
		return mav;
	}

	@RequestMapping(value = "/my/star/save", method = RequestMethod.POST)
	public ModelAndView save(
			@ModelAttribute StarResource starResource,
			@RequestParam(value = "image", required = false) MultipartFile image,
			String sid) {
		ModelAndView mav = new ModelAndView("view/my/user_star_show");
		String validateResult = validate(starResource);
		if (!"success".equals(validateResult)) {
			mav.setViewName("view/my/user_star_new");
		}
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		User dbUser = userService.find(loginUser.getId());
		// if (dbUser == null || !dbUser.getVisible() || dbUser.getLv() < 5)
		// {
		// return "帐户权限不足";
		// }
		starResource.setId(Helper.generatorID());
		starResource.setUser(dbUser);
		starResource.setStar(starService.find(sid));
		starResourceService.save(starResource);
		imageTaskService.creatTask(2, starResource.getId());
		return mav;
	}

	/**
	 * 校验Share数据：校验成功返回success，否则返回对应的提示信息
	 * 
	 * @param share
	 * @return
	 */
	private String validate(StarResource share) {
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

	@RequestMapping("/my/star/eidt")
	public ModelAndView starEdit() {
		ModelAndView mav = new ModelAndView("view/my/user_share_new");
		return mav;
	}

	@RequestMapping("/my/star/update")
	public ModelAndView starUpdate() {
		ModelAndView mav = new ModelAndView("view/my/user_share_new");
		return mav;
	}

	@RequestMapping(value = "/my/star/query/{keyword}")
	public @ResponseBody
	String starQuery(@PathVariable String keyword) {
		return starQuery(keyword);
	}

	@RequestMapping(value = "/my/star/query/ajax/{keyword}.json")
	public @ResponseBody
	String starQueryJson(@PathVariable String keyword) {
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
			orderby.put("id", "desc");
			StringBuffer jpql = new StringBuffer(
					"o.visible=?1 and (o.name like?2 or o.simplePy like?3 or o.fullPy=?4)");

			List<Object> params = new ArrayList<Object>();
			params.add(true);
			params.add("%" + keyword + "%");
			params.add("%" + keyword + "%");
			params.add("%" + keyword + "%");
			List<Star> stars = starService.getScrollData(0, 10,
					jpql.toString(), params.toArray(), orderby).getResultList();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (Star star : stars) {
				dataMap.put("id", star.getId());
				dataMap.put("label", star.getName());
				dataMap.put("icon", star.getImageUrl());
				dataMap.put("resnum", star.getResnum());
				list.add(dataMap);
			}
			String json = mapper.writeValueAsString(list);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}

}
