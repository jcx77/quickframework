package com.framework.api.sys.user.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_user_organ")
public class SysUserOrgan implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name = "ID_")
	private String id;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "ORGAN_ID")
	private String organId;

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

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}
}