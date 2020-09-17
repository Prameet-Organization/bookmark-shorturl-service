package com.hackathon.bookmarkshorturl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.hackathon.bookmarkshorturl.service.UrlService;

@RestController
@RequestMapping("r")
public class RedirectController {
	
	@Autowired
	private UrlService urlService;
	
	@GetMapping("/{username}/{pathName}")
	public RedirectView getUserOriginalUrl(@PathVariable String username, @PathVariable String pathName){
		return new RedirectView(this.urlService.getOriginalUrl(username,pathName));
	}
	
	@GetMapping("/{pathName}")
	public RedirectView getOriginalUrl(@PathVariable String pathName){
		return new RedirectView(this.urlService.getOriginalUrl(pathName));
	}
}
