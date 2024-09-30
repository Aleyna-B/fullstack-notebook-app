package com.aleyna.notebook_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aleyna.notebook_app.models.UserModel;
import com.aleyna.notebook_app.security.CustomUserDetails;
import com.aleyna.notebook_app.services.UserInfoServiceImpl;

@RestController
public class UserInfoController {
	
	@Autowired
	private UserInfoServiceImpl userInfoService;
	
	@PostMapping("/signUp")
	public boolean userSignIn(@RequestBody UserModel usermodel)
	{
		try {
			userInfoService.addUser(usermodel);
			return true; //"redirect:/login/"
		}catch(Exception e)
			{
				System.out.println(e.getMessage());
				return false;
			}
	}
		
	public static long userDetailId;
    @GetMapping("/user")
    public long getUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
    	if(userDetails != null)
    	{
    		userDetailId = userDetails.getId();
    		return userDetails.getId();
    	}
    	else return 0;
    }
    
    
}
