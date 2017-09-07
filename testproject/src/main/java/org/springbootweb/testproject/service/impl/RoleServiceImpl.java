package org.springbootweb.testproject.service.impl;

import org.springbootweb.testproject.entity.Role;
import org.springbootweb.testproject.repository.RoleRepository;
import org.springbootweb.testproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	public void saveRole(Role role) {
		roleRepository.save(role);
	}

	@Override
	public Role findByName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}
	
}
