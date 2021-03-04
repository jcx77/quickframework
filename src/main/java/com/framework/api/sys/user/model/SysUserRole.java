package com.framework.api.sys.user.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_user_role")
public class SysUserRole implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name = "ID_")
	private String id;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "ROLE_ID")
	private String roleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}