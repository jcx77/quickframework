package com.framework.api.sys.param.model;



import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Table(name = "sys_param")
public class SysParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODE_")
	private String code;

	@Column(name = "NAME_")
	private String name;

	@Column(name = "VALUE_")
	@NotBlank(message = "参数内容不允许为空")
	@Length(max = 500, message = "参数内容长度不允许大于500")
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