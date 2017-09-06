package org.springbootweb.testproject.service;


public interface SecurityService {

	String findLoggedInUserName();
	boolean login(String email, String password);
}
