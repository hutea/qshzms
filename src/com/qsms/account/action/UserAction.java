package com.qsms.account.action;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.account.ebean.PrivilegeGroup;
import com.qsms.account.ebean.User;
import com.qsms.account.service.PrivilegeGroupService;
import com.qsms.account.service.UserService;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/user")
@Controller
public class UserAction {
	@Resource
	private UserService userService;
	@Resource
	private PrivilegeGroupService groupService;
	@Autowired
	private HttpServletRequest request;

	private int maxresult = 10;

	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(required = false, defaultValue = "1") int page) {
		PageView<User> pageView = new PageView<User>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		pageView.setQueryResult(userService.getScrollData(
				pageView.getFirstResult(), maxresult, orderby));
		request.setAttribute("pageView", pageView);
		ModelAndView mav = new ModelAndView("/account/user_list");
		mav.addObject("paveView", pageView);
		mav.addObject("m", 3);
		return mav;
	}

	@RequestMapping("/edit")
	public ModelAndView editUI(@RequestParam String uid) {
		User user = userService.find(uid);
		List<PrivilegeGroup> groups = groupService.getScrollData()
				.getResultList();
		request.setAttribute("groups", groups);
		ModelAndView mav = new ModelAndView("/account/user_edit");
		mav.addObject("user", user);
		mav.addObject("m", 3);
		return mav;
	}

	@RequestMapping("/update")
	public ModelAndView edit(@ModelAttribute User user) {
		User entity = userService.find(user.getId());
		entity.setEmail(user.getEmail());
		entity.setPassword(user.getPassword());
		entity.setNickname(user.getNickname());
		entity.setLv(user.getLv());
		entity.setMaxComment(user.getMaxComment());
		entity.setMaxShare(user.getMaxShare());
		entity.setMaxUploadSize(user.getMaxUploadSize());
		entity.setVisible(user.getVisible());
		userService.update(entity);
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}

	@RequestMapping("/delete")
	public @ResponseBody
	String delete(@RequestParam String uid) {
		User entity = userService.find(uid);
		entity.setVisible(false);
		userService.update(entity);
		return "success";
	}

}
