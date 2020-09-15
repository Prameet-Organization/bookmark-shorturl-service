package com.hackathon.bookmarkshorturl.service.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.bookmarkshorturl.dto.BsGroupDto;
import com.hackathon.bookmarkshorturl.entity.BsGroup;
import com.hackathon.bookmarkshorturl.repository.BSGroupRepository;
import com.hackathon.bookmarkshorturl.service.BSGroupService;
import com.hackathon.bookmarkshorturl.service.UserService;

@Service
public class BSGroupServiceImpl implements BSGroupService {
	
	@Autowired
	private BSGroupRepository bsGroupRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void create(BsGroupDto groupDto) {
		BsGroup group = this.getEntity(groupDto);
		group.setAdmin(this.userService.getUser());
		this.bsGroupRepository.save(group);
	}

	private BsGroup getEntity(BsGroupDto groupDto) {
		BsGroup group = new BsGroup();
		group.setGroupName(groupDto.getGroupName());
		group.setGroupType(groupDto.getGroupType());
		return group;
	}
}
