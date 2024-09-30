package com.aleyna.notebook_app.models;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.aleyna.notebook_app.services.UserInfoServiceImpl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "user_notes")
public class NotesModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	@Column(name = "NOTE", length = 300)
	private String note;
	
	@Column(name="CREATION_TIME")
	private LocalDate creationTime;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="USER_ID")
	private long user_id;


	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public long getId() {
		return Id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDate getCreationTime() {
		return creationTime;
	}

	public void setCreationTime() {
		this.creationTime = LocalDate.now();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public NotesModel(String note,String title) {
		super();
		this.title = title;
		this.note = note;
		this.setCreationTime();
	}
	
	public NotesModel() {}
}
