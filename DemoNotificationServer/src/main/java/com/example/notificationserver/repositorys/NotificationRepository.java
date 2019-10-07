package com.example.notificationserver.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.notificationserver.entities.apps_notification;

@Repository
public interface NotificationRepository extends CrudRepository<apps_notification, String> {

	@Query("select count(i) from apps_notification i where i.msg_status = 1 and i.member_token= :name")
	public int appcount(@Param("name") String name);

	@Query("select i from apps_notification i where i.member_token=:name")
	List<apps_notification> findAllActiveUsers(@Param("name") String name);

	@Modifying
	@Transactional
	@Query("Update apps_notification t SET t.msg_status=0 WHERE t.member_token=:name")
	public void updateNotification(@Param("name") String name);

	@Modifying
	@Query("delete from apps_notification t WHERE t.msg_id = :id")
	public void deleteNotification(@Param("id") int id);

}
