package com.cj.admin.service;

import java.util.List;

import com.cj.admin.domain.Organization;
import com.cj.admin.domain.Role;
import com.cj.admin.domain.User;

public interface IUserService {
	
	User selectByCodeOrName(String userName);
	
	List<Role> getRoles(int userID);
	
	List<Organization> getOrganizations(int userID);

}
