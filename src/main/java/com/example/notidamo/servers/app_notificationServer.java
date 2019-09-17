package com.example.notidamo.servers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notidamo.entites.apps_notification;
import com.example.notidamo.repositorys.app_notiRepository;

@Service
public class app_notificationServer {

	@Autowired
	private app_notiRepository app_notirespository;

	public List<apps_notification> getAllCosmetics() {
		return (List<apps_notification>) app_notirespository.findAll();
	}

	public void addnotirespository(apps_notification noti) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		noti.setMsg_status(1);
		noti.setTimestamp(timestamp);
		app_notirespository.save(noti);
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

	public void checknotification(int id) {
		app_notirespository.updateNotification(id);
	}
}
