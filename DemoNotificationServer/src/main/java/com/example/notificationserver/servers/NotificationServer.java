package com.example.notificationserver.servers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notificationserver.entities.apps_notification;
import com.example.notificationserver.repositorys.NotificationRepository;

@Service
public class NotificationServer {

	@Autowired
	private NotificationRepository app_notirespository;

	public List<apps_notification> getAllCosmetics() {
		return (List<apps_notification>) app_notirespository.findAll();
	}

	public void addmuilnotirespository(Iterable<apps_notification> noti) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		for (apps_notification app : noti) {
			app.setTimestamp(timestamp);
			app.setMsg_status(1);
			app_notirespository.save(app);
		}
	}

	public void deletenotirespository(String id) {
		app_notirespository.deleteById(id);
	}

	public Optional<apps_notification> getnotirespository(String id) {
		return app_notirespository.findById(id);
	}

	public int selectstatusmsg(String name) {
		return app_notirespository.appcount(name);
	}

	public List<apps_notification> selectsomestatusmsg(String name) {
		return app_notirespository.findAllActiveUsers(name);
	}

	public void checknotification(String name) {
		app_notirespository.updateNotification(name);
	}
}

/*
 * public void addnotirespository(apps_notification noti) { Timestamp timestamp
 * = new Timestamp(System.currentTimeMillis()); noti.setMsg_status(1);
 * noti.setTimestamp(timestamp); app_notirespository.save(noti); }
 * 
 */
