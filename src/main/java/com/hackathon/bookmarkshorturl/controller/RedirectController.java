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
	
	@GetMapping("/{groupname}/{pathname}")
	public RedirectView getUserOriginalUrl(@PathVariable String groupname, @PathVariable String pathname){
		return new RedirectView(this.urlService.getOriginalUrl(groupname,pathname));
	}
	
	@GetMapping("/{pathname}")
	public RedirectView getOriginalUrl(@PathVariable String pathname){
		return new RedirectView(this.urlService.getOriginalUrl(pathname));
	}

}
