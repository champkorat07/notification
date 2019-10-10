package com.example.notificationserver.entities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NotificationEntities {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int msg_id;
	
	@Column
	private String member_token;
	
	@Column
	private String member_text;
	
	@Column
	private int msg_status;
	
	@Column
	private Timestamp timestamp;

	public String getMember_token() {
		return member_token;
	}

	public void setMember_token(String member_token) {
		this.member_token = member_token;
	}

	public int getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(int msg_id) {
		this.msg_id = msg_id;
	}

	public String getMember_text() {
		return member_text;
	}

	public void setMember_text(String member_text) {
		this.member_text = member_text;
	}

	public int getMsg_status() {
		return msg_status;
	}

	public void setMsg_status(int msg_status) {
		this.msg_status = msg_status;
	}

	public String getTimestamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String string = dateFormat.format(new Date(timestamp.getTime()));
		return string;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
