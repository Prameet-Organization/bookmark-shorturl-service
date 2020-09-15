package com.hackathon.bookmarkshorturl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.bookmarkshorturl.dto.BsGroupDto;
import com.hackathon.bookmarkshorturl.service.BSGroupService;

@RestController
@RequestMapping("/api/v1")
public class GroupController {
	
	@Autowired
	private BSGroupService bSGroupService;
	
	@PostMapping("groups")
	public ResponseEntity<Void> create(@RequestBody BsGroupDto bsGroup){
		this.bSGroupService.create(bsGroup);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
