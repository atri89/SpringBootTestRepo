package org.springbootweb.testproject.controller;

import javax.validation.Valid;

import org.springbootweb.testproject.entity.Role;
import org.springbootweb.testproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/role", method=RequestMethod.GET)
	public String showRolePage() {
		return "role";
	}

	@RequestMapping(value="/role", method=RequestMethod.POST)
	public ModelAndView processRollData(ModelAndView modelAndView, @Valid Role role) {
		roleService.saveRole(role);
		modelAndView.addObject("roleMessage","New Role has been updated");
		modelAndView.setViewName("role");
		return modelAndView;
	}
	
}
