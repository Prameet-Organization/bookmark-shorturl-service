package com.hackathon.bookmarkshorturl.service.iml;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.bookmarkshorturl.dto.BsGroupDto;
import com.hackathon.bookmarkshorturl.entity.BsGroup;
import com.hackathon.bookmarkshorturl.entity.Url;
import com.hackathon.bookmarkshorturl.entity.User;
import com.hackathon.bookmarkshorturl.repository.BsGroupRepository;
import com.hackathon.bookmarkshorturl.service.BsGroupService;
import com.hackathon.bookmarkshorturl.service.UrlConversionService;
import com.hackathon.bookmarkshorturl.service.UrlService;
import com.hackathon.bookmarkshorturl.service.UserService;

@Service
public class BsGroupServiceImpl implements BsGroupService {
	
	@Autowired
	private BsGroupRepository bsGroupRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UrlConversionService urlConversionService;
	
	@Autowired
	private UrlService urlService;
	
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
			return this.bsGroupRepository.findByGroupType(type);	
	}

	private BsGroup getEntity(BsGroupDto groupDto) {
		BsGroup group = new BsGroup();
		group.setId(groupDto.getId());
		group.setGroupName(groupDto.getGroupName());
		group.setGroupType(groupDto.getGroupType());
		return group;
	}
	
	@Override
	public List<BsGroup> getGroupsByName(String name){
		return this.bsGroupRepository.findByGroupNameLikeIgnoreCase(name+"%");
	}
	
	@Override
	public List<BsGroup> getGroups(){
		return this.bsGroupRepository.findAll();
	}
	
	@Override
	public void addUserToGroup(String groupname, String username){
		BsGroup group = this.bsGroupRepository.findByGroupName(groupname);
		User user = this.userService.getUsers(username).get(0);
		group.getUser().add(user);
		this.bsGroupRepository.save(group);
	}
	
	@Override
	public void addUrlToGroup(String groupname, String shortUrl) {
		BsGroup group = this.bsGroupRepository.findByGroupName(groupname);
		Url url = this.urlService.getUrlById(this.urlConversionService.decode(shortUrl.substring(shortUrl.lastIndexOf('/')+1)));
		url.setExpirationDateTime(null);
		this.urlService.save(url);
		group.getUrls().add(url);
		this.bsGroupRepository.save(group);
	}
	
	@Override
	public Set<BsGroup> getGroupsByUser(User user){
		return this.bsGroupRepository.findByUsersIn(Arrays.asList(user));
	}
}
