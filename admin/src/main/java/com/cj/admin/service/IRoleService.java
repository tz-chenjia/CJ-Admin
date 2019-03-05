package com.cj.admin.service;

import java.util.List;

import com.cj.admin.domain.Permission;

public interface IRoleService {
	
	List<Permission> getPermissions(int roleID);
	
}
