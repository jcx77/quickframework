package com.framework.commons.shiro.subject;



import com.framework.commons.constant.DataConstant;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShiroUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String account;
	private String password;
	private String name;
	private boolean locked;
	private String organId;
	private String organCode;
	private String organName;
	private List<Resource> resources;
	private Set<String> perms;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getOrganId() {
		return organId;
	}

	public void setOrganId(String organId) {
		this.organId = organId;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Set<String> getPerms() {
		if (perms == null) {
			perms = new HashSet<String>();
			for (Resource resource : getResources()) {
				perms.add(resource.getId());
			}
		}
		return perms;
	}

	public boolean isAdmin() {
		return DataConstant.DEFAULT_ADMIN.equals(this.id);
	}
}