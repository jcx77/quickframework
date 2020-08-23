package com.zxc.toolsproject.commons.shiro.filter;

import com.zxc.toolsproject.commons.mybatis.tools.AuditTools;
import com.zxc.toolsproject.commons.shiro.authc.JwtToken;
import com.zxc.toolsproject.commons.spring.tools.ServletTools;
import com.zxc.toolsproject.commons.utils.JwtUtils;
import com.zxc.toolsproject.commons.vo.response.Response;
import com.zxc.toolsproject.commons.vo.response.ResponseStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter extends AccessControlFilter {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		try {
			String token = ServletTools.getToken();
			JwtToken jwtToken = new JwtToken(token);
			getSubject(request, response).login(jwtToken);
			AuditTools.setUserId(JwtUtils.getSubject(token));
			logger.info("Token认证成功 {} {}", jwtToken.getPrincipal(), jwtToken.getCredentials());
			return true;
		} catch (AuthenticationException e) {
			logger.error("Token认证失败", e);
			ServletTools.writeJson((HttpServletResponse) response, Response.failure(ResponseStatus.AUTHENTICATION_FAILURE, e.getMessage()));
			return false;
		}
	}
}