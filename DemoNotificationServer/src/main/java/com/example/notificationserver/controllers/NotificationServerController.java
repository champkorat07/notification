package com.example.notificationserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.notificationserver.config.SchedulerTask;
import com.example.notificationserver.entities.apps_notification;
import com.example.notificationserver.servers.NotificationServer;

@RestController
public class NotificationServerController {

	@Autowired
	private NotificationServer app_notificationserver;

	@Autowired
	private SchedulerTask schedulertask;

	// add notification
	@RequestMapping(value = "/app_muilnotification", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:8080")
	public void addmuilnotification(@RequestBody Iterable<apps_notification> notiadd) {
		app_notificationserver.addmuilnotirespository(notiadd);
	}

	// update notification
	@ResponseBody
	@RequestMapping(value = "/updatenotification", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:8080")
	public void updatestaus(@RequestParam(value = "username") String name, Model model) {
		app_notificationserver.checknotification(name);
	}

	// select notification
	@RequestMapping(path = "/notifications", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:8080")
	public List<apps_notification> countnotification(@RequestParam(value = "username") String name, Model model) {
		return app_notificationserver.selectsomestatusmsg(name);
	}

	@RequestMapping(value = "/timecheck", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:8080")
	public void getnotification(@RequestParam(value = "username") String name) {
		schedulertask.sendMessageToClient(name);
	}

}

/*
 * @RequestMapping(value = "/app_notification", method = RequestMethod.POST)
 * 
 * @CrossOrigin(origins = "http://localhost:8080") public void
 * addnotification(@RequestBody apps_notification notiadd) {
 * app_notificationserver.addnotirespository(notiadd); }
 */
