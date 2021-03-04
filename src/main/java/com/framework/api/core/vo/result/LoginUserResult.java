package com.framework.api.core.vo.result;



import com.framework.commons.shiro.subject.Resource;
import com.framework.commons.shiro.subject.ShiroUser;
import com.framework.commons.vo.ui.MenuNode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LoginUserResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String account;
	private String name;
	private String organId;
	private String organCode;
	private String organName;
	private Set<String> perms;
	private List<MenuNode> menu;

	public LoginUserResult(ShiroUser shiroUser) {
		this.id = shiroUser.getId();
		this.account = shiroUser.getAccount();
		this.name = shiroUser.getName();
		this.organId = shiroUser.getOrganId();
		this.organCode = shiroUser.getOrganCode();
		this.organName = shiroUser.getOrganName();
		this.perms = shiroUser.getPerms();
		if (shiroUser.getResources() != null) {
			this.menu = new ArrayList<MenuNode>();
			for (Resource resource : shiroUser.getResources()) {
				if (resource.isMenu()) {
					MenuNode m = new MenuNode();
					m.setId(resource.getId());
					m.setText(resource.getName());
					m.setIconCls(resource.getIcon());
					m.setUrl(resource.getUrl());
					m.setPid(resource.getPid());
					menu.add(m);
				}
			}
		}
	}

	public LoginUserResult() {
	}

	public String getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public String getName() {
		return name;
	}

	public String getOrganId() {
		return organId;
	}

	public String getOrganCode() {
		return organCode;
	}

	public String getOrganName() {
		return organName;
	}

	public Set<String> getPerms() {
		return perms;
	}

	//public List<MenuNode> getMenu() {
	//	return menu;
	//}
}