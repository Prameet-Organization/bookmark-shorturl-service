package com.hackathon.bookmarkshorturl.service;

import java.util.List;
import java.util.Set;

import com.hackathon.bookmarkshorturl.dto.BsGroupDto;
import com.hackathon.bookmarkshorturl.entity.BsGroup;
import com.hackathon.bookmarkshorturl.entity.User;

public interface BsGroupService {

	void create(BsGroupDto group);

	List<BsGroup> getGroupsByType(String type);
	
	List<BsGroup> getGroupsByName(String name);
	
	List<BsGroup> getGroups();
	
	void addUserToGroup(String groupName, String userName);

	void addUrlToGroup(String groupname, String shortUrl);

	Set<BsGroup> getGroupsByUser(User user);
}