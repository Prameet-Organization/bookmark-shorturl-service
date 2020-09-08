package com.hackathon.bookmarkshorturl.controller;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.hackathon.bookmarkshorturl.dto.UrlRequest;
import com.hackathon.bookmarkshorturl.dto.UrlResponse;
import com.hackathon.bookmarkshorturl.service.UrlService;

@RestController
@CrossOrigin("http://localhost:4200")
public class UrlController {
	
	@Autowired
	private UrlService urlService;
	
	@PostMapping("/api/v1/create-short-url")
    public ResponseEntity<UrlResponse> createShortUrl(@RequestBody UrlRequest request) throws MalformedURLException {

		if(request != null && request.getUrl() != null) {
			var shortUrl = this.urlService.convertToShortUrl(request);
			var entity = new UrlResponse(
					new URL(ServletUriComponentsBuilder.fromCurrentContextPath().toUriString()+"/"+shortUrl)
					);
	        return new ResponseEntity<>(entity, HttpStatus.OK);
		}
		return new ResponseEntity<>(new UrlResponse(), HttpStatus.BAD_REQUEST);
    }
	
	@GetMapping("/{pathName}")
	public RedirectView getOriginalUrl(@PathVariable String pathName){
		return new RedirectView(this.urlService.getOriginalUrl(pathName));
	}

}
