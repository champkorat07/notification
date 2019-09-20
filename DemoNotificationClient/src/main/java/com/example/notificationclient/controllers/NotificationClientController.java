package com.example.notificationclient.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NotificationClientController {

	@RequestMapping(path = "/notification", method = RequestMethod.GET)
	public String gonotification(Authentication authentication, Model model) {
		String name = authentication.getName();
		model.addAttribute("name", name);
		return "notification";
	}

	@RequestMapping(path = "/addnotification", method = RequestMethod.GET)
	public String addnotification(Authentication authentication, Model model) {
		String name = authentication.getName();
		model.addAttribute("name", name);
		return "addnotification";
	}

}
