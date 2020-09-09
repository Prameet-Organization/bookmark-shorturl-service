package com.hackathon.bookmarkshorturl.dto;

import java.net.URL;
import java.time.LocalDateTime;

public class UrlRequest {
	private URL url;
	private LocalDateTime expirationDateTime;

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public LocalDateTime getExpirationDateTime() {
		return expirationDateTime;
	}

	public void setExpirationDateTime(LocalDateTime expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}

	@Override
	public String toString() {
		return "UrlRequest [url=" + url + ", expirationDateTime=" + expirationDateTime + "]";
	}
}
