package com.framework.api.sys.job.vo;


import com.framework.commons.vo.ui.PageRequest;

public class SysJobLogParam extends PageRequest {
	private static final long serialVersionUID = 1L;

	private String jobId;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
}