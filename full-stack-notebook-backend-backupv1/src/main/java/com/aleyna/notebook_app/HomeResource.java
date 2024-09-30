package com.aleyna.notebook_app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
	final String HOME_DIRECTORY = "/";
	@GetMapping(HOME_DIRECTORY)
	public String home()
	{
		return ("<h1> Home Page <h1>");
	}
	
	@GetMapping("/signUp")
	public String signUp()
	{
		return ("<h1> sign up screen here <h1>");
	}
	
}
