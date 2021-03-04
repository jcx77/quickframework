package com.framework.commons.cg.mybatis.config;

public class JavaModelConfig {
	private String targetPackage;

	public JavaModelConfig() {
	}

	public JavaModelConfig(String targetPackage) {
		this.targetPackage = targetPackage;
	}

	public String getTargetPackage() {
		return targetPackage;
	}

	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}
}