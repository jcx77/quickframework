package com.framework.api.sys.report.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "SYS_REPORT")
public class SysReport implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name = "FILE_")
	private String file;

	@Column(name = "CONTENT_")
	private String content;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
}