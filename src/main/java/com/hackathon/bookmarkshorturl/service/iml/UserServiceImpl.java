package com.hackathon.bookmarkshorturl.service.iml;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hackathon.bookmarkshorturl.entity.User;
import com.hackathon.bookmarkshorturl.repository.UserRepository;
import com.hackathon.bookmarkshorturl.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public String getUsername() {
		String username;
		Optional<User> user = Optional.empty();
    	if(SecurityContextHolder.getContext().getAuthentication() != null) {
    		 username = SecurityContextHolder.getContext().getAuthentication().getName();
    		 user = this.userRepository.findByEmail(username);
    	}
    	if(user.isPresent()) {
    		return user.get().getName();
    	}
    	return null;
	}
	
	@Autowired
	public User getUser() {
		String username;
		Optional<User> user = Optional.empty();
    	if(SecurityContextHolder.getContext().getAuthentication() != null) {
    		 username = SecurityContextHolder.getContext().getAuthentication().getName();
    		 user = this.userRepository.findByEmail(username);
    	}
    	if(user.isPresent()) {
    		return user.get();
    	}
    	return null;
	}
	
	@Override
	public List<User> getUsers(String name){
		return this.userRepository.findByNameLikeIgnoreCase(name+"%");
	}

}
