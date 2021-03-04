package com.framework.api.sys.user.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_user_res")
public class SysUserRes implements Serializable {
	private static final long serialVersionUID = 1L;
    
	@Id
	@Column(name = "ID_")
	private String id;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "RES_ID")
	private String resId;

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

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}
}