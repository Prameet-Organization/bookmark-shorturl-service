package com.hackathon.bookmarkshorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.hackathon.bookmarkshorturl.config.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class BookmarkShorturlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmarkShorturlApplication.class, args);
	}

}
