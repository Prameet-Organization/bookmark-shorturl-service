package com.hackathon.bookmarkshorturl.dto;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import com.hackathon.bookmarkshorturl.entity.Url;

public class UrlDto {

	private URL shortUrl;
	private URL longUrl;
	private LocalDateTime expirationDateTime;
	private String shortTitle;
	private String description;
	private String fileName;
	private String fileType;
	private byte[] faviconData;
	public URL getShortUrl() {
		return shortUrl;
	}
	public UrlDto setShortUrl(URL shortUrl) {
		this.shortUrl = shortUrl;
		return this;
	}
	public URL getLongUrl() {
		return longUrl;
	}
	public LocalDateTime getExpirationDateTime() {
		return expirationDateTime;
	}
	
	public String getShortTitle() {
		return shortTitle;
	}
	public String getDescription() {
		return description;
	}
	
	public String getFileName() {
		return fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public byte[] getFaviconData() {
		return faviconData;
	}
	public static UrlDto build(Url url) throws MalformedURLException {
		UrlDto urlDto = new UrlDto();
		urlDto.longUrl = new URL(url.getLongUrl());
		urlDto.expirationDateTime = url.getExpirationDateTime();
		urlDto.shortTitle = url.getShortTitle();
		urlDto.description = url.getDescription();
		urlDto.fileName = url.getFileName();
		urlDto.fileType = url.getFileType();
		urlDto.faviconData = url.getFaviconData();
		return urlDto;		
	}
	
}
