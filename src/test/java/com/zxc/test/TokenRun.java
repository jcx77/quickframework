package com.zxc.test;

import com.zxc.toolsproject.commons.utils.JwtUtils;


public class TokenRun {
	public static void main(String[] args) {
		System.out.println(JwtUtils.sign("admin", "f6fdffe48c908deb0f4c3bd36c032e72"));
	}
}