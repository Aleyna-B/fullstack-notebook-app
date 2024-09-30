package com.aleyna.notebook_app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aleyna.notebook_app.models.UserModel;

public interface IUserInfoRepository extends JpaRepository<UserModel,Long>{
	public Optional<UserModel> findById(Long Id);
	
	//@Query("SELECT u FROM UserModel u WHERE u.userName = ?1")
	public UserModel findByuserName(String userName);

}
