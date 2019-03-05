package com.cj.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cj.admin.dao.PermissionMapper;
import com.cj.admin.dao.RoleMapper;
import com.cj.admin.dao.RolePermissionMapper;
import com.cj.admin.domain.Permission;
import com.cj.admin.domain.RolePermission;
import com.cj.admin.service.IRoleService;

@Service
public class RoleService implements IRoleService {
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	RolePermissionMapper rolePermissionMapper;
	
	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public List<Permission> getPermissions(int roleID) {
		List<Permission> permissions = new ArrayList<Permission>();
		List<RolePermission> rolePermissions = rolePermissionMapper.selectAllByRoleID(roleID);
		for(RolePermission rp : rolePermissions) {
			permissions.add(permissionMapper.selectByPrimaryKey(rp.getPermissionid()));
		}
		return permissions;
	}

}
