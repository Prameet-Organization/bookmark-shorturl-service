package com.hackathon.bookmarkshorturl.service;

public interface UrlConversionService {

	String encode(long input);

	long decode(String input);

}