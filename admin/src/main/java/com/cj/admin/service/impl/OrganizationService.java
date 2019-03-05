package com.cj.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cj.admin.dao.OrganizationMapper;
import com.cj.admin.dao.OrganizationPermissionMapper;
import com.cj.admin.dao.PermissionMapper;
import com.cj.admin.domain.OrganizationPermission;
import com.cj.admin.domain.Permission;
import com.cj.admin.service.IOrganizationService;

@Service
public class OrganizationService implements IOrganizationService {
	
	@Autowired
	OrganizationMapper orgMapper;
	
	@Autowired
	OrganizationPermissionMapper orgPermissionMapper;
	
	@Autowired
	PermissionMapper permissionMapper;

	@Override
	public List<Permission> getPermissions(int orgID) {
		List<Permission> permissions = new ArrayList<Permission>();
		List<OrganizationPermission> orgPermissions = orgPermissionMapper.selectAllByOrgID(orgID);
		for(OrganizationPermission op : orgPermissions) {
			permissions.add(permissionMapper.selectByPrimaryKey(op.getPermissionid()));
		}
		return permissions;
	}

}
