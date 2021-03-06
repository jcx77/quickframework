package com.framework.api.core.vo.param;


import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class LoginParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "帐号不允许为空")
	private String account;

	@NotBlank(message = "密码不允许为空")
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}