package com.framework.commons.cg.mybatis.converter;

public interface DBConverter {
	public Class<?> dbTypeToJavaType(String type, int size, int precision);
}