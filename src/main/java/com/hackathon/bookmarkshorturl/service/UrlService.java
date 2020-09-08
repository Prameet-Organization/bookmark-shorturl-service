package com.hackathon.bookmarkshorturl.service;

import com.hackathon.bookmarkshorturl.dto.UrlRequest;

public interface UrlService {

	String convertToShortUrl(UrlRequest request);

	String getOriginalUrl(String pathName);

}