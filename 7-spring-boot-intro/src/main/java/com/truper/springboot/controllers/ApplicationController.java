package com.truper.springboot.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

	@GetMapping("/welcome")
	public String welcome() {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "welcome " + user.getUsername() + " !";
	}
	
	@GetMapping("/user")
	public String user(@AuthenticationPrincipal User user) {
		return "hello user: " + user.getUsername();
	}
	
	@GetMapping("/admin")
	public String admin(@AuthenticationPrincipal User user) {
		return "hello admin: " + user.getUsername();
	}
	
	@GetMapping("/root")
	public String root(@AuthenticationPrincipal User user) {
		return "hello root: " + user.getUsername();
	}
	// login -> success login -> /
}
