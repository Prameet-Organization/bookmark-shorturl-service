package com.hackathon.bookmarkshorturl.controller;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.bookmarkshorturl.dto.LongUrlRequest;
import com.hackathon.bookmarkshorturl.dto.ShortUrlResponse;

@RestController
@RequestMapping("/api/v1")
public class UrlController {
	
	@PostMapping("create-short-url")
    public ResponseEntity<ShortUrlResponse> createShortUrl(@RequestBody LongUrlRequest request) throws MalformedURLException {
		ShortUrlResponse entity = new ShortUrlResponse(new URL("http://test.com"));
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

}
