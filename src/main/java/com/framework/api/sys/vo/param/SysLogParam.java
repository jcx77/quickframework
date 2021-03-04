package com.framework.api.sys.vo.param;

import com.framework.commons.vo.ui.PageRequest;


import java.util.Date;

public class SysLogParam extends PageRequest {
	private static final long serialVersionUID = 1L;

	private String type;
	private String content;
	private Date createdTimeBegin;
	private Date createdTimeEnd;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTimeBegin() {
		return createdTimeBegin;
	}

	public void setCreatedTimeBegin(Date createdTimeBegin) {
		this.createdTimeBegin = createdTimeBegin;
	}

	public Date getCreatedTimeEnd() {
		return createdTimeEnd;
	}

	public void setCreatedTimeEnd(Date createdTimeEnd) {
		this.createdTimeEnd = createdTimeEnd;
	}
}