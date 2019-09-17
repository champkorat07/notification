package com.example.notidamo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.notidamo.servers.UserDetailsServiceImpl;

@Controller
public class AppController {

	@Autowired
	private UserDetailsServiceImpl userdetailsserviceImpl;


	@GetMapping({ "/login" })
	public String index() {
		return "index";
	}

	@GetMapping({ "/", "/menu" })
	public String menu(Authentication authentication,Model model) {
		String name = authentication.getName();
		model.addAttribute("name", name);
		return "menu";
	}

	@GetMapping("/user")
	public String user(Authentication authentication, Model model) {
		String username = authentication.getName();
		String email = userdetailsserviceImpl.findEmailByUsername(username);
		model.addAttribute("email", email);
		return "user";
	}

	@GetMapping("/admin")
	public String admin(Authentication authentication, Model model) {
		String username = authentication.getName();
		String email = userdetailsserviceImpl.findEmailByUsername(username);
		model.addAttribute("email", email);
		return "admin";
	}
	
	
}
