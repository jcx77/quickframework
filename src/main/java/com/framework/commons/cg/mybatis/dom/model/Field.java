package com.framework.commons.cg.mybatis.dom.model;


import com.framework.commons.cg.database.dom.Column;

public class Field {
	private String name;
	private Class<?> type;
	private Column column;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}
}