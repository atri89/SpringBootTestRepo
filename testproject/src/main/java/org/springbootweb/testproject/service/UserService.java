package org.springbootweb.testproject.service;

import org.springbootweb.testproject.entity.User;

public interface UserService {
	
	void saveUser(User user);
	User findByUserName(String userName);
	User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);
	
}
