package com.hackathon.bookmarkshorturl.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.bookmarkshorturl.dto.UrlRequest;
import com.hackathon.bookmarkshorturl.service.UrlService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value=UrlController.class)
class UrlControllerTest {

	@MockBean
	private UrlService urlService;
		
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
    protected ObjectMapper objectMapper;

	@Test
	void whenValidInputThenReturns200() throws Exception {
		UrlRequest request = new UrlRequest(new URL("http://test.com"));
		when(this.urlService.convertToShortUrl(any(UrlRequest.class))).thenReturn("abc");
		this.mockMvc.perform(post("/api/v1/create-short-url")
				.contentType(MediaType.APPLICATION_JSON)
				.content(this.objectMapper.writeValueAsString(request)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.url").value("http://localhost/abc"));
	}
}
