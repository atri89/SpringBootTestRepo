package org.springbootweb.testproject.controller;

import org.springbootweb.testproject.entity.User;
import org.springbootweb.testproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/userregistrationpage")
	public String registerUser() {
		return "UserRegistration";
	}
	
	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute User user) {
		userService.saveUser(user);
		//return "UserRegistration";
		return "redirect:/Welcome";
	}

}
