package com.framework.test;

import com.framework.commons.utils.RsaUtils;


public class RsaRun {
	public static void main(String[] args) {
		String data = RsaUtils.encrypt("test");
		System.out.println(data);
		System.out.println(RsaUtils.decrypt(data));
	}
}