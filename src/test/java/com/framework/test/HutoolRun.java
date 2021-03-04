package com.framework.test;

import cn.hutool.core.util.IdUtil;
import cn.hutool.system.SystemUtil;

public class HutoolRun {
	public static void main(String[] args) {
		System.out.println(IdUtil.fastSimpleUUID());
		System.out.println(SystemUtil.getHostInfo().getName());
		System.out.println(IdUtil.fastSimpleUUID());
	}
}