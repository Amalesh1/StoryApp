package com.storyapp.Controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.storyapp.Service.UsersService;
import com.storyapp.model.Role;
import com.storyapp.model.Users;
import com.storyapp.repository.RoleRepository;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/users/register")
	public String register(@ModelAttribute Users user) {

		Role defaultRole = roleRepository.findByName("ROLE_USER");
		if (defaultRole == null) {
			throw new RuntimeException("Default Role Not Found!");
		}
		user.setRoles(Set.of(defaultRole));
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		usersService.addUser(user);
		return "redirect:/login";
	}

	@GetMapping("/users/register")
	public String registrationForm(Model model) {
		model.addAttribute("user", new Users());
		return "register";
	}
}
