package com.framework.api.core.vo.param;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class PasswordParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "旧密码不允许为空")
	private String oldPassword;

	@NotBlank(message = "新密码不允许为空")
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}