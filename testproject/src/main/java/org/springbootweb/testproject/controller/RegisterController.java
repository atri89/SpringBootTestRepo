package org.springbootweb.testproject.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springbootweb.testproject.entity.User;
import org.springbootweb.testproject.service.EmailService;
import org.springbootweb.testproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.nulabinc.zxcvbn.Strength;
//import com.nulabinc.zxcvbn.Zxcvbn;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// RETURN REGISTRATION FORM TEMPLATE //
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user) {

		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	// PROCESS FORM INPUT DATA //
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user,
			BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes,
			@RequestParam Map<String, String> requestParams) {

		// CHECKING USER EXISTENCE IN DATABASE BY E-MAIL //
		// User userExistence = userService.findByEmail(user.getEmail());
		User userExistence = userService.findByEmail(requestParams.get("email"));
		
		if (userExistence != null) {
			modelAndView.addObject("alreadyRegisteredMessege", "Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} else {
			// NEW USER...
			user.setUserName(requestParams.get("email"));
			user.setEnabled(false);
			user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
			
			// Generate random 36-character string token for confirmation link
			user.setConfirmationToken(UUID.randomUUID().toString());

			//Zxcvbn passwordCheckingLib = new Zxcvbn();
			// Strength passwordStrength =	passwordCheckingLib.measure(requestParams.get("password"));
			/*if (passwordStrength.getScore() < 3) {
				bindingResult.reject("password");
				redirectAttributes.addFlashAttribute("passwordErrorMessege",
						"Your password is too weak.  Choose a stronger one.");
				//modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
				return modelAndView;
			}*/

			userService.saveUser(user);

			String appURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Test Email For Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below: \n\n" + appURL + "/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("atri.bhattacharyya@highpeaksw.com");
			emailService.sendEmail(registrationEmail);

			modelAndView.addObject("confirmationEmailMessege", "A confirmation e-mail has been sent to " + user.getEmail());
			modelAndView.setViewName("register");
		}
		
		return modelAndView;
	}

	// SHOW CONFIRMATION FORM TEMPLATE //
	@RequestMapping(value = "/confirm", method = RequestMethod.GET)
	public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {
		
		User user = userService.findByConfirmationToken(token);
		
		if (user == null) {
			modelAndView.addObject("invalidTokenMessege", "Oops!  This is an invalid confirmation link.");
		} else {
			//modelAndView.addObject("confirmationToken", user.getConfirmationToken());
			user.setEnabled(true);
			userService.saveUser(user);
			modelAndView.addObject("registrationSuccessMessege", "Your Account has been Confirmed");
		}
		
		modelAndView.setViewName("confirm");
		return modelAndView;
	}

	// PROCESS CONFIRMATION LINK //
	/*@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ModelAndView processConfirmationPage(ModelAndView modelAndView, BindingResult bindingResult,
			@RequestParam Map<String, String> requestParams, RedirectAttributes redirectAttributes) {

		modelAndView.setViewName("confirm");

		Zxcvbn passwordCheckingLib = new Zxcvbn();
		Strength passwordStrength = passwordCheckingLib.measure(requestParams.get("password"));

		if (passwordStrength.getScore() < 3) {
			bindingResult.reject("password");
			redirectAttributes.addFlashAttribute("passwordErrorMessege", "Your password is too weak.  Choose a stronger one.");
			modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
			return modelAndView;
		}

		User user = userService.findByConfirmationToken(requestParams.get("token"));

		user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
		user.setEnabled(true);
		userService.saveUser(user);

		modelAndView.addObject("registrationSuccessMessege", "Your password has been set!");
		return modelAndView;
	}*/

}