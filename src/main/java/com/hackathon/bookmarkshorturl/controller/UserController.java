package com.hackathon.bookmarkshorturl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.bookmarkshorturl.entity.User;
import com.hackathon.bookmarkshorturl.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("users")
	public ResponseEntity<List<User>> getUsers(@RequestParam String name){
		return new ResponseEntity<>(this.userService.getUsers(name), HttpStatus.OK);
	}

}
