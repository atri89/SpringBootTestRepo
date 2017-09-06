package org.springbootweb.testproject.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springbootweb.testproject.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(ModelAndView modelAndView, String error, String logout,
			@RequestParam Map<String, String> requestParams) {
		
		logger.info("login method start...");
		if (error != null) {
			modelAndView.addObject("error", "Invalid username and password");
			modelAndView.setViewName("login");
		} else if (logout != null) {
			modelAndView.addObject("logout_message", "You have been logged out");
			modelAndView.setViewName("login");
		}
		
		logger.debug("before checking username password...");
		
		if(securityService.login(requestParams.get("username"), requestParams.get("password"))) {
			
			logger.info("*********........login successful..........********");
			modelAndView.addObject("login_success_messege","Login Success");
			modelAndView.setViewName("home");
		}
		
		return modelAndView;
	}

	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView showLoginPage(ModelAndView modelAndView) {
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping("/home")
	public ModelAndView showHomePage(ModelAndView modelAndView) {
		logger.info("Redirecting to home page.....");
		modelAndView.setViewName("/home");
		return modelAndView;
	}
}
