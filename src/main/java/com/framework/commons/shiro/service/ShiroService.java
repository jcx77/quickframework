package com.framework.commons.shiro.service;


import com.framework.commons.shiro.subject.ShiroUser;

public interface ShiroService {
	ShiroUser getShiroUserById(String id);
}