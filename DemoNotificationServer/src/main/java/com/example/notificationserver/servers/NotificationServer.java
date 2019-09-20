package com.example.notificationserver.servers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notificationserver.entities.NotificationEntities;
import com.example.notificationserver.repositorys.NotificationRepository;

@Service
public class NotificationServer {

	@Autowired
	private NotificationRepository app_notirespository;

	public List<NotificationEntities> getAllCosmetics() {
		return (List<NotificationEntities>) app_notirespository.findAll();
	}

	public void addnotirespository(NotificationEntities noti) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		noti.setMsg_status(1);
		noti.setTimestamp(timestamp);
		app_notirespository.save(noti);
	}

	public void addmuilnotirespository(Iterable<NotificationEntities> noti) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		for (NotificationEntities app : noti) {
			app.setTimestamp(timestamp);
			app.setMsg_status(1);
			app_notirespository.save(app);
		}

	}

	public void deletenotirespository(String id) {
		app_notirespository.deleteById(id);
	}

	public Optional<NotificationEntities> getnotirespository(String id) {
		return app_notirespository.findById(id);
	}

	public int selectstatusmsg(String name) {
		return app_notirespository.appcount(name);
	}

	public List<NotificationEntities> selectsomestatusmsg(String name) {
		return app_notirespository.findAllActiveUsers(name);
	}

	public void checknotification(String name) {
		app_notirespository.updateNotification(name);
	}
}
