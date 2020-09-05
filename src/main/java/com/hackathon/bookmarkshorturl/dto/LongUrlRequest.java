package com.hackathon.bookmarkshorturl.dto;

import java.net.URL;

public class LongUrlRequest {
	private URL url;
	
	public LongUrlRequest() {
	}

	public LongUrlRequest(URL url) {
		this.url = url;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
}
