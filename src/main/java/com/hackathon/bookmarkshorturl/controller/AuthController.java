package com.hackathon.bookmarkshorturl.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.bookmarkshorturl.entity.User;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @GetMapping("/user")
	public ResponseEntity<User> getUser(Principal principal) {
    	if(principal != null) {
    		User user= new User();
    		user.setName(principal.getName());
    		return new ResponseEntity<>(user, HttpStatus.OK);
    	}
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
