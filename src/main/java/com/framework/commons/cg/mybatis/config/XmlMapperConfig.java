package com.framework.commons.cg.mybatis.config;

public class XmlMapperConfig {
	private String targetPackage;

	public XmlMapperConfig() {
	}

	public XmlMapperConfig(String targetPackage) {
		this.targetPackage = targetPackage;
	}

	public String getTargetPackage() {
		return targetPackage;
	}

	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}
}