package com.framework.commons.cg.database.config;


import com.framework.commons.utils.JdbcUtils;

public class JdbcConfig {
	private String url;
	private String username;
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return JdbcUtils.getDriverClassName(url);
	}

	public String getDbType() {
		return JdbcUtils.getDbType(url);
	}
}