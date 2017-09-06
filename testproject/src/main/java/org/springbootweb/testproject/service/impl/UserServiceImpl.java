package org.springbootweb.testproject.service.impl;

import java.util.HashSet;

import org.springbootweb.testproject.entity.User;
import org.springbootweb.testproject.repository.RoleRepository;
import org.springbootweb.testproject.repository.UserRepository;
import org.springbootweb.testproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void saveUser(User user) {
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
	}
	
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}

}
