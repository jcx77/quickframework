package com.framework.commons.utils;

import cn.hutool.core.util.IdUtil;

public abstract class StringUtils {
	public static String uuid() {
		return IdUtil.fastSimpleUUID();
	}
}