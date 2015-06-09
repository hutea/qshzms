package com.qsms.ttk.image.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qsms.ttk.TieTuKuClient;
import com.qsms.ttk.image.ebean.TkAlbum;
import com.qsms.ttk.image.service.TkAlbumService;
import com.qsms.ttk.json.Album;
import com.qsms.ttk.json.AlbumData;
import com.qsms.util.dao.PageView;

@RequestMapping("/manage/ttk")
@Controller
public class TkAlbumAction {
	@Resource
	private TkAlbumService tkAlbumService;
	private int maxresult = 20;

	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(defaultValue = "1", required = false) int page) {
		PageView<TkAlbum> pageView = new PageView<TkAlbum>(maxresult, page);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "desc");
		StringBuffer jpql = new StringBuffer("o.visible=?1");
		List<Object> params = new ArrayList<Object>();
		params.add(true);
		pageView.setQueryResult(tkAlbumService.getScrollData(
				pageView.getFirstResult(), maxresult, jpql.toString(),
				params.toArray(), orderby));
		ModelAndView mav = new ModelAndView("ttk/tkAlbum_list");
		mav.addObject("pageView", pageView);
		mav.addObject("page", page);
		mav.addObject("m", 9);
		return mav;
	}

	@RequestMapping("/syn/ttk")
	public ModelAndView syn(
			@RequestParam(defaultValue = "1", required = false) int page) {
		AlbumData albumData = TieTuKuClient.listAlbum();
		for (Album album : albumData.getAlbum()) {
			TkAlbum tkAlbum = tkAlbumService.find(album.getAid());
			if (tkAlbum != null) { // 更新
				tkAlbum.setNum(album.getNum());
				tkAlbum.setAid(album.getAid());
				tkAlbum.setAlbumname(album.getAlbumname());
				tkAlbum.setCode(album.getCode());
				tkAlbum.setVisible(true);
			} else { // 同步入库
				tkAlbum = new TkAlbum();
				tkAlbum.setNum(album.getNum());
				tkAlbum.setAid(album.getAid());
				tkAlbum.setAlbumname(album.getAlbumname());
				tkAlbum.setCode(album.getCode());
				tkAlbum.setVisible(true);
				tkAlbumService.save(tkAlbum);
			}
		}
		ModelAndView mav = new ModelAndView("redirect:../list");
		return mav;
	}

	@RequestMapping("/save")
	public ModelAndView add(@ModelAttribute TkAlbum tkAlbum) {
		Album album = TieTuKuClient.addAlbum(tkAlbum.getAlbumname());
		if ("200".equals(album.getCode())) {
			tkAlbum.setAid(album.getAlbumid());
			tkAlbum.setVisible(true);
			tkAlbumService.save(tkAlbum);
		} else {
			System.out.println("code=" + album.getCode());
		}
		ModelAndView mav = new ModelAndView("redirect:list");
		return mav;
	}

	@RequestMapping("/delete")
	public @ResponseBody
	int del(@RequestParam String aid) {
		int res = 1;
		try {
			tkAlbumService.delete(aid);
			/**
			 * String code = TieTuKuClient.deleteAlbum(Integer.parseInt(aid));
			 * if ("200".equals(code)) { TkAlbum entity =
			 * tkAlbumService.find(aid); entity.setVisible(false);
			 * tkAlbumService.update(entity); }
			 **/
		} catch (Exception e) {
			res = 0;
		}
		return res;
	}

}
