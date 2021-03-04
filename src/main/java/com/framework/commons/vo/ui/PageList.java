package com.framework.commons.vo.ui;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

public class PageList<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private long total;
	private List<T> list;

	public PageList(List<T> list) {
		this.list = list;
		if (list instanceof Page) {
			this.total = ((Page<T>) list).getTotal();
		} else {
			this.total = list.size();
		}
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
