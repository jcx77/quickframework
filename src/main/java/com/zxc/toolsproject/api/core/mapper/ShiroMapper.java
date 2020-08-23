package com.zxc.toolsproject.api.core.mapper;



import com.zxc.toolsproject.commons.shiro.subject.Resource;
import com.zxc.toolsproject.commons.shiro.subject.ShiroUser;

import java.util.List;

public interface ShiroMapper {
	ShiroUser getShiroUserById(String id);

	List<Resource> findResourceAll();

	List<Resource> findResourceById(String id);
}