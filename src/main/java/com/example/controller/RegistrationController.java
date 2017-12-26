package com.example.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Role;
import com.example.model.User;
import com.example.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByUsername(user.getUsername());
		User emailExists = userService.findUserByEmail(user.getEmail());
		if (emailExists != null) {
			bindingResult.rejectValue("email", "error.user",
					"There is already a user registered with the email provided");
		}

		if (userExists != null) {
			bindingResult.rejectValue("username", "error.user",
					"There is already a user registered with the username provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			 @SuppressWarnings({ "rawtypes", "unchecked" })
			Set<Role> roles = new HashSet();
			 Role userRole = new Role();
			 userRole.setRole("USER");
			roles.add(userRole);
			user.setRoles(roles);
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

}
