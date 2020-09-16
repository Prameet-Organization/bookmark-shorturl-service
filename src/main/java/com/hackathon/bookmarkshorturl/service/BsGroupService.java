package com.hackathon.bookmarkshorturl.service;

import java.util.List;

import com.hackathon.bookmarkshorturl.dto.BsGroupDto;
import com.hackathon.bookmarkshorturl.entity.BsGroup;

public interface BsGroupService {

	void create(BsGroupDto group);

	List<BsGroup> getGroupsByType(String type);
}