package com.framework.api.sys.res.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_res")
public class SysRes implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name = "ID_")
	private String id;

	@Column(name = "NAME_")
	private String name;

	@Column(name = "TYPE_")
	private String type;

	@Column(name = "ICON_")
	private String icon;

	@Column(name = "URL_")
	private String url;

	@Column(name = "SORT_")
	private Integer sort;

	@Column(name = "SHOW_")
	private String show;

	@Column(name = "PID_")
	private String pid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}