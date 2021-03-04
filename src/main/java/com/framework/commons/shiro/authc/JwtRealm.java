package com.framework.commons.shiro.authc;

import com.framework.commons.shiro.tools.ShiroTools;
import com.framework.commons.shiro.service.ShiroService;
import com.framework.commons.shiro.subject.ShiroUser;
import com.framework.commons.spring.tools.SpringTools;
import com.framework.commons.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


public class JwtRealm extends AuthorizingRealm {
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = ShiroTools.getShiroUser();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setStringPermissions(shiroUser.getPerms());
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String token = (String) authenticationToken.getCredentials();
		if (token == null) {
			throw new AuthenticationException("认证失败：Token不允许为空");
		}
		String id = (String) authenticationToken.getPrincipal();
		if (id == null) {
			throw new AuthenticationException("认证失败：Token非法无效");
		}
		ShiroService shiroService = SpringTools.getBean(ShiroService.class);
		ShiroUser shiroUser = shiroService.getShiroUserById(id);
		if (!JwtUtils.verify(token, id, shiroUser.getPassword())) {
			throw new AuthenticationException("认证失败：Token失效，请重新登录");
		}
		return new SimpleAuthenticationInfo(shiroUser, token, getName());
	}
}