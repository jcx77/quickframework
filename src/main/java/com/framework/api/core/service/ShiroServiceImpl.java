package com.framework.api.core.service;

import com.framework.api.core.mapper.ShiroMapper;
import com.framework.commons.shiro.service.ShiroService;
import com.framework.commons.shiro.subject.ShiroUser;
import org.apache.shiro.authc.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShiroServiceImpl implements ShiroService {
	@Autowired
	private ShiroMapper shiroMapper;

	@Cacheable(cacheNames = "shiro", key = "'shiro_user_'+#id")
	@Transactional(readOnly = true)
	@Override
	public ShiroUser getShiroUserById(String id) {
		ShiroUser shiroUser = shiroMapper.getShiroUserById(id);
		if (shiroUser == null) {
			throw new AuthenticationException("认证失败：帐号不存在");
		}
//		if (shiroUser.isLocked()) {
//			throw new AuthenticationException("认证失败：帐号已锁定");
//		}
//		if (shiroUser.getOrganCode() == null) {
//			throw new AuthenticationException("认证失败：机构已删除");
//		}
		if (shiroUser.isAdmin()) {
			shiroUser.setResources(shiroMapper.findResourceAll());
		} else {
			shiroUser.setResources(shiroMapper.findResourceById(id));
		}
		return shiroUser;
	}
}