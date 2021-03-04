package com.framework.commons.shiro.tools;

import com.framework.commons.shiro.subject.ShiroUser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;


public abstract class ShiroTools {
	private static final int DEFAULT_ITERATIONS = 1024;

	public static String md5(Object source, Object salt) {
		Md5Hash md5Hash = new Md5Hash(source, salt, DEFAULT_ITERATIONS);
		return md5Hash.toHex();
	}

	public static ShiroUser getShiroUser() {
		Subject subject = ThreadContext.getSubject();
		if (subject != null && subject.isAuthenticated()) {
			return (ShiroUser) subject.getPrincipal();
		} else {
			return null;
		}
	}
}