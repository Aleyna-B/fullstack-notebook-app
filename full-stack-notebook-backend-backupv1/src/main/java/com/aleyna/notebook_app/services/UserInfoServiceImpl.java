package com.aleyna.notebook_app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aleyna.notebook_app.models.UserModel;
import com.aleyna.notebook_app.repositories.IUserInfoRepository;

@Service
public class UserInfoServiceImpl{

	@Autowired
	private IUserInfoRepository userInfoRepo;


	public UserModel addUser(UserModel usermodel) {
		usermodel.setCreationTime();
		usermodel.setUserPassword("{noop}"+usermodel.getUserPassword());
  		return userInfoRepo.save(usermodel);
	}
	
}
