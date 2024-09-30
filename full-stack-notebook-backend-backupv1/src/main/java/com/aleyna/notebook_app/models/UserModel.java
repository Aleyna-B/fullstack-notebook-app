package com.aleyna.notebook_app.models;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "user_information")
public class UserModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	@Column(name = "USER_NAME", nullable = false, length = 300,unique=true)
	private String userName;
	
	@Column(name = "PASSWORD", nullable = false, length = 30,unique=true)
	private String userPassword;
	
	@Column(name="CREATION_TIME")
	private LocalDate creationTime;

	public long getId() {
		return Id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public LocalDate getCreationTime() {
		return creationTime;
	}

	public void setCreationTime() {
		this.creationTime = LocalDate.now();
	}
	
	public UserModel() {}

	public UserModel(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.creationTime = LocalDate.now();
	}
	
//	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private NotesModel notes;
	
}
