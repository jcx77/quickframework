package com.framework.api.sys.vo.param;

import java.io.Serializable;

public class SysUserResParam implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String[] ids;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
}
