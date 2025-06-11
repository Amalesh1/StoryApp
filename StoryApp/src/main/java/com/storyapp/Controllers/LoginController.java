package com.storyapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.storyapp.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/login")
	public String index() {
		return "login";
	}

	@GetMapping("/loading")
	public String loading() {
		return "loading";
	}

	@GetMapping("/home")
	public String home(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = "";
		try {
			username = userRepository.findByEmail(authentication.getName()).getName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("username", username);
		return "home"; // maps to home.html
	}

}
