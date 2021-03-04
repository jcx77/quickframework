package com.framework.commons.cg.mybatis.converter;

import java.util.Date;

public class H2Converter implements DBConverter {
	public Class<?> dbTypeToJavaType(String type, int size, int precision) {
		if ("VARCHAR".equals(type)) {
			return String.class;
		} else if ("CHAR".equals(type)) {
			return String.class;
		} else if ("DATE".equals(type)) {
			return Date.class;
		} else if ("TIMESTAMP".equals(type)) {
			return Date.class;
		} else if ("INTEGER".equals(type)) {
			return Integer.class;
		} else if ("BIGINT".equals(type)) {
			return Long.class;
		} else if ("CLOB".equals(type)) {
			return String.class;
		} else {
			throw new RuntimeException("无法对[" + type + "]类型进行转换");
		}
	}
}