package com.qsms.auxiliary.action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qsms.account.ebean.Account;
import com.qsms.account.ebean.User;
import com.qsms.account.service.UserService;
import com.qsms.util.Helper;
import com.qsms.util.WebUtil;

@RequestMapping(value = "/common")
@Controller
public class CommonAction {
	@Resource
	private UserService userService;

	@Autowired
	private HttpServletRequest request;

	/**
	 * CKEditor传图专用
	 * 
	 * @param himage
	 * @return
	 */
	@RequestMapping(value = "/ckeditor/himage", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFromCkeditor(
			@RequestParam(value = "himage", required = false) MultipartFile himage) {
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			ObjectMapper mapper = new ObjectMapper();
			Account account = WebUtil.getloginAccount(request);
			if (account == null) {// 没有后台登录帐户
				User loginUser = WebUtil.getLoginUser(request);
				User user = userService.find(loginUser.getId());
				if (user == null) {//
					dataMap.put("sign", "ERROR");
					String json = mapper.writeValueAsString(dataMap);
					return json;
				}
				if (user.getUploadSize() > user.getMaxUploadSize()) {// 今日上传Size大于了系统设定的最大上传Size
					dataMap.put("sign", "ERROR");
					String json = mapper.writeValueAsString(dataMap);
					return json;
				}
				int uploadSize = Math.round(user.getUploadSize()
						+ (himage.getSize() / 1024));
				user.setUploadSize(uploadSize);
				userService.update(user);
			}
			String oriname = himage.getOriginalFilename();
			String suffix = oriname.substring(oriname.lastIndexOf("."));
			String filePath = "resource/upload/" + Helper.genDateString() + "/"
					+ Helper.generatorID() + suffix;
			File tempFile = new File(request.getSession().getServletContext()
					.getRealPath(filePath));
			FileUtils.copyInputStreamToFile(himage.getInputStream(), tempFile);
			dataMap.put("url", request.getContextPath() + "/" + filePath);
			dataMap.put("title", oriname);
			dataMap.put("sign", "RIGHT");
			String json = mapper.writeValueAsString(dataMap);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
}
