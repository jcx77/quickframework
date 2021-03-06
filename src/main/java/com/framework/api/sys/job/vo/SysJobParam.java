package com.framework.api.sys.job.vo;

import java.io.Serializable;

public class SysJobParam implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String status;
	private String deleteFlag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}