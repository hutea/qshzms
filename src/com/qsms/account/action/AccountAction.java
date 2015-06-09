package com.qsms.account.action;

import java.util.Date;
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

import com.qsms.account.ebean.Account;
import com.qsms.account.ebean.PrivilegeGroup;
import com.qsms.account.service.AccountService;
import com.qsms.account.service.PrivilegeGroupService;
import com.qsms.util.Helper;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/account")
@Controller
public class AccountAction {
	@Resource
	private AccountService accountService;
	@Resource
	private PrivilegeGroupService groupService;
	@Autowired
	private HttpServletRequest request;

	private int maxresult = 10;

	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(required = false, defaultValue = "1") int page) {
		PageView<Account> pageView = new PageView<Account>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		pageView.setQueryResult(accountService.getScrollData(
				pageView.getFirstResult(), maxresult, orderby));
		request.setAttribute("pageView", pageView);
		ModelAndView mav = new ModelAndView("/account/account_list");
		mav.addObject("paveView", pageView);
		mav.addObject("m", 3);
		return mav;
	}

	@RequestMapping("/new")
	public ModelAndView addUI() {
		List<PrivilegeGroup> groups = groupService.getScrollData()
				.getResultList();
		ModelAndView mav = new ModelAndView("/account/account_add");
		mav.addObject("groups", groups);
		mav.addObject("m", 3);
		return mav;
	}

	@RequestMapping("/save")
	public ModelAndView add(@ModelAttribute Account account,
			@RequestParam(required = false) String[] gids) {
		account.setId(Helper.generatorShortID());
		account.setCreateTime(new Date());
		/** 权限组操作 **/
		if (gids != null && gids.length > 0) {
			for (String gid : gids) {
				account.getGroups().add(groupService.find(gid));
			}
		}
		accountService.save(account);
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}

	@RequestMapping("/edit")
	public ModelAndView editUI(@RequestParam String acid) {
		Account account = accountService.find(acid);
		List<PrivilegeGroup> groups = groupService.getScrollData()
				.getResultList();
		request.setAttribute("groups", groups);
		StringBuffer ugs = new StringBuffer();
		for (PrivilegeGroup group : account.getGroups()) {
			ugs.append("#" + group.getId());
		}
		ModelAndView mav = new ModelAndView("/account/account_edit");
		mav.addObject("account", account);
		mav.addObject("ugs", ugs.toString());
		mav.addObject("m", 3);
		return mav;
	}

	@RequestMapping("/update")
	public ModelAndView edit(@ModelAttribute Account account,
			@RequestParam String[] gids) {
		Account entity = accountService.find(account.getId());
		entity.setUsername(account.getUsername());
		entity.setPassword(account.getPassword());
		entity.setNickname(account.getNickname());
		entity.setLv(account.getLv());
		entity.setVisible(true);
		/** 权限组操作 **/
		entity.getGroups().clear();
		if (gids != null && gids.length > 0) {
			for (String gid : gids) {
				entity.getGroups().add(groupService.find(gid));
			}
		}
		accountService.update(entity);
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}

	@RequestMapping("/delete")
	public @ResponseBody
	String delete(@RequestParam String accid) {
		Account entity = accountService.find(accid);
		entity.setVisible(false);
		accountService.update(entity);
		return "success";
	}

}
