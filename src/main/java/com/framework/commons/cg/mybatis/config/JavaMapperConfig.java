package com.framework.commons.cg.mybatis.config;

public class JavaMapperConfig {
	private String targetPackage;

	public JavaMapperConfig() {
	}

	public JavaMapperConfig(String targetPackage) {
		this.targetPackage = targetPackage;
	}

	public String getTargetPackage() {
		return targetPackage;
	}

	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}
}