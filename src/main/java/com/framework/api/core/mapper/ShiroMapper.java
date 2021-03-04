package com.framework.api.core.mapper;



import com.framework.commons.shiro.subject.Resource;
import com.framework.commons.shiro.subject.ShiroUser;

import java.util.List;

public interface ShiroMapper {
	ShiroUser getShiroUserById(String id);

	List<Resource> findResourceAll();

	List<Resource> findResourceById(String id);
}