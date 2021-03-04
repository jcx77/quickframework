package com.framework.commons.cg.mybatis.config;

public class JavaServiceConfig {
	private String servicePackage;

	public JavaServiceConfig() {
	}

	public JavaServiceConfig(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}
}