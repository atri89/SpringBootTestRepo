package org.springbootweb.testproject.service;

import org.springbootweb.testproject.entity.Role;

public interface RoleService {
	void saveRole(Role role);
	Role findByName(String roleName);
}
