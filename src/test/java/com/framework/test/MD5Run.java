package com.framework.test;


import com.framework.commons.shiro.tools.ShiroTools;

public class MD5Run {
	public static void main(String[] args) {
		System.out.println(ShiroTools.md5("admin", "cDbj/mlVWTtA40YCvsxb8A=="));
	}
}