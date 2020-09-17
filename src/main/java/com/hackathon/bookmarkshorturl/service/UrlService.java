package com.hackathon.bookmarkshorturl.service;

import java.util.List;

import com.hackathon.bookmarkshorturl.dto.UrlDto;
import com.hackathon.bookmarkshorturl.entity.Url;

public interface UrlService {

	String convertToShortUrl(Url request);

	String getOriginalUrl(String pathName);
	
	String getOriginalUrl(String username, String pathName);
	
	List<UrlDto> getUrls();
	
	void update(Url urlObj, String pathName);
	
	void delete(String pathName);

	Url getUrlById(long id);

	void save(Url url);

}