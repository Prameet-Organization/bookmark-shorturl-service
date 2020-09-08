package com.hackathon.bookmarkshorturl.dto;

import java.net.URL;

public class UrlResponse {
	private URL url;

	public UrlResponse() {
		super();
	}

	public UrlResponse(URL url) {
		this.url = url;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
	
	
}
