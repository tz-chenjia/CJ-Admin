package com.cj.admin.service;

import java.util.List;

import com.cj.admin.domain.Permission;

public interface IOrganizationService {
	public List<Permission> getPermissions(int orgID);
}
