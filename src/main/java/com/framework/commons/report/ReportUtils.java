package com.framework.commons.report;

import com.framework.commons.report.vo.Set;
import freemarker.template.Configuration;
import freemarker.template.Template;


import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public abstract class ReportUtils {
	public static String createXml(Set... set) {
		try {
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
			cfg.setClassForTemplateLoading(ReportUtils.class, "");
			cfg.setDefaultEncoding("UTF-8");
			Template temp = cfg.getTemplate("report.ftl", cfg.getDefaultEncoding());
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("set", set);
			Writer out = new StringWriter();
			temp.process(root, out);
			out.close();
			return out.toString();
		} catch (Exception e) {
			return null;
		}
	}
}