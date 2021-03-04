package com.framework.commons.spring.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.framework.commons.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class ServletTools {
	private static final Logger logger = LoggerFactory.getLogger(ServletTools.class);

	private static ServletRequestAttributes getRequestAttributes() {
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}

	public static HttpServletRequest getRequest() {
		return getRequestAttributes().getRequest();
	}

	public static boolean isAjax() {
		String header = getRequest().getHeader("x-requested-with");
		if (header != null && header.equals("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}

	public static String getToken() {
		String token = getRequest().getHeader("token");
		if (token == null || "".equals(token)) {
			token = getRequest().getParameter("token");
		}
		return token;
	}

	public static void write(HttpServletResponse response, String s) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(s);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void writeJson(HttpServletResponse response, Object o) {
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(JsonUtils.getJson(o));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static String getClientIp() {
		String ip = "unknown";
		ip = getRequest().getHeader("X-FORWARDED-FOR ");
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getHeader("X-Real-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = getRequest().getRemoteAddr();
		}
		if (ip != null && ip.contains(",")) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	public static String getServerName() {
		return "unknown";
	}
}