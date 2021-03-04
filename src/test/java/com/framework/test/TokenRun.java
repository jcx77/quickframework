package com.framework.test;

import com.framework.commons.utils.JwtUtils;


public class TokenRun {
	public static void main(String[] args) {
		System.out.println(JwtUtils.sign("admin", "f6fdffe48c908deb0f4c3bd36c032e72"));
	}
}