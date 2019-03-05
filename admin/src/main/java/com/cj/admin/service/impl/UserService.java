package com.cj.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cj.admin.dao.OrganizationMapper;
import com.cj.admin.dao.RoleMapper;
import com.cj.admin.dao.UserMapper;
import com.cj.admin.dao.UserOrganizationMapper;
import com.cj.admin.dao.UserRoleMapper;
import com.cj.admin.domain.Organization;
import com.cj.admin.domain.Role;
import com.cj.admin.domain.User;
import com.cj.admin.domain.UserOrganization;
import com.cj.admin.domain.UserRole;
import com.cj.admin.service.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserRoleMapper userRoleMapper;
	
	@Autowired
	RoleMapper roleMapper;
	
	@Autowired
	UserOrganizationMapper userOrganizationMapper;
	
	@Autowired
	OrganizationMapper organizationMapper;

	@Override
	public User selectByCodeOrName(String userName) {
		return userMapper.selectByCodeOrName(userName);
	}

	@Override
	public List<Role> getRoles(int userID) {
		List<Role> roles = new ArrayList<Role>();
		List<UserRole> userRoles = userRoleMapper.selectAllByUserID(userID);
		for(UserRole ur : userRoles) {
			roles.add(roleMapper.selectByPrimaryKey(ur.getRoleid()));
		}
		return roles;
	}

	@Override
	public List<Organization> getOrganizations(int userID) {
		List<Organization> orgs = new ArrayList<Organization>();
		List<UserOrganization> userOrgs = userOrganizationMapper.selectAllByUserID(userID);
		for(UserOrganization uo : userOrgs) {
			orgs.add(organizationMapper.selectByPrimaryKey(uo.getOrganizationid()));
		}
		return orgs;
	}

}
