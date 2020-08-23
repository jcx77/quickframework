package com.zxc.test;

import com.zxc.toolsproject.commons.utils.RsaUtils;


public class RsaRun {
	public static void main(String[] args) {
		String data = RsaUtils.encrypt("test");
		System.out.println(data);
		System.out.println(RsaUtils.decrypt(data));
	}
}