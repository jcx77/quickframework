package com.zxc.toolsproject.api.app.test.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "test")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}