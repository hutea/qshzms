package com.qsms.share.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

import com.qsms.account.ebean.User;
import com.qsms.account.service.UserService;
import com.qsms.auxiliary.service.SystemConfigService;
import com.qsms.share.ebean.Share;
import com.qsms.share.ebean.Tag;
import com.qsms.share.service.ShareCommentService;
import com.qsms.share.service.ShareService;
import com.qsms.share.service.TagService;
import com.qsms.ttk.image.service.ImageTaskService;
import com.qsms.util.Helper;
import com.qsms.util.WebUtil;

@Controller
public class ShareFrontAction {
	@Resource
	private ShareService shareService;
	@Resource
	private TagService tagService;
	@Resource
	private UserService userService;
	@Resource
	private ShareCommentService shareCommentService;
	@Resource
	private SystemConfigService systemConfigService;
	@Resource
	private ImageTaskService imageTaskService;
	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/share/view/{sid}")
	public ModelAndView show(@PathVariable String sid) {
		User user = WebUtil.getLoginUser(request);
		Share share = shareService.find(sid);
		if (user != null) {
			share.viewload(user,
					shareCommentService.countBySuid(sid, user.getId()));
		} else {
			share.viewload(null, 0);
		}
		ModelAndView mav = new ModelAndView("view/share/share_show");
		mav.addObject("share", share);
		mav.addObject("pageView", shareCommentService.listForTheShare(sid, 1));
		mav.addObject("reportedMax",
				systemConfigService.shareCommentReportedMax());
		return mav;
	}

	@RequestMapping(value = "/share/save", method = RequestMethod.POST, produces = "plain/text; charset=UTF-8")
	public @ResponseBody
	String add(@ModelAttribute Share share,
			@RequestParam(required = false) String tag_str) {
		try {
			String validateResult = validate(share);
			if (!"success".equals(validateResult)) {
				return validateResult;
			}
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			User dbUser = userService.find(loginUser.getId());
			if (dbUser == null || !dbUser.getVisible() || dbUser.getLv() < 5) {
				return "帐户权限不足";
			}
			share.setId(Helper.generatorID());
			share.setUser(dbUser);
			if (dbUser.getLv() == 5) {
				share.setSiteauth(true);
			} else {
				share.setSiteauth(false);
			}
			share.setCreateDate(new Date());
			share.setUrl(share.getUrl().trim());// 对url进行首尾去空格操作
			share.genVideo();// 生成播放代码
			String text = WebUtil.HtmltoText(share.getContent());
			if (text.length() > 200) {// 大于400字符
				share.setSumary(text.substring(0, 200) + "......");
				share.setLoadMore(true);
			} else {// 小于400字符，摘要和内容相同
				share.setSumary(text);
				share.setLoadMore(false);
			}
			/** 开始处理标签 **/
			if (tag_str != null && tag_str.length() >= 0) {
				String[] tagNames = tag_str.trim().toLowerCase().split("#");
				int tagLength = 0;
				for (String tagName : tagNames) {
					tagLength = tagLength + tagName.length();
					if (tagLength > 35) {// 标签字符总长度大于35字符，则后面的字符去掉。
						break;
					}
					if (tagName != null
							&& !"".equals(tagName.replaceAll(" ", ""))) {// 空标签不存储
						Tag tag = tagService.findByName(tagName);
						share.getTags().add(tag);
					}
				}
			}
			shareService.save(share);
			dbUser.setShareds(dbUser.getShareds() + 1);
			imageTaskService.creatTask(2, share.getId());
			return "true";
		} catch (Exception e) {
			return "数据异常，请稍后重试";
		}
	}

	/**
	 * 校验Share数据：校验成功返回success，否则返回对应的提示信息
	 * 
	 * @param share
	 * @return
	 */
	private String validate(Share share) {
		if (share.getCategory() == 1) {// 下载
			if (share.getTitle() == null
					|| "".equals(share.getTitle().replace(" ", ""))) {
				return "资源名称未填写";
			}
			if (share.getUrl() == null
					|| share.getUrl().replace(" ", "").length() < 3) {
				return "下载地址填写错误";
			}
		} else if (share.getCategory() == 2) {// 文章
			if (share.getTitle() == null
					|| "".equals(share.getTitle().replace(" ", ""))) {
				return "文章标题未填写";
			}
			if (WebUtil.HtmltoText(share.getContent()).length() < 100) {
				return "文章内容过少";
			}

		} else if (share.getCategory() == 3) {// 音乐
			if (share.getTitle() == null
					|| "".equals(share.getTitle().replace(" ", ""))) {
				return "音乐名称未填写";
			}
			if (WebUtil.HtmltoText(share.getContent()).length() < 30) {
				return "写点有意义的分享理由";
			}
		} else if (share.getCategory() == 4) {// 视频
			if (share.getTitle() == null
					|| "".equals(share.getTitle().replace(" ", ""))) {
				return "视频名称未填写";
			}
			if (WebUtil.HtmltoText(share.getContent()).length() < 30) {
				return "写点有意义的分享理由";
			}
		} else if (share.getCategory() == 5) {// 图片
			if ("/resource/image/preview.png".equals(share.getUrl())) {
				return "未上传任何图片";
			}
		}
		return "success";
	}

	/**
	 * 图片分享：图片上传预览
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/share/upload", method = RequestMethod.POST)
	public @ResponseBody()
	String upload(
			@RequestParam(value = "file", required = false) MultipartFile file) {
		User loginUser = WebUtil.getLoginUser(request);
		User user = userService.find(loginUser.getId());
		if (user.getUploadSize() > user.getMaxUploadSize()) {// 今日上传Size大于了系统设定的最大上传Size
			return "ERROR";
		}
		String oriname = file.getOriginalFilename();
		String suffix = oriname.substring(oriname.lastIndexOf("."));
		String filePath = "resource/upload/" + Helper.genDateString() + "/"
				+ Helper.generatorID() + suffix;
		File imageFile = new File(request.getSession().getServletContext()
				.getRealPath(filePath));
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), imageFile);
			int uploadSize = Math.round(user.getUploadSize()
					+ (file.getSize() / 1024));
			user.setUploadSize(uploadSize);
			userService.update(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return request.getContextPath() + "/" + filePath;
	}

	@RequestMapping(value = "/share/upload/delete", method = RequestMethod.POST)
	public @ResponseBody
	String uploadDelete(@RequestParam String url) {
		File file = new File(request.getSession().getServletContext()
				.getRealPath(url));
		try {
			file.delete();
			return "true";
		} catch (Exception e) {
			return "false";
		}
	}
}
