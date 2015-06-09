package com.qsms.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreeMarkerUtil {
	public static Template getTemplate(HttpServletRequest request, String ftlName) {
		Template temp = null;
		try {
			Configuration cfg = new Configuration();
			cfg.setDefaultEncoding("UTF-8");
			String realTemplatePath = request.getSession().getServletContext().getRealPath(
					"/WEB-INF/ftl");
			cfg.setDirectoryForTemplateLoading(new File(realTemplatePath));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			temp = cfg.getTemplate(ftlName, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}
}
