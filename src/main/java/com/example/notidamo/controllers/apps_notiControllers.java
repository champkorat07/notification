package com.example.notidamo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.notidamo.config.SchedulerTask;
import com.example.notidamo.entites.apps_notification;
import com.example.notidamo.servers.app_notificationServer;

@RestController
public class apps_notiControllers {

	@Autowired
	private app_notificationServer app_notificationserver;

	@Autowired
	private SchedulerTask schedulertask;

	// add notification
	@RequestMapping(value = "/app_noti", method = RequestMethod.POST)
	public void addCosmetic(@RequestBody apps_notification notiadd) {
		app_notificationserver.addnotirespository(notiadd);
	}

	// update notification
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void getupdate(@RequestParam(value = "text") String ids) {
		int id = Integer.parseInt(ids);
		app_notificationserver.checknotification(id);
	}

	// select notification
	@RequestMapping(path = "/notifications", method = RequestMethod.GET)
	public List<apps_notification> notification(Authentication authentication, Model model) {
		String name = authentication.getName();
		return app_notificationserver.selectsomestatusmsg(name);
	}

	@RequestMapping(path = "/timecheck", method = RequestMethod.POST)
	public void notification(Authentication authentication) {
		String name = authentication.getName();
		schedulertask.sendMessageToClient(name);
	}

}
