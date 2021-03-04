package com.framework.commons.cg.mybatis.config;

public class JavaControllerConfig {
	private String controllerPackage;

	public JavaControllerConfig() {
	}

	public JavaControllerConfig(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}

	public String getControllerPackage() {
		return controllerPackage;
	}

	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}
}