package com.qsms.account.action;

import java.util.LinkedHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.account.ebean.PrivilegeGroup;
import com.qsms.account.ebean.SystemPrivilege;
import com.qsms.account.service.PrivilegeGroupService;
import com.qsms.account.service.SystemPrivilegeService;
import com.qsms.util.Helper;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/account/group")
@Controller
public class PrivilegeGroupAction {
	@Resource
	private PrivilegeGroupService groupService;
	@Resource
	private SystemPrivilegeService systemPrivilegeService;
	@Autowired
	private HttpServletRequest request;

	private int maxresult = 10;

	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(required = false, defaultValue = "1") int page) {
		PageView<PrivilegeGroup> pageView = new PageView<PrivilegeGroup>(
				maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createTime", "desc");
		orderby.put("id", "desc");
		pageView.setQueryResult(groupService.getScrollData(pageView
				.getFirstResult(), maxresult, orderby));
		ModelAndView mav = new ModelAndView("/account/privilegeGroup_list");
		mav.addObject("pageView", pageView);
		mav.addObject("sps1", systemPrivilegeService.listBylevel(1));
		mav.addObject("sps2", systemPrivilegeService.listBylevel(2));
		mav.addObject("sps3", systemPrivilegeService.listBylevel(3));
		mav.addObject("sps4", systemPrivilegeService.listBylevel(4));
		mav.addObject("sps5", systemPrivilegeService.listBylevel(5));
		mav.addObject("m", 3);
		return mav;
	}

	@RequestMapping("/save")
	public ModelAndView add(@RequestParam String[] privilegeIds,
			@RequestParam(required = false) String groupName) {
		PrivilegeGroup group = new PrivilegeGroup();
		if (privilegeIds != null && privilegeIds.length > 0) {
			for (String id : privilegeIds) {
				group.getPrivileges().add(systemPrivilegeService.find(id));
			}
		}
		group.setId(Helper.generatorShortID());
		group.setName(groupName);
		groupService.save(group);
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}

	@RequestMapping("/edit")
	public ModelAndView editUI(@RequestParam String gid) {
		PrivilegeGroup group = groupService.find(gid);
		StringBuffer gps = new StringBuffer();
		if (group.getPrivileges() != null) {
			for (SystemPrivilege p : group.getPrivileges()) {
				gps.append("#" + p.getId());
			}
		}
		ModelAndView mav = new ModelAndView("/account/privilegeGroup_edit");
		mav.addObject("gps", gps.toString());
		mav.addObject("sps1", systemPrivilegeService.listBylevel(1));
		mav.addObject("sps2", systemPrivilegeService.listBylevel(2));
		mav.addObject("sps3", systemPrivilegeService.listBylevel(3));
		mav.addObject("sps4", systemPrivilegeService.listBylevel(4));
		mav.addObject("sps5", systemPrivilegeService.listBylevel(5));
		mav.addObject("group", group);
		mav.addObject("m", 3);
		return mav;
	}

	@RequestMapping("/update")
	public ModelAndView edit(@RequestParam String gid,
			@RequestParam String groupName, @RequestParam String[] privilegeIds) {
		PrivilegeGroup entity = groupService.find(gid);
		entity.setName(groupName);
		entity.getPrivileges().clear();
		if (privilegeIds != null && privilegeIds.length > 0) {
			for (String id : privilegeIds) {
				entity.getPrivileges().add(systemPrivilegeService.find(id));
			}
		}
		groupService.update(entity);
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}

	@RequestMapping("/delete")
	public @ResponseBody
	String delete(@RequestParam String gid) {
		PrivilegeGroup entity = groupService.find(gid);
		if (entity != null) {
			if (entity.getInitSign()) { // 表示为系统初始定义不能删除
				return "INITGROUP";
			} else {
				groupService.delete(gid);
				return "SUCCESS";
			}
		}
		return "ERROR";
	}
}
