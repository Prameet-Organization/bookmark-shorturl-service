package com.hackathon.bookmarkshorturl.service;

import java.util.List;

import com.hackathon.bookmarkshorturl.entity.User;

public interface UserService {

	String getUsername();
	
	User getUser();
	
	List<User> getUsers(String name);

}