package com.hackathon.bookmarkshorturl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.hackathon.bookmarkshorturl.config.AppProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableSwagger2
public class BookmarkShorturlApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmarkShorturlApplication.class, args);
	}

}
