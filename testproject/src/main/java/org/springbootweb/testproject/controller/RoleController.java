package org.springbootweb.testproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleController {

	@RequestMapping(value="/role", method=RequestMethod.GET)
	public String showRolePage() {
		return "role";
	}

	/*@RequestMapping(value="/role", method=RequestMethod.POST)
	public ModelAndView processRollData(ModelAndView modelAndView) {
		
		return modelAndView;
	}*/
	
}
