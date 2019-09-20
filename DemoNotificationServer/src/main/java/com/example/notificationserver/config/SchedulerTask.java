package com.example.notificationserver.config;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.notificationserver.servers.NotificationServer;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Component
public class SchedulerTask {

	@Autowired
	private NotificationServer app_notificationserver;

	private static final Integer EXPIRE_MINS = 12;
	private LoadingCache<String, Integer> timenotification;
	private SimpMessagingTemplate template;
	int count = 0;

	@Autowired
	public SchedulerTask(SimpMessagingTemplate template) {
		this.template = template;
		timenotification = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MINS, TimeUnit.HOURS)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public void sendMessageToClient(String name) {
		int nowcountnotification = app_notificationserver.selectstatusmsg(name);
		try {
			count = timenotification.get(name);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		String msg = Integer.toString(nowcountnotification);
		if (count < nowcountnotification) {
			timenotification.put(name, nowcountnotification);
			this.template.convertAndSend("/topic/greetings/" + name, new Payload(msg));
			this.template.convertAndSend("/topic/notiings/" + name, new Payload(msg));
		} else if (count > nowcountnotification) {
			this.template.convertAndSend("/topic/greetings/" + name, new Payload(msg));
			timenotification.put(name, nowcountnotification);
		}
	}
}
