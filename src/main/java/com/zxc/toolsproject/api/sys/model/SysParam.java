package com.zxc.toolsproject.api.sys.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_param")
public class SysParam implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name = "CODE_")
	private String code;

	@Column(name = "NAME_")
	private String name;

	@Column(name = "VALUE_")
	private String value;

	@Column(name = "SORT_")
	private Integer sort;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}