package com.framework.commons.vo.ui;

import java.io.Serializable;

public class PageRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private int page;
	private int rows;

	public PageRequest() {
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}