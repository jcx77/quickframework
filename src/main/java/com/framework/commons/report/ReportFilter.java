package com.framework.commons.report;

import com.framework.commons.mybatis.tools.AuditTools;
import com.framework.commons.shiro.service.ShiroService;
import com.framework.commons.shiro.subject.ShiroUser;
import com.framework.commons.spring.tools.ServletTools;
import com.framework.commons.spring.tools.SpringTools;
import com.framework.commons.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReportFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		if (ServletTools.isAjax()) {
			try {
				String token = ServletTools.getToken();
				if (token == null) {
					throw new AuthenticationException("认证失败：Token不允许为空");
				}
				String id = (String) JwtUtils.getSubject(token);
				if (id == null) {
					throw new AuthenticationException("认证失败：Token非法无效");
				}
				ShiroService shiroService = SpringTools.getBean(ShiroService.class);
				ShiroUser shiroUser = shiroService.getShiroUserById(id);
				if (!JwtUtils.verify(token, id, shiroUser.getPassword())) {
					throw new AuthenticationException("认证失败：Token失效，请重新登录");
				}
				AuditTools.setUserId(id);
			} catch (AuthenticationException e) {
				ServletTools.write(httpServletResponse, e.getMessage());
				httpServletResponse.setStatus(400);
				return;
			}
		}
		chain.doFilter(httpServletRequest, httpServletResponse);
	}

}