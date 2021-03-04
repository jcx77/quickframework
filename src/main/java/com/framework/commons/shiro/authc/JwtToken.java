package com.framework.commons.shiro.authc;

import com.framework.commons.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationToken;


public class JwtToken implements AuthenticationToken {
	private static final long serialVersionUID = 1L;

	private String token;

	public JwtToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return JwtUtils.getSubject(token);
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}