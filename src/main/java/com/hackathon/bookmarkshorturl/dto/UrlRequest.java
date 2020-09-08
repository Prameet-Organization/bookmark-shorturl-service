package com.hackathon.bookmarkshorturl.dto;

import java.net.URL;

public class UrlRequest {
	private URL url;
	
	public UrlRequest() {
	}

	public UrlRequest(URL url) {
		this.url = url;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
}
