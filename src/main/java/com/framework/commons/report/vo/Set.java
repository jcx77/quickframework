package com.framework.commons.report.vo;

import java.io.Serializable;
import java.util.List;

public class Set implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private List<Item> item;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}
}