package com.qsms.ttk.image.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.qsms.auxiliary.service.SystemConfigService;
import com.qsms.blog.ebean.Blog;
import com.qsms.blog.service.BlogService;
import com.qsms.core.ebean.StarItem;
import com.qsms.core.service.StarItemService;
import com.qsms.share.ebean.Share;
import com.qsms.share.service.ShareService;
import com.qsms.ttk.TieTuKuClient;
import com.qsms.ttk.image.ebean.ImageTask;
import com.qsms.ttk.image.ebean.TkImage;
import com.qsms.ttk.json.UpLoadImage;
import com.qsms.util.Helper;
import com.qsms.util.dao.DAOSupport;

@Service
public class ImageTaskServiceBean extends DAOSupport<ImageTask> implements
		ImageTaskService {
	@Resource
	private BlogService blogService;
	@Resource
	private ShareService shareService;
	@Resource
	private StarItemService starItemService;
	@Resource
	private TkImageService tkImageService;
	@Resource
	private SystemConfigService systemConfigService;

	private Log dataLog = LogFactory.getLog("dataServerLog");

	@Override
	public void creatTask(int type, String sobId) {
		ImageTask imageTask = null;
		try {
			imageTask = (ImageTask) em
					.createQuery(
							"select o from ImageTask o where type=?1 and sobId=?2")
					.setParameter(1, type).setParameter(2, sobId)
					.getSingleResult();
		} catch (Exception e) {
			imageTask = null;
		}
		if (imageTask == null) {// 创建
			imageTask = new ImageTask();
			imageTask.setId(Helper.generatorID());
			imageTask.setType(type);
			imageTask.setSobId(sobId);
			imageTask.setVisible(false);
			this.save(imageTask);
		} else {// 更新为false
			imageTask.setVisible(false);
			this.update(imageTask);
		}
	}

	@Override
	public void upload() {
		String path = this.getClass().getClassLoader().getResource("")
				.getPath();
		File dir = new File(path);
		File appRoot = dir.getParentFile().getParentFile();
		dataLog.info(new Date() + "开始执行上传任务" + appRoot);
		List<ImageTask> tasks = this.listUnUpload(10);
		for (ImageTask imageTask : tasks) {
			if (imageTask.getType() == 1) {// 博客
				blogProcess(imageTask, appRoot);
			} else if (imageTask.getType() == 2) { // Share
				shareProcess(imageTask, appRoot);
			} else if (imageTask.getType() == 3) { // StarItem
				starItemProcess(imageTask, appRoot);
			}
		}
	}

	private void starItemProcess(ImageTask imageTask, File appRootDir) {
		StarItem starItem = starItemService.find(imageTask.getSobId());
		if (!starItem.getVisible()) {// share被删除，则移出imageTask
			dataLog.info("Share源被删除，ID:" + imageTask.getId());
			this.delete(imageTask.getId());
			return;
		}
		List<String> urlList = Helper.imagelist(starItem.getContent());
		int total = urlList.size();
		int i = 0;// 处理计数
		int aid = Integer.parseInt(systemConfigService.starAid());
		for (String url : urlList) {
			dataLog.info("图片url：" + url);
			if (systemConfigService.isExternalSite(url)) {// 存储在外链站的图片不需要替换
				i++;
				dataLog.info("在外链接站点：" + url);
				continue;
			}
			UpLoadImage upimage = null;
			File localImgFile = null;
			if (url.startsWith("/resource")) {// 存储在本地
				dataLog.info("本地图片上传：" + url);
				localImgFile = new File(appRootDir, url);
				upimage = TieTuKuClient.upload(localImgFile, aid);
			} else {// 网络图片
				dataLog.info("网络图片上传：" + url);
				upimage = TieTuKuClient.upload(url, aid);
			}
			if (upimage != null) {// 上传成功
				if (localImgFile != null) {// 删除本地图片
					boolean delresult = localImgFile.delete();
					dataLog.info("要删除本地图片的全路径："
							+ localImgFile.getAbsolutePath());
					dataLog.info("删除结果：" + delresult);
				}
				TkImage tkImage = new TkImage();
				BeanUtils.copyProperties(upimage, tkImage);
				tkImage.setOriurl(url); // 设置上传源地址
				tkImage.setUploadTime(new Date()); // 设置上传时间
				tkImage.setId(Helper.generatorID());
				String linkurl = upimage.getLinkurl();
				String find_url = linkurl
						.substring(linkurl.lastIndexOf("/") + 1);
				tkImage.setFind_url(find_url);
				tkImage.setImageTask(imageTask);
				tkImage.setStarItem(starItem);
				tkImageService.save(tkImage);
				starItem.setContent(starItem.getContent().replace(url,
						upimage.getLinkurl()));// 更新内容图片地址
				i++;
			} else {
				dataLog.info("上传失败：" + url + " ShARE ID:"
						+ imageTask.getSobId());
			}
		}
		// 如果是图片类型：
		if (starItem.getShowImage() != null && !"".equals(starItem.getShowImage())) {
			total = total + 1; // 对要处理的总数+1
			UpLoadImage upimage = null;
			File localImgFile = null;
			if (starItem.getShowImage().startsWith("/resource")) {// 存储在本地需要上传
				dataLog.info("图片类型：本地图片上传：" + starItem.getShowImage());
				localImgFile = new File(appRootDir, starItem.getShowImage());
				upimage = TieTuKuClient.upload(localImgFile, aid);
			} else if (!systemConfigService
					.isExternalSite(starItem.getShowImage())) {// 非外链站网络图片
				dataLog.info("图片类型：网络图片上传：" + starItem.getShowImage());
				upimage = TieTuKuClient.upload(starItem.getShowImage(), aid);
			} else {
				i++;
			}
			if (upimage != null) {// 上传成功
				if (localImgFile != null) {// 删除本地文件
					boolean delresult = localImgFile.delete();
					dataLog.info("要删除本地图片的全路径："
							+ localImgFile.getAbsolutePath());
					dataLog.info("删除结果：" + delresult);
				}
				TkImage tkImage = new TkImage();
				BeanUtils.copyProperties(upimage, tkImage);
				tkImage.setOriurl(starItem.getShowImage()); // 设置上传源地址
				tkImage.setUploadTime(new Date()); // 设置上传时间
				tkImage.setId(Helper.generatorID());
				String linkurl = upimage.getLinkurl();
				String find_url = linkurl
						.substring(linkurl.lastIndexOf("/") + 1);
				tkImage.setFind_url(find_url);
				tkImage.setImageTask(imageTask);
				tkImage.setStarItem(starItem);
				tkImageService.save(tkImage);
				starItem.setShowImage(tkImage.getLinkurl());// 更新图片地址
				i++;
			} else {
				dataLog.info("图片类型：上传失败：" + starItem.getShowImage()
						+ " starItem ID:" + imageTask.getSobId());
			}
		}
		starItemService.update(starItem); // 处理完成后更新(图片地址更新)
		if (total == i) {
			imageTask.setVisible(true); // 表示任务已处理
			this.update(imageTask);
		}
	}

	private void shareProcess(ImageTask imageTask, File appRootDir) {
		Share share = shareService.find(imageTask.getSobId());
		if (!share.getVisible()) {// share被删除，则移出imageTask
			dataLog.info("Share源被删除，ID:" + imageTask.getId());
			this.delete(imageTask.getId());
			return;
		}
		List<String> urlList = Helper.imagelist(share.getContent());
		int total = urlList.size();
		int i = 0;// 处理计数
		int aid = Integer.parseInt(systemConfigService.shareAid());
		for (String url : urlList) {
			dataLog.info("图片url：" + url);
			if (systemConfigService.isExternalSite(url)) {// 存储在外链站的图片不需要替换
				i++;
				dataLog.info("在外链接站点：" + url);
				continue;
			}
			UpLoadImage upimage = null;
			File localImgFile = null;
			if (url.startsWith("/resource")) {// 存储在本地
				dataLog.info("本地图片上传：" + url);
				localImgFile = new File(appRootDir, url);
				upimage = TieTuKuClient.upload(localImgFile, aid);
			} else {// 网络图片
				dataLog.info("网络图片上传：" + url);
				upimage = TieTuKuClient.upload(url, aid);
			}
			if (upimage != null) {// 上传成功
				if (localImgFile != null) {// 删除本地图片
					boolean delresult = localImgFile.delete();
					dataLog.info("要删除本地图片的全路径："
							+ localImgFile.getAbsolutePath());
					dataLog.info("删除结果：" + delresult);
				}
				TkImage tkImage = new TkImage();
				BeanUtils.copyProperties(upimage, tkImage);
				tkImage.setOriurl(url); // 设置上传源地址
				tkImage.setUploadTime(new Date()); // 设置上传时间
				tkImage.setId(Helper.generatorID());
				String linkurl = upimage.getLinkurl();
				String find_url = linkurl
						.substring(linkurl.lastIndexOf("/") + 1);
				tkImage.setFind_url(find_url);
				tkImage.setImageTask(imageTask);
				tkImage.setShare(share);
				tkImageService.save(tkImage);
				share.setContent(share.getContent().replace(url,
						upimage.getLinkurl()));// 更新内容图片地址
				share.setSumary(share.getSumary().replace(url,
						upimage.getLinkurl()));// 更新摘要图片地址
				i++;
			} else {
				dataLog.info("上传失败：" + url + " ShARE ID:"
						+ imageTask.getSobId());
			}
		}
		// 如果是图片类型：
		if (share.getCategory() == 5) {
			total = total + 1; // 对要处理的总数+1
			UpLoadImage upimage = null;
			File localImgFile = null;
			if (share.getUrl().startsWith("/resource")) {// 存储在本地需要上传
				dataLog.info("图片类型：本地图片上传：" + share.getUrl());
				localImgFile = new File(appRootDir, share.getUrl());
				upimage = TieTuKuClient.upload(localImgFile, aid);
			} else if (!systemConfigService.isExternalSite(share.getUrl())) {// 非外链站网络图片
				dataLog.info("图片类型：网络图片上传：" + share.getUrl());
				upimage = TieTuKuClient.upload(share.getUrl(), aid);
			} else {
				i++;
			}
			if (upimage != null) {// 上传成功
				if (localImgFile != null) {// 删除本地文件
					boolean delresult = localImgFile.delete();
					dataLog.info("要删除本地图片的全路径："
							+ localImgFile.getAbsolutePath());
					dataLog.info("删除结果：" + delresult);
				}
				TkImage tkImage = new TkImage();
				BeanUtils.copyProperties(upimage, tkImage);
				tkImage.setOriurl(share.getUrl()); // 设置上传源地址
				tkImage.setUploadTime(new Date()); // 设置上传时间
				tkImage.setId(Helper.generatorID());
				String linkurl = upimage.getLinkurl();
				String find_url = linkurl
						.substring(linkurl.lastIndexOf("/") + 1);
				tkImage.setFind_url(find_url);
				tkImage.setImageTask(imageTask);
				tkImage.setShare(share);
				tkImageService.save(tkImage);
				share.setUrl(tkImage.getLinkurl());// 更新图片地址
				i++;
			} else {
				dataLog.info("图片类型：上传失败：" + share.getUrl() + " ShARE ID:"
						+ imageTask.getSobId());
			}
		}
		shareService.update(share); // 处理完成后更新(图片地址更新)
		if (total == i) {
			imageTask.setVisible(true); // 表示任务已处理
			this.update(imageTask);
		}
	}

	private void blogProcess(ImageTask imageTask, File appRootDir) {
		Blog blog = blogService.find(imageTask.getSobId());
		if (!blog.getVisible()) {// blog被删除，则移出imageTask
			dataLog.info("Blog源被删除，ID:" + imageTask.getId());
			this.delete(imageTask.getId());
			return;
		}
		List<String> urlList = Helper.imagelist(blog.getContent());
		int i = 0;// 处理计算器
		for (String url : urlList) {
			dataLog.info("图片url：" + url);
			if (systemConfigService.isExternalSite(url)) {// 存储在外链站的图片不需要替换
				i++;
				dataLog.info("在外链接站点：" + url);
				continue;
			}
			int aid = Integer.parseInt(systemConfigService.blogAid());
			UpLoadImage upimage = null;
			File localImgFile = null;
			if (url.startsWith("/resource")) {// 存储在本地
				dataLog.info("本地图片上传：" + url);
				localImgFile = new File(appRootDir, url);
				upimage = TieTuKuClient.upload(localImgFile, aid);
			} else {// 网络图片
				dataLog.info("网络图片上传：" + url);
				upimage = TieTuKuClient.upload(url, aid);
			}
			dataLog.info("上传结果：" + upimage + "#");
			if (upimage != null) {// 上传成功
				if (localImgFile != null) {
					boolean delresult = localImgFile.delete();
					dataLog.info("要删除本地图片的全路径："
							+ localImgFile.getAbsolutePath());
					dataLog.info("删除结果：" + delresult);
				}
				TkImage tkImage = new TkImage();
				BeanUtils.copyProperties(upimage, tkImage);
				tkImage.setOriurl(url); // 设置上传源地址
				tkImage.setUploadTime(new Date()); // 设置上传时间
				tkImage.setId(Helper.generatorID());
				String linkurl = upimage.getLinkurl();
				String find_url = linkurl
						.substring(linkurl.lastIndexOf("/") + 1);
				tkImage.setFind_url(find_url);
				tkImage.setImageTask(imageTask);
				tkImage.setBlog(blog);
				tkImageService.save(tkImage);
				blog.setContent(blog.getContent().replace(url,
						upimage.getLinkurl()));// 更新引用地址
				i++;
			} else {
				dataLog.info("上传失败：" + url);
			}
		}
		blogService.update(blog); // 处理完成后更新(图片地址更新)
		if (urlList.size() == i) {
			imageTask.setVisible(true); // 表示已处理
			this.update(imageTask);
		}
	}

	@SuppressWarnings("unchecked")
	private List<ImageTask> listUnUpload(int maxresult) {
		return em
				.createQuery(
						"select o from ImageTask o where o.visible=?1 order by o.id asc")
				.setParameter(1, false).setMaxResults(maxresult)
				.getResultList();
	}
}