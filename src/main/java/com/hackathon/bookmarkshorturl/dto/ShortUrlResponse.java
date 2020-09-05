package com.hackathon.bookmarkshorturl.dto;

import java.net.URL;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ShortUrlResponse {
	URL url;

	public ShortUrlResponse(URL url) {
		this.url = url;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
	
	
}
