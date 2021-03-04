package com.framework.commons.mybatis.tools;


import com.framework.commons.spring.tools.ServletTools;

public abstract class AuditTools {
	private static final String USER_ID = "userId";

	public static void setUserId(String userId) {
		ServletTools.getRequest().setAttribute(USER_ID, userId);
	}

	public static String getUserId() {
		return (String) ServletTools.getRequest().getAttribute(USER_ID);
	}
}