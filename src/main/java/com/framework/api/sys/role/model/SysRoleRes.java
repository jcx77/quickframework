package com.framework.api.sys.role.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_role_res")
public class SysRoleRes implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name = "ID_")
	private String id;

	@Column(name = "ROLE_ID")
	private String roleId;

	@Column(name = "RES_ID")
	private String resId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}
}