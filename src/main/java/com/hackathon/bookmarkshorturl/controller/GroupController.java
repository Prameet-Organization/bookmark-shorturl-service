package com.hackathon.bookmarkshorturl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.bookmarkshorturl.dto.BsGroupDto;
import com.hackathon.bookmarkshorturl.entity.BsGroup;
import com.hackathon.bookmarkshorturl.service.BsGroupService;

@RestController
@RequestMapping("/api/v1")
public class GroupController {
	
	@Autowired
	private BsGroupService bsGroupService;
	
	@PostMapping("groups")
	public ResponseEntity<Void> create(@RequestBody BsGroupDto bsGroup){
		this.bsGroupService.create(bsGroup);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("groups")
	public ResponseEntity<List<BsGroup>> getGroups(@RequestParam(required = false) String type,
			@RequestParam(required = false)String name){
		if(type != null && name == null) {
			return new ResponseEntity<>(this.bsGroupService.getGroupsByType(type), HttpStatus.OK);
		}else if(type == null && name != null) {
			return new ResponseEntity<>(this.bsGroupService.getGroupsByName(name), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(this.bsGroupService.getGroups(), HttpStatus.OK);
		}
	}
	
	@PostMapping("groups/{groupname}")
	public ResponseEntity<Void> create(@PathVariable String groupname, @RequestBody BsGroupDto bsGroupDto){
		if(bsGroupDto.getUsername() != null) {
			this.bsGroupService.addUserToGroup(groupname, bsGroupDto.getUsername());
		}else if(bsGroupDto.getShortUrl() != null) {
			this.bsGroupService.addUrlToGroup(groupname, bsGroupDto.getShortUrl());
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
