package com.hackathon.bookmarkshorturl.service.iml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.bookmarkshorturl.dto.BsGroupDto;
import com.hackathon.bookmarkshorturl.entity.BsGroup;
import com.hackathon.bookmarkshorturl.repository.BsGroupRepository;
import com.hackathon.bookmarkshorturl.service.BsGroupService;
import com.hackathon.bookmarkshorturl.service.UserService;

@Service
public class BsGroupServiceImpl implements BsGroupService {
	
	@Autowired
	private BsGroupRepository bsGroupRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void create(BsGroupDto groupDto) {
		BsGroup group = this.getEntity(groupDto);
		if (group.getGroupType().equals("feature")){
				group.setTribe(this.getEntity(groupDto.getTribe()));
		}
		group.setAdmin(this.userService.getUser());
		this.bsGroupRepository.save(group);
	}
	
	@Override
	public List<BsGroup> getGroupsByType(String type) {
		if(type != null) {
			return this.bsGroupRepository.findByGroupType(type);	
		}
		return this.bsGroupRepository.findAll();
	}

	private BsGroup getEntity(BsGroupDto groupDto) {
		BsGroup group = new BsGroup();
		group.setId(groupDto.getId());
		group.setGroupName(groupDto.getGroupName());
		group.setGroupType(groupDto.getGroupType());
		return group;
	}
}
