package com.framework.commons.cg.mybatis.dom.mapper;


import com.framework.commons.cg.mybatis.dom.model.JavaModel;

public class JavaMapper {
	private String packageName;
	private String className;
	private JavaModel javaModel;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public JavaModel getJavaModel() {
		return javaModel;
	}

	public void setJavaModel(JavaModel javaModel) {
		this.javaModel = javaModel;
	}
}