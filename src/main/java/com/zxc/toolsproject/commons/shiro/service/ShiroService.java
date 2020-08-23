package com.zxc.toolsproject.commons.shiro.service;


import com.zxc.toolsproject.commons.shiro.subject.ShiroUser;

public interface ShiroService {
	ShiroUser getShiroUserById(String id);
}